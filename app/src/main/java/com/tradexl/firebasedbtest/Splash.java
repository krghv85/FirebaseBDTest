package com.tradexl.firebasedbtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.tradexl.firebasedbtest.utils.SharePrefrence;

/**
 * Created by Raghav on 28-Aug-17.
 */

public class Splash extends AppCompatActivity {
    private static final long delayTime = 3000;
    private String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                username = SharePrefrence.getSharedPreference(Splash.this, "Username");
                if (username != null) {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Splash.this, SignUP.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, delayTime);


    }
}
