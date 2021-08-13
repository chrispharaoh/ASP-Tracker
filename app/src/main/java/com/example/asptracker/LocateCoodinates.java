package com.example.asptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocateCoodinates extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String SEQUENCY = "sequency";
    private String text1, text2,text3;

    Button button1;
    TextView view1, view2, view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_coodinates);

        button1 = findViewById(R.id.btn2);
        view1 = findViewById(R.id.formail);
        view2 = findViewById(R.id.forphone);
        view3 =  findViewById(R.id.select);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                text1 = sharedPreferences.getString(EMAIL, "");
                text2 = sharedPreferences.getString(PHONE, "");
                text3 = sharedPreferences.getString(SEQUENCY, "");
                view1.setText(text1);
                view2.setText(text2);
                view3.setText(text3);
            }
        });
    }
}