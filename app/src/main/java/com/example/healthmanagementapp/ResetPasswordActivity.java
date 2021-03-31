package com.example.healthmanagementapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    Button resetPass;
    EditText checkEmail, newpass;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        checkEmail = findViewById(R.id.editTextResetUserEmail);
        newpass = findViewById(R.id.editTextResetUserPassword);

        resetPass = findViewById(R.id.buttonResetUserPassword);

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.resetPassword(checkEmail.getText().toString(), newpass.getText().toString());
            }
        });


    }

    /////////////////////////////////////////
    // to back button in action bar
    ////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}