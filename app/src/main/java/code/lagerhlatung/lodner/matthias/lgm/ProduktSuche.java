package code.lagerhlatung.lodner.matthias.lgm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProduktSuche extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;
    private ListView listView;
    private ArrayList<Produkt> produktArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produkt_suche);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);
        listView = (ListView) findViewById(R.id.lv_suche);

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
        if (id == R.id.option_suchen) {
            produktArrayList = new ArrayList<>();

            EditText editText = (EditText) findViewById(R.id.et_name_suche);

            Cursor data = dataBaseHelper.getProduktByName(String.valueOf(editText.getText()));

            if (data.getCount() == 0) {
                Toast.makeText(ProduktSuche.this, "Kein Eintrag zum Suchtext gefunden", Toast.LENGTH_LONG).show();
            } else {

                int i = 0;
                while (data.moveToNext()) {
                    produktArrayList.add(i, new Produkt(data.getInt(0), data.getString(1), data.getInt(2), data.getString(3)));
                    i++;
                }

                MyAdapterProduktListe myAdapterProduktListe = new MyAdapterProduktListe(ProduktSuche.this, produktArrayList);
                listView.setAdapter(myAdapterProduktListe);

                listView.setOnItemClickListener((adapterView, view, i1, l) -> {
                    startActivity(new Intent(getApplicationContext(), ProduktBearbeiten.class).putExtra("PNr", produktArrayList.get(i1)));
                });
            }
            data.close();
            dataBaseHelper.close();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        onNavigateUp();
        super.onBackPressed();
    }
}
