package com.example.alicja.aplikacjadietetyczna.Objects;


public class CPM {
    private double weight;
    private double height;
    private int age;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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

    public double getPal() {
        return pal;
    }

    public void setPal(double pal) {
        this.pal = pal;
    }

    private String sex;
    private double pal;

    public double Count_CPM(double weight, double height, int age, String sex, double pal) {
        double CPM;
        if (sex.equals("k")) {
            double PPM = 665.09 + (9.56 * weight) + (1.85 * height) - (4.67 * age);
            CPM = PPM * pal;

        } else {
            double PPM = 66.47 + (13.75 * weight) + (5 * height) - (6.75 * age);
            CPM = PPM * pal;

        }
        return CPM;
    }

    public static double ProteinsMinCalculate(double cpm) {
        return (cpm * 0.1) / 4;
    }
    public static double ProteinsMaxCalculate(double cpm) {
        return (cpm * 0.2) / 4;
    }
    public static double FatsMinCalculate(double cpm) {
        return (cpm * 0.2) / 9;
    }
    public static double FatsMaxCalculate(double cpm) {
        return (cpm * 0.35) / 9;
    }
    public static double CarbohydratesMinCalculate(double cpm) {
        return (cpm * 0.45) / 4;
    }
    public static double CarbohydratesMaxCalculate(double cpm) {
        return (cpm * 0.65) / 4;
    }

}
