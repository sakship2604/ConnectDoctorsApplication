package com.example.healthmanagementapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddQueryActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    DatabaseHelper databaseHelper;
    EditText  editTextNameCrediCard, editTextCrediCardNumber, editTextTextQuestion, editTextTextSolution;
    SharedPreferences preferences;
    String user_id, msp;
    TextView textAmout;
    Button buttonPay;
    int doctor_id;
    int flag_doctor;
    int query_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_query);
        databaseHelper = new DatabaseHelper(this);
        user_id = getIntent().getStringExtra("patientId");
        Log.d("PatIdIn", user_id);
        editTextTextQuestion = findViewById(R.id.editTextTextQuestion);
        editTextTextSolution = findViewById(R.id.editTextTextSolution);
        textAmout = findViewById(R.id.textAmout);
        editTextNameCrediCard = findViewById(R.id.editTextNameCrediCard);
        editTextCrediCardNumber = findViewById(R.id.editTextCrediCardNumber);
        buttonPay = findViewById(R.id.buttonPay);
        buttonPay.setVisibility(View.GONE);
        query_id = 0;
        flag_doctor = getIntent().getIntExtra("flag_doctor", 0);
        if (flag_doctor == 1) {
            textAmout.setVisibility(View.GONE);
            editTextNameCrediCard.setVisibility(View.GONE);
            editTextCrediCardNumber.setVisibility(View.GONE);
            query_id = getIntent().getIntExtra("position", 0);
            getQueryInfo();
        } else {
            doctor_id = getIntent().getIntExtra("doctor_id", 0);
            editTextTextSolution.setVisibility(View.GONE);
            preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            msp = preferences.getString("msp", "DEFAULT");

            if (!msp.equals("NO")) {
                textAmout.setVisibility(View.GONE);
                editTextNameCrediCard.setVisibility(View.GONE);
                editTextCrediCardNumber.setVisibility(View.GONE);
            }
        }
    }

    public void getQueryInfo() {
        Cursor c = databaseHelper.getQuery(query_id + 1);
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                doctor_id = Integer.parseInt(c.getString(1));
                editTextTextQuestion.setText(c.getString(3));
            }
        }
    }

    public void addQuery(View view) {
        boolean isInserted = false;
        if (validateForm()) {
            if (flag_doctor == 1) {
                isInserted = databaseHelper.updateQuery(editTextTextSolution.getText().toString(), String.valueOf(query_id + 1));
            } else {
                isInserted = databaseHelper.addQueries(doctor_id,Integer.parseInt(user_id),
                        editTextTextQuestion.getText().toString(), editTextTextSolution.getText().toString());
                if (msp.equals("NO"))
                    buttonPay.setVisibility(View.VISIBLE);
            }

            if (isInserted) {
                Toast.makeText(AddQueryActivity.this, "Data added", Toast.LENGTH_LONG).show();
                editTextTextQuestion.setText("");
                editTextTextSolution.setText("");
                functionView();
            } else {
                Toast.makeText(AddQueryActivity.this, "Data not added", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(AddQueryActivity.this, "Please fill the form!", Toast.LENGTH_LONG).show();
        }
    }

    public void functionView() {
        Cursor c = databaseHelper.viewDataQuery();
        StringBuilder str = new StringBuilder();

        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                Log.i("ID ", c.getString(0));
                Log.i("DOCTOR ID ", c.getString(1));
                Log.i("ID ", c.getString(3));
                str.append("\n");
            }
        }
        c.close();
    }

    public boolean validateForm() {
        return editTextTextQuestion.getText().toString().trim().length() > 0;
    }

    public void goToCheckHistory(View view) {

        Intent intent = new Intent(AddQueryActivity.this, PatientQuerryList.class);
        intent.putExtra("patientId", user_id);
        startActivity(intent);
    }

    public void goToCheckPay(View view) {
        Intent intent = new Intent(AddQueryActivity.this, CashierHomeActivity.class);
        intent.putExtra("nameCredicard", editTextNameCrediCard.getText().toString());
        intent.putExtra("credicardNumber", editTextCrediCardNumber.getText().toString());
        startActivity(intent);
    }
}