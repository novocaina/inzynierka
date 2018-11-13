package com.example.alicja.aplikacjadietetyczna;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsActivity extends AppCompatActivity implements StatisticChartFragment.OnFragmentInteractionListener,StatisticListFragment.OnFragmentInteractionListener {
    @BindView(R.id.tab_layout_stat)
    TabLayout tabLayout;
    @BindView(R.id.stat_pager)
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        tabLayout.addTab(tabLayout.newTab().setText("Wykres"));
        tabLayout.addTab(tabLayout.newTab().setText("Lista"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerStatisticAdapter pagerAdapter=new PagerStatisticAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
