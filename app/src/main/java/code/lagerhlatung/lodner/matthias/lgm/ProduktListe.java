package code.lagerhlatung.lodner.matthias.lgm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ProduktListe extends AppCompatActivity {

    private ArrayList<Produkt> ProduktArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produkt_liste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        ProduktArrayList = new ArrayList<>();
        Cursor data = dataBaseHelper.getProduktListContent();

        if (data.getCount() == 0) {
            Toast.makeText(this, "DB leer", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                ProduktArrayList.add(i, new Produkt(data.getInt(0), data.getString(1), data.getInt(2), data.getString(3)));
                i++;
            }

            ListView listView = (ListView) findViewById(R.id.LV_Produkt);

            MyAdapterProduktListe myAdapterProduktListe = new MyAdapterProduktListe(this, ProduktArrayList);
            listView.setAdapter(myAdapterProduktListe);


            listView.setOnItemClickListener((adapterView, view, i1, l) -> {
                startActivity(new Intent(getApplicationContext(), ProduktBearbeiten.class).putExtra("PNr", ProduktArrayList.get(i1)));
            });
        }
        dataBaseHelper.close();
        data.close();
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
            startActivity(new Intent(getApplicationContext(), Produkt_neu.class));
        } else if (id == R.id.option_produkte_liste_suchen) {
            startActivity(new Intent(getApplicationContext(), ProduktSuche.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        onNavigateUp();
        super.onBackPressed();
    }
}
