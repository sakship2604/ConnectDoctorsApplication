package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PatientHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
    }
    public void goToFindDoctor(View view) {
        Intent intent = new Intent(PatientHomeActivity.this, AddQueryActivity.class);
        startActivity(intent);
    }
}