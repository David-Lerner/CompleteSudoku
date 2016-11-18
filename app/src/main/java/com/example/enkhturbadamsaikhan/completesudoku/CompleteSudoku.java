package com.example.enkhturbadamsaikhan.completesudoku;

import com.parse.Parse;

/**
 * Created by enkhturbadamsaikhan on 11/18/16.
 */

public class CompleteSudoku extends android.app.Application{

    @Override
    public void onCreate(){
        super.onCreate();

        ParseValues a = new ParseValues();

        Parse.initialize(new Parse.Configuration.Builder(this.getApplicationContext())
                .applicationId(a.getAppId())
                .clientKey(a.getClientKey())
                .server(a.getServer())
                .build()
        );
    }
}
