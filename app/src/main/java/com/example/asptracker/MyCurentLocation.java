package com.example.asptracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class MyCurentLocation extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String PHONE = "phone";
    public LocationManager locationManager;
    TextView lat, lon;
    private String text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_curent_location);

        lat = findViewById(R.id.textView14);
        lon = findViewById(R.id.textView15);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(MyCurentLocation.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(MyCurentLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MyCurentLocation.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 2, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String longitude, latitude;
                lon.setText(String.valueOf(location.getLongitude()));
//                lat.setText(String.valueOf(location.getLatitude()));

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                text1 = sharedPreferences.getString(PHONE, "");

                if(lon.length() > 0) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(text1, null, "Longitude: " + String.valueOf(location.getLongitude()) + " Latitude " + String.valueOf(location.getLatitude()), null, null);
                    lat.setText(text1);
                }

//                longitude = String.valueOf(location.getLongitude());
////                latitude = String.valueOf(location.getLatitude());
//
//                ActivityCompat.requestPermissions( MyCurentLocation.this, new String[]{Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage(text1,"+265888820767","Coordinates "+"Latitude "+lat+ "Longitude"+ lon,null,null);


            }
        });
    }


}