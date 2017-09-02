package com.tradexl.firebasedbtest.model;

import android.app.Application;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;

import static android.content.ContentValues.TAG;

/**
 * Created by Raghav on 30-Aug-17.
 */

public class MyApp extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            throw new NullPointerException();
        } catch (NullPointerException ex) {
            FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
            FirebaseCrash.report(ex);
        }
    }
}
