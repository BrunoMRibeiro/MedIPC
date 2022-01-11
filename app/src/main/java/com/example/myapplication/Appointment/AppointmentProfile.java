package com.example.myapplication.Appointment;

import java.io.Serializable;

public class AppointmentProfile implements Serializable {
    private final Appointment appointment = new Appointment();


    public Appointment getAppointment() {
        return appointment;
    }

}