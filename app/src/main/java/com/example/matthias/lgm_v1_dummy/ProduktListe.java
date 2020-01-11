package com.example.matthias.lgm_v1_dummy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class ProduktListe extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    ListView listView;
    ArrayList<Produkt> ProduktArrayList;
    Produkt produkt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produkt_liste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dataBaseHelper = new DataBaseHelper(this);

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

            listView = (ListView) findViewById(R.id.LV_Produkt);

            MyAdapterProduktListe myAdapterProduktListe = new MyAdapterProduktListe(this, R.layout.produktliste_row_layout, ProduktArrayList);
            listView.setAdapter(myAdapterProduktListe);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    produkt = ProduktArrayList.get(i);

                    Intent intent = new Intent(getApplicationContext(), ProduktBearbeiten.class);
                    intent.putExtra("PNr", produkt);
                    startActivity(intent);

                    recreate();
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_produkte_liste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.option_produkte_liste_neu) {
            Intent intent = new Intent(getApplicationContext(), Produkt_neu.class);
            startActivity(intent);
        }else if(id == R.id.option_produkte_liste_suchen){
            Intent intent = new Intent(getApplicationContext(), ProduktSuche.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
