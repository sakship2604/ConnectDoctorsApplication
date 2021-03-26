package com.example.healthmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DoctorsListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<doctors_model> arrayList;
    public DoctorsListAdapter(Context context, ArrayList<doctors_model> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
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
            t1.setText(String.valueOf(doctors_model.getID()));
            t2.setText(doctors_model.getName());
         //   t3.setText(doctors_model.getEmail());
            t4.setText(doctors_model.getSpecality());
          //  t4.setText(String.valueOf(doctors_model.getFees()));
         //   t5.setText(String.valueOf(doctors_model.getPhonenumber()));
            b1.setText("Online Help");

            b2.setText("Book Appointment");

            return convertView;
        }

}