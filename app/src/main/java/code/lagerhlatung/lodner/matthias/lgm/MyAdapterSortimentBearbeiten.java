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


class MyAdapterSortimentBearbeiten extends ArrayAdapter<ProduktSortimment> {


    private final LayoutInflater inflater;
    private final ArrayList<ProduktSortimment> produktSortimmentArrayList;
    private final int ViewResourceID;


    public MyAdapterSortimentBearbeiten(@NonNull Context context, ArrayList<ProduktSortimment> pProduktSortimmentArrayList) {
        super(context, R.layout.bestellung_neu_produkteliste_row_layout, pProduktSortimmentArrayList);

        this.produktSortimmentArrayList = pProduktSortimmentArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = R.layout.bestellung_neu_produkteliste_row_layout;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        ProduktSortimment produktSortimment = produktSortimmentArrayList.get(position);

        if (produktSortimment != null) {
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Produkt);
            TextView TAnzahl = (TextView) convertView.findViewById(R.id.tV_Anzahl);
            TextView TMenge = (TextView) convertView.findViewById(R.id.tV_Menge);

            if (VName != null) {
                VName.setText(produktSortimment.getProdukt().getBezeichnung());
            }
            if (TAnzahl != null) {
                TAnzahl.setText(String.valueOf(produktSortimment.getSollBestand()));
            }
            if (TMenge != null) {
                TMenge.setText(String.valueOf(produktSortimment.getProdukt().getFuellmenge()));
            }
        }
        return convertView;
    }
}
