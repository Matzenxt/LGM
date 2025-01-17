package code.lagerhlatung.lodner.matthias.lgm;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;

public class Bestellung_Neu_Add_Produkt extends AppCompatActivity {

    private final int RequestCameraPermissionID = 1001;
    private SurfaceView surfaceView;
    private TextView textView;
    private CameraSource cameraSource;
    private DataBaseHelper dataBaseHelper;
    private ListView listView;
    private ArrayList<Produkt> produktArrayList;
    private Bestellung bestellung;
    private int anzahl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestellung__neu__add__produkt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bestellung = (Bestellung) getIntent().getSerializableExtra("BNr");

        dataBaseHelper = new DataBaseHelper(Bestellung_Neu_Add_Produkt.this);

        listView = (ListView) findViewById(R.id.lv_suche);


        surfaceView = (SurfaceView) findViewById(R.id.SV);
        textView = (TextView) findViewById(R.id.tv_bestellung_add_scan);


        NumberPicker np = (NumberPicker) findViewById(R.id.np_bestellung_add_scan);

        np.setMinValue(0);
        np.setMaxValue(12);
        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener((picker, oldVal, newVal) -> anzahl = newVal);


        //TODO: Format überprüfen
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build();
        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(1500, 1500).setAutoFocusEnabled(true).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(Bestellung_Neu_Add_Produkt.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Bestellung_Neu_Add_Produkt.this, new String[] {android.Manifest.permission.CAMERA}, RequestCameraPermissionID);
                        return;
                    }
                    cameraSource.start(surfaceView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if (qrcodes.size() != 0) {
                    textView.post(() -> {
                        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(500);
                        String barcode = qrcodes.valueAt(0).displayValue;
                        textView.setText(barcode);


                        produktArrayList = new ArrayList<>();
                        Cursor data = dataBaseHelper.getProduktByBarcode(barcode);

                        if (data.getCount() == 0) {
                            Toast.makeText(Bestellung_Neu_Add_Produkt.this, "Kein Eintrag mit passemden Barcode\nOder Produkt bereits verwendet", Toast.LENGTH_LONG).show();
                        } else {

                            int i = 0;
                            while (data.moveToNext()) {
                                produktArrayList.add(i, new Produkt(data.getInt(0), data.getString(1), data.getInt(2), data.getString(3)));
                                i++;
                            }

                            MyAdapterProduktListe myAdapterProduktListe = new MyAdapterProduktListe(Bestellung_Neu_Add_Produkt.this, produktArrayList);
                            listView.setAdapter(myAdapterProduktListe);
                            listView.setOnItemClickListener((adapterView, view, i12, l) -> {
                                if (dataBaseHelper.addProduktBestellung(bestellung.getBNr() - 1, produktArrayList.get(i12).getPNr(), anzahl)) {
                                    Toast.makeText(Bestellung_Neu_Add_Produkt.this, "Erfolgreich hinzugefügt", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Bestellung_Neu_Add_Produkt.this, "Fehler beim hinzufügen", Toast.LENGTH_SHORT).show();
                                }
                                //TODO: ggf finish() entfernen um nicht ständig neu in activity zu müssen
                                //onNavigateUp();
                            });
                        }
                        data.close();
                        dataBaseHelper.close();
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if ((grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }


    @Override
    public void onBackPressed() {
        onNavigateUp();
        super.onBackPressed();
    }
}
