package com.example.healthmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PaymentListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Payment_Model> arrayList;

    public PaymentListAdapter(Context context, ArrayList<Payment_Model> arrayList)
    {
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.payment_listview_layout, null);
        TextView t1 = convertView.findViewById(R.id.bill_id_txt);
        TextView t2 = convertView.findViewById(R.id.patient_id_txt);
        TextView t3 = convertView.findViewById(R.id.pay_amt_txt);
        Button b1 = convertView.findViewById(R.id.request_btn);

        Payment_Model payment_model = arrayList.get(position);
        t1.setText(payment_model.getBillingID());
        t2.setText(payment_model.getPatientID());
        t3.setText(payment_model.getPaymentAmt());

        return convertView;
    }


}
