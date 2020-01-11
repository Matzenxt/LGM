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


class MyAdapterVerkaeuferListe extends ArrayAdapter<Verkaeufer> {

    private final LayoutInflater inflater;
    private final ArrayList<Verkaeufer> verkaeufers;
    private final int ViewResourceID;

    public MyAdapterVerkaeuferListe(@NonNull Context context, ArrayList<Verkaeufer> verkaeufer) {
        super(context, R.layout.verkaeufer_row_layout, verkaeufer);

        this.verkaeufers = verkaeufer;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceID = R.layout.verkaeufer_row_layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(ViewResourceID, null);

        Verkaeufer verkaeufer = verkaeufers.get(position);

        if (verkaeufer != null) {
            TextView VName = (TextView) convertView.findViewById(R.id.tV_Name);
            TextView VOrt = (TextView) convertView.findViewById(R.id.tV_Ort);

            if (VName != null) {
                VName.setText(verkaeufer.getName());
            }
            if (VOrt != null) {
                VOrt.setText(verkaeufer.getOrt());
            }
        }

        //ImageView imageView = (ImageView) convertView.findViewById(R.id.iV_Verkaeufer);
        //imageView.setImageResource(R.drawable.unbenannt);

        return convertView;
    }
}
