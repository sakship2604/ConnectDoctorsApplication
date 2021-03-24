package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

//setting images to the panel
        logOutadmin = findViewById(R.id.imageViewALogout);
        adoc = findViewById(R.id.imageView3);
        acashier = findViewById(R.id.imageView4);
        apatient = findViewById(R.id.imageView5);

        logOutadmin.setImageResource(R.drawable.logout);
        adoc.setImageResource(R.drawable.doctor);
        acashier.setImageResource(R.drawable.cashier);
        apatient.setImageResource(R.drawable.patient);

      /*  logOutadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });*/

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

        logOutadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}