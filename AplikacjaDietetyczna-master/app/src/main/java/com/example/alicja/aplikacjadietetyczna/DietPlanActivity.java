package com.example.alicja.aplikacjadietetyczna;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DietPlanActivity extends AppCompatActivity implements MealFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
