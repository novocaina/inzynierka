package com.example.alicja.aplikacjadietetyczna.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.example.alicja.aplikacjadietetyczna.AddMealActivity;
import com.example.alicja.aplikacjadietetyczna.CateringActivity;
import com.example.alicja.aplikacjadietetyczna.DietInfoActivity;
import com.example.alicja.aplikacjadietetyczna.DietPlanActivity;
import com.example.alicja.aplikacjadietetyczna.FoodListActivity;
import com.example.alicja.aplikacjadietetyczna.MainActivity;
import com.example.alicja.aplikacjadietetyczna.ParametersActivity;
import com.example.alicja.aplikacjadietetyczna.R;
import com.example.alicja.aplikacjadietetyczna.SettingsActivity;
import com.example.alicja.aplikacjadietetyczna.StatisticsActivity;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Alicja on 2018-12-03.
 */

public class DrawerMenu {
    DrawerMenu toggle;



    public static void onNavigationItemSelected(@NonNull MenuItem item, Context context,DrawerLayout drawerLayout) {
        Intent intent;
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_stat:
                intent=new Intent(context, StatisticsActivity.class);
                drawerLayout.closeDrawers();

            case R.id.nav_param:
               intent=new Intent(context, ParametersActivity.class);
                drawerLayout.closeDrawers();

            case R.id.nav_list:
                intent=new Intent(context, FoodListActivity.class);
                drawerLayout.closeDrawers();

            case R.id.nav_map:
                intent=new Intent(context, CateringActivity.class);
                drawerLayout.closeDrawers();

            case R.id.nav_add:
                intent=new Intent(context, AddMealActivity.class);
                drawerLayout.closeDrawers();

            case R.id.nav_data:
                intent=new Intent(context, DietInfoActivity.class);
                drawerLayout.closeDrawers();

            case R.id.nav_diet:
               intent=new Intent(context, DietPlanActivity.class);
                drawerLayout.closeDrawers();

            case R.id.nav_settings:
               intent=new Intent(context, SettingsActivity.class);
                drawerLayout.closeDrawers();

        }

    }
}
