package ort.nt2.tpfinal;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ort.nt2.tpfinal.adapters.OdersAdapter;
import ort.nt2.tpfinal.entities.Order;
import ort.nt2.tpfinal.sql.SQLiteHelper;

public class OrdersActivity extends ListActivity {

    private ListView listView;
    private OdersAdapter ordersAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_list);

        listView = (ListView) findViewById(android.R.id.list);
        List<Order> movesOrderList = getOrders();

        ordersAdapter = new OdersAdapter(this, movesOrderList);
        listView.setAdapter(ordersAdapter);

        Button newOrderBtn = (Button) findViewById(R.id.new_order_btn);
        newOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrdersActivity.this, NewOrderActivity.class));
            }
        });
    }

    private List<Order> getOrders() {
        Cursor cursor = SQLiteHelper.getInstance(this).getReadableDatabase().rawQuery("select * from Orders", new String[]{});

        ArrayList<Order> orders = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int clientId = cursor.getInt(cursor.getColumnIndex("client_id"));
            int sellerID = cursor.getInt(cursor.getColumnIndex("seller_id"));
            orders.add(new Order(id, clientId, sellerID));
        }

        return orders;
    }

}
