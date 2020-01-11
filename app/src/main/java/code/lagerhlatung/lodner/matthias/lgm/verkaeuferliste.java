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

public class verkaeuferliste extends AppCompatActivity {

    private ArrayList<Verkaeufer> verkaeuferArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_verkaeuferliste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        verkaeuferArrayList = new ArrayList<>();
        Cursor data = dataBaseHelper.getVerkaeuferListContent();
        int numRows = data.getCount();

        if (numRows == 0) {
            Toast.makeText(this, "Kein Eintrag", Toast.LENGTH_LONG).show();
        } else {

            int i = 0;
            while (data.moveToNext()) {
                verkaeuferArrayList.add(i, new Verkaeufer(data.getInt(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6)));
                i++;
            }
            MyAdapterVerkaeuferListe adapterVerkaeuferListe = new MyAdapterVerkaeuferListe(this, verkaeuferArrayList);
            ListView listView = (ListView) findViewById(R.id.LV_Verkaeufer);
            listView.setAdapter(adapterVerkaeuferListe);

            listView.setOnItemClickListener((adapterView, view, i1, l) -> {
                startActivity(new Intent(getApplicationContext(), verkaeufer_edit.class).putExtra("Verkaeufer", verkaeuferArrayList.get(i1)));
            });
        }
        dataBaseHelper.close();
        data.close();
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
            startActivity(new Intent(getApplicationContext(), verkaeufer_neu.class));
        } else if (id == R.id.option_verkaeufer_liste_suchen) {
            Toast.makeText(verkaeuferliste.this, "!!!  Funktion noch nicht erstellt   !!!", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        onNavigateUp();
        super.onBackPressed();
    }
}
