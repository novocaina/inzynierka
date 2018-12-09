package com.example.alicja.aplikacjadietetyczna.Objects;


import android.content.Context;

import com.example.alicja.aplikacjadietetyczna.DatabaseHelper;
import com.example.alicja.aplikacjadietetyczna.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MyDiet {

    public ArrayList<DailyMeal> parseJson(JSONObject jsonObject) throws JSONException {
        JSONArray meals = jsonObject.getJSONArray("Baza");
        ArrayList<DailyMeal> mealList = new ArrayList<>();
        for (int i = 0; i < meals.length(); i++) {
            JSONObject jsonObj = meals.getJSONObject(i);
            String name = jsonObj.getString("Nazwa");
            double calories = Double.parseDouble(jsonObj.getString("Kalorie"));
            String type = jsonObj.getString("Typ");
            String kind = jsonObj.getString("Rodzaj");
            String ingredients = jsonObj.getString("Składniki");
            String prepare = jsonObj.getString("Przepis");
            String url = jsonObj.getString("Źródło");
            double proteins = Double.parseDouble(jsonObj.getString("Białka"));
            double fat = Double.parseDouble(jsonObj.getString("Tłuszcze"));
            double carbohydrates = Double.parseDouble(jsonObj.getString("Weglowodany"));
            String portions = jsonObj.getString("Porcje");
            String imageUrl = jsonObj.getString("Url");
            mealList.add(new DailyMeal(name, ingredients, kind, type, portions, prepare, url, calories, proteins, carbohydrates, fat, imageUrl));

        }
        return mealList;
    }


    public ArrayList<DailyMeal> updateList(ArrayList<DailyMeal> mealList, String elimination, String prefer) {
        ArrayList<DailyMeal> newMealList = new ArrayList<>();
        if (prefer.equals("Wegetariańska")) {
            for (DailyMeal dailyMeal : mealList) {
                if (dailyMeal.getKind().equals("w")) {
                    newMealList.add(dailyMeal);
                }
            }
        } else {
            newMealList.addAll(mealList);
        }
        if (elimination.isEmpty()) {
            return newMealList;
        } else {
            ArrayList<DailyMeal> toRemove = new ArrayList<>();
            String[] result = elimination.split(",");
            for (int i = 0; i < result.length; i++) {
                for (DailyMeal meal : newMealList) {
                    if (meal.getIngredients().contains(result[i])) {
                        toRemove.add(meal);
                    }
                }
            }
            newMealList.removeAll(toRemove);
            return newMealList;
        }
    }

    public ArrayList<DailyMeal> PlanDiet(ArrayList<DailyMeal> meals, double cpm, String goal) {
        double cpm_daily;
        switch (goal) {
            case "Redukcja":
                cpm_daily = cpm - 200;
                break;
            case "Masa":
                cpm_daily = cpm + 200;
                break;
            default:
                cpm_daily = cpm;
                break;
        }


        Random r = new Random();
        double sum = 0;
        double sumProteins = 0;
        double sumFats = 0;
        double sumCarbohydrates = 0;
        double cpm1 = cpm_daily - 150;
        double cpm2 = cpm_daily + 150;
        double proteinsMin = CPM.ProteinsMinCalculate(cpm1);
        double proteinsMax = CPM.ProteinsMaxCalculate(cpm2);
        double fatsMin = CPM.FatsMinCalculate(cpm1);
        double fatsMax = CPM.FatsMaxCalculate(cpm2);
        double carbohydratesMin = CPM.CarbohydratesMinCalculate(cpm1);
        double carbohydratesMax = CPM.CarbohydratesMaxCalculate(cpm2);

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


        while (!((sum > cpm1) & (sum < cpm2) & (sumProteins > proteinsMin) & (sumProteins < proteinsMax) & (sumCarbohydrates > carbohydratesMin) & (sumCarbohydrates < carbohydratesMax) & (sumFats > fatsMin) & (sumFats < fatsMax))) {
            a = r.nextInt((breakfastList.size() - 1) + 1);
            b = r.nextInt((secondBreakfastList.size() - 1) + 1);
            c = r.nextInt((dinnerList.size() - 1) + 1);
            d = r.nextInt((dessertList.size() - 1) + 1);
            e = r.nextInt((supperList.size() - 1) + 1);

            breakfast = breakfastList.get(a);
            secondbreakfast = secondBreakfastList.get(b);
            dinner = dinnerList.get(c);
            dessert = dessertList.get(d);
            supper = supperList.get(e);

            sum = breakfast.getCalories() + secondbreakfast.getCalories() + dinner.getCalories() + dessert.getCalories() + supper.getCalories();
            sumProteins = breakfast.getProteins() + secondbreakfast.getProteins() + dinner.getProteins() + dessert.getProteins() + supper.getProteins();
            sumFats = breakfast.getFat() + secondbreakfast.getFat() + dinner.getFat() + dessert.getFat() + supper.getFat();
            sumCarbohydrates = breakfast.getCarbohydrates() + secondbreakfast.getCarbohydrates() + dinner.getCarbohydrates() + dessert.getCarbohydrates() + supper.getCarbohydrates();
        }

        ArrayList<DailyMeal> planMeals = new ArrayList<>();
        DailyMeal meal1 = new DailyMeal(breakfast.getName(), breakfast.getIngredients(), breakfast.getType(),
                breakfast.getKind(), breakfast.getPortions(), breakfast.getPrepare(), breakfast.getUrl(),
                breakfast.getCalories(), breakfast.getProteins(), breakfast.getCarbohydrates(), breakfast.getFat(), breakfast.getImageUrl());
        planMeals.add(meal1);
        DailyMeal meal2 = new DailyMeal(secondbreakfast.getName(), secondbreakfast.getIngredients(), secondbreakfast.getType(),
                secondbreakfast.getKind(), secondbreakfast.getPortions(), secondbreakfast.getPrepare(), secondbreakfast.getUrl(),
                secondbreakfast.getCalories(), secondbreakfast.getProteins(), secondbreakfast.getCarbohydrates(), secondbreakfast.getFat(), secondbreakfast.getImageUrl());
        planMeals.add(meal2);
        DailyMeal meal3 = new DailyMeal(dinner.getName(), dinner.getIngredients(), dinner.getType(),
                dinner.getKind(), dinner.getPortions(), dinner.getPrepare(), dinner.getUrl(),
                dinner.getCalories(), dinner.getProteins(), dinner.getCarbohydrates(), dinner.getFat(), dinner.getImageUrl());
        planMeals.add(meal3);
        DailyMeal meal4 = new DailyMeal(dessert.getName(), dessert.getIngredients(), dessert.getType(),
                dessert.getKind(), dessert.getPortions(), dessert.getPrepare(), dessert.getUrl(),
                dessert.getCalories(), dessert.getProteins(), dessert.getCarbohydrates(), dessert.getFat(), dessert.getImageUrl());
        planMeals.add(meal4);
        DailyMeal meal5 = new DailyMeal(supper.getName(), supper.getIngredients(), supper.getType(),
                supper.getKind(), supper.getPortions(), supper.getPrepare(), supper.getUrl(),
                supper.getCalories(), supper.getProteins(), supper.getCarbohydrates(), supper.getFat(), supper.getImageUrl());
        planMeals.add(meal5);

        return planMeals;
    }

    public ArrayList<DailyMeal> initMealList(Context context) {
        ArrayList<DailyMeal> mealList = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(JSONHelper.readJSONFromAsset(context));
            mealList = parseJson(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DatabaseHelper db = new DatabaseHelper(context);
        if (db.getUserMealCount() != 0) {
            for (int i = 1; i <= db.getUserMealCount(); i++) {
                mealList.add(db.getUserMeal(i));
            }
        }
        for (DailyMeal mealIngredients : mealList) {
            mealIngredients.setIngredients(mealIngredients.getIngredients().replace(",", "\n"));
        }
        User user = db.getUser();
        return updateList(mealList, user.getElimination(), user.getPrefer());
    }

    public void SaveUserList(DatabaseHelper db, ArrayList<DailyMeal> meals) {
        User user = db.getUser();
        int j = 1;
        for (int x = 1; x <= 7; x++) {
            ArrayList<DailyMeal> oneDayDiet = PlanDiet(meals, user.getCpm(), user.getGoal());
            for (DailyMeal meal : oneDayDiet) {
                db.updateMeal(meal, j);
                j++;
            }
        }
    }
}
