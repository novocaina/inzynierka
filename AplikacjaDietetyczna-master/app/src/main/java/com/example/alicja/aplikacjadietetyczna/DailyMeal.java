package com.example.alicja.aplikacjadietetyczna;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Random;

import static android.provider.Settings.Global.getString;
import static java.security.AccessController.getContext;

/**
 * Created by Alicja on 2018-06-12.
 */

public class DailyMeal {
    private String name;
    private String ingredients;
    private String portions;
    private String prepare;
    private String url;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String kind;
    private String type;
    private double calories,proteins, carbohydrates,fat;

    public DailyMeal(String name, String ingredients,String type, String kind, String portions, String prepare, String url, double calories, double proteins, double carbohydrates, double fat) {
        this.name = name;
        this.ingredients = ingredients;
        this.portions = portions;
        this.prepare = prepare;
        this.url = url;
        this.calories = calories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.type=type;
        this.kind=kind;
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
    public ArrayList<DailyMeal> PlanDiet(ArrayList<DailyMeal> meals, double cpm, String goal) {
        double cpm_daily;
        if (goal.equals("Redukcja")) {
            cpm_daily = cpm - 200;
        } else if (goal.equals("Masa")) {
            cpm_daily = cpm + 200;
        } else {
            cpm_daily = cpm;
        }


        Random r = new Random();
        double sum = 0;
        double cpm1 = cpm_daily - 150;
        double cpm2 = cpm_daily + 150;
        int a, b, c, d, e;
        ArrayList<DailyMeal> breakfastList = new ArrayList<>();
        ArrayList<DailyMeal> secondBreakfastList = new ArrayList<>();
        ArrayList<DailyMeal> dinnerList = new ArrayList<>();
        ArrayList<DailyMeal> supperList = new ArrayList<>();
        ArrayList<DailyMeal> dessertList = new ArrayList<>();
        DailyMeal breakfast = new DailyMeal();
        DailyMeal secondbreakfast = new DailyMeal();
        DailyMeal dinner = new DailyMeal();
        DailyMeal dessert = new DailyMeal();
        DailyMeal supper = new DailyMeal();

        for (DailyMeal dailyMeal : meals) {
            if (dailyMeal.getType().equals("sn")) {
                breakfastList.add(dailyMeal);
            }
            if (dailyMeal.getType().equals("ds")) {
                secondBreakfastList.add(dailyMeal);
            }
            if (dailyMeal.getType().equals("ob")) {
                dinnerList.add(dailyMeal);
            }
            if (dailyMeal.getType().equals("pd")) {
                dessertList.add(dailyMeal);
            }
            if (dailyMeal.getType().equals("kl")) {
                supperList.add(dailyMeal);
            }
        }


            while (!((sum > cpm1) & (sum < cpm2))) {
                a = r.nextInt((breakfastList.size() - 1)  + 1);
                b = r.nextInt((secondBreakfastList.size() - 1) +1);
                c = r.nextInt((dinnerList.size() - 1) + 1) ;
                d = r.nextInt((dessertList.size() - 1) + 1);
                e = r.nextInt((supperList.size() - 1) + 1) ;

                breakfast = breakfastList.get(a);
                secondbreakfast = secondBreakfastList.get(b);
                dinner = dinnerList.get(c);
                dessert = dessertList.get(d);
                supper = supperList.get(e);

                sum = breakfast.getCalories() + secondbreakfast.getCalories() + dinner.getCalories() + dessert.getCalories() + supper.getCalories();

            }
ArrayList<DailyMeal> planMeals=new ArrayList<>();
            DailyMeal meal1 = new DailyMeal(breakfast.getName(), breakfast.getIngredients(), breakfast.getType(),
                    breakfast.getKind(), breakfast.getPortions(), breakfast.getPrepare(), breakfast.getUrl(),
                    breakfast.getCalories(), breakfast.getProteins(), breakfast.getCarbohydrates(), breakfast.getFat());
            planMeals.add(meal1);
            DailyMeal meal2 = new DailyMeal(secondbreakfast.getName(), secondbreakfast.getIngredients(), secondbreakfast.getType(),
                    secondbreakfast.getKind(), secondbreakfast.getPortions(), secondbreakfast.getPrepare(), secondbreakfast.getUrl(),
                    secondbreakfast.getCalories(), secondbreakfast.getProteins(), secondbreakfast.getCarbohydrates(), secondbreakfast.getFat());
            planMeals.add(meal2);
            DailyMeal meal3 = new DailyMeal(dinner.getName(), dinner.getIngredients(), dinner.getType(),
                    dinner.getKind(), dinner.getPortions(), dinner.getPrepare(), dinner.getUrl(),
                    dinner.getCalories(), dinner.getProteins(), dinner.getCarbohydrates(), dinner.getFat());
            planMeals.add(meal3);
            DailyMeal meal4 = new DailyMeal(dessert.getName(), dessert.getIngredients(), dessert.getType(),
                    dessert.getKind(), dessert.getPortions(), dessert.getPrepare(), dessert.getUrl(),
                    dessert.getCalories(), dessert.getProteins(), dessert.getCarbohydrates(), dessert.getFat());
            planMeals.add(meal4);
            DailyMeal meal5 = new DailyMeal(supper.getName(), supper.getIngredients(), supper.getType(),
                    supper.getKind(), supper.getPortions(), supper.getPrepare(), supper.getUrl(),
                    supper.getCalories(), supper.getProteins(), supper.getCarbohydrates(), supper.getFat());
            planMeals.add(meal5);

        return planMeals;
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
