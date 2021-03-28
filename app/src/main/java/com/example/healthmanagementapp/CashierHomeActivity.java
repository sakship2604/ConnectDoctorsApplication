package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CashierHomeActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Button btnShow, btnPayment, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_home);

        databaseHelper = new DatabaseHelper(this);
        btnShow = findViewById(R.id.buttonShowPayments);
        btnLogout = findViewById(R.id.buttonCashierLogout);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpay = new Intent(CashierHomeActivity.this, Payments_List.class);
                startActivity(intentpay);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}