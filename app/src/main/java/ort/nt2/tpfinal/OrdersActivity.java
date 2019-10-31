package ort.nt2.tpfinal;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

import ort.nt2.tpfinal.adapters.OdersAdapter;
import ort.nt2.tpfinal.adapters.ProductAdapter;
import ort.nt2.tpfinal.entities.Orders;
import ort.nt2.tpfinal.entities.Orders_product;
import ort.nt2.tpfinal.entities.Product;
import ort.nt2.tpfinal.sql.SQLiteHelper;

public class OrdersActivity extends ListActivity {

    private ListView listView;
    private OdersAdapter ordersAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_list);

        listView = (ListView) findViewById(R.id.listOrders);
        List<Orders> movesOrderList = getOrders();

        ordersAdapter = new OdersAdapter(this, movesOrderList);
        listView.setAdapter(ordersAdapter);
    }

    private List<Orders> getOrders() {
        Cursor cursor = SQLiteHelper.getInstance(this).getReadableDatabase().rawQuery("select * from Orders", new String[]{});

        ArrayList<Orders> orders = new ArrayList<>();

        while (cursor.moveToNext()) {

            int clientId = cursor.getInt(cursor.getColumnIndex("client_id"));
            int sellerID = cursor.getInt(cursor.getColumnIndex("seller_id"));
            int productId = cursor.getInt(cursor.getColumnIndex("product_id"));
            orders.add(new Orders(clientId, sellerID, productId));
        }

        return orders;


    }

}
