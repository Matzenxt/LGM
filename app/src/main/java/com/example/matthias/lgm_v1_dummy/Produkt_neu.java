package com.example.matthias.lgm_v1_dummy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Produkt_neu extends AppCompatActivity {

    EditText etName, etFuellmenge, etBarcode;
    Button btnErstellen;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produkt_neu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);
        etName = (EditText) findViewById(R.id.et_Name);
        etFuellmenge = (EditText) findViewById(R.id.et_Fuellmenge);
        etBarcode = (EditText) findViewById(R.id.et_Barcode);

        btnErstellen = (Button) findViewById(R.id.btn_Erstellen);

        btnErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pName = etName.getText().toString();
                String pFuellmenge = etFuellmenge.getText().toString();
                String pBarcode = etBarcode.getText().toString();

                if(pName.length() != 0 && pFuellmenge.length() != 0 && pBarcode.length() != 0){
                    AddData(pName, pFuellmenge, pBarcode);
                    etName.setText("Name");
                    etFuellmenge.setText("Füllmenge");
                    etBarcode.setText("Barcode");

                }else {
                    Toast.makeText(Produkt_neu.this, "Es müssen alle Fälder ausgefüllt sein", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void AddData(String pName, String pFuellmenge, String pBarcode){
        boolean insertData = dataBaseHelper.addProdukt(pName, pFuellmenge, pBarcode);

        if(insertData){
            Toast.makeText(this, "Eintrag erfolgreich angelegt", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Fehler beim anlegen des Eintrags", Toast.LENGTH_LONG).show();
        }
    }

}
