package com.example.myapplication.Medication;


import java.io.Serializable;

// Classe responsável por guardar a informação de todas as Medicações
public class MedicationProfile implements Serializable {
    private final Medication medication = new Medication();


    public Medication getMedication() {
        return medication;
    }

}