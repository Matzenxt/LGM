package com.example.matthias.lgm_v1_dummy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Administration extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    Button bp, lp, bv, lv, bs, ls, bb, lb, ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);


        bp = (Button)findViewById(R.id.btn_admin_back_produkte);
        lp = (Button)findViewById(R.id.btn_admin_lade_produkte);

        bv = (Button)findViewById(R.id.btn_admin_back_verkaeufer);
        lv = (Button)findViewById(R.id.btn_admin_laden_verkaeufer);

        bs = (Button)findViewById(R.id.btn_admin_backup_sortimente);
        ls = (Button)findViewById(R.id.btn_admin_lade_sortimente);

        bb = (Button)findViewById(R.id.btn_admin_back_bestellungen);
        lb = (Button)findViewById(R.id.btn_admin_laden_bestellungen);

        ba = (Button)findViewById(R.id.btn_admin_back_alles);



        lp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Excel_IO excel_io = new Excel_IO();

                excel_io.ImportProdukte(dataBaseHelper);
                Toast.makeText(Administration.this, "Fertig", Toast.LENGTH_LONG).show();


            }
        });



    }

}
