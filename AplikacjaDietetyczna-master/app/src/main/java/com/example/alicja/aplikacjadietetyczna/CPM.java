package com.example.alicja.aplikacjadietetyczna;

/**
 * Created by Alicja on 2018-04-18.
 */

public class CPM {
float weight;
float height;
int age;

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(float height) {
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

    String sex;
double pal;
    public double Count_CPM(double weight, double height, int age, String sex,double pal){
    double CPM;
        if(sex.equals("k"))
        {
            double PPM = 665.09+(9.56*weight)+(1.85*height)-(4.67*age);
            CPM=PPM*pal;

        }
        else
        {
            double PPM = 66.47 + (13.75*weight) + (5*height)-(6.75*age);
            CPM=PPM*pal;

        }
        return CPM;
    }

}
