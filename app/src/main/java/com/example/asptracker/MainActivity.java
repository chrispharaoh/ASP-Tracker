package com.example.asptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView buttonConfig, buttonCoordinates,buttonLock ;
        TextView control;
        ImageView image1, image2, image3, image4;

        image1 = findViewById(R.id.imageView5);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyLocation.class);
                startActivity(intent);
            }
        });
        buttonConfig = findViewById(R.id.configure);
        buttonConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyLocation.class);
                startActivity(intent);

            }
        });
        buttonConfig.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                return false;
            }
        });

        buttonLock = findViewById(R.id.lockp);
        image2 = findViewById(R.id.imageView7);
        buttonLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RemoteLock.class);
                startActivity(intent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RemoteLock.class);
                startActivity(intent);
            }
        });

        buttonCoordinates = findViewById(R.id.location1);
        image3 = findViewById(R.id.imageView4);

        buttonCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Location.class);
                startActivity(intent);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Location.class);
                startActivity(intent);
            }
        });
        image4 = findViewById(R.id.imageView8);
        control = findViewById(R.id.control);
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RemoteControl.class);
                startActivity(intent);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RemoteControl.class);
                startActivity(intent);
            }
        });
    }
}