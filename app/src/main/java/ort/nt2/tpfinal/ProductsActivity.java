package ort.nt2.tpfinal;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import ort.nt2.tpfinal.adapters.ProductAdapter;
import ort.nt2.tpfinal.entities.Product;
import ort.nt2.tpfinal.services.ProductsService;

public class ProductsActivity extends ListActivity {
    private ListView listView;
    private ProductAdapter productAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_list);

        listView = (ListView) findViewById(android.R.id.list);
        List<Product> moviesList = ProductsService.getAllProducts();

        productAdapter = new ProductAdapter(this, moviesList);
        listView.setAdapter(productAdapter);
    }

}
