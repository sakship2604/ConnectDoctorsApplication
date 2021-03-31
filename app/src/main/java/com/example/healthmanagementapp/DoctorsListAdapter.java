package com.example.healthmanagementapp;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;

public class DoctorsListAdapter extends BaseAdapter implements DatePickerDialog.OnDateSetListener
{
    Context context;
    ArrayList<doctors_model> arrayList;
    DatabaseHelper databaseHelper;
    String patId;
    int pos;
    String doctor_id;
    public DoctorsListAdapter(Context context, ArrayList<doctors_model> arrayList, String patId)
    {
        this.patId = patId;
        this.context = context;
        this.arrayList = arrayList;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
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
        this.setDoctor_id(String.valueOf(doctors_model.getID()));
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddQueryActivity.class);
                    intent.putExtra("doctor_id", doctors_model.getID());
                    intent.putExtra("patientId", patId);
                    context.startActivity(intent);
                }
            });

            b2.setText("Book Appointment");

            //appoint booking button
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context,
                            DoctorsListAdapter.this::onDateSet,
                            Calendar.getInstance().get(Calendar.YEAR),
                            Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            });

            return convertView;
        }

    //appoint booking
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        databaseHelper =  new DatabaseHelper(context);

        //(int doctorId, int patientId, String date, int status, int fees)
        String date = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(dayOfMonth);
        int timeSpot = 1;
        //verificar disponibilidade

        final EditText taskEditText = new EditText(context);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Select an spot of time")
                .setMessage("You can choose any one 08:00 to 18:00 24 hours the spot represent 1 hour!")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("VALUE", String.valueOf(doctor_id));
                        Intent intent = new Intent(context, ListDoctorAppointmentActivity.class);
                        intent.putExtra("doctor_id", doctor_id);
                        intent.putExtra("patient_id", patId);
                        intent.putExtra("time_spot", taskEditText.getText().toString());
                        intent.putExtra("date", date);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        //
        dialog.show();

    }
}
