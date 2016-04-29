package com.siervi.claudio.easysale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Lista de produtos cadastrados
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<Product> productList;
    private Context context;

    public ProductsAdapter(Context current, List<Product> products) {
        this.productList = products;
        this.context = current;
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

        String valor_prod = String.format(context.getResources().getString(R.string.st_valor_produto),
                productList.get(position).getPrice());

        holder.tvValorProd.setText(valor_prod);

        holder.btnEditProduct.setTag(Integer.valueOf(position));
        holder.btnEditProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editProduct(v);
            }
        });
    }

    private void editProduct(View v) {

// Recupera a posição clicada
        ImageButton btn = (ImageButton) v;
        int clickedPos = ((Integer) btn.getTag()).intValue();

// Recupera o produto selecionado
        Product product = productList.get(clickedPos);

// Chama a tela de edição para o produto selecionado
        Intent intent = new Intent(v.getContext(), EditProductActivity.class);
        intent.putExtra("ID", String.valueOf(product.getId()));
        v.getContext().startActivity(intent);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNameProd, tvValorProd;
        public ImageButton btnEditProduct;
        public ViewHolder(View view) {
            super(view);
            tvNameProd = (TextView) view.findViewById(R.id.tv_name_prod);
            tvValorProd = (TextView) view.findViewById(R.id.tv_valor_prod);
            btnEditProduct = (ImageButton) view.findViewById(R.id.btn_edit_prod);
        }
    }


    @Override
    public int getItemCount() {

        return productList.size();
    }
    
}
