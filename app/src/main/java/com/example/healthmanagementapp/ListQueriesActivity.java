package com.example.healthmanagementapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListQueriesActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    DatabaseHelper databaseHelper;
    SharedPreferences preferences;
    String doctor_id;
    int flag_doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_queries);
        databaseHelper = new DatabaseHelper(this);

        flag_doctor = getIntent().getIntExtra("flag_doctor", 0);
        doctor_id = "0";
        if (flag_doctor == 1) {
            preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            doctor_id = preferences.getString("doctor_id", "DEFAULT");
        }
        functionView();
    }

    ///////////////////////////////////
    // to view queries for doctor
    /////////////////////////////////////

    public void functionView() {
        Cursor c;
        if (flag_doctor == 1)
            c = databaseHelper.viewDataQueryDoctor(doctor_id);
        else
            c = databaseHelper.viewDataQuery();

        StringBuilder str = new StringBuilder();

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        if (c.getCount() > 0) {
            while (c.moveToNext()) {

                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("textpateintid", c.getString(2));
                hashMap.put("txtQuery", c.getString(3));
                hashMap.put("txtSolution", c.getString(4));
                aList.add(hashMap);

                Log.i("ID ", c.getString(0));
                Log.i("DOCTOR ID ", c.getString(1));
                Log.i("ID ", c.getString(3));

                str.append("\n");
            }
        }
        String[] from = {"textpateintid", "txtQuery", "txtSolution"};
        int[] to = {R.id.textpateintid, R.id.txtQuery, R.id.txtSolution};

        SimpleAdapter adapter = new SimpleAdapter
                (getBaseContext(), aList, R.layout.listview_layout, from, to);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flag_doctor == 1) {
                    Intent intent = new Intent(ListQueriesActivity.this, AddQueryActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("flag_doctor", 1);
                    startActivity(intent);
                }
            }
        });
        c.close();
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