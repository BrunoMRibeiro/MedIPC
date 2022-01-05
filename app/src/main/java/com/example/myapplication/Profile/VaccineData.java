package com.example.myapplication.Profile;

import java.io.Serializable;

public class VaccineData implements Serializable {
    private String vaccineName;
    private String date;

    public VaccineData(String vaccineName, String date) {
        this.vaccineName = vaccineName;
        this.date = date;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
