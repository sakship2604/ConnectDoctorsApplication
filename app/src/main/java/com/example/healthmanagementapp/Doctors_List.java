package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Doctors_List extends AppCompatActivity
{
  DatabaseHelper databaseHelper;
  ListView l1;
  ArrayList<doctors_model> arrayList;
  DoctorsListAdapter doctorsListAdapter;
  RegisterActivity ra = new RegisterActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors__list);
        l1 = findViewById(R.id.listView);
        EditText name = ra.docName;
        //EditText email = ra.docEmail;
        //EditText docphn = ra.docPhone;
        EditText spcl = ra.docSpl;
        //EditText  fees = ra.docFees;

        databaseHelper = new DatabaseHelper(this);
        arrayList = new ArrayList<>();

        loadDataInListView();

    }

    private void loadDataInListView()
    {
      Intent intent = getIntent();
      String whichButton = intent.getStringExtra("whichButton");
      if(whichButton.equalsIgnoreCase("byPostalCode")){
        String postalcode = intent.getStringExtra("postalcode");
        postalcode = postalcode.substring(0,2);
        arrayList = databaseHelper.getDoctorsByPostalCode(postalcode);
      }
      else{
        arrayList = databaseHelper.getAllDoctors();
      }
      doctorsListAdapter = new DoctorsListAdapter(this,arrayList);
      l1.setAdapter(doctorsListAdapter);
      doctorsListAdapter.notifyDataSetChanged();


    }
    public void insert(View v)
    {

    }


}