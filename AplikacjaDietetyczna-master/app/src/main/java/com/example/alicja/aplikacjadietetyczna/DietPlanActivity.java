package com.example.alicja.aplikacjadietetyczna;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.alicja.aplikacjadietetyczna.Adapter.PagerMealAdapter;
import com.example.alicja.aplikacjadietetyczna.Objects.DailyMeal;
import com.example.alicja.aplikacjadietetyczna.Objects.MyDiet;
import com.example.alicja.aplikacjadietetyczna.Objects.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DietPlanActivity extends AppCompatActivity implements MealFragment.OnFragmentInteractionListener {
    private ActionBarDrawerToggle toggle;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.tab_layout_meal)
    TabLayout tabLayout;
    @BindView(R.id.meal_pager)
    ViewPager viewPager;
    private JSONObject obj;
    private ArrayList<DailyMeal> mealList;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);
        ButterKnife.bind(this);
        checkDatabase();
        setDrawerMenu();
    }

    private void checkDatabase() {
        db = new DatabaseHelper(this);
        if (db.getUserCount() != 0) {
            setTabLayout();
            if (db.getMealCount() == 0) {
                MyDiet myDiet = new MyDiet();
                ArrayList<DailyMeal> meals = myDiet.initMealList(this);
                User user = db.getUser();
                for (int i = 1; i <= 7; i++) {
                    ArrayList<DailyMeal> oneDayDiet = myDiet.PlanDiet(meals, user.getCpm(), user.getGoal());
                    for (DailyMeal meal : oneDayDiet) {
                        db.insertMeal(meal);
                    }
                    }
                }
            }
         else {
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

    @Override
    public void onFragmentInteraction() {

    }

    private void setDrawerMenu() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                DrawerMenu.selectedItemDrawer(item, DietPlanActivity.this, drawerLayout);
                return true;
            }
        });
    }

    private void setTabLayout() {
        for (int i = 1; i <= 7; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.day) + " " + String.valueOf(i)));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerMealAdapter pagerAdapter = new PagerMealAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
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
    }

    public void planDiet() {
        MyDiet myDiet = new MyDiet();
        try {
            obj = new JSONObject(JSONHelper.readJSONFromAsset(this));
            mealList = myDiet.parseJson(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}


