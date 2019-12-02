package ort.nt2.tpfinal.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ort.nt2.tpfinal.ContextApp;
import ort.nt2.tpfinal.NewOrderActivity;
import ort.nt2.tpfinal.entities.Client;
import ort.nt2.tpfinal.entities.Orders_product;
import ort.nt2.tpfinal.entities.Product;
import ort.nt2.tpfinal.services.ProductsService;


public class SQLiteHelper extends SQLiteOpenHelper {
    private static SQLiteHelper sqLiteHelper;

    private final static String DATA_BASE_NAME = "ventas_mobile";
    private final static int DATA_BASE_VERSION = 1;

    private final static String NEW_ORDER_QUERY = "insert into orders (client_id, seller_id) values ('%d', '1')";
    private final static String NEW_ORDERS_PRODUCT_QUERY = "insert into orders_product (product_id, orders_id, quantity) values ('%d', '%d', '%d')";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    public static SQLiteHelper getInstance(Context context) {
        if (sqLiteHelper == null) sqLiteHelper = new SQLiteHelper(context);

        return sqLiteHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        loadData(db);
    }

    private void loadData(SQLiteDatabase db) {
        // ±±±±± CREATES ±±±±±

        db.execSQL("create table client (" +
                "id INTEGER primary key AUTOINCREMENT not null," +
                "dni int not null," +
                "name varchar not null," +
                "last_name varchar not null)");

        db.execSQL("create table orders (" +
                "id INTEGER primary key AUTOINCREMENT not null," +
                "client_id int not null," +
                "seller_id int not null," +
                "foreign key(client_id) references client(id))");

        db.execSQL("create table product (" +
                "id INTEGER primary key AUTOINCREMENT not null," +
                "name varchar not null," +
                "price decimal not null)");

        db.execSQL("create table stock (" +
                "product_id int not null," +
                "id INTEGER primary key AUTOINCREMENT not null," +
                "available_quantity int not null," +
                "foreign key(product_id) references product(id))");

        db.execSQL("create table seller (" +
                "id INTEGER primary key AUTOINCREMENT not null," +
                "name varchar not null," +
                "last_name varchar not null," +
                "zone varchar not null)");

        db.execSQL("create table orders_product (" +
                "id INTEGER primary key AUTOINCREMENT not null," +
                "product_id int not null," +
                "orders_id int not null," +
                "quantity int not null)");

        // ±±±±± INSERTS ±±±±±

        db.execSQL("insert into product (name, price)" +
                "values ('Galletitas sin gluten', '100')," +
                "('Yerba organica', '130')," +
                "('Alfajores', '200')," +
                "('Tofu', '220')," +
                "('Queso descremado', '150')");

        db.execSQL("insert into seller (name, last_name, zone)" +
                "values('Martin', 'Tablada','Belgrano')," +
                "('Segundo', 'Sanders','Boedo')," +
                "('Ignacio', 'Bustamante','Saavedra')");

        db.execSQL("insert into orders (client_id, seller_id)" +
                "values('1', '1')," +
                "('2',  '1')," +
                "('3', '1')");

        db.execSQL("insert into orders_product (product_id, orders_id, quantity)" +
                "values('1', '1', '5')," +
                "('2', '2', '4')," +
                "('3', '3', '7')");

        db.execSQL("insert into stock (product_id, available_quantity)" +
                "values (1, 12)," +
                "(2, 23)," +
                "(3, 12)," +
                "(4, 2)," +
                "(5, 0)");

        db.execSQL("insert into client (name, last_name, dni)" +
                "values('Pepito', 'Gonzalez', 23857338)," +
                "('Juancito', 'Ramirez', 30949382)," +
                "('Fulanito', 'Gomez', 34000299)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS client");
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS product");
        db.execSQL("DROP TABLE IF EXISTS stock");
        db.execSQL("DROP TABLE IF EXISTS seller");
        db.execSQL("DROP TABLE IF EXISTS orders_product");
        onCreate(db);
    }

    public static void createNewOrder(int clientId, List<NewOrderActivity.RowProduct> products) {
        SQLiteDatabase writer = getInstance(ContextApp.getContext()).getWritableDatabase();

        writer.execSQL(String.format(NEW_ORDER_QUERY, getClientIdByPersonalId(clientId)));
        Cursor cursor = writer.rawQuery("SELECT id FROM orders ORDER BY id DESC LIMIT 1", new String[]{});

        int newOrderId = -1;

        while (cursor.moveToNext()) newOrderId = cursor.getInt(cursor.getColumnIndex("id"));

        for (NewOrderActivity.RowProduct p : products) {
            int productId = p.product.getId();
            int qty = Integer.valueOf(p.quantity.getText().toString());

            writer.execSQL(String.format(NEW_ORDERS_PRODUCT_QUERY, productId, newOrderId, qty));

            ProductsService.updateProduct(productId, resolveNewQty(qty, p.checkBox.getText().toString()));
        }
    }

    private static int getClientIdByPersonalId(int personalId) {
        Cursor cursor = executeQuery(ContextApp.getContext(), String.format("select id from client where dni = %d", personalId));

        int id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(getColumn(cursor, "id"));
        }

        return id;
    }

    public static void createNewClient(String name, String lastName, int dni) {
        SQLiteDatabase writer = getInstance(ContextApp.getContext()).getWritableDatabase();
        writer.execSQL(String.format("insert into client (name, last_name, dni) values ('%s', '%s', %d)", name, lastName, dni));
    }

    public static Product getProductById(int productId) {
        Cursor cursor = executeQuery(ContextApp.getContext(), String.format("select * from product where id = %d", productId));
        Product p = null;

        while (cursor.moveToNext()) {
            p = new Product(
                    cursor.getString(getColumn(cursor, "name")),
                    cursor.getFloat(getColumn(cursor, "price"))
            );
        }

        return p;
    }

    public static List<Orders_product> getProductsForOrder(int orderId) {
        Cursor cursor = executeQuery(ContextApp.getContext(), "select * from orders_product where orders_id = " + orderId);

        ArrayList<Orders_product> items = new ArrayList<>();

        while (cursor.moveToNext()) {
            items.add(new Orders_product(
                    cursor.getInt(getColumn(cursor, "quantity")),
                    orderId,
                    cursor.getInt(getColumn(cursor, "product_id"))
            ));
        }

        return items;
    }

    public static Cursor executeQuery(Context context, String query) {
        return getInstance(context).getReadableDatabase().rawQuery(query, new String[]{});
    }

    public static void executeUpdate(Context context, String query) {
        getInstance(context).getWritableDatabase().execSQL(query);
    }

    public static int getColumn(Cursor cursor, String column) {
        return cursor.getColumnIndex(column);
    }

    private static int resolveNewQty(int qtyRequested, String checkBox) {
        return Integer.valueOf(checkBox.substring(checkBox.indexOf("s:"), checkBox.indexOf(",")).replace("s:", "")) - qtyRequested;
    }

}
