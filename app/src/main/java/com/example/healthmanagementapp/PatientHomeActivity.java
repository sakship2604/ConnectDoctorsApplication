package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PatientHomeActivity extends AppCompatActivity
{
    TextView track_calories, book_appointment, online_help, my_account;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

       // track_calories = findViewById(R.id.trackCalories);
     //   book_appointment = findViewById(R.id.bookAppointment);
    //    online_help = findViewById(R.id.onlineHelp);
      //  my_account = findViewById(R.id.myAccount);

        track_calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                startActivity(new Intent(PatientHomeActivity.this, TrackCalories.class));
            }
        });
    }
}