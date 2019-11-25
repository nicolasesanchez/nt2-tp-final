package ort.nt2.tpfinal.services;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ort.nt2.tpfinal.ContextApp;
import ort.nt2.tpfinal.entities.Product;
import ort.nt2.tpfinal.sql.SQLiteHelper;

public class ProductsService {

    public static List<Product> getAvailableProducts() {
        Cursor cursor = SQLiteHelper.executeQuery(ContextApp.getContext(), "select p.id, p.name, p.price, s.available_quantity stock from product p join stock s on p.id = s.product_id and stock > 0");

        ArrayList<Product> products = new ArrayList<>();

        while (cursor.moveToNext()) {
            products.add(new Product(
                    cursor.getInt(SQLiteHelper.getColumn(cursor, "id")),
                    cursor.getString(SQLiteHelper.getColumn(cursor, "name")),
                    cursor.getFloat(SQLiteHelper.getColumn(cursor, "price")),
                    cursor.getInt(SQLiteHelper.getColumn(cursor, "stock"))
            ));
        }

        return products;
    }

    public static List<Product> getAllProducts() {
        Cursor cursor = SQLiteHelper.executeQuery(ContextApp.getContext(), "select p.id, p.name, p.price, s.available_quantity stock from product p join stock s on p.id = s.product_id");

        ArrayList<Product> products = new ArrayList<>();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            float price = cursor.getFloat(cursor.getColumnIndex("price"));
            int stock = cursor.getInt(cursor.getColumnIndex("stock"));

            products.add(new Product(name, price, stock));
        }

        return products;
    }

    public static void updateProduct(int productId, int qty) {
        SQLiteHelper.executeUpdate(ContextApp.getContext(), String.format("update stock set available_quantity = %d where product_id = %d", qty, productId));
    }

}
