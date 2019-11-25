package ort.nt2.tpfinal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ort.nt2.tpfinal.entities.Product;
import ort.nt2.tpfinal.services.ProductsService;
import ort.nt2.tpfinal.sql.SQLiteHelper;
import ort.nt2.tpfinal.util.Utils;

public class NewOrderActivity extends AppCompatActivity {
    Spinner clientsSpinner;
    EditText productQty;
    List<RowProduct> productsList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_order);

        clientsSpinner = findViewById(R.id.client_spinner);
        ArrayAdapter<String> clientsListAdapterNames = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getClientsNames());
        clientsListAdapterNames.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientsSpinner.setAdapter(clientsListAdapterNames);

        LinearLayout linearLayout = findViewById(R.id.new_order_linear_layout);

        for (Product product : ProductsService.getAvailableProducts()) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(String.format("%s [s:%d, p:$%.2f]", product.getName(), product.getStock(), product.getPrice()));
            checkBox.setLayoutParams(Utils.getWrapContent());
//            checkBox.setGravity(Gravity.END | Gravity.LEFT);

            productQty = new EditText(this);
            productQty.setLayoutParams(Utils.getWrapContent());
            productQty.setGravity(Gravity.END | Gravity.RIGHT);
            productQty.setInputType(InputType.TYPE_CLASS_NUMBER);

            LinearLayout row = new LinearLayout(this);
            row.addView(checkBox);
            row.addView(productQty);


            linearLayout.addView(row);
            productsList.add(new RowProduct(checkBox, productQty, product));
        }

        Button submitButton = new Button(this);
        submitButton.setText(R.string.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewOrder();
            }
        });

        linearLayout.addView(submitButton);
    }

    private void createNewOrder() {
        int clientPersonalId = getPersonalId(clientsSpinner.getSelectedItem().toString());

        List<RowProduct> products = new ArrayList<>();

        for (RowProduct product : productsList) {
            if (product.checkBox.isChecked()) products.add(product);
        }

        SQLiteHelper.createNewOrder(clientPersonalId, products);

        Intent intent = new Intent(NewOrderActivity.this, OrdersActivity.class);
        startActivity(intent);
    }

    private List<String> getClientsNames() {
        Cursor cursor = SQLiteHelper.executeQuery(this, "select dni, last_name from client");

        ArrayList<String> clients = new ArrayList<>();
        clients.add("");

        while (cursor.moveToNext()) {
            clients.add(String.format("%s, %s", cursor.getString(cursor.getColumnIndex("last_name")), Utils.parsePersonalId(cursor.getInt(cursor.getColumnIndex("dni")))));
        }

        return clients;
    }

    public static class RowProduct {
        public CheckBox checkBox;
        public EditText quantity;
        public Product product;

        public RowProduct(CheckBox checkBox, EditText quantity, Product product) {
            this.checkBox = checkBox;
            this.quantity = quantity;
            this.product = product;
        }
    }

    private int getPersonalId(String id) {
        return Integer.valueOf(id.split(",")[1].trim().replaceAll("\\.", ""));
    }

}
