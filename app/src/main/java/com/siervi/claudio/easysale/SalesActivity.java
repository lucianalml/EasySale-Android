package com.siervi.claudio.easysale;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import io.realm.Realm;

public class SalesActivity extends AppCompatActivity {

    private List<Product> products;
    private RecyclerView mRecyclerView;
    private SaleItemsAdapter mSaleItemsAdapter;
    private Realm realm;

    Button btnConfirmSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        setUI();
        setActions();

// Recupera todos os produtos cadastrados
        realm = Realm.getDefaultInstance();
        products = realm.where(Product.class).findAll();


// Determina os atributos da RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

// Atribui o SaleItemsAdapter para a RecycleView
        mSaleItemsAdapter = new SaleItemsAdapter(products);
        mRecyclerView.setAdapter(mSaleItemsAdapter);

  }

// Referencia os elementos do layout
    private void setUI(){
        btnConfirmSale = (Button) findViewById(R.id.btn_confirmSale );
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_sales);
    }

// Determina ações dos botões
    private void setActions(){
        btnConfirmSale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                confirmSale(v);
            }
        });

    }

// Fecha instancia do real
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

// Salva os itens vendidos no banco de dados
    public void confirmSale(View view) {

// Exibe o alerta de confirmação

        AlertDialog.Builder builder = new AlertDialog.Builder(SalesActivity.this);
        builder.setTitle("Confirmar a venda?");

        String mensagem = "";
        double valorItem, valorTotal = 0;
        for (int i = 0; i < mSaleItemsAdapter.saleList.size(); i++) {

// Calcula o valor cada item
            valorItem = mSaleItemsAdapter.saleList.get(i).getProduct().getPrice() * mSaleItemsAdapter.saleList.get(i).getQuantity();

// Calcula o valor total da compra
            valorTotal = valorTotal + valorItem;

// Monta a mensagem de confirmação
            mensagem = mensagem + String.valueOf(mSaleItemsAdapter.saleList.get(i).getQuantity()) + " " +
                        mSaleItemsAdapter.saleList.get(i).getProduct().getName() +
                        " R$ " + String.valueOf(valorItem) + "\n";
        }
        mensagem = mensagem + "\n Total R$: " + String.valueOf(valorTotal);

        builder.setMessage(mensagem);

// Add the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                salvarVenda();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

// Exibe o alerta de confirmação
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void salvarVenda() {
        Calendar c = Calendar.getInstance();

        // Recupera o próximo id
        int saleId = 1;
        if (! realm.where(Sale.class).findAll().isEmpty() ) {
            saleId = realm.where(Sale.class).max("id").intValue() + 1;
        }

        realm.beginTransaction();
        for (int i = 0; i < mSaleItemsAdapter.saleList.size(); i++) {
            Sale sale = realm.createObject(Sale.class);
            sale.setId(saleId);
            sale.setQuantity(mSaleItemsAdapter.saleList.get(i).getQuantity());
            sale.setProduct(mSaleItemsAdapter.saleList.get(i).getProduct());
            sale.setDate(c.getTime());
        }

        realm.commitTransaction();
        Toast.makeText(SalesActivity.this, "Dados Salvos", Toast.LENGTH_SHORT).show();

// Limpa a lista da venda e reinicia o processo
        mSaleItemsAdapter.saleList.clear();
        mSaleItemsAdapter.notifyDataSetChanged();
    }

}
