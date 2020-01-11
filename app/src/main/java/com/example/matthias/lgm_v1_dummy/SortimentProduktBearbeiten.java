package com.example.matthias.lgm_v1_dummy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class SortimentProduktBearbeiten extends AppCompatActivity {

    ProduktSortimment produktSortimment;
    TextView textViewN, textViewM;
    EditText editText;

    int bestand;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestellung_produkte_bearbeiten);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        produktSortimment = (ProduktSortimment) getIntent().getSerializableExtra("PSNr");

        dataBaseHelper = new DataBaseHelper(this);
        textViewN = (TextView)findViewById(R.id.tv_ProduktName);
        textViewM = (TextView)findViewById(R.id.tv_ProduktMenge);
        editText = (EditText)findViewById(R.id.et_produkt_menge);

        textViewN.setText(produktSortimment.getProdukt().getBezeichnung());
        textViewM.setText(String.valueOf(produktSortimment.getProdukt().getFuellmenge()));
        editText.setText(String.valueOf(produktSortimment.getSollBestand()));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_produkt_bearbeiten, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.option_produkt_bearbeiten_save) {
            bestand = Integer.parseInt(String.valueOf(editText.getText()));
            dataBaseHelper.updateProduktSortiment(produktSortimment.getPSNr(), produktSortimment.getSNr(), produktSortimment.getProdukt().getPNr(), bestand);
            finish();
        } else if (id == R.id.option_produkt_bearbeiten_delete) {
            dataBaseHelper.deletProduktSortiment(produktSortimment.getPSNr(), produktSortimment.getSNr(), produktSortimment.getProdukt().getPNr(), String.valueOf(produktSortimment.getSollBestand()));
            finish();
        }else if(id == R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
