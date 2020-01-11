package com.example.matthias.lgm_v1_dummy;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProduktAddToSortiment extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    ListView listView;
    EditText editText;
    ArrayList<Produkt> ProduktArrayList;
    Produkt produkt;
    Sortiment sortiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produkt_addt_to);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sortiment = (Sortiment) getIntent().getSerializableExtra("SNr");

        dataBaseHelper = new DataBaseHelper(this);
        editText = (EditText) findViewById(R.id.et_addto_Anzahl);

        ProduktArrayList = new ArrayList<>();
        Cursor data = dataBaseHelper.getProduktListContent();
        int numRows = data.getCount();

        if(numRows == 0){
            Toast.makeText(this, "DB leer", Toast.LENGTH_LONG).show();
        }else{

            int i = 0;
            while (data.moveToNext()){
                produkt = new Produkt(data.getInt(0), data.getString(1), data.getInt(2), data.getString(3));
                ProduktArrayList.add(i, produkt);
                i++;
            }
            MyAdapterProduktListe myAdapterProduktListe = new MyAdapterProduktListe(this, R.layout.produktliste_row_layout, ProduktArrayList);

            listView = (ListView) findViewById(R.id.lv_addto);
            listView.setAdapter(myAdapterProduktListe);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    produkt = ProduktArrayList.get(i);
                    int anzahl = Integer.valueOf(editText.getText().toString());

                    boolean b = dataBaseHelper.addProduktSortiment(sortiment.getSNr() - 1, produkt.getPNr(), anzahl);
                    if(b){
                        Toast.makeText(ProduktAddToSortiment.this, "Erfolgreich hinzugefügt", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ProduktAddToSortiment.this, "Fehler beim hinzufügen", Toast.LENGTH_LONG).show();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
