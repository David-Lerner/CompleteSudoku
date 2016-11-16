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

public class LoginActivity extends AppCompatActivity {

    EditText et_username;
    EditText et_password;

    Button login;
    Button register;

    TextView t_username;

    ProgressDialog progressDialog;

    TextView t_usernameText;
    TextView t_emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = (EditText) findViewById(R.id.et_username_login);
        et_password = (EditText) findViewById(R.id.et_password_login);

        login = (Button) findViewById(R.id.b_login);
        register = (Button) findViewById(R.id.b_register_login);

        t_usernameText = (TextView) findViewById(R.id.tv_usernameText);
        t_emailText = (TextView) findViewById(R.id.tv_emailText);

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
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    void parseLogin(){
        ParseUser.logInInBackground(et_username.getText().toString(), et_password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    //progressDialog.dismiss();
                    getUserDetailFromParse();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                } else {
                    //progressDialog.dismiss();
                    alertDisplayer("Login Fail", e.getMessage()+" Please re-try");
                }
            }
        });
    }

    void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
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
        t_usernameText.setText(user.getUsername());
        t_emailText.setText(user.getEmail());
        alertDisplayer("Welcome Back", "User:" + t_usernameText.getText().toString() +" Login.Email:"+t_emailText.getText().toString());

    }
}
