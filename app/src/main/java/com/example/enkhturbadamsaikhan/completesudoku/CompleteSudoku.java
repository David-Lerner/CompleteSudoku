package com.example.enkhturbadamsaikhan.completesudoku;

import android.app.Application;

import com.parse.Parse;


/**
 * Created by enkhturbadamsaikhan on 9/21/16.
 */
public class CompleteSudoku extends Application {
    @Override
    public void onCreate() {
        Parse.Configuration.Builder builder = new Parse.Configuration.Builder(this);
        builder.applicationId("PrXBXkLIg6DBeO0cwzDXvIeTBBq2boYa8a8AiC2I");
        builder.clientKey("p85AdO1k753rekhqvjP5SstSvAuDgDTdPEzrR9k6");
        builder.server("https://parseapi.back4app.com/");
        Parse.initialize(builder.build());
        super.onCreate();
    }
}
