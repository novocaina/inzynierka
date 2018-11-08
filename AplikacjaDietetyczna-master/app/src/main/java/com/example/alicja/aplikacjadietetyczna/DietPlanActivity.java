package com.example.alicja.aplikacjadietetyczna;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DietPlanActivity extends AppCompatActivity implements MealFragment.OnFragmentInteractionListener {
@BindView(R.id.tab_layout_meal)
TabLayout tabLayout;
@BindView(R.id.meal_pager)
ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);
        ButterKnife.bind(this);
        for(int i=1;i<=7;i++) {
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.day)+ " " + String.valueOf(i)));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerMealAdapter pagerAdapter=new PagerMealAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
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
