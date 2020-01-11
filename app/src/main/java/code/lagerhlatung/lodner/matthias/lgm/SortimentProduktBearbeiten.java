package code.lagerhlatung.lodner.matthias.lgm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class SortimentProduktBearbeiten extends AppCompatActivity {

    private ProduktSortimment produktSortimment;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestellung_produkte_bearbeiten);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        produktSortimment = (ProduktSortimment) getIntent().getSerializableExtra("PSNr");

        TextView textViewN = (TextView) findViewById(R.id.tv_ProduktName);
        TextView textViewM = (TextView) findViewById(R.id.tv_ProduktMenge);
        editText = (EditText) findViewById(R.id.et_produkt_menge);

        textViewN.setText(produktSortimment.getProdukt().getBezeichnung());
        textViewM.setText(String.valueOf(produktSortimment.getProdukt().getFuellmenge()));
        editText.setText(String.valueOf(produktSortimment.getSollBestand()));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_bearbeiten_pro_ver, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.option_bearbeiten_pro_ver_save) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
            dataBaseHelper.updateProduktSortiment(produktSortimment.getPSNr(), Integer.parseInt(String.valueOf(editText.getText())));
            dataBaseHelper.close();
            finish();
        } else if (id == R.id.option_bearbeiten_pro_ver_delete) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
            dataBaseHelper.deletProduktSortiment(produktSortimment.getPSNr());
            dataBaseHelper.close();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }
}