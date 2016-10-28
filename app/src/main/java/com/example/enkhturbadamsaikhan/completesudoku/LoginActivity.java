package com.example.enkhturbadamsaikhan.completesudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.editTextLoginUsername);
        password = (EditText) findViewById(R.id.editTextLoginPassword);

        login = (Button) findViewById(R.id.buttonLogin);
        login.setOnClickListener(this);

        register = (Button) findViewById(R.id.buttonGoToRegister);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            Toast a = Toast.makeText(getApplicationContext(), "Not Logged In", Toast.LENGTH_LONG);
                            a.show();
                        }
                    }
                });
                break;

            case R.id.buttonGoToRegister:
                Intent i = new Intent(LoginActivity.this, Register.class);
                startActivity(i);
                break;
        }
    }

}
