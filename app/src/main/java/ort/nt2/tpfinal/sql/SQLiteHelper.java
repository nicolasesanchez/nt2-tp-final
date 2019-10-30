package ort.nt2.tpfinal.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class SQLiteHelper extends SQLiteOpenHelper {
    private static SQLiteHelper sqLiteHelper;

    private final static String DATA_BASE_NAME = "ventas_mobile";
    private final static int DATA_BASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    public static SQLiteHelper getInstance(Context context) {
        if (sqLiteHelper == null) sqLiteHelper = new SQLiteHelper(context);

        return sqLiteHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table client (" +
                "id INTEGER primary key AUTOINCREMENT not null," +
                "name varchar not null," +
                "last_name varchar not null)");

        db.execSQL("create table orders (" +
                "id INTEGER primary key AUTOINCREMENT not null," +
                "client_id int not null," +
//                "seller_id int not null," +
                "product_id int not null," +
                "foreign key(client_id) references client(id)," +
                "foreign key (product_id) references product(id))");

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

        db.execSQL("insert into product (name, price)" +
                "values ('Galletitas sin gluten', '100')," +
                "('Yerba organica', '130')," +
                "('Alfajores', '200')," +
                "('Tofu', '220')," +
                "('Queso descremado', '150')");

        db.execSQL("insert into stock (product_id, available_quantity)" +
                "values (1, 12)," +
                "(2, 23)," +
                "(3, 12)," +
                "(4, 2)," +
                "(5, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS client");
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS product");
        db.execSQL("DROP TABLE IF EXISTS stock");
        db.execSQL("DROP TABLE IF EXISTS seller");
        db.execSQL("DROP TABLE IF EXISTS orders_product");
        onCreate(db);
    }


}
