package com.example.alicja.aplikacjadietetyczna.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.alicja.aplikacjadietetyczna.MealFragment;
import com.example.alicja.aplikacjadietetyczna.Objects.DailyMeal;
import com.example.alicja.aplikacjadietetyczna.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Alicja on 2018-10-31.
 */

public class PagerMealAdapter extends FragmentStatePagerAdapter {
    private int pageNumber;
   private Context context;
    private ArrayList<ArrayList<DailyMeal>> listMeal;
    public PagerMealAdapter(FragmentManager fm, Context context, int number) {
        super(fm);
        this.context = context;
        this.pageNumber=number;
    }


    @Override
    public Fragment getItem(int position) {
        initObjects();
        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return MealFragment.newInstance(listMeal.get(position));

            default:
                return null;
        }
    }
    private void initObjects() {
        listMeal = new ArrayList<>();
        ArrayList<DailyMeal> dailyMealList=new ArrayList<>();
        DatabaseHelper db = new DatabaseHelper(context);
        for (int i = 1; i <= db.getMealCount(); i++) {
            dailyMealList.add(db.getMeal(i));
            if(dailyMealList.size()==5){
                listMeal.add(dailyMealList);
                dailyMealList= new ArrayList<>();
            }
        }
        Bundle args = new Bundle();
        args.putSerializable("mealList", listMeal);
    }
    @Override
    public int getCount() {
        return pageNumber;
    }

}
