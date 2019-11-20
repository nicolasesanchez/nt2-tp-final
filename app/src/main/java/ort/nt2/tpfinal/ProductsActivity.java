package ort.nt2.tpfinal;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ort.nt2.tpfinal.adapters.ProductAdapter;
import ort.nt2.tpfinal.entities.Product;
import ort.nt2.tpfinal.sql.SQLiteHelper;

public class ProductsActivity extends ListActivity {
    private ListView listView;
    private ProductAdapter productAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_list);

        listView = (ListView) findViewById(android.R.id.list);
        List<Product> moviesList = getProducts();

        productAdapter = new ProductAdapter(this, moviesList);
        listView.setAdapter(productAdapter);
    }

    private List<Product> getProducts() {
        Cursor cursor = SQLiteHelper.getInstance(this).getReadableDatabase().rawQuery("select p.id, p.name, p.price, s.available_quantity stock from product p join stock s on p.id = s.product_id", new String[]{});

        ArrayList<Product> products = new ArrayList<>();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            float price = cursor.getFloat(cursor.getColumnIndex("price"));
            int stock = cursor.getInt(cursor.getColumnIndex("stock"));

            products.add(new Product(name, price, stock));
        }

        return products;
    }

}
