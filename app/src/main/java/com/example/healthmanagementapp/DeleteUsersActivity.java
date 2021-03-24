package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class DeleteUsersActivity extends AppCompatActivity {

    EditText delemail;
    Button btndel;
    RadioButton radDelPat, radDelDoc, radDelCashier;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_users);

        radDelPat = findViewById(R.id.radioButtonDELpat);
        radDelDoc = findViewById(R.id.radioButtonDELdoc);
        radDelCashier = findViewById(R.id.radioButtonDELcashier);
        delemail = findViewById(R.id.editTextDelEmail);
        btndel = findViewById(R.id.buttonDEL);
        databaseHelper = new DatabaseHelper(this);


        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radDelPat.isChecked()) {
                    databaseHelper.deletePatient(delemail.getText().toString());
                    Toast.makeText(DeleteUsersActivity.this, "deleted Patient Record", Toast.LENGTH_SHORT).show();
                } else if (radDelDoc.isChecked()) {
                    databaseHelper.deleteDoctor(delemail.getText().toString());
                    Toast.makeText(DeleteUsersActivity.this, "deleted Doctor Record", Toast.LENGTH_SHORT).show();
                } else if (radDelCashier.isChecked()) {
                    databaseHelper.deleteCashier(delemail.getText().toString());
                    Toast.makeText(DeleteUsersActivity.this, "deleted Cashier Record", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}