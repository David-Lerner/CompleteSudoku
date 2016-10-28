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

public class Register extends AppCompatActivity {

    EditText username;
    EditText firstName;
    EditText lastName;
    EditText password;
    EditText passwordRetype;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.etUsername);
        firstName = (EditText) findViewById(R.id.etFirstname);
        lastName = (EditText) findViewById(R.id.etLastname);
        password = (EditText) findViewById(R.id.etPassword);
        passwordRetype = (EditText) findViewById(R.id.etRetypePassword);

        register = (Button) findViewById(R.id.bRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstName.getText().toString().equals("") || lastName.getText().toString().equals("")){
                    Toast a = Toast.makeText(getApplicationContext(), "Please enter your first and last name", Toast.LENGTH_LONG);
                    a.show();
                }
                else{
                    // Checking if same password is typed twice
                    String pass = password.getText().toString();
                    String passCheck = passwordRetype.getText().toString();

                    if(pass.equals(passCheck)){
                        ParseUser user = new ParseUser();
                        user.setUsername(username.getText().toString());
                        user.setPassword(pass);
                        user.put("first_name", firstName.getText().toString());
                        user.put("last_name", lastName.getText().toString());

                        user.signUpInBackground(new SignUpCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    // Hooray! Let them use the app now.
                                    Toast a = Toast.makeText(getApplicationContext(), "You are signed up, please log in", Toast.LENGTH_LONG);
                                    a.show();
                                    Intent i = new Intent(Register.this, LoginActivity.class);
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
