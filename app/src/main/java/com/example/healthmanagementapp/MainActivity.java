package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    RadioButton radAdmin, radPatient,radDoctor,radCashier;
    EditText username,pass;
    Button btnULogin, btnSignUp, btnALogin;
    TextView register, resetPass;
    LinearLayout layoutAdmin, layoutUser;
    DatabaseHelper databaseHelper;
    //firebase connection
   // private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// object to connect firebase
        //auth = FirebaseAuth.getInstance();


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
                if(radPatient.isChecked()) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    intent.putExtra("layoutToShow", 3);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Only patients canregister", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //for user to set new password
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        btnULogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radPatient.isChecked()) {
                    boolean result = databaseHelper.getPatient(username.getText().toString(), pass.getText().toString());
                    Toast.makeText(MainActivity.this, String.valueOf("Successfully Logged In"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, PatientHomeActivity.class);
                    startActivity(intent);
                } else if (radDoctor.isChecked()) {
                    boolean result = databaseHelper.getDoctor(username.getText().toString(), pass.getText().toString());
                    Toast.makeText(MainActivity.this, String.valueOf("Successfully Logged In"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DoctorHomeActivity.class);
                    startActivity(intent);
                } else if (radCashier.isChecked()) {
                    boolean result = databaseHelper.getCashier(username.getText().toString(), pass.getText().toString());
                    Toast.makeText(MainActivity.this, String.valueOf("Successfully Logged In"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CashierHomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        radUsers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(radAdmin.isChecked()){
                    layoutAdmin.setVisibility(View.VISIBLE);
                    layoutUser.setVisibility(View.GONE);

                }
                else if(radPatient.isChecked() || radDoctor.isChecked() || radCashier.isChecked()){
                    layoutUser.setVisibility(View.VISIBLE);
                    layoutAdmin.setVisibility(View.GONE);
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radAdmin.isChecked()){
                    databaseHelper.addAdmin(username.getText().toString(), pass.getText().toString());
                }
            }
        });

        btnALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radAdmin.isChecked()){
                    boolean result = databaseHelper.getAdmin(username.getText().toString(), pass.getText().toString());
                    Toast.makeText(MainActivity.this, String.valueOf("Successfully Logged In"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,AdminActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    /*public void Signup(){
        String email = username.getText().toString();
        String password = pass.getText().toString();
        if (email.length() > 0 && password.length() > 0){
            if(password.length()>=9){
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show();

                            } else {
                                // If sign in fails, display a message to the user.
                                // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(this, "There's an exiting account with same Email.",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else{
                Toast.makeText(this, "Password should at least have 9 characters.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Oh no, seems like you missed a spot!", Toast.LENGTH_LONG).show();
        }
    }
    public void Login(){
        String email = username.getText().toString();
        String password = pass.getText().toString();
        if(email.length() > 0 && password.length() > 0){
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if(task.isSuccessful()){
                    Toast.makeText(this, "Successfully Logged In",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this,"Please enter email and password!",Toast.LENGTH_SHORT).show();
        }
    }*/
}