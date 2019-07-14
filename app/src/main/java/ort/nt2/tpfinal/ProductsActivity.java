package ort.nt2.tpfinal;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProductsActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // storing string resources into Array
        String[] numbers = {"one", "two", "three", "four"};
        // here you store the array of string you got from the database

        // Binding Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.products_list, R.id.products, numbers));
        // refer the ArrayAdapter Document in developer.android.com
        ListView lv = getListView();
    }
}
