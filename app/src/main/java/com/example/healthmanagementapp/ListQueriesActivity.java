package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListQueriesActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_queries);
        databaseHelper = new DatabaseHelper(this);
        functionView();
    }

    public void functionView(){
        Cursor c = databaseHelper.viewDataQuery();
        StringBuilder str = new StringBuilder();


        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        if(c.getCount()>0){
            while(c.moveToNext()){

                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("txtQuery", c.getString(3));
                hashMap.put("txtSolution", c.getString(4));
                aList.add(hashMap);

                Log.i("ID ", c.getString(0));
                Log.i("DOCTOR ID ", c.getString(1));
                Log.i("ID ", c.getString(3));

                str.append("\n");


            }

        }
        String[] from= {"txtQuery","txtSolution"};        //Why need theses arrays?
        int[]to= {R.id.txtQuery,R.id.txtSolution};
        SimpleAdapter adapter = new SimpleAdapter
                (getBaseContext(), aList, R.layout.listview_layout,from,to);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.douglascollege.ca/")));
                        break;
                    case 1:
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.sfu.ca/")));
                        break;
                    case 2:
                        //startActivity(new Intent(MainActivity.this, Willis.class));

                }


            }
        });

        c.close();
    }
}