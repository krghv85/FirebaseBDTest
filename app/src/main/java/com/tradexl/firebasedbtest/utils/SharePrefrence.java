package com.tradexl.firebasedbtest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Raghav on 28-Aug-17.
 */

public class SharePrefrence {
    private static final String MY_PREFS_NAME = "MYDATA";
    private static SharedPreferences sharedpreferences;
    ;

    private SharePrefrence() {
    }

    public static String getSharedPreference(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString(key, null);
        if (restoredText != null) {
        }
        return restoredText;

    }


    public static void setSharedPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }


}
