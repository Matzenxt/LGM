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


public class MyAdapterSortimentA extends ArrayAdapter<Sortiment> {


    private LayoutInflater inflater;
    private ArrayList<Sortiment> sortiments;
    private int ViewResourceID;

    DataBaseHelper dataBaseHelper;

    public MyAdapterSortimentA(@NonNull Context context, int textViewResourceId, ArrayList<Sortiment> sortiment, DataBaseHelper pdataBaseHelper) {
        super(context, textViewResourceId, sortiment);

        this.sortiments = sortiment;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = textViewResourceId;
        dataBaseHelper = pdataBaseHelper;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        Sortiment sortiment = sortiments.get(position);

        if(sortiment != null){
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Name);
            TextView VVerkaeufer = (TextView) convertView.findViewById(R.id.tV_Verkaeufer);
            TextView VAnzahl = (TextView) convertView.findViewById(R.id.tV_Anzahl);


            if(VName != null){
                VName.setText(sortiment.getBeschreibung());
            }
            if(VVerkaeufer != null){
                Cursor content = dataBaseHelper.getSortimentVerkaeuferName(sortiment.getVNr());

                content.moveToFirst();
                String bla = content.getString(0);

                VVerkaeufer.setText(bla);
            }
            if(VAnzahl != null){
                Cursor cursor = dataBaseHelper.getSortimentProduktAnzahl(sortiment.getSNr());
                cursor.moveToFirst();
                String bla = String.valueOf(cursor.getInt(0));

                VAnzahl.setText(bla);
            }
        }
        return convertView;
    }
}
