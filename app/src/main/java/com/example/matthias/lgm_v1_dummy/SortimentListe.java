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

public class SortimentListe extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    ListView listView;
    ArrayList<Sortiment> SortimentArrayList;
    Sortiment sortiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sortiment_liste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);

        SortimentArrayList = new ArrayList<>();
        Cursor data = dataBaseHelper.getSortimentListContent();
        int numRows = data.getCount();

        if(numRows == 0){
            Toast.makeText(SortimentListe.this, "Kein Eintrag", Toast.LENGTH_LONG).show();
        }else{

            int i = 0;
            while (data.moveToNext()){
                sortiment = new Sortiment(data.getInt(0), data.getInt(1), data.getString(2), dataBaseHelper);
                SortimentArrayList.add(i, sortiment);
                i++;
            }
            MyAdapterSortimentA adapterSortimentListe = new MyAdapterSortimentA(this, R.layout.sortiment_row_layout, SortimentArrayList, dataBaseHelper);
            listView = (ListView) findViewById(R.id.LV_Sortiment);
            listView.setAdapter(adapterSortimentListe);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    sortiment = SortimentArrayList.get(i);

                    String test = "Du hast auf gelickt: " + String.valueOf(adapterView.getItemAtPosition(i));

                    Toast.makeText(SortimentListe.this, "Item ID: " + sortiment.getSNr() + " postion: " + i, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), SortimentBearbeiten.class);
                    intent.putExtra("Sortiment", sortiment);
                    startActivity(intent);

                    recreate();
                }
            });
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_verkaeufer_liste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.option_verkaeufer_liste_neu) {
            Intent intent = new Intent(getApplicationContext(), SortimentNeu.class);
            startActivity(intent);
        }else if(id == R.id.option_verkaeufer_liste_suchen){
            Toast.makeText(SortimentListe.this, "Es müssen alle Fälder ausgefüllt sein", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
