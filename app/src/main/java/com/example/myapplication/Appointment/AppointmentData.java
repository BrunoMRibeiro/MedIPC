package com.example.myapplication.Appointment;

import java.io.Serializable;

public class AppointmentData implements Serializable {
    private String info;
    private String date;

    public AppointmentData(String info, String date) {
        this.info = info;
        this.date = date;
    }

    //Getters
    public String getinfo(){
        return info;
    }

    public String getDate(){
        return date;
    }

    //Setters
    public void setInfo(String info) {
        this.info = info;
    }

    public void setDate(){ this.date = date; }
}