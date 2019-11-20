package ort.nt2.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ort.nt2.tpfinal.sql.SQLiteHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
        sqlHelper.onUpgrade(sqlHelper.getWritableDatabase(), 1, 2);
//        sqlHelper.onCreate(sqlHelper.getWritableDatabase());

        // sqlHelper.getWritableDatabase() // TODO returns un SQLiteDatabase
        // sqlHelper.getReadableDatabase() // TODO returns un SQLiteDatabase

        // TODO usar siempre SQLiteDatabasequery, la otra forma es conveniente si uso alias (SQLiteQueryBuilder) el objeto que devuelve es similar a un ResultSet

        // TODO revisar clse Esquema y Contrato

        // TODO para hacer un insert btener un writable, crear un ContenValues(es un mapish) y el método es insert()

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.products);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProductsActivity.class));
            }
        });

        Button btn2 = (Button) findViewById(R.id.orders);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
                startActivity(intent);
            }
        });

        btn = (Button) findViewById(R.id.profile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        btn = (Button) findViewById(R.id.clients);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ClientActivity.class));
            }
        });
    }

    public void goToOrder(View v) {
        Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
        startActivity(intent);
    }

}
