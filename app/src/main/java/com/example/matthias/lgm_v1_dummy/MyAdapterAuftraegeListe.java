package com.example.matthias.lgm_v1_dummy;

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


public class MyAdapterAuftraegeListe extends ArrayAdapter<Bestellung> {


    private LayoutInflater inflater;
    private ArrayList<Bestellung> bestellungs;
    private int ViewResourceID;
    private DataBaseHelper dataBaseHelper;


    public MyAdapterAuftraegeListe(@NonNull Context context, int textViewResourceId, ArrayList<Bestellung> bestellung, DataBaseHelper pDataBaseHelper) {
        super(context, textViewResourceId, bestellung);

        this.bestellungs = bestellung;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = textViewResourceId;
        dataBaseHelper = pDataBaseHelper;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        Bestellung bestellung = bestellungs.get(position);

        if(bestellung != null){
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Name);
            TextView VVerkaeufer = (TextView) convertView.findViewById(R.id.tV_Verkaeufer);
            TextView VAnzahl = (TextView) convertView.findViewById(R.id.tV_Anzahl);

            if(VName != null){
                VName.setText(bestellung.getDatum());
            }
            if(VVerkaeufer != null){
                Cursor content = dataBaseHelper.getBestellungVerkaeuferName(bestellung.getVNr());

                content.moveToFirst();
                String bla = content.getString(0);

                VVerkaeufer.setText(bla);
            }
            if(VAnzahl != null){
                Cursor cursor = dataBaseHelper.getBestellungProduktAnzahl(bestellung.getBNr());
                cursor.moveToFirst();
                String bla = String.valueOf(cursor.getInt(0));

                VAnzahl.setText(bla);
            }
        }
        return convertView;
    }
}
