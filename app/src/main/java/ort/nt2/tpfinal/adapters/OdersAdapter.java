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

import java.util.List;

import ort.nt2.tpfinal.R;
import ort.nt2.tpfinal.entities.Orders;

public class OdersAdapter extends ArrayAdapter<Orders> {

    private Context mContext;
    private List<Orders> orderList;

    public OdersAdapter(@NonNull Context context, @LayoutRes List<Orders> list) {
        super(context, 0, list);
        mContext = context;
        orderList = list;
    }

    @NonNull
    @Override
    public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
        View listOrder = view;

        if (listOrder == null)
            view = LayoutInflater.from(mContext).inflate(R.layout.ordersitmelist, viewGroup, false);

        // TODO revisar porque no pasa por acá

        Orders currentOrder = orderList.get(i);

//        TextView pedidoHecho = (TextView) view.findViewById(R.id.pedidoHechoDefault);
        TextView idCliente = (TextView) view.findViewById(R.id.IdCliente);
        idCliente.setText(String.valueOf(currentOrder.getClient_id()));

        return view;

    }
}
