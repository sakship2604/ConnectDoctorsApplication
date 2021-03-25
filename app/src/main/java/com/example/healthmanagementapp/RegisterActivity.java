package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    LinearLayout layoutDoctor,layoutPatient,layoutImg, layoutCashier;
    ImageView aImage;
    Button regBack, addPat,addDoc,addCash;
    EditText patName, patEmail, patPass, patCode, patPhone, patHeight, patWeight, patAge, patMedi, patDiseases;
    RadioButton radMale, radFemale, radMSP, radNoMSP;

    EditText docName, docEmail, docPass, docCode, docPhone, docSpl, docFees;
    EditText cashName, cashEmail, cashPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);

        aImage = findViewById(R.id.imageViewAdminReg);
        aImage.setImageResource(R.drawable.admin);

        regBack = findViewById(R.id.buttonAdminBack);
        addPat = findViewById(R.id.buttonAddPat);
        addDoc = findViewById(R.id.buttonAddDoctor);
        addCash = findViewById(R.id.buttonAddCashier);

        layoutImg = findViewById(R.id.linearLayout);
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
                layoutCashier.setVisibility(View.GONE);
                layoutPatient.setVisibility(View.GONE);
                layoutDoctor.setVisibility(View.VISIBLE);
                break;
            case 2:

                layoutDoctor.setVisibility(View.GONE);
                layoutPatient.setVisibility(View.GONE);
                layoutCashier.setVisibility(View.VISIBLE);
                break;
            case 3:

                layoutDoctor.setVisibility(View.GONE);
                layoutCashier.setVisibility(View.GONE);
                layoutPatient.setVisibility(View.VISIBLE);
                break;
        }
        // go to previous screen
        regBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // to add patients in database
        addPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = "";
                if(radMale.isChecked()){
                    gender = "MALE";
                }
                else{
                    gender = "FEMALE";
                }
                String msp = "";
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
            }
        });

        // to add doctors
        addDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                databaseHelper.addDoctor(docName.getText().toString(),docEmail.getText().toString(),
                        docPass.getText().toString(),docCode.getText().toString(),docPhone.getText().toString(),docSpl.getText().toString(),docFees.getText().toString());
            }
        });
        // to  add cashiers
        addCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addCashier(cashName.getText().toString(),cashEmail.getText().toString(),
                        cashPass.getText().toString());
            }
        });
    }


}