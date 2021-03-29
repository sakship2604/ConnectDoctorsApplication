package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class DoctorHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
    }

    public void goToListQuery(View view) {
        Intent intent = new Intent(DoctorHomeActivity.this, ListQueriesActivity.class);
        intent.putExtra("flag_doctor", 1);
        startActivity(intent);
    }
}