package com.example.healthmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FindADoctorActivity extends AppCompatActivity {
    EditText etPostalCode;
    Button searchAllDoctors, searchDoctorsCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_a_doctor);
        searchAllDoctors = findViewById(R.id.buttonSearchDoctor);
        searchDoctorsCode = findViewById(R.id.buttonSearchDoctorByPostalCode);

        etPostalCode = findViewById(R.id.postalcode_id);
        String patId = getIntent().getStringExtra("patientId");

        //////////////////////////////////////////////////
        // search all doctors
        /////////////////////////////////////////////////

        searchAllDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindADoctorActivity.this, Doctors_List.class);
                intent.putExtra("whichButton", "allDoctors");
                intent.putExtra("patientId", patId);
                startActivity(intent);
            }
        });

        ////////////////////////////////////////////////
        // to seacrh doctors by  code
        ///////////////////////////////////////////////

        searchDoctorsCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postalCode = etPostalCode.getText().toString();
                if (!postalCode.isEmpty()) {
                    String patientId = getIntent().getStringExtra("patientId");
                    Intent intent = new Intent(FindADoctorActivity.this, Doctors_List.class);
                    intent.putExtra("whichButton", "byPostalCode");
                    intent.putExtra("postalcode", postalCode);
                    intent.putExtra("patientId", patientId);
                    intent.putExtra("patientId", patId);
                    startActivity(intent);
                } else {
                    Log.i("TEST", "I am in else");
                    Toast.makeText(getApplicationContext(), "Please enter postal code", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    /////////////////////////////////////////
    // to back button in action bar
    ////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}