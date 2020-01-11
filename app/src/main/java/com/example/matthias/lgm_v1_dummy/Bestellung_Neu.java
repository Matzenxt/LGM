package com.example.matthias.lgm_v1_dummy;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Bestellung_Neu extends AppCompatActivity {

    Produkt produkt;

    DataBaseHelper dataBaseHelper;

    Cursor cursor;
    Spinner spinner;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortiment_bearbeiten);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        dataBaseHelper = new DataBaseHelper(this);

        cursor= dataBaseHelper.getVerkaeuferListContent();


        editText = (EditText)findViewById(R.id.et_Sortiment_Bearbeitung);

        spinner = (Spinner)findViewById(R.id.SP_Sortiment_Bearbeiten);

        startManagingCursor(cursor);
        String[] from = new String[]{"Namen"};
        int[] to = new int[]{android.R.id.text1};
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, from, to);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mAdapter);
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

        }else if(id == R.id.option_bestellung_save){
            cursor.moveToPosition(spinner.getSelectedItemPosition());
            boolean bla = dataBaseHelper.addBestellung(cursor.getInt(0), String.valueOf(editText.getText()));
            if(bla){
                Toast.makeText(Bestellung_Neu.this, "Erfolgreich angelget", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Bestellung_Neu.this, "Fehler beim anglegen", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}