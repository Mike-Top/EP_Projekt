package com.begreen2;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


// <String> später mit Liste auswechseln??????
public class QRListAdapter extends ArrayAdapter<String> {



    private Context qrContext;
    int mresource;

    public QRListAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        qrContext = context;
        mresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //String name = getItem(position).getName();
        //String birthday = getItem(position).getBirthday();
        // String sex = getItem(position).getSex();

        // Test Strings....
        String name = "Tomate";
        String datum = "12.12.2012";

        Liste liste = new Liste();

        LayoutInflater inflater = LayoutInflater.from(qrContext);
        convertView = inflater.inflate(mresource, parent, false);

        TextView textViewName = (TextView) convertView.findViewById(R.id.textView1);
        TextView textViewDatum = (TextView) convertView.findViewById(R.id.textView2);

        // für den test nur die strings hier drinnen (von etwas weiter oben)
        textViewName.setText(name);
        textViewDatum.setText(datum);

        return convertView;
    }
}
