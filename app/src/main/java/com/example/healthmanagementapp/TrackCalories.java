package com.example.healthmanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrackCalories extends AppCompatActivity {
    final int dailyCalorieLimit = 1000;
    DatabaseHelper databaseHelper;
    EditText food_item, amount;
    Button btnadd, calculateCalories, back;
    TextView output;
    String datePattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_calories);


        datePattern = "yyyy/MM/dd";

        food_item = findViewById(R.id.food_item);
        amount = findViewById(R.id.food_amount);

        btnadd = findViewById(R.id.buttonAddFood);
        calculateCalories = findViewById(R.id.buttonTotalCal);
        back = findViewById(R.id.buttonFoodBack);

        output = findViewById(R.id.textViewDisplayCalories);

        databaseHelper = new DatabaseHelper(this);

        // to add calories with food items in table
        btnadd.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;

            @Override
            public void onClick(View v) {
                String dateTodayString = new SimpleDateFormat(datePattern).format(new Date());
                isInserted = databaseHelper.addFoodItem(food_item.getText().toString(),
                        Integer.parseInt(amount.getText().toString()), dateTodayString);

                if (isInserted) {
                    Toast.makeText(TrackCalories.this, "Data added", Toast.LENGTH_LONG).show();
                    food_item.setText("");
                    amount.setText("");
                } else {
                    Toast.makeText(TrackCalories.this, "Data not added", Toast.LENGTH_LONG).show();
                }
            }
        });

        // to  calculate calories
        calculateCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateTodayString = new SimpleDateFormat(datePattern).format(new Date());
                Cursor c = databaseHelper.getFoodData(dateTodayString);

                StringBuilder str = new StringBuilder();
                int totalCalories = 0;
                if (c.getCount() > 0) {
                    while (c.moveToNext()) {

                        if (c.getInt(4) != 0) {
                            totalCalories = c.getInt(4);
                        }
                        if (totalCalories > dailyCalorieLimit) {
                            Toast.makeText(getApplicationContext(), "You have exceeded today's calorie limit!", Toast.LENGTH_SHORT).show();
                        }
                        str.append("Total Calories Eaten Today: ").append(totalCalories);
                        str.append("\n");
                    }
                    str.append("\n");
                    output.setText(str);
                }
                c.close();
            }
        });

        // the back button

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackCalories.this, PatientHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}