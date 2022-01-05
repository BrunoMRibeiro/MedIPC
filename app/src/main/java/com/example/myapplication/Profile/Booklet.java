package com.example.myapplication.Profile;

import java.io.Serializable;
import java.util.ArrayList;

public class Booklet implements Serializable {
    private ArrayList<VaccineData> VaccinesData = new ArrayList<>();

    public ArrayList<VaccineData> getVaccinesData() {
        return VaccinesData;
    }

    public void setVaccinesData(ArrayList<VaccineData> vaccinesData) {
        this.VaccinesData = vaccinesData;
    }
}
