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
    public ArrayList<ArrayList<String>> listeNameundDatum = new ArrayList();
    private String bla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersicht);

        getSupportActionBar().setTitle("Übersicht");
        // TestString für die Liste:
        ArrayList<String> list = new ArrayList<>();

        test = (TextView) findViewById(R.id.textViewUebersicht);

        //Liste<String> liste = new Liste;
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");


//        Bundle extras = getIntent().getExtras();
//        if (extras!=null) {
//            bla = extras.getString("bla");}
//
//        test.setText(bla);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            uebergabeArray = extras.getStringArray("uebergabeKey");
            String[] arrayTmp = uebergabeArray;
            for (int i = 0; i < arrayTmp.length; i+=2) {
                ArrayList tmpList = new ArrayList(2);

                if (i % 2 == 0) {
                    tmpList.add(arrayTmp[i]);
                    tmpList.add(arrayTmp[i+1]);
                }
                listeNameundDatum.add(tmpList);
            }
            //Testausgabe von 2 Werten
            test.setText((String) listeNameundDatum.get(0).get(0));
        }


        /**  Liste  **/
        ListView listView = (ListView) findViewById(R.id.listViewNeu);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        QRListAdapter adapter = new QRListAdapter(this, R.layout.adapter_view_layout, list);
        adapter.datenUebergabe(listeNameundDatum);
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


