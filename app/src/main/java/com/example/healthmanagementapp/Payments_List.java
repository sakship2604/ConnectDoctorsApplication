package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class Payments_List extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView l1;
    List<Payment_Model> arrayList;
    PaymentListAdapter paymentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payments_list);
        l1 = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);
        arrayList = new ArrayList<>();
        loadDataInListView();
    }

    private void loadDataInListView() {
        arrayList = databaseHelper.getAllbills();
        paymentListAdapter = new PaymentListAdapter(this, arrayList);
        l1.setAdapter(paymentListAdapter);
        paymentListAdapter.notifyDataSetChanged();
    }
}