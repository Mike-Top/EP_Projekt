package com.begreen2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class QRListAdapter extends ArrayAdapter<Produktdaten> {

    private ArrayList<Produktdaten> listeNameundDatum2;

    private static final String TAG = "QRListAdapter";
    private Context qrContext;
    int mresource;
    private String test;

    public QRListAdapter(Context context, int resource, ArrayList<Produktdaten> objects) {
        super(context, resource, objects);
        qrContext = context;
        mresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getPrduktName();
        String datum = getItem(position).getDatum();

        Produktdaten produktdaten = new Produktdaten(name, datum);

        LayoutInflater inflater = LayoutInflater.from(qrContext);
        convertView = inflater.inflate(mresource, parent, false);

        TextView textViewName = (TextView) convertView.findViewById(R.id.textView1);
        TextView textViewDatum = (TextView) convertView.findViewById(R.id.textView2);

        textViewName.setText(name);
        textViewDatum.setText(datum);

        return convertView;
    }

    public void datenUebergabe(ArrayList<Produktdaten> arrayList) {
        listeNameundDatum2 = arrayList;
    }

    public void datenUebergabe2(String datenUebergabeString) {
        test = datenUebergabeString;
    }

}
