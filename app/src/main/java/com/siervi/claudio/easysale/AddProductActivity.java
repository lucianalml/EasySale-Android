package com.siervi.claudio.easysale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

/**
 * Create new products
 */

public class AddProductActivity extends AppCompatActivity {

    Button btnRegisterProduct;
    EditText edtProductName, edtProductPrice;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setUI();
        setActions();

        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
    // define buttons
    private void setUI() {
        btnRegisterProduct = (Button) findViewById(R.id.btn_RegisterProduct);
        edtProductName = (EditText) findViewById(R.id.edt_ProductName);
        edtProductPrice = (EditText) findViewById(R.id.edt_ProductPrice);
    }

    private void setActions() {
        btnRegisterProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registerProduct(v);
            }
        });
    }

    // save a new object
    private  void registerProduct(View view){

// Recupera proximo id
        int id = 1;
        if (! realm.where(Product.class).findAll().isEmpty() ) {
            id = realm.where(Product.class).max("id").intValue() + 1;
        }

        try{

            realm.beginTransaction();

            Product product = realm.createObject(Product.class);
            product.setId(id);
            product.setName(edtProductName.getText().toString());
            product.setPrice(Double.parseDouble(edtProductPrice.getText().toString()));
            product.setAtivo(true);

            realm.commitTransaction();

            Product produto = realm.where(Product.class).equalTo("name",edtProductName.getText().toString()).findFirst();
            Toast.makeText(AddProductActivity.this, produto.getName() + " cadastrado.", Toast.LENGTH_SHORT).show();


        } catch (Exception e){
            Log.e("Realm Error", "error", e);
        }

// clean view
    edtProductName.setText("");
    edtProductPrice.setText("");
    }

}