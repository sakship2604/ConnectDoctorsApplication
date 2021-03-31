package com.example.healthmanagementapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    ///////////////////////////////////////////////////
    // for patient to view queries
    /////////////////////////////////////////////////////

    public void functionView() {
        Cursor c;
        if (patientId != "-1") {
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

            String[] from = {"textpateintid", "txtQuery", "txtSolution"};
            int[] to = {R.id.textpateintid, R.id.txtQuery, R.id.txtSolution};
            SimpleAdapter adapter = new SimpleAdapter
                    (getBaseContext(), aList, R.layout.listview_layout, from, to);
            ListView listView = findViewById(R.id.listView);
            listView.setAdapter(adapter);
            c.close();
        }
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