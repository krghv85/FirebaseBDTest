package com.tradexl.firebasedbtest.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Raghav on 28-Aug-17.
 */

public class Dialog {
    private static ProgressDialog pDialog = null;

    public static ProgressDialog showProgressDialog(Context context) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        return pDialog;
    }

    public static void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
