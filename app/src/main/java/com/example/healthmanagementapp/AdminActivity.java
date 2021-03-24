package com.example.healthmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
    LinearLayout lvDoctor , lvCashier, lvPatient;
    ImageView logOutadmin, adoc,acashier,apatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//setting images to the panel
        adoc = findViewById(R.id.imageView3);
        acashier = findViewById(R.id.imageView4);
        apatient = findViewById(R.id.imageView5);

        adoc.setImageResource(R.drawable.doctor);
        acashier.setImageResource(R.drawable.cashier);
        apatient.setImageResource(R.drawable.patient);

        // TO MANAGE ADMIN PANEL
        lvDoctor = findViewById(R.id.AdminDoctor);
        lvCashier = findViewById(R.id.AdminCashier);
        lvPatient = findViewById(R.id.AdminPatient);

        lvDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,RegisterActivity.class);
                intent.putExtra("layoutToShow",1);
                startActivity(intent);
            }
        });
        lvCashier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,RegisterActivity.class);
                intent.putExtra("layoutToShow",2);
                startActivity(intent);
            }
        });
        lvPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,RegisterActivity.class);
                intent.putExtra("layoutToShow",3);
                startActivity(intent);
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