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

public class Auftraege_Liste extends AppCompatActivity {

    private ArrayList<Bestellung> BestellArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sortiment_liste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        BestellArrayList = new ArrayList<>();
        Cursor data = dataBaseHelper.getBestellListContent();

        if (data.getCount() == 0) {
            Toast.makeText(this, "Kein Eintrag", Toast.LENGTH_LONG).show();
        } else {

            int i = 0;
            while (data.moveToNext()) {
                BestellArrayList.add(i, new Bestellung(data.getInt(0), data.getInt(1), data.getString(2)));
                i++;
            }

            MyAdapterAuftraegeListe adapterAuftraegeListe = new MyAdapterAuftraegeListe(this, BestellArrayList, dataBaseHelper);
            ListView listView = (ListView) findViewById(R.id.LV_Sortiment);
            listView.setAdapter(adapterAuftraegeListe);

            listView.setOnItemClickListener((adapterView, view, i1, l) -> {
                startActivity(new Intent(getApplicationContext(), Bestellung_Bearbeiten.class).putExtra("Auftraege", BestellArrayList.get(i1)));

                //TODO: reacreate weider einfügen
                //recreate();
            });
        }
        data.close();
        dataBaseHelper.close();
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
            startActivity(new Intent(getApplicationContext(), Bestellung_Neu.class));
        } else if (id == R.id.option_auftraege_liste_suchen) {
            Toast.makeText(Auftraege_Liste.this, "Es müssen alle Fälder ausgefüllt sein", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        onNavigateUp();
        super.onBackPressed();
    }
}
