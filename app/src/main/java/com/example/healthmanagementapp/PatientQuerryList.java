package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PatientQuerryList extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    String patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_queries);
        databaseHelper = new DatabaseHelper(this);
        patientId = getIntent().getStringExtra("patientId");
        functionView();
    }

    public void functionView() {
        Cursor c;
        if(patientId != "-1") {
            c = databaseHelper.viewPatientQuery(patientId);
            Log.d("Patid", patientId);
            StringBuilder str = new StringBuilder();
            List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
            if (c.getCount() > 0) {
                while (c.moveToNext()) {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("textpateintid", c.getString(2));
                    hashMap.put("txtQuery", c.getString(3));
                    hashMap.put("txtSolution", c.getString(4));
                    aList.add(hashMap);
                    str.append("\n");
                }
            }

            String[] from = {"textpateintid", "txtQuery", "txtSolution"};        //Why need theses arrays?
            int[] to = {R.id.textpateintid, R.id.txtQuery, R.id.txtSolution};
            SimpleAdapter adapter = new SimpleAdapter
                    (getBaseContext(), aList, R.layout.listview_layout, from, to);
            ListView listView = findViewById(R.id.listView);
            listView.setAdapter(adapter);
            c.close();
        }
    }
}