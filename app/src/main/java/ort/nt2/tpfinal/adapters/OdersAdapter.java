package ort.nt2.tpfinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ort.nt2.tpfinal.OrderDetailActivity;
import ort.nt2.tpfinal.R;
import ort.nt2.tpfinal.entities.Client;
import ort.nt2.tpfinal.entities.Order;
import ort.nt2.tpfinal.services.ClientsService;
import ort.nt2.tpfinal.sql.SQLiteHelper;

public class OdersAdapter extends ArrayAdapter<Order> {

    private Context mContext;
    private List<Order> orderList;

    public OdersAdapter(@NonNull Context context, @LayoutRes List<Order> list) {
        super(context, 0, list);
        mContext = context;
        orderList = list;
    }

    @NonNull
    @Override
    public View getView(final int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
        View listOrder = view;

        if (listOrder == null)
            view = LayoutInflater.from(mContext).inflate(R.layout.ordersitmelist, viewGroup, false);

        final Order currentOrder = orderList.get(i);
        Client client = ClientsService.getClientById(currentOrder.getClientId());

        TextView idCliente = (TextView) view.findViewById(R.id.IdCliente);
        idCliente.setText(String.format("%d. %s %s", client.getId(), client.getName(), client.getLastName()));

        view.findViewById(R.id.buttonVer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", currentOrder.getId()); //Your id
                b.putInt("clientId", currentOrder.getClientId());
                intent.putExtras(b); //Put your id to your next Intent
                mContext.startActivity(intent);
            }
        });

        return view;

    }
}
