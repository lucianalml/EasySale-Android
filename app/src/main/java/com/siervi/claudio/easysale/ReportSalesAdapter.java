package com.siervi.claudio.easysale;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        holder.v_txt_id.setText(String.valueOf(saleList.get(position).getId()));
        holder.v_txt_itemName.setText(saleList.get(position).getProduct().getName());
        holder.v_txt_qtd.setText(String.valueOf(saleList.get(position).getQuantity()));

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView v_txt_id, v_txt_itemName, v_txt_qtd;

        public ViewHolder(View view) {
            super(view);
            v_txt_id = (TextView) view.findViewById(R.id.txt_id);
            v_txt_itemName = (TextView) view.findViewById(R.id.txt_itemName);
            v_txt_qtd = (TextView) view.findViewById(R.id.txt_qtd);

        }
    }


    @Override
    public int getItemCount() {

        return saleList.size();
    }
}
