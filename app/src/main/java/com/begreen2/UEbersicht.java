package com.begreen2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UEbersicht extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersicht);

        getSupportActionBar().setTitle("Übersicht");
        // TestString für die Liste:
        ArrayList<String> list = new ArrayList<>();

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


        /**  Liste  **/
        ListView listView = (ListView) findViewById(R.id.listView);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        QRListAdapter adapter = new QRListAdapter(this, R.layout.adapter_view_layout, list);
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

    }
}
