package com.example.myapplication.Alarms;

import java.io.Serializable;

public class AlarmData implements Serializable {
    private String medicineName;
    private String description;
    private String setTime;

    public AlarmData(String medicineName, String description, String setTime) {
        this.medicineName = medicineName;
        this.description = description;
        this.setTime = setTime;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }
}

