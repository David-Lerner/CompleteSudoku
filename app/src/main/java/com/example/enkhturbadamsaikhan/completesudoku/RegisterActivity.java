package com.example.enkhturbadamsaikhan.completesudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText username;
    Button register;
    EditText password;
    EditText passwordRetype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.et_username_register);
        email = (EditText) findViewById(R.id.et_email_register);
        password = (EditText) findViewById(R.id.et_password_register);
        passwordRetype = (EditText) findViewById(R.id.et_passwordRT_register);

        register = (Button) findViewById(R.id.b_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")) {
                    Toast a = Toast.makeText(getApplicationContext(), "Please enter your username", Toast.LENGTH_LONG);
                    a.show();
                } else {
                    // Checking if same password is typed twice
                    String pass = password.getText().toString();
                    String passCheck = passwordRetype.getText().toString();

                    if(pass.equals(passCheck)){
                        ParseUser user = new ParseUser();
                        Toast b = Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_SHORT);
                        b.show();
                        user.setUsername(username.getText().toString());
                        user.setEmail(email.getText().toString());
                        user.setPassword(pass);

                        user.signUpInBackground(new SignUpCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    // Hooray! Let them use the app now.
                                    Toast a = Toast.makeText(getApplicationContext(), "You are signed up, please log in", Toast.LENGTH_LONG);
                                    a.show();
                                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    // Sign up didn't succeed. Look at the ParseException
                                    // to figure out what went wrong
                                    Toast a = Toast.makeText(getApplicationContext(), "That username may be taken or something went wrong",
                                            Toast.LENGTH_LONG);
                                    a.show();
                                }
                            }
                        });

                    }
                    else{
                        Toast a = Toast.makeText(getApplicationContext(), "Please type the same Passwords", Toast.LENGTH_LONG);
                        a.show();
                    }
                }
            }
        });
    }

}
