package ort.nt2.tpfinal;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import ort.nt2.tpfinal.adapters.ProductAdapter;
import ort.nt2.tpfinal.entities.Product;

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
        return Arrays.asList(
                new Product("Galletitas sin gluten", 100),
                new Product("Yerba Organica", 130),
                new Product("Hamburguesas V", 200),
                new Product("Tofu", 220),
                new Product("Queso Descremado", 150),
                new Product("Vino Organico", 500),
                new Product("Aceite oliva 2lt", 310),
                new Product("Te Matcha Organico", 700),
                new Product("Helado Vegano", 110),
                new Product("Almidon Mandioca 1kg", 190)
        );
    }

}
