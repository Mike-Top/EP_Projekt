package com.begreen2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class UEbersicht extends AppCompatActivity implements Serializable {

    private TextView test;
    private String[] uebergabeArray;
    public ArrayList<ArrayList<String>> listeNameundDatum = new ArrayList<ArrayList<String>>();
    private String bla;
    private Produktdaten produktdaten;
    ArrayList<Produktdaten> produktdatenliste = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersicht);
        test = (TextView) findViewById(R.id.textViewUebersicht);

        getSupportActionBar().setTitle("Übersicht");
        // TestString für die Liste:
     //   ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();


        //Liste<String> liste = new Liste;
//        list.add(listeNameundDatum.get(0));
//        list.add(listeNameundDatum.get(1));
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Apple");
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Apple");
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Apple");


//        Bundle extras = getIntent().getExtras();
//        if (extras!=null) {
//            bla = extras.getString("bla");}
//

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            uebergabeArray = extras.getStringArray("uebergabeKey");
            String[] arrayTmp = uebergabeArray;
            for (int i = 0; i < arrayTmp.length; i+=2) {
                Produktdaten tmpDaten = null;

                if (i % 2 == 0) {
                    tmpDaten = new Produktdaten(arrayTmp[0], arrayTmp[1]);
           //         tmpList.add(arrayTmp[i+1]);
                }
                produktdatenliste.add(tmpDaten);
            }
            //Testausgabe von 2 Werten
            test.setText((String) produktdatenliste.get(0).getPrduktName());
        }


        /**  Liste  **/
        ListView listView = (ListView) findViewById(R.id.listViewNeu);
        QRListAdapter adapter = new QRListAdapter(this, R.layout.adapter_view_layout, produktdatenliste);
        adapter.datenUebergabe(produktdatenliste);
        //adapter.datenUebergabe2(bla);
        listView.setAdapter(adapter);



        /**  BottomNavigation **/
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // Setze QR-Menü als Standard
        bottomNavigationView.setSelectedItemId(R.id.uebersicht);
        // "Auswahl" wechseln
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.qr_code:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.uebersicht:
                        return true;
                    case R.id.recipe:
                        startActivity(new Intent(getApplicationContext(), Rezepte.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });





    } // on Create ENDE


}


