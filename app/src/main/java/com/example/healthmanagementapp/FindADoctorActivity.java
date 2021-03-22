package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindADoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_a_doctor);
        Button search = findViewById(R.id.buttonSearchDoctor);
        Button back = findViewById(R.id.buttonBack);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(FindADoctorActivity.this,Doctors_List.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(FindADoctorActivity.this,PatientHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}