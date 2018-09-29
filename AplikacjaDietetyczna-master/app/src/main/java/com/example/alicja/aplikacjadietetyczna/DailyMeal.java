package com.example.alicja.aplikacjadietetyczna;

/**
 * Created by Alicja on 2018-06-12.
 */

public class DailyMeal {
    private String name;

    public DailyMeal(String name, double calories) {
        this.name = name;
        this.calories = calories;
    }

    public DailyMeal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    private double calories;

    public static final String TABLE2 = "meal";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CALORIES = "calories";
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE2 + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    CALORIES + " NUMBER " +
                     "); ";

}
