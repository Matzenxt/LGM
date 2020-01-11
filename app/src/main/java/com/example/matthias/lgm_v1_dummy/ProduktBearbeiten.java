package com.example.matthias.lgm_v1_dummy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ProduktBearbeiten extends AppCompatActivity {

    Produkt produkt;
    EditText et_name, et_menge, et_barcode;
    DataBaseHelper dataBaseHelper;

    String name, menge, barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produkt_bearbeiten);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        produkt = (Produkt) getIntent().getSerializableExtra("PNr");

        dataBaseHelper=new DataBaseHelper(this);

        et_name = (EditText)findViewById(R.id.et_produkt_bearbeiten_name);
        et_menge = (EditText)findViewById(R.id.et_produkt_bearbeiten_menge);
        et_barcode = (EditText)findViewById(R.id.et_produkt_bearbeiten_barcode);

        et_name.setText(produkt.getBezeichnung());
        et_menge.setText(String.valueOf(produkt.getFuellmenge()));
        et_barcode.setText(produkt.getBarcode());
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
            name = String.valueOf(et_name.getText());
            menge = String.valueOf(et_menge.getText());
            barcode =String.valueOf(et_barcode.getText());

            dataBaseHelper.updateProdukt(produkt.getPNr(), name, menge, barcode);
            finish();
        } else if (id == R.id.option_produkt_bearbeiten_delete) {
            dataBaseHelper.deletProdukt(produkt.getPNr(), produkt.getBezeichnung(), produkt.getFuellmenge(), produkt.getBarcode());
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
