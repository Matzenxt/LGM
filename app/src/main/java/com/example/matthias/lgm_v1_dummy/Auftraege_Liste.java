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

public class Auftraege_Liste extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    ListView listView;
    ArrayList<Bestellung> BestellArrayList;
    Bestellung bestellung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sortiment_liste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);

        BestellArrayList = new ArrayList<>();
        Cursor data = dataBaseHelper.getBestellListContent();
        int numRows = data.getCount();

        if(numRows == 0){
            Toast.makeText(this, "Kein Eintrag", Toast.LENGTH_LONG).show();
        }else{

            int i = 0;
            while (data.moveToNext()){
                bestellung = new Bestellung(data.getInt(0), data.getInt(1), data.getString(2));
                BestellArrayList.add(i, bestellung);
                i++;
            }
            MyAdapterAuftraegeListe adapterAuftraegeListe = new MyAdapterAuftraegeListe(this, R.layout.sortiment_row_layout, BestellArrayList, dataBaseHelper);
            listView = (ListView) findViewById(R.id.LV_Sortiment);
            listView.setAdapter(adapterAuftraegeListe);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    bestellung = BestellArrayList.get(i);

                    String test = "Du hast auf gelickt: " + String.valueOf(adapterView.getItemAtPosition(i));

                    Intent intent = new Intent(getApplicationContext(), Bestellung_Bearbeiten.class);
                    intent.putExtra("Auftraege", bestellung);
                    startActivity(intent);

                    recreate();
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_auftraege_liste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.option_auftraege_liste_neu) {
            Intent intent = new Intent(getApplicationContext(), Bestellung_Neu.class);
            startActivity(intent);
        }else if(id == R.id.option_auftraege_liste_suchen){
            Toast.makeText(Auftraege_Liste.this, "Es müssen alle Fälder ausgefüllt sein", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
