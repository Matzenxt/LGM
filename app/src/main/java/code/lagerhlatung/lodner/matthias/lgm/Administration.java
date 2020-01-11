package code.lagerhlatung.lodner.matthias.lgm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

public class Administration extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);


        Button bp = (Button) findViewById(R.id.btn_admin_back_produkte);
        Button lp = (Button) findViewById(R.id.btn_admin_lade_produkte);

        Button bv = (Button) findViewById(R.id.btn_admin_back_verkaeufer);
        Button lv = (Button) findViewById(R.id.btn_admin_laden_verkaeufer);

        Button bs = (Button) findViewById(R.id.btn_admin_backup_sortimente);
        Button ls = (Button) findViewById(R.id.btn_admin_lade_sortimente);

        Button bb = (Button) findViewById(R.id.btn_admin_back_bestellungen);
        Button lb = (Button) findViewById(R.id.btn_admin_laden_bestellungen);

        Button ba = (Button) findViewById(R.id.btn_admin_back_alles);


        lp.setOnClickListener(view -> {
            Toast.makeText(Administration.this, "Starte Kopiervorgang", Toast.LENGTH_LONG).show();
            Excel_IO excel_io = new Excel_IO();
            excel_io.ImportProdukte(dataBaseHelper);
            Toast.makeText(Administration.this, "Fertig", Toast.LENGTH_LONG).show();
        });

        bp.setOnClickListener(view -> {
            Excel_IO excel_io = new Excel_IO();
            Toast.makeText(Administration.this, "Starte Kopiervorgang", Toast.LENGTH_LONG).show();
            excel_io.ExportTableProdukte(dataBaseHelper);
            Toast.makeText(Administration.this, "Fertig", Toast.LENGTH_LONG).show();
        });


        bv.setOnClickListener(view -> {
            Excel_IO excel_io = new Excel_IO();
            Toast.makeText(Administration.this, "Starte Kopiervorgang", Toast.LENGTH_LONG).show();
            excel_io.ExportTableVerkaeufer(dataBaseHelper);
            Toast.makeText(Administration.this, "Fertig", Toast.LENGTH_LONG).show();
        });


        bs.setOnClickListener(view -> {
            Excel_IO excel_io = new Excel_IO();
            Toast.makeText(Administration.this, "Starte Kopiervorgang", Toast.LENGTH_LONG).show();
            excel_io.ExportTableSortimente(dataBaseHelper);
            Toast.makeText(Administration.this, "Fertig", Toast.LENGTH_LONG).show();
        });


        bb.setOnClickListener(view -> {
            Toast.makeText(Administration.this, "Starte Kopiervorgang", Toast.LENGTH_LONG).show();
            Excel_IO excel_io = new Excel_IO();
            excel_io.ExportTableBestellungen(dataBaseHelper);
            Toast.makeText(Administration.this, "Fertig", Toast.LENGTH_LONG).show();
        });


        ba.setOnClickListener(view -> {
            Excel_IO excel_io = new Excel_IO();
            Toast.makeText(Administration.this, "Starte Kopiervorgang", Toast.LENGTH_LONG).show();
            excel_io.ExportAll(dataBaseHelper);
            Toast.makeText(Administration.this, "Fertig", Toast.LENGTH_LONG).show();
        });

        dataBaseHelper.close();
    }
}
