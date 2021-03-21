package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TrackCalories extends AppCompatActivity
{
    DatabaseHelper databaseHelper;

    EditText food_item, amount;


    Button btnadd = findViewById(R.id.buttonAddFood);
    Button calculateCalories = findViewById(R.id.buttonTotalCal);
    Button refresh = findViewById(R.id.refresh_button);
    Button back = findViewById(R.id.buttonFoodBack);

    TextView output = findViewById(R.id.textViewDisplayCalories);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_calories);

        food_item = findViewById(R.id.food_item);
        amount = findViewById(R.id.food_amount);

        databaseHelper = new DatabaseHelper(this);

        btnadd.setOnClickListener(new View.OnClickListener()
        {
            boolean isInserted;
            @Override
            public void onClick(View v) {
                isInserted = databaseHelper.addRecord(food_item.getText().toString(),
                        amount.getText().toString());

                if(isInserted)
                {
                    Toast.makeText(TrackCalories.this,"Data added",Toast.LENGTH_LONG).show();
                    food_item.setText("");
                    amount.setText("");
                }
                else
                {
                    Toast.makeText(TrackCalories.this,"Data not added",Toast.LENGTH_LONG).show();
                }
            }
        });

        calculateCalories.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Cursor c = databaseHelper.viewData();
                Cursor c1 = databaseHelper.viewAmountTotal();

                StringBuilder str = new StringBuilder();
                if(c.getCount()>0) {
                    while (c.moveToNext()) {
                        str.append("ID : " + c.getString(0));
                        str.append("Food : " + c.getString(1));
                        str.append("Amount" + c.getString(2));
                        str.append("Total Calories Eaten : " + c1.getString(0));

                        str.append("\n");
                    }
                    output.setText(str);
                }
                c.close();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor c = databaseHelper.onrefresh();

                c.close();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackCalories.this,PatientHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}