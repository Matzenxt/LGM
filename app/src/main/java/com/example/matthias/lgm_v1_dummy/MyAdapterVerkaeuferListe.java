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


public class MyAdapterVerkaeuferListe extends ArrayAdapter<Verkaeufer> {


    private LayoutInflater inflater;
    private ArrayList<Verkaeufer> verkaeufers;
    private int ViewResourceID;


    public MyAdapterVerkaeuferListe(@NonNull Context context, int textViewResourceId, ArrayList<Verkaeufer> verkaeufer) {
        super(context, textViewResourceId, verkaeufer);

        this.verkaeufers = verkaeufer;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = textViewResourceId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        Verkaeufer verkaeufer = verkaeufers.get(position);

        if(verkaeufer != null){
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Name);
            TextView VOrt = (TextView) convertView.findViewById(R.id.tV_Ort);

            if(VName != null){
                VName.setText(verkaeufer.getName());
            }
            if(VOrt != null){
                VOrt.setText(verkaeufer.getOrt());
            }
        }

        /*
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(R.layout.verkaeufer_row_layout, parent, false);

        String produkte = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.tV_Adresse);

        textView.setText(produkte);


        TextView textViewName = (TextView) view.findViewById(R.id.tV_Name);
        textViewName.setText(produkte);



        ImageView imageView = (ImageView) view.findViewById(R.id.iV_Verkaeufer);

        imageView.setImageResource(R.drawable.unbenannt);
*/
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iV_Verkaeufer);

        imageView.setImageResource(R.drawable.unbenannt);


        return convertView;


    }
}
