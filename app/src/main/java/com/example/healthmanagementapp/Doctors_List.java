package com.example.healthmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Doctors_List extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ListView l1;
    ArrayList<doctors_model> arrayList;
    DoctorsListAdapter doctorsListAdapter;
    RegisterActivity ra = new RegisterActivity();
    String patId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors__list);
        l1 = findViewById(R.id.listView);
        EditText name = ra.docName;

        EditText spcl = ra.docSpl;

        patId = getIntent().getStringExtra("patientId");
        databaseHelper = new DatabaseHelper(this);
        arrayList = new ArrayList<>();
        loadDataInListView();

    }

    private void loadDataInListView() {
        Intent intent = getIntent();
        String whichButton = intent.getStringExtra("whichButton");
        if (whichButton.equalsIgnoreCase("byPostalCode")) {
            String postalcode = intent.getStringExtra("postalcode");
            arrayList = databaseHelper.getDoctorsByPostalCode(postalcode);
        } else {
            arrayList = databaseHelper.getAllDoctors();
        }
        doctorsListAdapter = new DoctorsListAdapter(this, arrayList, patId);
        l1.setAdapter(doctorsListAdapter);
        doctorsListAdapter.notifyDataSetChanged();

    }

    public void insert(View v) {

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