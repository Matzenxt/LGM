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


public class MyAdapterSortimentBearbeiten extends ArrayAdapter<ProduktSortimment> {


    private LayoutInflater inflater;
    private ArrayList<ProduktSortimment> produktSortimmentArrayList;
    ProduktSortimment produktSortimment;
    private int ViewResourceID;
    DataBaseHelper dataBaseHelper;


    public MyAdapterSortimentBearbeiten(@NonNull Context context, int textViewResourceId, ArrayList<ProduktSortimment> pProduktSortimmentArrayList, DataBaseHelper pdataBaseHelper) {
        super(context, textViewResourceId, pProduktSortimmentArrayList);

        this.produktSortimmentArrayList = pProduktSortimmentArrayList;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = textViewResourceId;

        dataBaseHelper = pdataBaseHelper;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        produktSortimment = produktSortimmentArrayList.get(position);

        if(produktSortimment != null){
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Produkt);
            TextView TAnzahl = (TextView) convertView.findViewById(R.id.tV_Anzahl);
            TextView TMenge = (TextView) convertView.findViewById(R.id.tV_Menge);

            if(VName != null){
                VName.setText(produktSortimment.getProdukt().getBezeichnung());
            }
            if(TAnzahl != null){
                TAnzahl.setText(String.valueOf(produktSortimment.getSollBestand()));
            }
            if(TMenge != null){
                TMenge.setText(String.valueOf(produktSortimment.getProdukt().getFuellmenge()));
            }
        }
        return convertView;
    }
}
