package com.example.alicja.aplikacjadietetyczna.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.alicja.aplikacjadietetyczna.BMIFragment;
import com.example.alicja.aplikacjadietetyczna.CPMFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int pageNumber;

    public PagerAdapter(FragmentManager fm, int number) {
        super(fm);
        this.pageNumber = number;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BMIFragment bmiFragment = new BMIFragment();
                return bmiFragment;
            case 1:
                CPMFragment cpmFragment = new CPMFragment();
                return cpmFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageNumber;
    }
}
