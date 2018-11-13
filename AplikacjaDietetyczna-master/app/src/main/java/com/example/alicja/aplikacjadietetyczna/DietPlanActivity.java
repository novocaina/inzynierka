package com.example.alicja.aplikacjadietetyczna;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DietPlanActivity extends AppCompatActivity implements MealFragment.OnFragmentInteractionListener {
    @BindView(R.id.tab_layout_meal)
    TabLayout tabLayout;
    @BindView(R.id.meal_pager)
    ViewPager viewPager;
    JSONObject obj;
    ArrayList<DailyMeal> mealList;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);
        ButterKnife.bind(this);
        for (int i = 1; i <= 7; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.day) + " " + String.valueOf(i)));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerMealAdapter pagerAdapter = new PagerMealAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        try {
            obj = new JSONObject(readJSONFromAsset());
            parseJson(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        db = new DatabaseHelper(this);
        getUserInfo();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("database.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void parseJson(JSONObject jsonObject) throws JSONException {
        JSONArray meals = jsonObject.getJSONArray("Baza");
        mealList = new ArrayList<>();
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
            mealList.add(new DailyMeal(name, ingredients, kind, type, portions, prepare, url, calories, proteins, carbohydrates, fat));

        }
    }

    private void getUserInfo() {

        if (db.getUserCount() != 0) {
            User user = db.getUser();
            double cpm = user.getCpm();
            String goal = user.getGoal();
            String elimination = user.getElimination();
            String prefer = user.getPrefer();
            ArrayList<DailyMeal> meals = updateList(elimination, prefer);
            PlanDiet(meals, cpm, goal);
        } else {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.info)
                    .setMessage(R.string.empty_database)
                    .setPositiveButton(R.string.open_activity, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            Intent intent = new Intent(DietPlanActivity.this, DietInfoActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .create();
            dialog.show();
        }
    }

    private void PlanDiet(ArrayList<DailyMeal> meals, double cpm, String goal) {
        double cpm_daily;
        if (goal.equals(getResources().getString(R.string.reduction))) {
            cpm_daily = cpm - 200;
        } else if (goal.equals(getResources().getString(R.string.mass))) {
            cpm_daily = cpm + 200;
        } else {
            cpm_daily = cpm;
        }


        Random r = new Random();
        double sum = 0;
        double cpm1 = cpm_daily - 150;
        double cpm2 = cpm_daily + 150;
        int max = meals.size() - 1;
        int a, b, c, d, e;
        ArrayList<DailyMeal> breakfastList = new ArrayList<>();
        ArrayList<DailyMeal> secondBreakfastList = new ArrayList<>();
        ArrayList<DailyMeal> dinnerList = new ArrayList<>();
        ArrayList<DailyMeal> supperList = new ArrayList<>();
        ArrayList<DailyMeal> dessertList = new ArrayList<>();
       DailyMeal breakfast;
        DailyMeal secondbreakfast;
        DailyMeal dinner;
        DailyMeal dessert;
        DailyMeal supper;

        for (DailyMeal dailyMeal : mealList) {
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
        for (int i = 0; i < 7; i++) {

            while (!((sum > cpm1) & (sum < cpm2))) {
                a = r.nextInt((max - 1) + 1) + 1;
                b = r.nextInt((max - 1) + 1) + 1;
                c = r.nextInt((max - 1) + 1) + 1;
                d = r.nextInt((max - 1) + 1) + 1;
                e = r.nextInt((max - 1) + 1) + 1;

                breakfast = breakfastList.get(a);
                 secondbreakfast = secondBreakfastList.get(b);
                 dinner = dinnerList.get(c);
                 dessert = dessertList.get(d);
                 supper = supperList.get(e);

                sum = breakfast.getCalories() + secondbreakfast.getCalories() + dinner.getCalories() + dessert.getCalories() + supper.getCalories();

            }

          //  DailyMeal meal1 = new DailyMeal(breakfast.getName(), Double.parseDouble(food1.getCalories()));
          //  db.insertMeal(meal1);



        }
    }

    private ArrayList<DailyMeal> updateList(String elimination, String prefer) {
        ArrayList<DailyMeal> newMealList = new ArrayList<>();
        if (prefer.equals(getString(R.string.veget))) {
            for (DailyMeal dailyMeal : mealList) {
                if (dailyMeal.getKind().equals("w")) {
                    newMealList.add(dailyMeal);
                }
            }
        } else {
            for (DailyMeal dailyMeal : mealList) {
                newMealList.add(dailyMeal);
            }
        }
        return newMealList;
    }
}
