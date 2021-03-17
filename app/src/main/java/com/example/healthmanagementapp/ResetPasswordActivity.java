package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    String TAG;
    boolean connected = false;
    EditText resetEmail;
    ImageView imgAdmin, imgUser;
    Button resetPassword;
    LinearLayout layoutResetAdmin;
    private FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        resetEmail = findViewById(R.id.editTextResetEmail);
        imgAdmin = findViewById(R.id.imageViewAdmin);
        imgUser = findViewById(R.id.imageViewUser);
        resetPassword = findViewById(R.id.buttonSend);
        layoutResetAdmin = findViewById(R.id.linearLayoutAdmin);
        mauth = FirebaseAuth.getInstance();


        imgAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutResetAdmin.setVisibility(View.VISIBLE);

            }
        });
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkConnection()){
                    ResetPassword();
                }
                else {
                    Toast.makeText(ResetPasswordActivity.this, "Please connect to the Internet!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void ResetPassword() {
        String emailAddress = resetEmail.getText().toString();
        if (emailAddress.length() > 0) {
            mauth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            Toast.makeText(ResetPasswordActivity.this, "Email sent!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, "Please enter valid email!", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(ResetPasswordActivity.this, "Please enter valid email!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }


}