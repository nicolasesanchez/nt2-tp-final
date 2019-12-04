package ort.nt2.tpfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import ort.nt2.tpfinal.sql.SQLiteHelper;

public class NewClientsActivity extends AppCompatActivity {
    EditText clientName;
    EditText clientLastName;
    EditText clientDni;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_client);

        Button submitButton = new Button(this);
        submitButton.setText(R.string.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewClient();
            }
        });

        LinearLayout linearLayout = findViewById(R.id.new_client_linear_layout);
        linearLayout.addView(submitButton);

        clientName = findViewById(R.id.new_client_name);
        clientLastName = findViewById(R.id.new_client_last_name);
        clientDni = findViewById(R.id.new_client_dni);
    }

    private void createNewClient() {
        String clientName = this.clientName.getText().toString();
        String clientLastName = this.clientLastName.getText().toString();
        int clientDni = Integer.valueOf(this.clientDni.getText().toString());

        SQLiteHelper.createNewClient(clientName, clientLastName, clientDni);

        startActivity(new Intent(NewClientsActivity.this, ClientActivity.class));
    }

}
