package com.example.myapplication.Profile;

import java.io.Serializable;
import java.util.ArrayList;

// Classe responsável por guardar a informação de todas as vacinas
public class Booklet implements Serializable {
    private ArrayList<VaccineData> VaccinesData = new ArrayList<>();

    public ArrayList<VaccineData> getVaccinesData() {
        return VaccinesData;
    }

    public void setVaccinesData(ArrayList<VaccineData> vaccinesData) {
        this.VaccinesData = vaccinesData;
    }
}
