package ort.nt2.tpfinal;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ort.nt2.tpfinal.adapters.ClientAdapter;
import ort.nt2.tpfinal.entities.Client;
import ort.nt2.tpfinal.sql.SQLiteHelper;

public class ClientActivity extends ListActivity {
    private ListView clientsList;
    private ClientAdapter clientAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clients_list);

        clientsList = (ListView) findViewById(android.R.id.list);
        List<Client> clients = getClients();

        clientAdapter = new ClientAdapter(this, clients);
        clientsList.setAdapter(clientAdapter);


        /*FragmentManager fm = getSupportFragmentManager();
        ClientModal editNameDialogFragment = new ClientModal();
        editNameDialogFragment.show(fm, "fragment_edit_name");*/

    }

    private List<Client> getClients() {
        Cursor cursor = SQLiteHelper.executeQuery(this, "select dni, name, last_name from client");

        ArrayList<Client> clients = new ArrayList<>();

        while (cursor.moveToNext()) {
            clients.add(new Client(
                    cursor.getInt(cursor.getColumnIndex("dni")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("last_name"))
            ));
        }

        return clients;
    }

}
