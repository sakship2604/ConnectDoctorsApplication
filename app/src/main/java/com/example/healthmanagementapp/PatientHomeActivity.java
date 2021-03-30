package com.example.healthmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.healthmanagementapp.RegisterActivity.MyPREFERENCES;

public class PatientHomeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    ImageView track_calories, find_doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        if (sharedPreferences.getInt("admin", 0) == 1) {
            Intent intent = new Intent(this, ResetPasswordActivity.class);
            startActivity(intent);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("admin", 0);
            editor.apply();
        }
        find_doctor = findViewById(R.id.imageViewFindDoctor);
        track_calories = findViewById(R.id.imageViewTrackCalories);
        find_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Pat", "click");
                String patientId = getIntent().getStringExtra("patientId");
                Intent intent = new Intent(PatientHomeActivity.this, FindADoctorActivity.class);
                intent.putExtra("patientId", patientId);
                startActivity(intent);
            }
        });

        track_calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientHomeActivity.this, TrackCalories.class));
            }
        });
    }

// for back navigation
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