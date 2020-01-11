package com.example.matthias.lgm_v1_dummy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapterBestellungBearbeitenListe extends ArrayAdapter<ProduktBestellt> {


    private LayoutInflater inflater;
    private ArrayList<ProduktBestellt> produktBestelltArrayList;
    ProduktBestellt produktBestellt;
    private int ViewResourceID;
    DataBaseHelper dataBaseHelper;


    public MyAdapterBestellungBearbeitenListe(@NonNull Context context, int textViewResourceId, ArrayList<ProduktBestellt> pProduktBestelltArrayList, DataBaseHelper pdataBaseHelper) {
        super(context, textViewResourceId, pProduktBestelltArrayList);

        this.produktBestelltArrayList = pProduktBestelltArrayList;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = textViewResourceId;

        dataBaseHelper = pdataBaseHelper;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        produktBestellt = produktBestelltArrayList.get(position);

        if(produktBestellt != null){
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Produkt);
            TextView TAnzahl = (TextView) convertView.findViewById(R.id.tV_Anzahl);
            TextView TMenge = (TextView) convertView.findViewById(R.id.tV_Menge);

            if(VName != null){
                VName.setText(produktBestellt.getProdukt().getBezeichnung());
            }
            if(TAnzahl != null){
                TAnzahl.setText(String.valueOf(produktBestellt.getBestand()));
            }
            if(TMenge != null){
                TMenge.setText(String.valueOf(produktBestellt.getProdukt().getFuellmenge()));
            }
        }
        return convertView;
    }
}
