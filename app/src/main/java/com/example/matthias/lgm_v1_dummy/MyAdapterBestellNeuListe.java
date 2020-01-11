package com.example.matthias.lgm_v1_dummy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class MyAdapterBestellNeuListe extends ArrayAdapter<Produkt> {


    private LayoutInflater inflater;
    private ArrayList<Produkt> produkts;
    private Sortiment sortiment;
    private int ViewResourceID;
    DataBaseHelper dataBaseHelper;


    public MyAdapterBestellNeuListe(@NonNull Context context, int textViewResourceId, ArrayList<Produkt> produkt, DataBaseHelper pdataBaseHelper) {
        super(context, textViewResourceId, produkt);

        //sortiment = pSortiment;
        this.produkts = produkt;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = textViewResourceId;

        dataBaseHelper = pdataBaseHelper;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        Produkt produkt = produkts.get(position);

        /*
        if(produkt != null){
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Name);
            TextView TAnzahl = (TextView) convertView.findViewById(R.id.tV_Anzahl);
            TextView TMenge = (TextView) convertView.findViewById(R.id.tV_Menge);

            if(VName != null){
                VName.setText(produkt.getBezeichnung());
            }
            if(TAnzahl != null){
                TAnzahl.setText(String.valueOf(produktBestellt.getBestand()));
            }
            if(TMenge != null){
                TMenge.setText(String.valueOf(produktBestellt.getProdukt().getFuellmenge()));
            }
        }
        */

        return convertView;
    }
}
