package com.siervi.claudio.easysale;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Classe para listar os itens vendidos
 */

public class ReportSalesAdapter extends RecyclerView.Adapter<ReportSalesAdapter.ViewHolder> {

    private List<Sale> saleList;

    public ReportSalesAdapter(List<Sale> sales) {
        this.saleList = sales;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_sale_item, parent, false);

        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.v_txt_itemName.setText(saleList.get(position).getProduct().getName());
        holder.v_txt_qtd.setText(String.valueOf(saleList.get(position).getQuantity()));

// Formata a data
        String newDateStr = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        if (saleList.get(position).getDate() != null) {
            newDateStr = dateFormat.format(saleList.get(position).getDate());
        }

        holder.v_txt_dt_venda.setText(newDateStr);

// Valor total do item
        holder.v_txt_valor.setText(String.valueOf(saleList.get(position).getValor_total()));

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView v_txt_dt_venda, v_txt_itemName, v_txt_qtd, v_txt_valor;

        public ViewHolder(View view) {
            super(view);
            v_txt_dt_venda = (TextView) view.findViewById(R.id.txt_dt_venda);
            v_txt_itemName = (TextView) view.findViewById(R.id.txt_itemName);
            v_txt_qtd = (TextView) view.findViewById(R.id.txt_qtd);
            v_txt_valor = (TextView) view.findViewById(R.id.txt_valor);

        }
    }


    @Override
    public int getItemCount() {

        return saleList.size();
    }
}
