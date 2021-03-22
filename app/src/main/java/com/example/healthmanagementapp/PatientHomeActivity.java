package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PatientHomeActivity extends AppCompatActivity
{
    ImageView track_calories, find_doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);


        find_doctor = findViewById(R.id.imageViewFindDoctor);
        track_calories = findViewById(R.id.imageViewTrackCalories);


        track_calories.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                startActivity(new Intent(PatientHomeActivity.this, TrackCalories.class));
            }
        });
        find_doctor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                startActivity(new Intent(PatientHomeActivity.this, FindADoctorActivity.class));
            }
        });

    }
}