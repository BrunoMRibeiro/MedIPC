package com.example.myapplication.Alarms;


import java.io.Serializable;

public class AlarmProfile implements Serializable {
    private final Alarm alarm = new Alarm();


    public Alarm getAlarm() {
        return alarm;
    }

}
