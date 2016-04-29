package com.siervi.claudio.easysale;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para listar os produtos disponíveis e adicioná-los em uma venda
 */

public class SaleItemsAdapter extends RecyclerView.Adapter<SaleItemsAdapter.ViewHolder> {

    public List<Sale> saleList = new ArrayList<Sale>();
    private List<Product> productList;
    private int idSale = 1;


    public SaleItemsAdapter(List<Product> products) {
        this.productList = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sale_product_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.productName.setText(productList.get(position).getName());

// Recupera o produto
        Product product = productList.get(position);

// Recupera a venda daquele produto
        Sale sale = recoverSale(product.getName());

// Se o produto não foi selecionado nenhuma vez seta quantidade 0
// Senão recupera quantidade da venda
        if (sale != null){
           holder.productQuantity.setText(String.valueOf(sale.getQuantity()));
        } else {
            holder.productQuantity.setText("0");
        }

        holder.sumProducts.setTag(new Integer(position));
        holder.sumProducts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addItemSale(v);
            }
        });

        holder.removeProducts.setTag(new Integer(position));
        holder.removeProducts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                removeItemSale(v);
            }
        });
        
    }

// Recupera o item da venda de um produto
// Se ainda não foi vendido nenhum retorna nulo
    private Sale recoverSale(String name) {
        Sale sale = null;
        for (int i = 0; i < saleList.size(); i++) {
            if (saleList.get(i).getProduct().getName().equals(name)) {
                return saleList.get(i);

            }
        }
        return sale;
    }

// Adiciona um item a venda
    public void addItemSale(View v) {

// Recupera a linha que foi clicada
        Button btn = (Button) v;
        int clickedPos = ((Integer) btn.getTag()).intValue();

// Recupera o produto daquela linha
        Product product = productList.get(clickedPos);

// Recupera a venda daquele produto
        Sale sale = recoverSale(product.getName());

// Recupera a linha do item da venda
        int salePos = recoverItemSale(product.getName());

// Se o produto já havia sido selecionado (qtd > 0) adiciona 1 a quantidade
        if ( sale != null ) {
            sale.setQuantity(sale.getQuantity() + 1);
            saleList.set(salePos,sale);

// Se o produto ainda não estava na venda adiciona uma nova linha
        } else {
            sale = new Sale();
            sale.setId(idSale);
            sale.setProduct(product);
            sale.setQuantity(1);
            saleList.add(sale);
        }

        notifyItemChanged(clickedPos);
    }

// Recupera a posição do item da venda
    private int recoverItemSale(String name) {
        int i;
        for (i = 0; i < saleList.size(); i++) {
            if (saleList.get(i).getProduct().getName().equals(name)) {
                return i;

            }
        }
        return i;
    }

    private void removeItemSale(View v) {

// Recupera a linha que foi clicada
        Button btn = (Button) v;
        int clickedPos = ((Integer) btn.getTag()).intValue();

// Recupera o produto daquela linha
        Product product = productList.get(clickedPos);

// Recupera a venda daquele produto
        Sale sale = recoverSale(product.getName());

// Se não havia nenhuma quantidade daquele produto não subtrai nada
        if (sale == null ) {
            return;
        }

// Recupera a linha do item da venda
        int salePos = recoverItemSale(product.getName());

// Se o produto já havia sido selecionado (qtd > 0) adiciona 1 a quantidade
        if ( sale.getQuantity() > 1 ) {
            sale.setQuantity(sale.getQuantity() - 1);
            saleList.set(salePos,sale);

// Se havia apenas 1 quantidade do produto na venda remove o item da venda
        } else {
            saleList.remove(salePos);
        }

        notifyItemChanged(clickedPos);

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, productQuantity;
        public Button sumProducts, removeProducts;

        public ViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.txt_productName);
            productQuantity = (TextView) view.findViewById(R.id.txt_quantity);
            sumProducts = (Button) view.findViewById(R.id.btn_sumProducts);
            removeProducts = (Button) view.findViewById(R.id.btn_removeProducts);
        }
    }

    @Override
    public int getItemCount() {

        return productList.size();
    }
}