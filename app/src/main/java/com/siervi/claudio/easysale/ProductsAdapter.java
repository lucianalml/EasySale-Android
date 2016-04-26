package com.siervi.claudio.easysale;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Lista de produtos cadastrados
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<Product> productList;

    public ProductsAdapter(List<Product> products) {
        this.productList = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);

        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvNameProd.setText(String.valueOf(productList.get(position).getName()));
        holder.tvValorProd.setText("R$ " + String.valueOf(productList.get(position).getPrice()));

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNameProd, tvValorProd;

        public ViewHolder(View view) {
            super(view);
            tvNameProd = (TextView) view.findViewById(R.id.tv_name_prod);
            tvValorProd = (TextView) view.findViewById(R.id.tv_valor_prod);
        }
    }


    @Override
    public int getItemCount() {

        return productList.size();
    }
    
}
