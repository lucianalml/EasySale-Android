package com.siervi.claudio.easysale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.realm.Realm;

public class ListProductActivity extends AppCompatActivity {

    private List<Product> products;
    private RecyclerView mRecyclerView;
    private ProductsAdapter mProductsAdapter;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        setUI();
        setActions();

        realm = Realm.getDefaultInstance();

        products = realm.where(Product.class).findAll();

        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_product_list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mProductsAdapter = new ProductsAdapter(products);
        mRecyclerView.setAdapter(mProductsAdapter);

    }

    private void setActions() { }

    private void setUI() { }

// Fecha instância do Realm
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
