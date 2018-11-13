package com.example.alicja.aplikacjadietetyczna;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerStatisticAdapter extends FragmentStatePagerAdapter {
    int pageNumber;

    public PagerStatisticAdapter(FragmentManager fm, int number) {
        super(fm);
        this.pageNumber = number;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                StatisticChartFragment statisticChartFragment = new StatisticChartFragment();
                return statisticChartFragment;
            case 1:
               StatisticListFragment statisticListFragment = new StatisticListFragment();
                return statisticListFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageNumber;
    }
}

