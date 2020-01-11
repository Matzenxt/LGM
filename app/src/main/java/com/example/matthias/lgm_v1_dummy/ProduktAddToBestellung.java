package com.example.matthias.lgm_v1_dummy;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProduktAddToBestellung extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    ListView listView;
    EditText editText, suche;
    ArrayList<Produkt> produktArrayList;
    Produkt produkt;
    Bestellung bestellung;
    String sucheText = "";
    int anzahl = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produkt_addt_to);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bestellung = (Bestellung) getIntent().getSerializableExtra("BNr");


        dataBaseHelper = new DataBaseHelper(this);


        produktArrayList = new ArrayList<>();

        listView = (ListView)findViewById(R.id.lv_addto);
        editText = (EditText)findViewById(R.id.et_addto_Anzahl);
        suche = (EditText)findViewById(R.id.et_addtoSuche);
        sucheText = String.valueOf(editText.getText());


        Cursor data = dataBaseHelper.getProduktByName(sucheText);
        int numRows = data.getCount();

        if(numRows == 0){
            Toast.makeText(ProduktAddToBestellung.this, "Kein Eintrag zum Suchtext gefunden", Toast.LENGTH_LONG).show();
        }else{

            int i = 0;
            while (data.moveToNext()){
                produkt = new Produkt(data.getInt(0), data.getString(1), data.getInt(2), data.getString(3));
                produktArrayList.add(i, produkt);
                i++;
            }
            MyAdapterProduktListe myAdapterProduktListe = new MyAdapterProduktListe(ProduktAddToBestellung.this, R.layout.produktliste_row_layout, produktArrayList);

            listView.setAdapter(myAdapterProduktListe);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    produkt = produktArrayList.get(i);
                    anzahl = Integer.valueOf(editText.getText().toString());

                    boolean b = dataBaseHelper.addProduktBestellung(bestellung.getBNr() - 1, produkt.getPNr(), anzahl);
                    if(b){
                        Toast.makeText(ProduktAddToBestellung.this, "Erfolgreich hinzugefügt", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ProduktAddToBestellung.this, "Fehler beim hinzufügen", Toast.LENGTH_LONG).show();
                    }
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_suche, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.option_suchen) {
            produktArrayList = new ArrayList<>();

            //editText = (EditText)findViewById(R.id.et_name_suche);
            sucheText = String.valueOf(suche.getText());

            Cursor data = dataBaseHelper.getProduktByName(sucheText);
            int numRows = data.getCount();

            if(numRows == 0){
                Toast.makeText(ProduktAddToBestellung.this, "Kein Eintrag zum Suchtext gefunden", Toast.LENGTH_LONG).show();
            }else{

                int i = 0;
                while (data.moveToNext()){
                    produkt = new Produkt(data.getInt(0), data.getString(1), data.getInt(2), data.getString(3));
                    produktArrayList.add(i, produkt);
                    i++;
                }
                MyAdapterProduktListe myAdapterProduktListe = new MyAdapterProduktListe(ProduktAddToBestellung.this, R.layout.produktliste_row_layout, produktArrayList);

                listView.setAdapter(myAdapterProduktListe);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        produkt = produktArrayList.get(i);
                        anzahl = Integer.valueOf(editText.getText().toString());

                        boolean b = dataBaseHelper.addProduktBestellung(bestellung.getBNr() - 1, produkt.getPNr(), anzahl);
                        if(b){
                            Toast.makeText(ProduktAddToBestellung.this, "Erfolgreich hinzugefügt", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ProduktAddToBestellung.this, "Fehler beim hinzufügen", Toast.LENGTH_LONG).show();
                        }
                        finish();
                    }
                });
            }
        }


        return super.onOptionsItemSelected(item);
    }
}
