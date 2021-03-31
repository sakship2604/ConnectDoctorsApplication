package com.example.healthmanagementapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PaymentListAdapter extends BaseAdapter {

    Context context;
    List<Payment_Model> arrayList;

    public PaymentListAdapter(Context context, List<Payment_Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
            convertView = myLayoutInflater.inflate(R.layout.payment_listview_layout, parent, false);
        }
        TextView t1 = convertView.findViewById(R.id.bill_id_txt);
        TextView t2 = convertView.findViewById(R.id.patient_id_txt);
        TextView t3 = convertView.findViewById(R.id.pay_amt_txt);
        Button b1 = convertView.findViewById(R.id.request_btn);
        Log.d("SIZE", String.valueOf(arrayList.get(position).billingID));
        t1.setText("BillId" + arrayList.get(position).billingID);
        t2.setText(" PatId" + arrayList.get(position).patientID);
        t3.setText(" Amt" + arrayList.get(position).paymentAmt);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Payment Approved", Toast.LENGTH_LONG).show();
                DatabaseHelper db = new DatabaseHelper(parent.getContext());
                db.updateBilling(String.valueOf(arrayList.get(position).billingID), 0);
            }
        });


        return convertView;
    }


}
