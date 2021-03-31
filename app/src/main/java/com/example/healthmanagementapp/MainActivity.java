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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    RadioButton radAdmin, radPatient, radDoctor, radCashier;
    EditText username, pass;
    Button btnULogin, btnSignUp, btnALogin;
    TextView register, resetPass;
    LinearLayout layoutAdmin, layoutUser;
    DatabaseHelper databaseHelper;
    int FLAG = 0;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        databaseHelper = new DatabaseHelper(this);
        btnALogin = findViewById(R.id.buttonAdminLogin);
        btnSignUp = findViewById(R.id.buttonAdminSignup);
        btnULogin = findViewById(R.id.buttonLogin);

        register = findViewById(R.id.textViewRegister);
        resetPass = findViewById(R.id.textViewForgotPassword);

        radAdmin = findViewById(R.id.radioButtonAdmin);
        radPatient = findViewById(R.id.radioButtonPatient);
        radDoctor = findViewById(R.id.radioButtonDoctor);
        radCashier = findViewById(R.id.radioButtonCashier);
        RadioGroup radUsers = findViewById(R.id.radioGroupUsers);

        username = findViewById(R.id.editTextLoginUsername);
        pass = findViewById(R.id.editTextLoginPassword);

        layoutUser = findViewById(R.id.linearLayoutUser);
        layoutAdmin = findViewById(R.id.linearLayoutAdmin);

        layoutAdmin.setVisibility(View.GONE);
        layoutUser.setVisibility(View.GONE);

        // when patient wants to register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPatient.isChecked()) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    intent.putExtra("layoutToShow", 3);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Only patients can register", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //for user to set new password
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
        ////////////////////////////////////////////////
        // to login as patient doctor or cashier
        ///////////////////////////////////////////////

        btnULogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPatient.isChecked()) {
                    boolean result = databaseHelper.getPatient(username.getText().toString(), pass.getText().toString());
                    FLAG = 1;
                    String patId = "";
                    String patEmail = "";
                    if (result) {
                        try (Cursor c = databaseHelper.getPatientId(username.getText().toString(), pass.getText().toString())) {
                            if (c.getCount() > 0) {
                                while (c.moveToNext()) {
                                    Log.i("ID ", c.getString(0));
                                    Log.i("Name ", c.getString(1));
                                    Log.i("MSP ", c.getString(11));
                                    patId = c.getString(0);
                                    patEmail = c.getString(2);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("user_id", c.getString(0));
                                    editor.putString("msp", c.getString(11));
                                    editor.apply();
                                }
                                Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, PatientHomeActivity.class);
                                intent.putExtra("patientId", patId);
                                intent.putExtra("Flag", FLAG);
                                intent.putExtra("patEmail", patEmail);
                                startActivity(intent);
                            }
                        }
                    }
                } else if (radDoctor.isChecked()) {
                    boolean result = databaseHelper.getDoctor(username.getText().toString(), pass.getText().toString());
                    String docEmail = "";
                    FLAG = 1;
                    if (result) {
                        try (Cursor c = databaseHelper.getDoctorId(username.getText().toString(), pass.getText().toString())) {
                            if (c.getCount() > 0) {
                                while (c.moveToNext()) {
                                    Log.i("ID ", c.getString(0));
                                    Log.i("Name ", c.getString(1));
                                    docEmail = c.getString(2);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("doctor_id", c.getString(0));
                                    editor.apply();
                                }

                                Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, DoctorHomeActivity.class);
                                intent.putExtra("doctorEmail", docEmail);
                                startActivity(intent);
                            }
                        }
                    }
                } else if (radCashier.isChecked()) {
                    boolean result = databaseHelper.getCashier(username.getText().toString(), pass.getText().toString());
                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CashierHomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        /////////////////////////////////////////////
        // different layout switch
        /////////////////////////////////////////////////
        radUsers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radAdmin.isChecked()) {
                    layoutAdmin.setVisibility(View.VISIBLE);
                    layoutUser.setVisibility(View.GONE);

                } else if (radPatient.isChecked() || radDoctor.isChecked() || radCashier.isChecked()) {
                    layoutUser.setVisibility(View.VISIBLE);
                    layoutAdmin.setVisibility(View.GONE);
                }
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////
        // admin signup and login
        ////////////////////////////////////////////////////////////////////////////////////////
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAdmin.isChecked()) {
                    databaseHelper.addAdmin(username.getText().toString(), pass.getText().toString());
                }
            }
        });

        btnALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radAdmin.isChecked()) {
                    boolean result = databaseHelper.getAdmin(username.getText().toString(), pass.getText().toString());
                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

}