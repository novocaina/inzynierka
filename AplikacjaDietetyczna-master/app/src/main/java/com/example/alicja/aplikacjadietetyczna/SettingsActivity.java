package com.example.alicja.aplikacjadietetyczna;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.alicja.aplikacjadietetyczna.Objects.DailyMeal;
import com.example.alicja.aplikacjadietetyczna.Objects.MyDiet;
import com.example.alicja.aplikacjadietetyczna.Objects.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private ArrayList<DailyMeal> meals;
    private ArrayList<DailyMeal> oneDayDiet;
    private MyDiet myDiet;
    private User user;
    private ActionBarDrawerToggle toggle;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.day_list)
    Spinner dayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initSpinner();
        setDrawerMenu();
        checkDatabase();
    }

    private void checkDatabase() {
        db = new DatabaseHelper(this);
        if (db.getUserCount() == 0) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.info)
                    .setMessage(R.string.empty_database)
                    .setPositiveButton(R.string.open_activity, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            Intent intent = new Intent(SettingsActivity.this, DietInfoActivity.class);
                            startActivity(intent);
                        }
                    })
                    .create();
            dialog.show();
        }
    }

    @OnClick({R.id.btn_change_week, R.id.btn_change_day})
    public void onClick(Button button) {
        myDiet = new MyDiet();
        meals = myDiet.initMealList(this);
        switch (button.getId()) {
            case R.id.btn_change_week:

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.info)
                        .setMessage(R.string.change_week_plan_alert)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                myDiet.SaveUserList(db, meals);
                                startActivity(new Intent(SettingsActivity.this, DietPlanActivity.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .create();
                dialog.show();

                break;
            case R.id.btn_change_day:
                AlertDialog dialogDaily = new AlertDialog.Builder(this)
                        .setTitle(R.string.info)
                        .setMessage(R.string.change_daily_alert)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                oneDayDiet = new ArrayList<>();
                                int j = (Integer.parseInt(dayList.getSelectedItem().toString()) - 1) * 5 + 1;
                                oneDayDiet = myDiet.PlanDiet(meals, user.getCpm(), user.getGoal());
                                for (DailyMeal meal : oneDayDiet) {
                                    db.updateMeal(meal, j);
                                    j++;
                                }
                                startActivity(new Intent(SettingsActivity.this, DietPlanActivity.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .create();
                dialogDaily.show();
        }
    }


    private void initSpinner() {
        String[] day_table = {"1", "2", "3", "4", "5", "6", "7"};
        ArrayAdapter<String> adapter_sx = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, day_table);
        dayList.setAdapter(adapter_sx);
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
                DrawerMenu.selectedItemDrawer(item, SettingsActivity.this, drawerLayout);
                return true;
            }
        });
    }

}
