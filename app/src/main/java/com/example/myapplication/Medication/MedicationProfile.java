package com.example.myapplication.Medication;


import java.io.Serializable;

public class MedicationProfile implements Serializable {
    private final Medication medication = new Medication();


    public Medication getMedication() {
        return medication;
    }

}