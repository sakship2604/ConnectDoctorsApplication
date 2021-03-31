package com.example.healthmanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.function.LongFunction;

public class DoctorHomeActivity extends AppCompatActivity {

    TextView docinfo;
    DatabaseHelper databaseHelper;
    StringBuilder docInfoString;
    TextView viewAppoint;
    String docId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        databaseHelper = new DatabaseHelper(this);
        docinfo = findViewById(R.id.textViewDocInfo);
        viewAppoint = findViewById(R.id.textViewViewAppointments);
        String email = getIntent().getExtras().getString("doctorEmail");
        docInfoString = new StringBuilder();
        try{
            Cursor cursor = databaseHelper.getDoctorDetails(email);
            while (cursor.moveToNext()) {
                docId = cursor.getString(0);
                docInfoString.append(" Id: ").append(cursor.getString(0)).append("\n");
                docInfoString.append(" Name: ").append(cursor.getString(1)).append("\n");
                docInfoString.append(" Email: ").append(cursor.getString(2)).append("\n");
                docInfoString.append(" Postal Code: ").append(cursor.getString(4)).append("\n");
                docInfoString.append(" Phone: ").append(cursor.getString(5)).append("\n");
                docInfoString.append(" Speciality: ").append(cursor.getString(6)).append("\n");
                docInfoString.append(" Fees: ").append(cursor.getString(7)).append("\n");
            }
        }
        catch (Exception e){
            Log.d("Doc", e.getMessage());
        }
        docinfo.setText(docInfoString);
        viewAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomeActivity.this, ListDoctorAppointmentActivity.class);
                Log.d("DOCID", String.valueOf(docId));
                intent.putExtra("doctor_id", docId);
                intent.putExtra("VisibilityFlag", 1);
                startActivity(intent);
            }
        });
    }

    public void goToListQuery(View view) {
        Intent intent = new Intent(DoctorHomeActivity.this, ListQueriesActivity.class);
        intent.putExtra("flag_doctor", 1);
        startActivity(intent);
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