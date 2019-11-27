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
import ort.nt2.tpfinal.entities.Orders;
import ort.nt2.tpfinal.sql.SQLiteHelper;

public class OrdersActivity extends ListActivity {

    private ListView listView;
    private OdersAdapter ordersAdapter;
    private Button verdetalle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_list);

        listView = (ListView) findViewById(android.R.id.list);
        List<Orders> movesOrderList = getOrders();

        ordersAdapter = new OdersAdapter(this, movesOrderList);
        listView.setAdapter(ordersAdapter);

        //obtener id del pedido, no esta en el adapter.

        //boton ver, deberia cargar en los campos textview los valores correspondientes al id del producto.

        Button btn = (Button) findViewById(R.id.buttonVer);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.detalle_orders);

                //buscar de la listview el id de la orden

                /*
                Orders itemListView = listView.getAdapter().;
                */

            }
        });
    }

    private List<Orders> getOrders() {
        Cursor cursor = SQLiteHelper.getInstance(this).getReadableDatabase().rawQuery("select * from Orders", new String[]{});

        ArrayList<Orders> orders = new ArrayList<>();

        while (cursor.moveToNext()) {
            int clientId = cursor.getInt(cursor.getColumnIndex("client_id"));
            int sellerID = cursor.getInt(cursor.getColumnIndex("seller_id"));
            int productId = cursor.getInt(cursor.getColumnIndex("orders_product_id"));
            orders.add(new Orders(clientId, sellerID, productId));
        }

        return orders;
    }

}
