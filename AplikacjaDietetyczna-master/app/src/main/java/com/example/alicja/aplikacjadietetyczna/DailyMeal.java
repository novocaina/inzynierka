package com.example.alicja.aplikacjadietetyczna;

/**
 * Created by Alicja on 2018-06-12.
 */

public class DailyMeal {
    private String name,ingredients,portions,prepare,url;
    private double calories,proteins, carbohydrates,fat;

    public DailyMeal(String name, String ingredients, String portions, String prepare, String url, double calories, double proteins, double carbohydrates, double fat) {
        this.name = name;
        this.ingredients = ingredients;
        this.portions = portions;
        this.prepare = prepare;
        this.url = url;
        this.calories = calories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPortions() {
        return portions;
    }

    public void setPortions(String portions) {
        this.portions = portions;
    }

    public String getPrepare() {
        return prepare;
    }

    public void setPrepare(String prepare) {
        this.prepare = prepare;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }
    DailyMeal(){

    }
    public static final String TABLE2 = "meal";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CALORIES = "calories";
    public static final String PROTEINS = "proteins";
    public static final String CARBOHYDRATES = "carbohydrates";
    public static final String FAT = "fat";
    public static final String INGREDIENTS= "ingredients";
    public static final String PREPARE = "prepare";
    public static final String PORTIONS = "portions";
    public static final String URL = "url";
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE2 + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    CALORIES + " NUMBER, " +
                    PROTEINS + " NUMBER, " +
                    CARBOHYDRATES + " NUMBER, " +
                    FAT + " NUMBER, " +
                    INGREDIENTS + " TEXT, " +
                    PREPARE + " TEXT, " +
                    PORTIONS + " TEXT, " +
                    URL + " TEXT " +
                     "); ";

}
