package com.begreen2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class QRListAdapter extends ArrayAdapter<String> {

    //private ArrayList<ArrayList> listeNameundDatum2 = new ArrayList();

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
        //String name = getItem(position).;
        //String birthday = getItem(position).getBirthday();
        //String sex = getItem(position).getSex();



        // Test Strings....
        String name = "Tomate";
        String datum = "12.12.2012";

        //uEbersicht.getListeNameundDatum().get(0).get(0);

        //listeNameundDatum.get(0).get(0);

        //Liste liste = new Liste();


        LayoutInflater inflater = LayoutInflater.from(qrContext);
        convertView = inflater.inflate(mresource, parent, false);

        TextView textViewName = (TextView) convertView.findViewById(R.id.textView1);
        TextView textViewDatum = (TextView) convertView.findViewById(R.id.textView2);

        textViewName.setText(name);
        // für den test nur die strings hier drinnen (von etwas weiter oben)
        //textViewName.setText((String)listeNameundDatum2.get(0).get(0));
        textViewDatum.setText(datum);
        //textViewDatum.setText((String)listeNameundDatum2.get(0).get(1));

        return convertView;
    }

    /*public void datenUebergabe(ArrayList<ArrayList> arrayList) {
        listeNameundDatum2 = arrayList;
    }*/



}
