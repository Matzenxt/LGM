package code.lagerhlatung.lodner.matthias.lgm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class verkaeufer_edit extends AppCompatActivity {

    private EditText etName;
    private EditText etInhaber;
    private EditText etOrt;
    private EditText etPLZ;
    private EditText etStrasse;
    private EditText etNr;
    private DataBaseHelper dataBaseHelper;
    private Verkaeufer verkaeufer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verkaeufer_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);
        etName = (EditText) findViewById(R.id.et_Name);
        etInhaber = (EditText) findViewById(R.id.et_Inhaber);
        etOrt = (EditText) findViewById(R.id.et_Ort);
        etPLZ = (EditText) findViewById(R.id.et_PLZ);
        etStrasse = (EditText) findViewById(R.id.et_Strasse);
        etNr = (EditText) findViewById(R.id.et_HausNr);


        verkaeufer = (Verkaeufer) getIntent().getSerializableExtra("Verkaeufer");

        etName.setText(verkaeufer.getName());
        etInhaber.setText(verkaeufer.getInhaber());
        etOrt.setText(verkaeufer.getOrt());
        etPLZ.setText(verkaeufer.getPLZ());
        etStrasse.setText(verkaeufer.getStrasse());
        etNr.setText(verkaeufer.getNr());


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
            String pName = etName.getText().toString();
            String pInhaber = etInhaber.getText().toString();
            String pOrt = etOrt.getText().toString();
            String pPLZ = etPLZ.getText().toString();
            String pStrasse = etStrasse.getText().toString();
            String pNr = etNr.getText().toString();

            if (pName.length() != 0 && pInhaber.length() != 0 && pOrt.length() != 0 && pPLZ.length() != 0 && pStrasse.length() != 0 && pNr.length() != 0) {
                if (dataBaseHelper.updateVerkaeufer(verkaeufer.getVNr(), pName, pInhaber, pOrt, pStrasse, pPLZ, pNr)) {
                    Toast.makeText(this, "Eintrag erfolgreich geändert", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Fehler beim ändern des Eintrags", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(verkaeufer_edit.this, "Es müssen alle Fälder ausgefüllt sein", Toast.LENGTH_LONG).show();
            }

        }else if (id == R.id.option_bearbeiten_pro_ver_delete) {
            dataBaseHelper.deletVerkaeufer(verkaeufer.getVNr(), verkaeufer.getName(), verkaeufer.getInhaber(), verkaeufer.getOrt(), verkaeufer.getPLZ(), verkaeufer.getStrasse(), verkaeufer.getNr());
        }

        dataBaseHelper.close();
        onNavigateUp();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        onNavigateUp();
        super.onBackPressed();
    }
}
