package com.siervi.claudio.easysale;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class EditProductActivity extends AppCompatActivity {

    Button btnRegisterProduct, btnDeleteProduct;
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
        btnRegisterProduct = (Button) findViewById(R.id.btn_RegisterProduct);
        btnDeleteProduct = (Button) findViewById(R.id.btn_eliminar);
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
        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteProduct(v);
            }
        });
    }

// Elimina um produto
    private void deleteProduct(View v) {

// Exibe o alerta de confirmação

        AlertDialog.Builder builder = new AlertDialog.Builder(EditProductActivity.this);
        builder.setTitle("Confirmar exclusão?");
        builder.setMessage("Deseja eliminar o produto " + edtProductName.getText() + "?");

// Add the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                eliminarProduto();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

// Exibe o alerta de confirmação
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void eliminarProduto() {

        // Recupera o produto do banco de dados
        Product product = realm.where(Product.class).equalTo("name",nome).findFirst();

// Elimina

        realm.beginTransaction();
        product.removeFromRealm();
        realm.commitTransaction();

        Toast.makeText(EditProductActivity.this, nome + " eliminado.", Toast.LENGTH_SHORT).show();

        finish();

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
