package com.begreen2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Rezepte extends AppCompatActivity {

    private TextView TextViewResult;
    private String suchWert = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezepte);
        getSupportActionBar().setTitle("Rezepte");


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


        TextViewResult = (TextView) findViewById(R.id.testAusgabe);
        OkHttpClient client = new OkHttpClient();


        // Besipeil API
//        String url = "https://reqres.in/api/users/2";
//        Request request = new Request.Builder()
//                .url(url)
//                .build();

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            suchWert = extras.getString("suchWert");
        }

        if (suchWert != null ) {
            Request request = rezeptSuche(suchWert);
//        Request request = rezeptsucheMitIngredients("apple");


            // Enqueue - Background request, weil geht nicht in Main
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        final String myResponse = response.body().string();

                        //   ------------- Api Test für Klassen mit Beispiel API --------> Funktioniert
//                    TestApiData myobject = new Gson().fromJson(myResponse, TestApiData.class);
//                    final String test = myobject.getData().getEmail();

                        // ---------------- Spoonacular API -----------------------> FUNKTIONIERT AUCH - Verbraucht Requests
                        SpoonacularApi myobject = new Gson().fromJson(myResponse, SpoonacularApi.class);
                        final String title = myobject.getResults().get(0).getTitle();

                        // ---------------- SpoonacularIngredients API -----------------------> Verbraucht Requests
//                    SpoonacularApiIngredients myobject = new Gson().fromJson(myResponse, SpoonacularApiIngredients.class);
//                    final String title = myobject.getResults().get(0).getTitle();
//                    final String title = myResponse;      TEST


                        // weil kein Zugriff auf Background wird Mainactivity aufgerufen
                        Rezepte.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //TextViewResult.setText(test);          // Test API Ausgabe
                                TextViewResult.setText(title);       // Spoonacular Testausgabe
                            }
                        });
                    }
                }
            });
        }




    }

    public Request rezeptSuche(String suchwort) {
    String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?number=5&query=";
        return new Request.Builder()
                .url( url + suchwort )
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "e406a6096dmsh4677d4f778bf5f4p17452fjsna50032559fb4")
                .build();
    }


    public Request rezeptsucheMitIngredients (String suchwort){
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?number=5&ranking=1&ingredients=";
        return new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?number=5&ranking=1&ingredients=apples")
          //      .url( url + suchwort )
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "e406a6096dmsh4677d4f778bf5f4p17452fjsna50032559fb4")
                .build();
    }


}
