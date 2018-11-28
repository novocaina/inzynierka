package com.example.alicja.aplikacjadietetyczna.Objects;

/**
 * Created by Alicja on 2018-04-18.
 */

public class BMI {
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

     private  double weight;
    private  double height;
    public double BMI_Count(double weight, double height){
        return weight/((height/100)*(height/100));
    }

}
