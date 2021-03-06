package com.example.asptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LogIn extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USERNAME = "email";
    public static final String PASSWORD = "phone";
    //initializing text
    TextView t1,t2;
    TextInputEditText textInputEditTextUsername,textInputEditTextPassword;
    Button buttonLogIn;
    TextView textViewSignUp;
    //object for the progressbar
    ProgressBar progressBar;
    private String text1, text2,usernamedata,passwordata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //initializing objects
        textInputEditTextUsername = findViewById(R.id.usernamelog);
        textInputEditTextPassword = findViewById(R.id.passwordlog);
        buttonLogIn = findViewById(R.id.btnlogin);
        textViewSignUp = findViewById(R.id.signuptext);
        progressBar = findViewById(R.id.progress);


        //setting onclick listener for text view to redirected to login activity
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        //checking if the user is already have an account in shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text1 = sharedPreferences.getString(USERNAME, null);
        text2 = sharedPreferences.getString(PASSWORD, null);


        if (text1 != null && text2 != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else{

            if (text1 == null && text2 == null) {
                buttonLogIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //variables for getting real input data
                        String username, password;
                        //capturing text

                        username = String.valueOf(textInputEditTextUsername.getText());

                        password = String.valueOf(textInputEditTextPassword.getText());

                        //checking whether there is an error in text fields before submiting
                        if (!username.equals("") && !password.equals("")) {

                            //logging the user with shared preferences
                            usernamedata = sharedPreferences.getString(USERNAME, "");
                            passwordata = sharedPreferences.getString(PASSWORD, "");

                            //Start ProgressBar first (Set visibility VISIBLE)\

                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //Starting Write and Read data with URL
                                    //Creating array for parameters
                                    String[] field = new String[2];

                                    field[0] = "username";
                                    field[1] = "password";

                                    //Creating array for data
                                    String[] data = new String[2];

                                    data[0] = username;
                                    data[1] = password;


                                    //From the emulator, 127.0.0.1 refers to the emulator itself - not your local machine. You need to use ip 10.0.2.2, which is bridged to your local machine.
                                    PutData putData = new PutData("http://10.0.2.2/LoginRegister/login.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            //End ProgressBar (Set visibility to GONE) // after completing the process

                                            String result = putData.getResult();
                                            if (result.equals("Login Success")) {
                                                //storing user data to local storage
                                                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString(USERNAME, textInputEditTextUsername.getText().toString());
                                                editor.putString(PASSWORD, textInputEditTextPassword.getText().toString());
                                                editor.apply();
                                                //showing a toast message when loged successfully
                                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                //creating intent to navigate to login activity
                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(intent);
                                                finish();

                                            } else {
                                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                            }
                                            // Log.i("PutData", result);
                                        }
                                    }
                                    //End Write and Read data with URL
                                }
                            });
                        } else {
                            //creating a toast to display an error message when the one or more fields is empty
                            textInputEditTextUsername.setError("Username cannot be empty");
                            Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                        }
//end of codes
                    }

                });
            }
    }

    }
}