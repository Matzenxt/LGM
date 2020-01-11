package code.lagerhlatung.lodner.matthias.lgm;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class MyAdapterAuftraegeListe extends ArrayAdapter<Bestellung> {


    private final LayoutInflater inflater;
    private final ArrayList<Bestellung> bestellungs;
    private final int ViewResourceID;
    private final DataBaseHelper dataBaseHelper;


    public MyAdapterAuftraegeListe(@NonNull Context context, ArrayList<Bestellung> bestellung, DataBaseHelper pDataBaseHelper) {
        super(context, R.layout.sortiment_row_layout, bestellung);

        this.bestellungs = bestellung;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = R.layout.sortiment_row_layout;
        dataBaseHelper = pDataBaseHelper;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        Bestellung bestellung = bestellungs.get(position);

        if (bestellung != null) {
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Name);
            TextView VVerkaeufer = (TextView) convertView.findViewById(R.id.tV_Verkaeufer);
            TextView VAnzahl = (TextView) convertView.findViewById(R.id.tV_Anzahl);

            if (VName != null) {
                VName.setText(bestellung.getDatum());
            }
            if (VVerkaeufer != null) {
                Cursor content = dataBaseHelper.getBestellungVerkaeuferName(bestellung.getVNr());
                content.moveToFirst();

                VVerkaeufer.setText(content.getString(0));
            }
            if (VAnzahl != null) {
                Cursor cursor = dataBaseHelper.getBestellungProduktAnzahl(bestellung.getBNr());
                cursor.moveToFirst();

                VAnzahl.setText(String.valueOf(cursor.getInt(0)));
            }
        }
        return convertView;
    }
}
