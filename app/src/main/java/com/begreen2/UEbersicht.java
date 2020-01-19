package com.begreen2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Type;
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
   //     test = (TextView) findViewById(R.id.textViewUebersicht);

        getSupportActionBar().setTitle("Übersicht");
        // TestString für die Liste:
        //   ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();





        //    loadData();     // Auskommentieren für Liste leeren (cheatweg)
        /** Für save: **/
//        Button buttonSave = findViewById(R.id.button_save);
//        buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveData();
//            }
//        });



        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            uebergabeArray = extras.getStringArray("uebergabeKey");
            String[] arrayTmp = uebergabeArray;
            for (int i = 0; i < arrayTmp.length; i+=2) {
                Produktdaten tmpDaten = null;

                if (i % 2 == 0) {
                    tmpDaten = new Produktdaten(arrayTmp[i], arrayTmp[i+1]);
                    //         tmpList.add(arrayTmp[i+1]);
                }
                produktdatenliste.add(tmpDaten);
            }
            //Testausgabe von 2 Werten
         //   test.setText((String) produktdatenliste.get(0).getPrduktName());
        }

        saveData();


        /**  Liste  **/
        ListView listView = (ListView) findViewById(R.id.listViewNEU);
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
                        //    startActivity(new Intent(getApplicationContext(), Rezepte.class));
                        Intent i = new Intent(getApplicationContext(), Rezepte.class);
                        if(produktdatenliste!=null) {i.putExtra("suchWert", produktdatenliste.get(0).getPrduktName());}
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    } // on Create ENDE


    /** Save Data **/ // Hier wird die produktdatenliste in den sharedpreferences gespeichert
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(produktdatenliste);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Produktdaten>>(){}.getType();
        produktdatenliste = gson.fromJson(json, type);

        if (produktdatenliste == null) {
            produktdatenliste = new ArrayList<>();
        }
    }



}