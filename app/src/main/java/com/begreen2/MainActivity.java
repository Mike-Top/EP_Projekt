package com.begreen2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import github.nisrulz.qreader.QRDataListener;
import github.nisrulz.qreader.QREader;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView txt_result;
    private TextView test;
    private TextView date;
    private TextView TextViewResult;

    private SurfaceView surfaceView;
    private QREader qrEader;
    private Liste listeUeberblick = new Liste();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Activity Button
        /*
        button = (Button) findViewById(R.id.button_a);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

         */

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Setze QR-Menü als Standard
        bottomNavigationView.setSelectedItemId(R.id.qr_code);

        // "Auswahl" wechseln
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.qr_code:
                        return true;
                    case R.id.uebersicht:
                        startActivity(new Intent(getApplicationContext(), UEbersicht.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.recipe:
                        startActivity(new Intent(getApplicationContext(), Rezepte.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        TextViewResult = (TextView) findViewById(R.id.testAusgabe);
        OkHttpClient client = new OkHttpClient();

        String url = "https://reqres.in/api/users/2";

        Request request = new Request.Builder()
                .url(url)
                .build();


  /*
 ///////////  API Spoonacular
        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/quickAnswer?q=How%20much%20vitamin%20c%20is%20in%202%20apples%253F")
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "e406a6096dmsh4677d4f778bf5f4p17452fjsna50032559fb4")
                .build();
*/

  /*
        try  {
            final Response response = client.newCall(request).execute();
            TextViewResult.setText(response.body().string());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
*/

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

                    //           JSONObject myobject = new JSONObject(myResponse);


                    // weil kein Zugriff auf Background - Mainactivity aufrufen
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextViewResult.setText(myResponse);
                        }
                    });
                }
            }
        });

        //Permission
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                setupCamera();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(MainActivity.this, "Enable Permission", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

            }
        }).check();

    }

    private void openActivity2() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    private void setupCamera() {
        txt_result = (TextView) findViewById(R.id.code_info);
        test = (TextView) findViewById(R.id.anzeigeListe);
        date = (TextView) findViewById(R.id.date);

        final ToggleButton btn_on_off = (ToggleButton) findViewById(R.id.btn_enable_disable);

        btn_on_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qrEader.isCameraRunning()) {
                    btn_on_off.setChecked(false);
                    qrEader.stop();
                } else {
                    btn_on_off.setChecked(true);
                    qrEader.start();
                }
            }
        });

        surfaceView = (SurfaceView) findViewById(R.id.camera_view);
        setupQREader();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void setupQREader() {
        qrEader = new QREader.Builder(this, surfaceView, new QRDataListener() {
            @Override
            public void onDetected(final String data) {
                txt_result.post(new Runnable() {
                    @Override
                    public void run() {
                        txt_result.setText(data);

                        // Eingelesener QR Code wird unterteilt in Arrays
                        String[] arrayTmp = data.split(", ");
                        for (int i = 0; i < arrayTmp.length; i++) {

                //            if (i == 0) listeUeberblick.list.add(arrayTmp[0]);//           else if (i == 1) listeUeberblick.dateList.add(arrayTmp[1]);
                            if      (i % 2 == 0) listeUeberblick.list.add(arrayTmp[i]);
                            else if (i % 2 == 1) listeUeberblick.dateList.add(arrayTmp[i]);
                        }

                        //Testausgabe von 2 TextViews
                        test.setText(listeUeberblick.list.get(0));
                        date.setText(listeUeberblick.dateList.get(3));
                    }
                });
            }
        })

                .facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(surfaceView.getHeight())
                .width(surfaceView.getWidth())
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                if (qrEader != null)
                    qrEader.initAndStart(surfaceView);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(MainActivity.this, "Enable Permission", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

            }
        }).check();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onPause() {
        super.onPause();
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                if (qrEader != null)
                    qrEader.releaseAndCleanup();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(MainActivity.this, "Enable Permission", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

            }
        }).check();

    }
}
