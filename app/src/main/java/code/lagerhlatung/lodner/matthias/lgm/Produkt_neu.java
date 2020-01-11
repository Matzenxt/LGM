package code.lagerhlatung.lodner.matthias.lgm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Produkt_neu extends AppCompatActivity {

    private EditText etName;
    private EditText etFuellmenge;
    private EditText etBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produkt_neu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etName = (EditText) findViewById(R.id.et_Name);
        etFuellmenge = (EditText) findViewById(R.id.et_Fuellmenge);
        etBarcode = (EditText) findViewById(R.id.et_Barcode);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_neu_erstellen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.option_neu_erstellen_add) {

            String pName = etName.getText().toString();
            String pFuellmenge = etFuellmenge.getText().toString();
            String pBarcode = etBarcode.getText().toString();

            if (pName.length() != 0 && pFuellmenge.length() != 0 && pBarcode.length() != 0) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

                if (dataBaseHelper.addProdukt(pName, pFuellmenge, pBarcode)) {
                    Toast.makeText(this, "Eintrag erfolgreich angelegt", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Fehler beim anlegen des Eintrags", Toast.LENGTH_SHORT).show();
                }
                dataBaseHelper.close();
                onNavigateUp();

            } else {
                Toast.makeText(Produkt_neu.this, "Es müssen alle Fälder ausgefüllt sein", Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        onNavigateUp();
        super.onBackPressed();
    }
}
