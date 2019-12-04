package ort.nt2.tpfinal.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import ort.nt2.tpfinal.R;
import ort.nt2.tpfinal.entities.Client;

import static ort.nt2.tpfinal.util.Utils.parsePersonalId;

public class ClientAdapter extends ArrayAdapter<Client> {
    private Context context;
    private List<Client> clientsList;

    public ClientAdapter(@NonNull Context context, @LayoutRes List<Client> list) {
        super(context, 0, list);
        this.context = context;
        clientsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View clientsList = convertView;

        if (clientsList == null) clientsList = LayoutInflater.from(context).inflate(R.layout.row_client, parent, false);

        Client currentClient = this.clientsList.get(position);

        TextView clientFullName = (TextView) clientsList.findViewById(R.id.client_name);
        clientFullName.setText((String.format("%s %s", currentClient.getName(), currentClient.getLastName())));
        clientFullName.setPadding(15, 5, 0, 5);

        TextView clientPersonalId = (TextView) clientsList.findViewById(R.id.client_id);
        clientPersonalId.setText(parsePersonalId(currentClient.getPersonalId()));
        clientPersonalId.setPadding(0, 5, 5, 15);

        return clientsList;
    }

}
