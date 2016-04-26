package com.siervi.claudio.easysale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // main activity objects
    Button btnListProducts, btnMakeSale, btnReport;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setActions();
    }

// Atribui os elementos do layout
    private void setUI(){
        btnListProducts = (Button) findViewById(R.id.btn_ProductRegistration );
        btnMakeSale = (Button) findViewById(R.id.btn_MakeSale );
        btnReport = (Button) findViewById(R.id.btn_Report );
    }

// Seta as ações dos botoes
    private void setActions(){
        btnListProducts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listProductActivity(v);
            }
        });
        btnMakeSale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saleActivity(v);
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reportActivity(v);
            }
        });
    }

// Lista os produtos cadastrados
    public void listProductActivity(View view) {
        Intent intent = new Intent(this, ListProductActivity.class);
        startActivity(intent);
    }

// Inicia o processo de venda
    public void saleActivity(View view) {
        Intent intent = new Intent(this, SalesActivity.class);
        startActivity(intent);
    }

// Exibe os relatórios
    public void reportActivity(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

}

