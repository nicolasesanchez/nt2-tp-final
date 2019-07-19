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
import ort.nt2.tpfinal.entities.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context mContext;
    private List<Product> productList;

    public ProductAdapter(@NonNull Context context, @LayoutRes List<Product> list) {
        super(context, 0, list);
        mContext = context;
        productList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listProduct = convertView;
        if (listProduct == null)
            listProduct = LayoutInflater.from(mContext).inflate(R.layout.row_product, parent, false);

        Product currentProduct = productList.get(position);

        TextView name = (TextView) listProduct.findViewById(R.id.product_name);
        name.setText(currentProduct.name);
        name.setPadding(10, 10, 0, 10);

        TextView qty = (TextView) listProduct.findViewById(R.id.product_stock);
        qty.setText(String.valueOf(currentProduct.qty));
        qty.setPadding(0, 10, 10, 10);

        return listProduct;
    }
}
