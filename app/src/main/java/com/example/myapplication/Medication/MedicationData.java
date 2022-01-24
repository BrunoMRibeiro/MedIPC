package com.example.myapplication.Medication;

import java.io.Serializable;

// Classe responsável por guardar a informação de cada Medicação
public class MedicationData implements Serializable {
    private String name;
    private String hours;
    private String next_take;

    public MedicationData(String name, String hours, String nextTake) {
        this.name = name;
        this.hours = hours;
        this.next_take = nextTake;
    }

    //Getters
    public String getName(){
        return name;
    }

    public String getHours(){
        return hours;
    }

    public String getNext_take(){
        return next_take;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setNext_take(String next_take) {
        this.next_take = next_take;
    }
}
