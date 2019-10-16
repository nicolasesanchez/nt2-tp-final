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
        List<Product> moviesList = getStock();

        productAdapter = new ProductAdapter(this, moviesList);
        listView.setAdapter(productAdapter);
    }

    private List<Product> getStock() {
        Cursor cursor = SQLiteHelper.getInstance(this).getReadableDatabase().rawQuery("select * from product", new String[]{});

        ArrayList<Product> products = new ArrayList<>();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            float price = cursor.getFloat(cursor.getColumnIndex("price"));

            products.add(new Product(name, price));
        }

        return products;
    }

}
