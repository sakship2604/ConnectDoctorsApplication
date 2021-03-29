package com.example.healthmanagementapp;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;

public class DoctorsListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<doctors_model> arrayList;
    DatabaseHelper databaseHelper;
    int pos;
    DatePickerDialog.OnDateSetListener dateSetListener;
    SharedPreferences preferences;

    public DoctorsListAdapter(Context context, ArrayList<doctors_model> arrayList, SharedPreferences preferences)
    {
        this.context = context;
        this.arrayList = arrayList;
        this.preferences = preferences;
    }
    @Override
    public int getCount()
    {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.doctors_listview_layout, null);
            TextView t1 = convertView.findViewById(R.id.id_txt);
            TextView t2 = convertView.findViewById(R.id.name_txt);
         //   TextView t3 = convertView.findViewById(R.id.email_txt);
            TextView t4 = convertView.findViewById(R.id.speciality_txt);
         //   TextView t5 = convertView.findViewById(R.id.fees_txt);
         //   TextView t6 = convertView.findViewById(R.id.phonenumber_txt);
            Button b1 = convertView.findViewById(R.id.online_help);
            Button b2 = convertView.findViewById(R.id.book_appointment);

            doctors_model doctors_model = arrayList.get(position);
            pos = position;
            t1.setText(String.valueOf(doctors_model.getID()));
            t2.setText(doctors_model.getName());
         //   t3.setText(doctors_model.getEmail());
            t4.setText(doctors_model.getSpecality());
          //  t4.setText(String.valueOf(doctors_model.getFees()));
         //   t5.setText(String.valueOf(doctors_model.getPhonenumber()));
            b1.setText("Online Help");

            b2.setText("Book Appointment");

            //appoint booking button
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context,
                            dateSetListener,
                            Calendar.getInstance().get(Calendar.YEAR),
                            Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            });

            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    databaseHelper.bookAppointment(
                            doctors_model.getID(),
                            Integer.valueOf(preferences.getString("user_id", "DEFAULT")),
                            year + " " + month + " " + dayOfMonth,
                            0,
                            doctors_model.getFees()
                    );

                    databaseHelper.addBilling(
                            0,
                            Integer.valueOf(preferences.getString("user_id", "DEFAULT")),
                            doctors_model.getFees(),
                            0
                    );



                };
            };

            return convertView;
        }

}
