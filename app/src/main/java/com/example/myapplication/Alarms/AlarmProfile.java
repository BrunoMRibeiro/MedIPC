package com.example.myapplication.Alarms;


import java.io.Serializable;

// Classe responsável pela informação de cada Alarme
public class AlarmProfile implements Serializable {
    private final Alarm alarm = new Alarm();


    public Alarm getAlarm() {
        return alarm;
    }

}
