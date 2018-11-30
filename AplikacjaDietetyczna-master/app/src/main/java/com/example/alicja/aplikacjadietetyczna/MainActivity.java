package com.example.alicja.aplikacjadietetyczna;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;


import com.facebook.stetho.Stetho;
import com.google.firebase.FirebaseApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActionBarDrawerToggle toggle;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerLayout;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSingleEvent(linearLayout);


    }

    private void setSingleEvent(LinearLayout linearLayout) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            CardView cardView = (CardView) linearLayout.getChildAt(i);
            final int finall = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finall == 0) {
                        Intent intent = new Intent(MainActivity.this, ParametersActivity.class);
                        startActivity(intent);
                    }
                    if (finall == 1) {
                        Intent intent = new Intent(MainActivity.this, DietInfoActivity.class);
                        startActivity(intent);
                    }
                    if (finall == 2) {
                        Intent intent = new Intent(MainActivity.this, DietPlanActivity.class);
                        startActivity(intent);
                    }
                    if (finall == 3) {
                        Intent intent = new Intent(MainActivity.this, AddMealActivity.class);
                        startActivity(intent);
                    }
                    if (finall == 4) {
                        Intent intent = new Intent(MainActivity.this, FoodListActivity.class);
                        startActivity(intent);
                    }
                    if (finall == 5) {
                        Intent intent = new Intent(MainActivity.this, CateringActivity.class);
                        startActivity(intent);
                    }
                    if (finall == 6) {
                        Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
                        startActivity(intent);
                    }
                    if (finall == 7) {
                        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_stat:
                startActivity(new Intent(MainActivity.this, StatisticsActivity.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav_param:
                startActivity(new Intent(MainActivity.this, ParametersActivity.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav_list:
                startActivity(new Intent(MainActivity.this, FoodListActivity.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav_map:
                startActivity(new Intent(MainActivity.this, CateringActivity.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav_add:
                startActivity(new Intent(MainActivity.this, AddMealActivity.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav_data:
                startActivity(new Intent(MainActivity.this, DietInfoActivity.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav_diet:
                startActivity(new Intent(MainActivity.this, DietPlanActivity.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                drawerLayout.closeDrawers();
                return true;
        }
        return false;
    }
}
