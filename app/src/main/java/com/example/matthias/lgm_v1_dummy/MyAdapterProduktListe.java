package com.example.matthias.lgm_v1_dummy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapterProduktListe extends ArrayAdapter<Produkt> {


    private LayoutInflater inflater;
    private ArrayList<Produkt> produkts;
    private int ViewResourceID;


    public MyAdapterProduktListe(@NonNull Context context, int textViewResourceId, ArrayList<Produkt> produkt) {
        super(context, textViewResourceId, produkt);

        this.produkts = produkt;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = textViewResourceId;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        Produkt produkt = produkts.get(position);

        if(produkt != null){
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Name);
            TextView VFuellmenge = (TextView) convertView.findViewById(R.id.tV_Menge);

            if(VName != null){
                VName.setText(produkt.getBezeichnung());
            }
            if(VFuellmenge != null){
                VFuellmenge.setText(String.valueOf(produkt.getFuellmenge()));
            }
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.iV_Produkt);

        imageView.setImageResource(R.drawable.unbenannt);


        return convertView;
    }
}
