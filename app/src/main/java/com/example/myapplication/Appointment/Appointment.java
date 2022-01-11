package com.example.myapplication.Appointment;

import java.io.Serializable;
import java.util.ArrayList;

public class Appointment implements Serializable {
    private ArrayList<com.example.myapplication.Appointment.AppointmentData> AppointmentData = new ArrayList<>();

    public ArrayList<AppointmentData> getAppointmentData() { return AppointmentData; }

    public void setAppointmentData(ArrayList<AppointmentData> appointmentData) { this.AppointmentData = appointmentData; }
}