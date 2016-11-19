package com.example.enkhturbadamsaikhan.completesudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class userMenuActivity extends AppCompatActivity {

    Button easy;
    Button medium;
    Button hard;
    Button extreme;

    Button upload;
    Button saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        easy = (Button) findViewById(R.id.easyButton);
        medium = (Button) findViewById(R.id.mediumButton);
        hard = (Button) findViewById(R.id.hardButton);
        extreme = (Button) findViewById(R.id.extremeButton);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userMenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userMenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userMenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        extreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userMenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        upload =  (Button) findViewById(R.id.uploadButton);
        saved = (Button) findViewById(R.id.savedSudokuButton);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userMenuActivity.this, UploadActivity.class);
                startActivity(i);
            }
        });

        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userMenuActivity.this, savedGamesActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Define the listener
        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when action item collapses
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        };

//        MenuItem searchItem = menu.findItem(R.id.);
//        SearchView searchView =
//                (SearchView) MenuItemCompat.getActionView(searchItem);
//
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.logout_user:
                ParseUser.logOut();
                Intent i = new Intent(userMenuActivity.this, IntroActivity.class);
                startActivity(i);
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
