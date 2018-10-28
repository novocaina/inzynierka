package com.example.alicja.aplikacjadietetyczna;

/**
 * Created by Alicja on 2018-05-30.
 */

public class User {
    double cpm, weight, height, activity;
    String goal;
    String prefer;

    public String getElimination() {
        return elimination;
    }

    public void setElimination(String elimination) {
        this.elimination = elimination;
    }

    String elimination;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public double getActivity() {
        return activity;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    String sex;

    int age;

    public User(double cpm, double weight, double height, String goal, String prefer, String sex, double activity, int age, String elimination) {
        this.cpm = cpm;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
        this.prefer = prefer;
        this.sex = sex;
        this.activity = activity;
        this.age = age;
        this.elimination=elimination;
    }

    User() {
    }

    public double getCpm() {
        return cpm;
    }

    public void setCpm(double cpm) {
        this.cpm = cpm;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getPrefer() {
        return prefer;
    }

    public void setPrefer(String prefer) {
        this.prefer = prefer;
    }


    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String AGE = "age";
    public static final String SEX = "sex";
    public static final String ACTIVITY = "activity";
    public static final String CPM = "cpm";
    public static final String GOAL = "goal";
    public static final String PREFERENCE = "preference";
    public static final String ELIMINATION = "elimination";
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    HEIGHT + " NUMBER, " +
                    WEIGHT + " NUMBER, " +
                    AGE + " NUMBER, " +
                    SEX + " TEXT, " +
                    CPM + " NUMBER, " +
                    GOAL + " TEXT, " +
                    ACTIVITY + " NUMBER, " +
                    PREFERENCE + " TEXT, " +
                    ELIMINATION + " TEXT "
                    + "); ";

}
