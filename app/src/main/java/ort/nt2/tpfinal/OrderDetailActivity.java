package ort.nt2.tpfinal;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ort.nt2.tpfinal.entities.Client;
import ort.nt2.tpfinal.entities.Orders_product;
import ort.nt2.tpfinal.entities.Product;
import ort.nt2.tpfinal.sql.SQLiteHelper;
import ort.nt2.tpfinal.util.Utils;

public class OrderDetailActivity extends AppCompatActivity {
    private final String TITLE_TEMPLATE = "Order %d: %s %s";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        Bundle b = getIntent().getExtras();
        int clientId = -1; // or other values
        int orderId = -1;

        if (b != null) {
            clientId = b.getInt("clientId");
            orderId = b.getInt("id");
        }

        Client client = SQLiteHelper.getClientById(clientId);

        TextView titleTextView = findViewById(R.id.order_detail_header);
        titleTextView.setText(String.format(TITLE_TEMPLATE, orderId, client.getName(), client.getLastName()));

        List<Orders_product> products = SQLiteHelper.getProductsForOrder(orderId);

        LinearLayout ll = findViewById(R.id.order_detail_linear_layout);

        int orderTotal = 0;

        for (Orders_product p : products) {
            TextView productName = new TextView(this);
            TextView productQty = new TextView(this);

            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);

            Product product = SQLiteHelper.getProductById(p.getProduct_id());

            productName.setGravity(Gravity.END | Gravity.LEFT);
            productName.setText(product.getName());
            productName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            LinearLayout.LayoutParams params = Utils.getWrapContent();
            params.setMargins(0, 0, 80, 0);
            productName.setLayoutParams(params);

            productQty.setLayoutParams(Utils.getWrapContent());
            productQty.setGravity(Gravity.END | Gravity.RIGHT);
            productQty.setText("Qty: " + p.getQuantity());
            productQty.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

            row.addView(productName);
            row.addView(productQty);

            ll.addView(row);
            orderTotal += product.getPrice() * p.getQuantity();
        }

        TextView total = new TextView(this);
        total.setLayoutParams(Utils.getWrapContent());
        total.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        total.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams params = Utils.getWrapContent();
        params.setMargins(0, 20, 0, 0);
        total.setText("Total: \t\t\t$" + orderTotal);

        ll.addView(total);
    }
}
