package com.example.enkhturbadamsaikhan.completesudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class guestActivity extends AppCompatActivity {

    Button easy;
    Button medium;
    Button hard;
    Button extreme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        easy = (Button) findViewById(R.id.easyButton_guest);
        medium = (Button) findViewById(R.id.mediumButton_guest);
        hard = (Button) findViewById(R.id.hardButton_guest);
        extreme = (Button) findViewById(R.id.extremeButton_guest);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(guestActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(guestActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(guestActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        extreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(guestActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
