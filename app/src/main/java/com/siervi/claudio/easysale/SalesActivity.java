package com.siervi.claudio.easysale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;

public class SalesActivity extends AppCompatActivity {

    private List<Product> product;
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
        product = realm.where(Product.class).findAll();


// Determina os atributos da RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

// Atribui o SaleItemsAdapter para a RecycleView
        mSaleItemsAdapter = new SaleItemsAdapter(product);
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

// Salva os itens vendidos no banco de dados
    public void confirmSale(View view) {

        realm.beginTransaction();
        for (int i = 0; i < mSaleItemsAdapter.saleList.size(); i++) {
            Sale sale = realm.createObject(Sale.class);

// Recupera o próximo id
            sale.setId(realm.where(Sale.class).max("id").intValue() + 1);
            sale.setQuantity(mSaleItemsAdapter.saleList.get(i).getQuantity());
            sale.setProduct(mSaleItemsAdapter.saleList.get(i).getProduct());

        }

        realm.commitTransaction();
        Toast.makeText(SalesActivity.this, "Dados Salvos",Toast.LENGTH_SHORT).show();

// Limpa a lista de produtos e reinicia a venda
        mSaleItemsAdapter.saleList.clear();
        mSaleItemsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // close Realm when done
    }

}
