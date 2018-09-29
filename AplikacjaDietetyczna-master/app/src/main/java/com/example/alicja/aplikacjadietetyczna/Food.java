package com.example.alicja.aplikacjadietetyczna;

/**
 * Created by Alicja on 2018-05-16.
 */

public class Food {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getGrams() {
        return grams;
    }

    public void setGrams(String grams) {
        this.grams = grams;
    }

    public String getPrepare() {
        return prepare;
    }

    public void setPrepare(String prepare) {
        this.prepare = prepare;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    private String name;
    private String calories;
    private String preferences;
    private String meal;
    private String components;
    private  String grams;
    private String prepare;
    private String portion;

    Food() {
    }
}
