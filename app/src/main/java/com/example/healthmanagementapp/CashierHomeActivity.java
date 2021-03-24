package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CashierHomeActivity extends AppCompatActivity {

    Button btnShow, btnPayment, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_home);

        btnShow = findViewById(R.id.buttonShowPayments);
        btnLogout = findViewById(R.id.buttonCashierLogout);
        btnPayment = findViewById(R.id.buttonPayment);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //would load the payment data here
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashierHomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //would use the patients email to notify/request payment from them
            }
        });
    }
}