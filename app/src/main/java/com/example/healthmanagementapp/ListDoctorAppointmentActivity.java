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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListDoctorAppointmentActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    DatabaseHelper databaseHelper;
    SharedPreferences preferences;
    String doctor_id;
    String patient_id;
    int flag_doctor, visibilityFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctor_appointment);
        databaseHelper = new DatabaseHelper(this);
        Button book = findViewById(R.id.buttonBook);
        patient_id = "1";
        visibilityFlag = getIntent().getIntExtra("VisibilityFlag", 0);
        if(visibilityFlag == 1){
            book.setVisibility(View.GONE);
        }
        else {
            book.setVisibility(View.VISIBLE);
        }
        doctor_id = getIntent().getStringExtra("doctor_id");
        Log.d("FUNVI", String.valueOf(doctor_id));
        patient_id = getIntent().getStringExtra("patient_id");
        String bookDate = getIntent().getStringExtra("date");
        String timeSpot = getIntent().getStringExtra("time_spot");
        functionView();
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseHelper.checkBookAppointment(Integer.parseInt(doctor_id),bookDate, timeSpot)){
                    Toast.makeText(ListDoctorAppointmentActivity.this, "No time avalible for this time spot!", Toast.LENGTH_SHORT).show();
                }else{
                    databaseHelper.bookAppointment(Integer.parseInt(doctor_id), Integer.parseInt(patient_id), bookDate, 1, 90, timeSpot);
                    Toast.makeText(ListDoctorAppointmentActivity.this, "Appointment Confirmed", Toast.LENGTH_SHORT).show();
                    functionView();
                }
            }
        });
    }

    public void functionView() {
        Cursor c;
        c = databaseHelper.viewDataDoctorAppointment(doctor_id);
        StringBuilder str = new StringBuilder();
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("textpateintid", c.getString(0));
                hashMap.put("txtQuery", c.getString(3));
                hashMap.put("txtSolution", c.getString(6));
                aList.add(hashMap);

                Log.i("Appointment Date ", c.getString(3));
                Log.i("Time spot ", c.getString(6));

                str.append("\n");
            }
        }
        String[] from = {"textpateintid","txtQuery", "txtSolution"};        //Why need theses arrays?
        int[] to = {R.id.textpateintid, R.id.txtQuery, R.id.txtSolution};
        SimpleAdapter adapter = new SimpleAdapter
                (getBaseContext(), aList, R.layout.listview_layout, from, to);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flag_doctor == 1) {
                    Intent intent = new Intent(ListDoctorAppointmentActivity.this, AddQueryActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("flag_doctor", 1);
                    startActivity(intent);
                }
            }
        });
        c.close();
    }
}