package com.tradexl.firebasedbtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tradexl.firebasedbtest.model.SignUpModel;
import com.tradexl.firebasedbtest.utils.Dialog;
import com.tradexl.firebasedbtest.utils.SharePrefrence;

/**
 * Created by Raghav on 28-Aug-17.
 */

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText passward;
    private Button login;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId = null;
    private String emailid = null;
    private String pass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("signup");
        email = (EditText) findViewById(R.id.email);
        passward = (EditText) findViewById(R.id.passward);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailid = email.getText().toString().trim();
                pass = passward.getText().toString().trim();
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
                getData();
            }
        });

    }

    private void getData() {
        Dialog.showProgressDialog(Login.this);
        userId = mFirebaseDatabase.push().getKey();
        if (userId != null) {
            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                        SignUpModel signUpModel = singleSnapshot.getValue(SignUpModel.class);
                        if (signUpModel.getEmail().equals(emailid) && signUpModel.getPassword().equals(pass)) {
                            SharePrefrence.setSharedPreferences(Login.this,"Username",signUpModel.getEmail().toString());
                            SharePrefrence.setSharedPreferences(Login.this,"Userpass",signUpModel.getPassword().toString());
                            Intent intent=new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                            Dialog.hideProgressDialog();
                            finish();
                        }else {
                            email.setError("Incorrect User Name Password");
                            passward.setError("Incorrect User Name Password");
                            email.requestFocus();
                            passward.requestFocus();
                            Dialog.hideProgressDialog();
                        }
//                        Toast.makeText(Login.this, signUpModel.getFname().toString(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

}
