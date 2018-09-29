package com.example.alicja.aplikacjadietetyczna;

/**
 * Created by Alicja on 2018-05-30.
 */

public class User {
    private double cpm;

    public User(double cpm, String goal, String prefer) {
        this.cpm = cpm;
        this.goal = goal;
        this.prefer = prefer;
    }

    public User() {
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

    private String goal;
    private String prefer;

    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String CPM = "cpm";
    public static final String GOAL = "goal";
    public static final String PREFERENCE = "preference";
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CPM + " NUMBER, " +
                    GOAL + " TEXT, " +
                    PREFERENCE + " TEXT "
                    + "); ";

}
