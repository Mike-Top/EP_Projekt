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
        //String name = getItem(position).;
        //String birthday = getItem(position).getBirthday();
        //String sex = getItem(position).getSex();


        // Test Strings....
        //   String name = test;
        //   String datum = "12.12.2012";
        String name = getItem(position).getPrduktName();
        String datum = getItem(position).getDatum();

        Produktdaten produktdaten = new Produktdaten(name, datum);

//        String name = getItem(position).get(0);
//        String datum = getItem(position).get(1);

        LayoutInflater inflater = LayoutInflater.from(qrContext);
        convertView = inflater.inflate(mresource, parent, false);

        TextView textViewName = (TextView) convertView.findViewById(R.id.textView1);
        TextView textViewDatum = (TextView) convertView.findViewById(R.id.textView2);

//        for (int i = 0; i < listeNameundDatum2.size(); i+=2){
//            String name = listeNameundDatum2.get(i).get(i);
//            String datum = listeNameundDatum2.get(i).get(i+1);
//            textViewName.setText(name);
//            textViewDatum.setText(datum);
//        }

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
