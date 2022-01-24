package com.example.myapplication.Profile;


import java.io.Serializable;

// Classe responsável por guardar a informação do utilizador
public class Profile implements Serializable {
    private Booklet booklet = new Booklet();
    private String name = "";
    private int age;
    private String sex = "";
    private int Height;
    private double Weight;
    private String bloodType = "";
    private String alergies = "";


    public Booklet getBooklet() {
        return booklet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAlergies() {
        return alergies;
    }

    public void setAlergies(String alergies) {
        this.alergies = alergies;
    }
}
