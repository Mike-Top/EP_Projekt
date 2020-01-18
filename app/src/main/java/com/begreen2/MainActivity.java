package com.begreen2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
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

import java.io.Serializable;
import java.util.ArrayList;

import github.nisrulz.qreader.QRDataListener;
import github.nisrulz.qreader.QREader;

public class MainActivity extends AppCompatActivity implements Serializable {

    private TextView txt_result;
    private TextView test;
    private TextView date;
    private TextView TextViewResult;

    private SurfaceView surfaceView;
    private QREader qrEader;
 //   private Liste listeUeberblick = new Liste();
    private ArrayList<ArrayList> listNameAndDate = new ArrayList<ArrayList>();
    private String testString = "Teststring";
    private String[] uebergabeArray = {"Hafer, 22.22.2222, Flocken2, 44.44.4444"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // NAVIGATIONSMENÜ ////////////////////////////////////
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
                        Intent i = new Intent(getApplicationContext(), UEbersicht.class);
                        if(uebergabeArray!=null) {i.putExtra("uebergabeKey", uebergabeArray);}
                        startActivity(i);
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
        //////////////////////////////////////////////////////


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

                        final String data2 = "Banane, 22.22.2222, Apfel, 33.33.3333";
                        // Eingelesener QR Code wird unterteilt in Arrays
                        uebergabeArray = data.split(", ");
                        String[] arrayTmp = uebergabeArray;
                        for (int i = 0; i < arrayTmp.length; i+=2) {

                //            if (i == 0) listeUeberblick.list.add(arrayTmp[0]);//           else if (i == 1) listeUeberblick.dateList.add(arrayTmp[1]);
//                            if      (i % 2 == 0) listeUeberblick.list.add(arrayTmp[i]);
//                            else if (i % 2 == 1) listeUeberblick.dateList.add(arrayTmp[i]);

                            ArrayList tmpList = new ArrayList(2);

                            if      (i % 2 == 0){
                                tmpList.add(arrayTmp[i]);
                                tmpList.add(arrayTmp[i+1]);
                            }
                         //   else if (i+1 % 2 == 1) tmpList.add(arrayTmp[i+1]);

                            listNameAndDate.add(tmpList);
                        }



                        //Testausgabe von 2 Werten
                        test.setText((String) listNameAndDate.get(0).get(0));
                        date.setText((String) listNameAndDate.get(1).get(1));
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
