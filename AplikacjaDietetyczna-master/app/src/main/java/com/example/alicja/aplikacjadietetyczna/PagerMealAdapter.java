package com.example.alicja.aplikacjadietetyczna;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Alicja on 2018-10-31.
 */

public class PagerMealAdapter extends FragmentStatePagerAdapter {
    int pageNumber;

    public PagerMealAdapter(FragmentManager fm, int number) {
        super(fm);
        this.pageNumber = number;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            MealFragment mealFragment=new MealFragment();
            return mealFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageNumber;
    }
}
