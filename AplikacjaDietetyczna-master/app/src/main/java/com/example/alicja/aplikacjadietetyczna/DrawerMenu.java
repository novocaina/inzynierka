package com.example.alicja.aplikacjadietetyczna;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

class DrawerMenu {

    static void selectedItemDrawer(MenuItem menuItem, Context context, DrawerLayout drawerLayout) {
        Intent select;
        switch (menuItem.getItemId()) {
            case R.id.nav_stat:
                select=new Intent(context, StatisticsActivity.class);
                break;

            case R.id.nav_param:
                select= new Intent(context, ParametersActivity.class);
                break;

            case R.id.nav_list:
                select= new Intent(context, FoodListActivity.class);
                break;

            case R.id.nav_map:
                select= new Intent(context, CateringActivity.class);
                break;

            case R.id.nav_add:
                select= new Intent(context, AddMealActivity.class);
                break;

            case R.id.nav_data:
                select= new Intent(context, DietInfoActivity.class);
                break;

            case R.id.nav_diet:
                select= new Intent(context, DietPlanActivity.class);
                break;

            case R.id.nav_settings:
                select= new Intent(context, SettingsActivity.class);
                break;
            default:
                select = new Intent(context, MainActivity.class);
                break;
        }

        context.startActivity(select);
        drawerLayout.closeDrawers();
    }
}
