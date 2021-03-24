package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddQueryActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText editTextPatientId,editTextDoctorId,editTextTextQuestion,editTextTextSolution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_query);
        databaseHelper = new DatabaseHelper(this);

        editTextPatientId = findViewById(R.id.editTextPatientId);
        editTextDoctorId = findViewById(R.id.editTextDoctorId);
        editTextTextQuestion = findViewById(R.id.editTextTextQuestion);
        editTextTextSolution = findViewById(R.id.editTextTextSolution);
        //if(typeUser.equals("d") || )
        //editTextTextSolution.setVisibility(View.GONE);
    }

    public void addQuery(View view) {

        boolean isInserted;

        if(validateForm()){
            isInserted = databaseHelper.addQueries(Integer.parseInt(editTextPatientId.getText().toString()),
                    Integer.parseInt(editTextDoctorId.getText().toString()),
                    editTextTextQuestion.getText().toString(), editTextTextSolution.getText().toString());
            if(isInserted){
                Toast.makeText(AddQueryActivity.this, "Data added", Toast.LENGTH_LONG).show();
                editTextPatientId.setText("");
                editTextDoctorId.setText("");
                editTextTextQuestion.setText("");
                editTextTextSolution.setText("");
                functionView();
            }else{
                Toast.makeText(AddQueryActivity.this,"Data not added",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(AddQueryActivity.this,"Please fill the form!",Toast.LENGTH_LONG).show();
        }

    }


    public void functionView(){
        Cursor c = databaseHelper.viewDataQuery();
        StringBuilder str = new StringBuilder();

        if(c.getCount()>0){
            while(c.moveToNext()){

                Log.i("ID ", c.getString(0));
                Log.i("DOCTOR ID ", c.getString(1));
                Log.i("ID ", c.getString(3));

                str.append("\n");
            }
        }
        c.close();
    }

    public boolean validateForm(){
        if (editTextTextQuestion.getText().toString().trim().length() > 0)
            return true;
        else
            return false;
    }

    public void goToCheckHistory(View view) {
        Intent intent = new Intent(AddQueryActivity.this, ListQueriesActivity.class);
        startActivity(intent);
    }
}