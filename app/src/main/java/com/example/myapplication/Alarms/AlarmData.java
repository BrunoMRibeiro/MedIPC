package com.example.myapplication.Alarms;

import java.io.Serializable;

public class AlarmData implements Serializable {
    private String name;
    private String description;
    private String setTime;

    public AlarmData(String name, String description, String setTime) {
        this.name = name;
        this.description = description;
        this.setTime = setTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

