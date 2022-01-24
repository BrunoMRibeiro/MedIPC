package com.example.myapplication.Appointment;

import java.io.Serializable;

// Classe que guarda a informação das consultas
public class AppointmentProfile implements Serializable {
    private final Appointment appointment = new Appointment();


    public Appointment getAppointment() {
        return appointment;
    }

}