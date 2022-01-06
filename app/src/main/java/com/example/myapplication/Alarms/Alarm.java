package com.example.myapplication.Alarms;

import java.io.Serializable;
import java.util.ArrayList;

public class Alarm implements Serializable {
    private ArrayList<AlarmData> AlarmsData = new ArrayList<>();

    public ArrayList<AlarmData> getAlarmsData() {
        return AlarmsData;
    }

    public void setAlarmsData(ArrayList<AlarmData> alarmsData) {
        this.AlarmsData = alarmsData;
    }
}