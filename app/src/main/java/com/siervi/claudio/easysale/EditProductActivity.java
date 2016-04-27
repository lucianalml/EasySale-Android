package com.siervi.claudio.easysale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import io.realm.Realm;

public class EditProductActivity extends AppCompatActivity {

    ImageButton btnRegisterProduct;
    EditText edtProductName, edtProductPrice;
    private Realm realm;

    String nome, preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

// Recupera dados do produto para edição
        Intent intent = getIntent();
        nome = intent.getStringExtra("NOME");
        preco = intent.getStringExtra("PRECO");

        setUI();
        setActions();

        realm = Realm.getDefaultInstance();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

// Atribui os elementos da tela
    private void setUI() {
        btnRegisterProduct = (ImageButton) findViewById(R.id.btn_RegisterProduct);
        edtProductName = (EditText) findViewById(R.id.edt_ProductName);
        edtProductPrice = (EditText) findViewById(R.id.edt_ProductPrice);

// Atribui os dados
        edtProductName.setText(nome);
        edtProductPrice.setText(preco);
    }

// Atribui as ações
    private void setActions() {
        btnRegisterProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registerProduct(v);
            }
        });
    }

// Salva as alterações
    private  void registerProduct(View view){
        try{

// Recupera o produto do banco de dados
            Product product = realm.where(Product.class).equalTo("name",nome).findFirst();

            realm.beginTransaction();

// Atualiza os dados
            product.setName(edtProductName.getText().toString());
            product.setPrice(Double.parseDouble(edtProductPrice.getText().toString()));

            realm.commitTransaction();

            Product produto = realm.where(Product.class).equalTo("name",edtProductName.getText().toString()).findFirst();
            Toast.makeText(EditProductActivity.this, produto.getName() + " atualizado.", Toast.LENGTH_SHORT).show();

            finish();

        } catch (Exception e){
            Log.e("Realm Error", "error", e);
        }

    }

}
