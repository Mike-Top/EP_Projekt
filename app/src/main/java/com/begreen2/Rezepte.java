package com.begreen2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Rezepte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezepte);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Setze QR-Menü als Standard
        bottomNavigationView.setSelectedItemId(R.id.recipe);

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
                        startActivity(new Intent(getApplicationContext(), UEbersicht.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.recipe:
                        return true;
                }
                return false;
            }
        });
    }
}
