package ort.nt2.tpfinal.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
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
        super(context,0,list);
        mContext = context;
        orderList = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Orders getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override


    public View getView(int i, View view, ViewGroup viewGroup) {

        view= LayoutInflater.from(mContext).inflate(R.layout.ordersitmelist,null);
        TextView pedidoHecho= (TextView) view.findViewById(R.id.pedidoHechoDefault);
        TextView idCliente = (TextView) view.findViewById(R.id.IdCliente);
        Orders item= (Orders) getItem(i);
        idCliente.setText(item.getClient_id());
        return view;

    }
}
