package com.example.asptracker;

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

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.List;

public class MyLocation extends AppCompatActivity {
public TextView textViewLatitude, textViewLongitude;
public LocationManager locationManager;
public Button button2;
public Spinner spinnerDropdown;
public EditText emailText;
public String emailPattern;
public String mail;
public EditText emailValidate;
public  EditText phone;
public EditText thisEmail;
public EditText thisPhone;
public  EditText emailField;
public  EditText phoneField;
public String phonePattern;
public String sequency;
public static final String SHARED_PREFS = "sharedPrefs";
public static final String SEQUENCE = "text";
public static final String EMAIL = "email";
public static final String PHONE = "phone";
public static final String SEQUENCY = "sequency";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        textViewLatitude = findViewById(R.id.lat);
        textViewLongitude = findViewById(R.id.log);
        spinnerDropdown = findViewById(R.id.spinner1);
        phone = findViewById(R.id.phonenumber);
        thisEmail = findViewById(R.id.editTextTextEmailAddress);
        thisPhone = findViewById(R.id.phonenumber);

        //populating spinner
        List<String> list = new ArrayList<>();
        list.add("2 Hours");
        list.add("5 Hours");
        list.add("10 Hours");
        list.add("15 Hours");
        list.add("24 Hours");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDropdown.setAdapter(adapter);
        spinnerDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = parent.getSelectedItem().toString();
                sequency = parent.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "Selected "+ itemValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //validating email when typing
        emailText = findViewById(R.id.editTextTextEmailAddress);
        button2 = findViewById(R.id.button2);
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        mail = emailText.getText().toString().trim();
        phone = findViewById(R.id.phonenumber);
        phonePattern = "^[+][0-9]{10,13}$";



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailText.getText().toString().isEmpty()) {
                   // Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                    emailText.setError("Email cannot be empty");
                }else {
                    if (!emailText.getText().toString().trim().matches(emailPattern)) {
                        //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                        emailText.setError("Invalid email");
                    }
                    else {
                        if(phone.getText().toString().isEmpty()){
                            phone.setError("Phone cannot be empty");
                        }else{
                                if(!phone.getText().toString().trim().matches(phonePattern)){
                                    phone.setError("Invalid phone number");
                                }else{
                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(EMAIL, thisEmail.getText().toString());
                                    editor.putString(PHONE, thisPhone.getText().toString());
                                    editor.putString(SEQUENCY, sequency);
                                    editor.apply();
                                    Toast.makeText(getApplicationContext(), "Data Saved successfully", Toast.LENGTH_SHORT).show();

                                }
                        }
                    }

                }
            }
        });

        
//
//        if (!emailText.getText().toString().trim().matches(emailPattern)) {
//            Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
//        }
        //Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();

//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        //CHECKING PERMISSIONS
//        if(ContextCompat.checkSelfPermission(MyLocation.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//            ContextCompat.checkSelfPermission(MyLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(MyLocation.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},1);
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 2, new LocationListener() {
//            @Override
//            public void onLocationChanged(@NonNull Location location) {
//                String longitude, latitude;
//                textViewLongitude.setText(String.valueOf(location.getLongitude()));
//                textViewLatitude.setText(String.valueOf(location.getLatitude()));
//
//
//                longitude = String.valueOf(location.getLongitude());
//                latitude = String.valueOf(location.getLatitude());
//
//
//                if(!textViewLatitude.equals(null) && !textViewLongitude.equals(null)){
//
//                    Handler handler = new Handler(Looper.getMainLooper());
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            //Starting Write and Read data with URL
//                            //Creating array for parameters
//                            String[] field = new String[2];
//
//                            field[0] = "latitude";
//                            field[1] = "longitude";
//
//                            //Creating array for data
//                            String[] data = new String[2];
//
//                            data[0] = latitude;
//                            data[1] = longitude;
//
//
//                            //From the emulator, 127.0.0.1 refers to the emulator itself - not your local machine. You need to use ip 10.0.2.2, which is bridged to your local machine.
//                            PutData putData = new PutData("http://10.0.2.2/LoginRegister/sendmail.php", "POST", field, data);
//                            if (putData.startPut()) {
//                                if (putData.onComplete()) {
//                                    String result = putData.getResult();
//                                    if(result.equals("Email is sent successifully")){
//                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
//                                    }
//                                    else{
//                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
//                                    }
//                                    // Log.i("PutData", result);
//                                }
//                            }
//                            //End Write and Read data with URL
//                        }
//                    });
//                }
//            }
//        });

    }
}