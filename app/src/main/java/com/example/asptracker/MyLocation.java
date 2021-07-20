package com.example.asptracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MyLocation extends AppCompatActivity {
public TextView textViewLatitude, textViewLongitude;
public LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        textViewLatitude = findViewById(R.id.lat);
        textViewLongitude = findViewById(R.id.log);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //CHECKING PERMISSIONS
        if(ContextCompat.checkSelfPermission(MyLocation.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(MyLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MyLocation.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 2, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                textViewLongitude.setText(String.valueOf(location.getLongitude()));
                textViewLatitude.setText(String.valueOf(location.getLatitude()));
            }
        });

    }
}