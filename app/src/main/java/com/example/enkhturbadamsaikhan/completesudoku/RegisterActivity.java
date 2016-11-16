package com.example.enkhturbadamsaikhan.completesudoku;

import android.app.Activity;
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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText et_email;
    EditText et_username;
    Button register;
    TextView t_username;
    TextView t_email;

    ProgressDialog progressDialog;
    final List<String> permissions = Arrays.asList("public_profile", "email");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username = (EditText) findViewById(R.id.et_username_register);
        et_email = (EditText) findViewById(R.id.et_email_register);

        register = (Button) findViewById(R.id.b_register);

        t_username = (TextView) findViewById(R.id.tv_username_register);
        t_email = (TextView) findViewById(R.id.tv_email_register);

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

    }

    void parseRegister(){
        ParseUser user = new ParseUser();
        user.setUsername(et_username.getText().toString());
        user.setPassword(et_email.getText().toString());
        user.setEmail(et_email.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                    //progressDialog.dismiss();
                    t_username.setText(ParseUser.getCurrentUser().getUsername());
                    t_email.setText(ParseUser.getCurrentUser().getEmail());
                    saveNewUser();

                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    //progressDialog.dismiss();
                    alertDisplayer("Register Fail", e.getMessage());
                }
            }
        });
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

    void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
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

}
