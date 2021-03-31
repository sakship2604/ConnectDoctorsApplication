package com.example.healthmanagementapp;

public class doctors_model {
    String name, email, password, Specality, button1, button2;
    int ID, fees, phonenumber;


    public doctors_model(int id, String name, String email, String speciality, int fees, int phone, String button1, String button2) {
        this.name = name;
        this.email = email;

        this.Specality = speciality;
        this.fees = fees;
        this.phonenumber = phone;
        this.ID = id;
        this.button1 = button1;
        this.button2 = button2;
    }

    public doctors_model(String name, String email, String password, String specality, int fees, int phonenumber, int ID, String button1, String button2) {

    }

    public String getButton1() {
        return button1;
    }

    public void setButton1(String button1) {
        this.button1 = button1;
    }

    public String getButton2() {
        return button2;
    }

    public void setButton2(String button2) {
        this.button2 = button2;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpecality() {
        return Specality;
    }

    public void setSpecality(String specality) {
        Specality = specality;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }


}
