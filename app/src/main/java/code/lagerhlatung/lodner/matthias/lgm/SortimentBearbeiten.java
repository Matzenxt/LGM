package code.lagerhlatung.lodner.matthias.lgm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SortimentBearbeiten extends AppCompatActivity {

    private Sortiment sortiment;
    private ArrayList<ProduktSortimment> produktSortimmentArrayList;

    private DataBaseHelper dataBaseHelper;

    private Cursor cursor;
    private Spinner spinner;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortiment_bearbeiten);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sortiment = (Sortiment) getIntent().getSerializableExtra("Sortiment");

        dataBaseHelper = new DataBaseHelper(this);
        cursor = dataBaseHelper.getVerkaeuferListContent();

        editText = (EditText) findViewById(R.id.et_Sortiment_Bearbeitung);
        spinner = (Spinner) findViewById(R.id.SP_Sortiment_Bearbeiten);

        String[] from = new String[] {"Namen"};
        int[] to = new int[] {android.R.id.text1};

        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, dataBaseHelper.getVerkaeuferListContent(), from, to, 0);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mAdapter);

        spinner.setSelection(sortiment.getVNr() - 1);
        editText.setText(sortiment.getBeschreibung());


        produktSortimmentArrayList = new ArrayList<>();

        Cursor ps = dataBaseHelper.getProduktSortimentContent(sortiment.getSNr());

        if (ps.getCount() == 0) {
            Toast.makeText(this, "DB leer", Toast.LENGTH_LONG).show();
        } else {
            Cursor data = null;
            int i = 0;
            while (ps.moveToNext()) {
                data = dataBaseHelper.getProdukt(ps.getInt(2));
                data.moveToFirst();

                produktSortimmentArrayList.add(i, new ProduktSortimment(ps.getInt(0), ps.getInt(2), ps.getInt(3), new Produkt(data.getInt(0), data.getString(1), data.getInt(2), data.getString(3))));

                i++;
            }

            MyAdapterSortimentBearbeiten myAdapterSortimentBearbeiten = new MyAdapterSortimentBearbeiten(this, produktSortimmentArrayList);
            ListView listView = (ListView) findViewById(R.id.LV_Sortiment_Bearbeitung);

            listView.setAdapter(myAdapterSortimentBearbeiten);
            listView.setOnItemClickListener((adapterView, view, i1, l) -> {
                startActivity(new Intent(getApplicationContext(), SortimentProduktBearbeiten.class).putExtra("PSNr", produktSortimmentArrayList.get(i1)));
            });
            data.close();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_bestell_neu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.option_bestellung_add) {
            startActivity(new Intent(getApplicationContext(), ProduktAddToSortiment.class).putExtra("SNr", sortiment));

        } else if (id == R.id.option_bestellung_scan) {
            Toast.makeText(this, "!!! Funktion nicht verfügbar !!!", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(getApplicationContext(), Bestellung_Neu_Add_Produkt.class).putExtra("SNr", sortiment));

        } else if (id == R.id.option_bestellung_save) {
            cursor.moveToPosition(spinner.getSelectedItemPosition());
            if (dataBaseHelper.updateSortiment(sortiment.getSNr(), cursor.getInt(0), String.valueOf(editText.getText()))) {
                Toast.makeText(this, "Erfolgreich geändert", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Fehler beim ändern", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.option_bestellung_export) {

            String name, ort;
            Cursor data = dataBaseHelper.getVerkaeufer(sortiment.getVNr());
            data.moveToFirst();
            name = data.getString(1);
            ort = data.getString(3);


            Excel_IO excel_io = new Excel_IO();
            excel_io.ExportSortiment(dataBaseHelper, sortiment.getSNr());

            data.close();
            dataBaseHelper.close();


            //File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "test.xls");
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("vnd.android.cursor.dir/email");
            emailIntent.setType("application/x-vcard");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, "Matthias.Lodner@web.de");
            //emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + filelocation.getAbsolutePath()));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Neuer Auftrag für Verkäufer: " + name + " im Ort: " + ort);
            startActivity(Intent.createChooser(emailIntent, "Email senden..."));


        } else if (id == R.id.option_bestellung_loeschen) {
            dataBaseHelper.deletSortiment(sortiment.getSNr(), sortiment.getVNr(), sortiment.getBeschreibung());
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
