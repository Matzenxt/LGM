package com.example.matthias.lgm_v1_dummy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class verkaeufer_neu extends AppCompatActivity {

    EditText etName, etInhaber, etOrt, etPLZ, etStrasse, etNr;
    Button btnErstellen;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verkaeufer_neu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);
        etName = (EditText) findViewById(R.id.et_Name);
        etInhaber = (EditText) findViewById(R.id.et_Inhaber);
        etOrt = (EditText) findViewById(R.id.et_Ort);
        etPLZ = (EditText) findViewById(R.id.et_PLZ);
        etStrasse = (EditText) findViewById(R.id.et_Strasse);
        etNr = (EditText) findViewById(R.id.et_HausNr);

        btnErstellen = (Button) findViewById(R.id.btn_Erstellen);

        btnErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pName = etName.getText().toString();
                String pInhaber = etInhaber.getText().toString();
                String pOrt = etOrt.getText().toString();
                String pPLZ = etPLZ.getText().toString();
                String pStrasse = etStrasse.getText().toString();
                String pNr = etNr.getText().toString();

                if(pName.length() != 0 && pInhaber.length() != 0 && pOrt.length() != 0 && pPLZ.length() != 0 && pStrasse.length() != 0 && pNr.length() != 0){
                    AddData(pName,pInhaber,pOrt,pPLZ,pStrasse,pNr);

                    etName.setText("Name");
                    etInhaber.setText("Inhaber");
                    etOrt.setText("Ort");
                    etPLZ.setText("PLZ");
                    etStrasse.setText("Strasse");
                    etNr.setText("HausNummer");
                }else {
                    Toast.makeText(verkaeufer_neu.this, "Es müssen alle Fälder ausgefüllt sein", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void AddData(String pName, String pInhaber, String pOrt, String pPLZ, String pStrasse, String pNr){
        boolean insertData = dataBaseHelper.addVerkaeufer(pName, pInhaber, pOrt, pStrasse, pPLZ, pNr);

        if(insertData){
            Toast.makeText(this, "Eintrag erfolgreich angelegt", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Fehler beim anlegen des Eintrags", Toast.LENGTH_LONG).show();
        }
    }
}
