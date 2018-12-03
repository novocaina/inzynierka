package com.example.alicja.aplikacjadietetyczna.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.alicja.aplikacjadietetyczna.BMIFragment;
import com.example.alicja.aplikacjadietetyczna.CPMFragment;
import com.example.alicja.aplikacjadietetyczna.FatLevelFragment;

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
                return new BMIFragment();
            case 1:
                return new CPMFragment();
            case 2:
                return new FatLevelFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageNumber;
    }
}
