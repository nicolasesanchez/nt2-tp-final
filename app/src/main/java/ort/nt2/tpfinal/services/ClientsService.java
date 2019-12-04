package ort.nt2.tpfinal.services;

import android.database.Cursor;

import ort.nt2.tpfinal.ContextApp;
import ort.nt2.tpfinal.entities.Client;
import ort.nt2.tpfinal.sql.SQLiteHelper;

import static ort.nt2.tpfinal.sql.SQLiteHelper.getColumn;

public class ClientsService {

    public static Client getClientById(int id) {
        Client client = null;

        Cursor cursor = SQLiteHelper.executeQuery(ContextApp.getContext(), String.format("select * from client where id = '%d'", id));

        while (cursor.moveToNext()) {
            client = new Client(
                    cursor.getInt(getColumn(cursor, "id")),
                    cursor.getInt(getColumn(cursor, "dni")),
                    cursor.getString(getColumn(cursor, "name")),
                    cursor.getString(getColumn(cursor, "last_name"))
            );
        }

        return client;
    }

}
