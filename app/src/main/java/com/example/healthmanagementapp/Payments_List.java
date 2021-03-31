package com.example.healthmanagementapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    /////////////////////////////////////////
    // to back button in action bar
    ////////////////////////////////////////
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