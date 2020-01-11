package code.lagerhlatung.lodner.matthias.lgm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class MyAdapterBestellungBearbeitenListe extends ArrayAdapter<ProduktBestellt> {


    private final LayoutInflater inflater;
    private final ArrayList<ProduktBestellt> produktBestelltArrayList;
    private final int ViewResourceID;


    public MyAdapterBestellungBearbeitenListe(@NonNull Context context, ArrayList<ProduktBestellt> pProduktBestelltArrayList) {
        super(context, R.layout.bestellung_neu_produkteliste_row_layout, pProduktBestelltArrayList);

        this.produktBestelltArrayList = pProduktBestelltArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = R.layout.bestellung_neu_produkteliste_row_layout;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        ProduktBestellt produktBestellt = produktBestelltArrayList.get(position);

        if (produktBestellt != null) {
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Produkt);
            TextView TAnzahl = (TextView) convertView.findViewById(R.id.tV_Anzahl);
            TextView TMenge = (TextView) convertView.findViewById(R.id.tV_Menge);

            if (VName != null) {
                VName.setText(produktBestellt.getProdukt().getBezeichnung());
            }
            if (TAnzahl != null) {
                TAnzahl.setText(String.valueOf(produktBestellt.getBestand()));
            }
            if (TMenge != null) {
                TMenge.setText(String.valueOf(produktBestellt.getProdukt().getFuellmenge()));
            }
        }
        return convertView;
    }
}
