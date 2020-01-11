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

public class verkaeuferliste extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    ListView listView;
    ArrayList<Verkaeufer> verkaeuferArrayList;
    Verkaeufer verkaeufer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_verkaeuferliste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);

        verkaeuferArrayList = new ArrayList<>();
        Cursor data = dataBaseHelper.getVerkaeuferListContent();
        int numRows = data.getCount();

        if(numRows == 0){
            Toast.makeText(this, "Kein Eintrag", Toast.LENGTH_LONG).show();
        }else{

            int i = 0;
            while (data.moveToNext()){
                verkaeufer = new Verkaeufer(data.getInt(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6));
                verkaeuferArrayList.add(i, verkaeufer);
                i++;
            }
            MyAdapterVerkaeuferListe adapterVerkaeuferListe = new MyAdapterVerkaeuferListe(this, R.layout.verkaeufer_row_layout, verkaeuferArrayList);
            listView = (ListView) findViewById(R.id.LV_Verkaeufer);
            listView.setAdapter(adapterVerkaeuferListe);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    verkaeufer = verkaeuferArrayList.get(i);

                    String test = "Du hast auf gelickt: " + String.valueOf(adapterView.getItemAtPosition(i));

                    Toast.makeText(verkaeuferliste.this, test + "\n Item ID: " + verkaeufer.getVNr() + verkaeufer.getName(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), verkaeufer_edit.class);
                    intent.putExtra("Verkaeufer", verkaeufer);
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
            Intent intent = new Intent(getApplicationContext(), verkaeufer_neu.class);
            startActivity(intent);
        }else if(id == R.id.option_verkaeufer_liste_suchen){
            Toast.makeText(verkaeuferliste.this, "Es müssen alle Fälder ausgefüllt sein", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
