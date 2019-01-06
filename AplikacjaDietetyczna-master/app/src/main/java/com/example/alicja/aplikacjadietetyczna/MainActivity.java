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
import android.widget.LinearLayout;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
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

        setDrawerMenu();
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
                DrawerMenu.selectedItemDrawer(item, MainActivity.this, drawerLayout);
                return true;
            }
        });
    }
}
