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


class MyAdapterSortimentA extends ArrayAdapter<Sortiment> {


    private final LayoutInflater inflater;
    private final ArrayList<Sortiment> sortiments;
    private final int ViewResourceID;

    private final DataBaseHelper dataBaseHelper;

    public MyAdapterSortimentA(@NonNull Context context, ArrayList<Sortiment> sortiment, DataBaseHelper pdataBaseHelper) {
        super(context, R.layout.sortiment_row_layout, sortiment);

        this.sortiments = sortiment;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = R.layout.sortiment_row_layout;
        dataBaseHelper = pdataBaseHelper;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        Sortiment sortiment = sortiments.get(position);

        if (sortiment != null) {
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Name);
            TextView VVerkaeufer = (TextView) convertView.findViewById(R.id.tV_Verkaeufer);
            TextView VAnzahl = (TextView) convertView.findViewById(R.id.tV_Anzahl);


            if (VName != null) {
                VName.setText(sortiment.getBeschreibung());
            }
            if (VVerkaeufer != null) {
                Cursor content = dataBaseHelper.getSortimentVerkaeuferName(sortiment.getVNr());

                content.moveToFirst();
                String bla = content.getString(0);

                VVerkaeufer.setText(bla);
            }
            if (VAnzahl != null) {
                Cursor cursor = dataBaseHelper.getSortimentProduktAnzahl(sortiment.getSNr());
                cursor.moveToFirst();
                String bla = String.valueOf(cursor.getInt(0));

                VAnzahl.setText(bla);
            }
        }
        return convertView;
    }
}
