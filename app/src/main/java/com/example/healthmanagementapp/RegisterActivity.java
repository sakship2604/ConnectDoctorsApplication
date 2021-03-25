package com.example.healthmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    int flag =0;
    DatabaseHelper databaseHelper;
    LinearLayout layoutDoctor,layoutPatient,layoutImg, layoutCashier;
    ImageView aImage;
    Button regBack, addPat,addDoc,addCash;
    Button delPat, delDoc, delCashier;
    Button updatePat, updateDoc, updateCashier;
    EditText patName, patEmail, patPass, patCode, patPhone, patHeight, patWeight, patAge, patMedi, patDiseases;
    RadioButton radMale, radFemale, radMSP, radNoMSP;
    String gender = "";
    String msp = "";
    EditText docName, docEmail, docPass, docCode, docPhone, docSpl, docFees;
    EditText cashName, cashEmail, cashPass;
    SharedPreferences preferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        flag = getIntent().getIntExtra("layoutToShow",0);

        databaseHelper = new DatabaseHelper(this);

        aImage = findViewById(R.id.imageViewUserType);
        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        addPat = findViewById(R.id.buttonAddPat);
        addDoc = findViewById(R.id.buttonAddDoctor);
        addCash = findViewById(R.id.buttonAddCashier);

        updatePat = findViewById(R.id.buttonUpdatePat);
        updateDoc = findViewById(R.id.buttonUpdateDoctor);
        updateCashier = findViewById(R.id.buttonUpdateCashier);

        delPat = findViewById(R.id.buttonDeletePat);
        delDoc = findViewById(R.id.buttonDeleteDoctor);
        delCashier = findViewById(R.id.buttonDeleteCashier);

        layoutDoctor = findViewById(R.id.linearLayoutDoctor);
        layoutPatient = findViewById(R.id.linearLayoutPatient);
        layoutCashier = findViewById(R.id.linearLayoutCashier);

        // patient objects
        patName = findViewById(R.id.editTextPatName);
        patEmail = findViewById(R.id.editTextPatEmail);
        patPass = findViewById(R.id.editTextPatPass);
        patCode = findViewById(R.id.editTextPatPostalCode);
        patPhone = findViewById(R.id.editTextPatPhone);
        patHeight = findViewById(R.id.editTextHeight);
        patWeight = findViewById(R.id.editTextWeight);
        patAge = findViewById(R.id.editTextAge);
        patMedi = findViewById(R.id.editTextMedication);
        patDiseases = findViewById(R.id.editTextDiseases);

        radMale = findViewById(R.id.radioButtonMale);
        radFemale = findViewById(R.id.radioButtonFemale);
        radMSP = findViewById(R.id.radioButtonMSP);
        radNoMSP = findViewById(R.id.radioButtonNOMSP);

        //doctor objects
        docName = findViewById(R.id.editTextDocName);
        docEmail = findViewById(R.id.editTextDocEmail);
        docPass = findViewById(R.id.editTextDocPassword);
        docPhone = findViewById(R.id.editTextDocPhone);
        docCode = findViewById(R.id.editTextDocPostalCode);
        docSpl = findViewById(R.id.editTextSpeciality);
        docFees = findViewById(R.id.editTextfees);

        //cashier objects
        cashName = findViewById(R.id.editTextCashierName);
        cashEmail = findViewById(R.id.editTextCashierEmail);
        cashPass = findViewById(R.id.editTextCashierPassword);

        // to switch layout according to selection
        int layoutToShow = getIntent().getIntExtra("layoutToShow",0);

        switch (layoutToShow){
            case 1:
                aImage.setImageResource(R.drawable.doctor);
                getSupportActionBar().setTitle("Admin Panel - Doctor");
                layoutCashier.setVisibility(View.GONE);
                layoutPatient.setVisibility(View.GONE);
                layoutDoctor.setVisibility(View.VISIBLE);
                break;
            case 2:
                aImage.setImageResource(R.drawable.cashier);
                getSupportActionBar().setTitle("Admin Panel - Cashier");
                layoutDoctor.setVisibility(View.GONE);
                layoutPatient.setVisibility(View.GONE);
                layoutCashier.setVisibility(View.VISIBLE);
                break;
            case 3:
                aImage.setImageResource(R.drawable.patient);
                getSupportActionBar().setTitle("Admin Panel - Patient");
                layoutDoctor.setVisibility(View.GONE);
                layoutCashier.setVisibility(View.GONE);
                layoutPatient.setVisibility(View.VISIBLE);
                break;
        }

        // to add patients in database
        addPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radMale.isChecked()){
                    gender = "MALE";
                }
                else{
                    gender = "FEMALE";
                }

                if(radMSP.isChecked()){
                    msp = "YES";
                }
                else{
                    msp = "NO";
                }
                databaseHelper.addPatient(patName.getText().toString(),
                        patEmail.getText().toString(),
                        patPass.getText().toString(),patCode.getText().toString(),patPhone.getText().toString()
                        ,Double.parseDouble(patHeight.getText().toString()), Double.parseDouble(patWeight.getText().toString()),
                        gender, 0, Integer.parseInt(patAge.getText().toString()), msp, patMedi.getText().toString(),patDiseases.getText().toString());
                Toast.makeText(RegisterActivity.this, "Patient added", Toast.LENGTH_SHORT).show();
                if(layoutToShow == 3){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("admin",1);
                    editor.apply();
                }
            }
        });
        // to add doctors
        addDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addDoctor(docName.getText().toString(),docEmail.getText().toString(),
                        docPass.getText().toString(),docCode.getText().toString(),docPhone.getText().toString(),docSpl.getText().toString(),docFees.getText().toString());
                Toast.makeText(RegisterActivity.this, "Doctor added", Toast.LENGTH_SHORT).show();
            }
        });
        // to add cashiers
        addCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addCashier(cashName.getText().toString(),cashEmail.getText().toString(),
                        cashPass.getText().toString());
                Toast.makeText(RegisterActivity.this, "Cashier Added", Toast.LENGTH_SHORT).show();
            }
        });

        //to delete patient
        delPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(RegisterActivity.this,DeleteUsersActivity.class);
               startActivity(intent);
            }
        });
        // to delete doctor
        delDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,DeleteUsersActivity.class);
                startActivity(intent);
            }
        });
        // to delete cashier
        delCashier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,DeleteUsersActivity.class);
                startActivity(intent);
            }
        });

        //to update patients
        updatePat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radMale.isChecked()){
                    gender = "MALE";
                }
                else{
                    gender = "FEMALE";
                }

                if(radMSP.isChecked()){
                    msp = "YES";
                }
                else{
                    msp = "NO";
                }
                databaseHelper.updatePatient(patName.getText().toString(),
                        patEmail.getText().toString(),
                        patCode.getText().toString(),
                        patPhone.getText().toString(),
                        Double.parseDouble(patHeight.getText().toString()),
                        Double.parseDouble(patWeight.getText().toString()),
                        gender,
                        Integer.parseInt(patAge.getText().toString()),
                        msp,
                        patMedi.getText().toString(),
                        patDiseases.getText().toString());
                Toast.makeText(RegisterActivity.this, "Patient updated", Toast.LENGTH_SHORT).show();
            }
        });

        updateDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateDoctor((docName.getText().toString()),docEmail.getText().toString(),docCode.getText().toString(),docPhone.getText().toString(),docSpl.getText().toString(),docFees.getText().toString());
            }
        });

        updateCashier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateCashier(cashName.getText().toString(),cashEmail.getText().toString());
            }
        });
    }

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