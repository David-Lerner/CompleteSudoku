package com.example.enkhturbadamsaikhan.completesudoku;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText et_username;
    EditText et_password;
    EditText et_email;

    Button login;
    Button register;


    TextView t_username;
    TextView t_email;

    ProgressDialog progressDialog;
    final List<String> permissions = Arrays.asList("public_profile", "email");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);

        login = (Button) findViewById(R.id.b_login);
        register = (Button) findViewById(R.id.b_register);


        t_username = (TextView) findViewById(R.id.tv_usernameText);
        t_email = (TextView) findViewById(R.id.tv_emailText);

        //getKeyHash();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("PrXBXkLIg6DBeO0cwzDXvIeTBBq2boYa8a8AiC2I")
                .clientKey("p85AdO1k753rekhqvjP5SstSvAuDgDTdPEzrR9k6")
                .server("https://parseapi.back4app.com/").build()
        );

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressDialog.setMessage("Please Wait");
//                progressDialog.setTitle("Logging in");
//                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            parseLogin();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressDialog.setMessage("Please Wait");
//                progressDialog.setTitle("Registering");
//                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            parseRegister();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });



//        ParseFacebookUtils.logInWithReadPermissionsInBackground(MainActivity.this, permissions, new LogInCallback() {
//            @Override
//            public void done(ParseUser user, ParseException err) {
//                if (user == null) {
//                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
//                } else if (user.isNew()) {
//                    Log.d("MyApp", "User signed up and logged in through Facebook!");
//                } else {
//                    Log.d("MyApp", "User logged in through Facebook!");
//                }
//            }
//        });

//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setMessage("Connecting...");
//        dialog.show();
//        ParseObject object = new ParseObject("Test");
//        object.put("Score", 67);
//        object.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null){
//                    Toast.makeText(MainActivity.this,"Successfully connected to Back4app", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        ParseFacebookUtils.initialize(this);
    }

    void parseLogin(){
        ParseUser.logInInBackground(et_username.getText().toString(), et_password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    //progressDialog.dismiss();
                    getUserDetailFromParse();
                    Intent i = new Intent(MainActivity.this, navActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                } else {
                    //progressDialog.dismiss();
                    alertDisplayer("Login Fail", e.getMessage()+" Please re-try");
                }
            }
        });
    }

    void parseRegister(){
        ParseUser user = new ParseUser();
        user.setUsername(et_username.getText().toString());
        user.setPassword(et_password.getText().toString());
        user.setEmail(et_email.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                    //progressDialog.dismiss();
                    t_username.setText(ParseUser.getCurrentUser().getUsername());
                    t_email.setText(ParseUser.getCurrentUser().getEmail());
                    saveNewUser();
//                    Intent i = new Intent(MainActivity.this, navActivity.class);
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
                } else {
                    //progressDialog.dismiss();
                    alertDisplayer("Register Fail", e.getMessage());
                }
            }
        });
    }

    void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    void getUserDetailFromParse(){
        ParseUser user = ParseUser.getCurrentUser();
        t_username.setText(user.getUsername());
        t_email.setText(user.getEmail());
        alertDisplayer("Welcome Back", "User:" + t_username.getText().toString() +" Login.Email:"+t_email.getText().toString());

    }

    void saveNewUser(){
        ParseUser user = ParseUser.getCurrentUser();
        user.setUsername(t_username.getText().toString());
        user.setEmail(t_email.getText().toString());
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                alertDisplayer("Register Successful Welcome", "User:" + t_username.getText().toString() + " Login.Email:" + t_email.getText().toString());
            }
        });
    }


//    @Override
//    public void onCreate() {
//        super.onCreate();
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
