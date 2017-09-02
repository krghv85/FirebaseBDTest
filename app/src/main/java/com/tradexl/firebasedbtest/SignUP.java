package com.tradexl.firebasedbtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tradexl.firebasedbtest.model.SignUpModel;
import com.tradexl.firebasedbtest.utils.Dialog;

/**
 * Created by Raghav on 26-Aug-17.
 */

public class SignUP extends AppCompatActivity {
    private static final String TAG = SignUP.class.getSimpleName();
    private Button submit;
    private Button login;
    private EditText fname;
    private EditText lname;
    private EditText mobile;
    private EditText email;
    private EditText passward;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("signup");

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        mobile = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email);
        passward = (EditText) findViewById(R.id.passward);
        submit = (Button) findViewById(R.id.submit);
        login = (Button) findViewById(R.id.login);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String frstname = fname.getText().toString().trim();
                String lastname = lname.getText().toString().trim();
                String mobilenumber = mobile.getText().toString().trim();
                String emailid = email.getText().toString().trim();
                String pass = passward.getText().toString().trim();

                if (frstname.isEmpty()) {
                    fname.setError("Enter First Name");
                    fname.requestFocus();
                    return;
                }
                if (lastname.isEmpty()) {
                    lname.setError("Enter Last Name");
                    lname.requestFocus();
                    return;
                }
                if (mobilenumber.isEmpty()) {
                    mobile.setError("Enter Mobile");
                    mobile.requestFocus();
                    return;
                }
                if (emailid.isEmpty()) {
                    email.setError("Enter Email Id");
                    email.requestFocus();
                    return;
                }
                if (pass.isEmpty()) {
                    passward.setError("Enter Password");
                    passward.requestFocus();
                    return;
                }
                createUser(frstname, lastname, mobilenumber, emailid, pass);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUP.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void createUser(String fname, String lname, String mobile, String email, String pass) {
        Dialog.showProgressDialog(this);
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }
        System.out.print("userid" + userId);
        Log.i(TAG, userId);

        SignUpModel signUpModel = new SignUpModel(fname, lname, mobile, email, pass);

        Task<Void> base = mFirebaseDatabase.child(userId).setValue(signUpModel);
        base.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    showToast("Sign Up Succssfully");
                    Dialog.hideProgressDialog();
                    Intent intent=new Intent(SignUP.this,Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    showToast("Sign Up Failded");

                }
            }
        });
    }

    public void showToast(String msg) {
        Toast.makeText(SignUP.this, msg, Toast.LENGTH_SHORT).show();
    }
}
