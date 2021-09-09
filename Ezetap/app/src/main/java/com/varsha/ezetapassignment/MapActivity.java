package com.varsha.ezetapassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private String apiKey = "AIzaSyCzTy2-BLNyY89cVO-lPdInQJ7LVTigBAk";
    private TextView placeSearch_Tv;
    double latitude = 0.0;
    double longitude = 0.0;
    StringBuilder sb = new StringBuilder();
    StringBuilder sb1 = new StringBuilder();
    private GoogleMap nMap;
    private ImageView  buttonproceed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SharedPref.init(this);

        placeSearch_Tv = findViewById(R.id.placeSearch_tv);
        buttonproceed=findViewById(R.id.button_proceed);

        sb.append(latitude);

        buttonproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MapActivity.this,ThirdScreen.class);
                startActivity(intent);
            }
        });

        placeSearch_Tv.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (sb != null) {
                    placeSearch_Tv.setText("Lat:-> " + sb.substring(3) + "\n" + "Lng:-> " + sb1);
                    sb.delete(0,sb.length()-1);
                    sb1.delete(0,sb1.length()-1);
                }
            }
        });

        // Initialize the AutocompleteSupportFragment.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        nMap = googleMap;
        nMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                latitude = latLng.latitude;
                longitude = latLng.longitude;
                SharedPref.write(SharedPref.LATTITUDE,String.valueOf(latLng.latitude));
                sb.append(latLng.latitude);
                SharedPref.write(SharedPref.LONGITUDE,String.valueOf(latLng.longitude));
                sb1.append(latLng.longitude);
                nMap.clear();
                nMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
                nMap.addMarker(markerOptions);
            }
        });

    }

}