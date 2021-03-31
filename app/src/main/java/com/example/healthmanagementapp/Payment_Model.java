package com.example.healthmanagementapp;

public class Payment_Model {

    int billingID, appointmentID, cashierID, patientID, paymentAmt, paymentStatus;

    public Payment_Model(int billingID, int appointmentID, int cashierID, int patientID, int paymentAmt, int paymentStatus) {
        this.billingID = billingID;
        this.appointmentID = appointmentID;
        this.cashierID = cashierID;
        this.patientID = patientID;
        this.paymentAmt = paymentAmt;
        this.paymentStatus = paymentStatus;
    }

    public int getBillingID() {
        return billingID;
    }

    public int getPatientID() {
        return patientID;
    }

    public int getPaymentAmt() {
        return paymentAmt;
    }

}
