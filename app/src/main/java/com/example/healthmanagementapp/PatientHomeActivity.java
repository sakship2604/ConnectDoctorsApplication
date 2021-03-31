package com.example.healthmanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.healthmanagementapp.RegisterActivity.MyPREFERENCES;

public class PatientHomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ImageView track_calories, find_doctor;
    TextView patientinfo;
    StringBuilder patInfoString;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        patInfoString = new StringBuilder();
        databaseHelper = new DatabaseHelper(this);
        patientinfo = findViewById(R.id.textViewPatDisplay);
        String email = getIntent().getExtras().getString("patEmail");
        patInfoString = new StringBuilder();
        patInfoString.append("");
        try{
            Cursor cursor = databaseHelper.getPatientDetails(email);
            Log.d("ART", String.valueOf(cursor.getCount()));
            while (cursor.moveToNext()) {
                patInfoString.append(" Id: ").append(cursor.getString(0)).append("\n");
                patInfoString.append(" Name: ").append(cursor.getString(1)).append("\n");
                patInfoString.append(" Email: ").append(cursor.getString(2)).append("\n");
                patInfoString.append(" Postal Code: ").append(cursor.getString(4)).append("\n");
                patInfoString.append(" Phone: ").append(cursor.getString(5)).append("\n");
                patInfoString.append(" Height: ").append(cursor.getString(6)).append("\n");
                patInfoString.append(" Weight: ").append(cursor.getString(7)).append("\n");
                patInfoString.append(" Age: ").append(cursor.getString(8)).append("\n");
                patInfoString.append(" Medication: ").append(cursor.getString(12)).append("\n");
                patInfoString.append(" Diseases: ").append(cursor.getString(13)).append("\n");
            }
        }
        catch (Exception e){
            Log.d("Pat", e.getMessage());
        }
        patientinfo.setText(patInfoString);

        // ////////////////////////////////////////
        // to set for reset pass if new login
        //////////////////////////////////////////
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

        // to find doctors
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

        //to find calories
        track_calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientHomeActivity.this, TrackCalories.class));
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