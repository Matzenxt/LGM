package code.lagerhlatung.lodner.matthias.lgm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class verkaeufer_neu extends AppCompatActivity {

    private EditText etName;
    private EditText etInhaber;
    private EditText etOrt;
    private EditText etPLZ;
    private EditText etStrasse;
    private EditText etNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verkaeufer_neu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etName = (EditText) findViewById(R.id.et_Name);
        etInhaber = (EditText) findViewById(R.id.et_Inhaber);
        etOrt = (EditText) findViewById(R.id.et_Ort);
        etPLZ = (EditText) findViewById(R.id.et_PLZ);
        etStrasse = (EditText) findViewById(R.id.et_Strasse);
        etNr = (EditText) findViewById(R.id.et_HausNr);
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
            String pInhaber = etInhaber.getText().toString();
            String pOrt = etOrt.getText().toString();
            String pPLZ = etPLZ.getText().toString();
            String pStrasse = etStrasse.getText().toString();
            String pNr = etNr.getText().toString();

            if (pName.length() != 0 && pInhaber.length() != 0 && pOrt.length() != 0 && pPLZ.length() != 0 && pStrasse.length() != 0 && pNr.length() != 0) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

                if (dataBaseHelper.addVerkaeufer(pName, pInhaber, pOrt, pStrasse, pPLZ, pNr)) {
                    Toast.makeText(this, "Eintrag erfolgreich angelegt", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Fehler beim anlegen des Eintrags", Toast.LENGTH_LONG).show();
                }
                dataBaseHelper.close();
                onNavigateUp();
            } else {
                Toast.makeText(verkaeufer_neu.this, "Es müssen alle Fälder ausgefüllt sein", Toast.LENGTH_LONG).show();
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
