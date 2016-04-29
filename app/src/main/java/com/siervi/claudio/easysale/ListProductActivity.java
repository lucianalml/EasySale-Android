package com.siervi.claudio.easysale;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import io.realm.Realm;

public class ListProductActivity extends AppCompatActivity {

    private List<Product> products;
    private RecyclerView mRecyclerView;
    private ProductsAdapter mProductsAdapter;
    private Realm realm;

    FloatingActionButton btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        setUI();
        setActions();

        realm = Realm.getDefaultInstance();

        products = realm.where(Product.class).equalTo("ativo",true).findAll();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mProductsAdapter = new ProductsAdapter(products);
        mRecyclerView.setAdapter(mProductsAdapter);

    }

// Atribui os elementos da tela
    private void setUI() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_product_list);
        btnAddProduct = (FloatingActionButton) findViewById(R.id.btn_add_product);
    }

// Atribui as ações
    private void setActions() {
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addProductActivity(v);
            }
        });
    }

// Chama a classe para adicionar novos produtos
    private void addProductActivity(View v) {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

// Atualiza lista de produtos
        mProductsAdapter.notifyDataSetChanged();
    }

// Fecha instância do Realm
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
