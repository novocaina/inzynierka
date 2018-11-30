package com.example.alicja.aplikacjadietetyczna;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.alicja.aplikacjadietetyczna.Objects.DailyMeal;
import com.example.alicja.aplikacjadietetyczna.Objects.MyDiet;
import com.example.alicja.aplikacjadietetyczna.Objects.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<DailyMeal> meals, mealList,oneDayDiet;
    MyDiet myDiet;
    User user;
@BindView(R.id.day_list)
    Spinner dayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initSpinner();
        db = new DatabaseHelper(this);
        if (db.getUserCount() == 0) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.info)
                    .setMessage(R.string.empty_database)
                    .setPositiveButton(R.string.open_activity, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            Intent intent = new Intent(SettingsActivity.this, DietInfoActivity.class);
                            startActivity(intent);
                        }
                    })
                    .create();
            dialog.show();
        }
    }

    @OnClick({R.id.btn_change_week,R.id.btn_change_day})
    public void onClick(Button button) {

        initMealList();
        switch (button.getId()) {
            case R.id.btn_change_week:

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.info)
                        .setMessage(R.string.change_week_plan_alert)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                oneDayDiet=new ArrayList<>();
                                int j = 1;
                                for (int x = 1; x <= 7; x++) {
                                    oneDayDiet = myDiet.PlanDiet(meals, user.getCpm(), user.getGoal());
                                    for (DailyMeal meal : oneDayDiet) {
                                        db.updateMeal(meal, j);
                                        j++;
                                    }
                                }
                                startActivity(new Intent(SettingsActivity.this, DietInfoActivity.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .create();
                dialog.show();

                break;
            case R.id.btn_change_day:
                AlertDialog dialogDaily = new AlertDialog.Builder(this)
                        .setTitle(R.string.info)
                        .setMessage(R.string.change_daily_alert)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                oneDayDiet=new ArrayList<>();
                                int j= (Integer.parseInt(dayList.getSelectedItem().toString())-1)*5+1;
                                    oneDayDiet = myDiet.PlanDiet(meals, user.getCpm(), user.getGoal());
                                    for (DailyMeal meal : oneDayDiet) {
                                        db.updateMeal(meal, j);
                                        j++;
                                }
                                startActivity(new Intent(SettingsActivity.this, DietInfoActivity.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .create();
                dialogDaily.show();
        }
    }




    private void initMealList() {
        myDiet = new MyDiet();
        try {
            JSONObject obj = new JSONObject(JSONHelper.readJSONFromAsset(this));
            mealList = myDiet.parseJson(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        db = new DatabaseHelper(this);
        user = db.getUser();
        meals = myDiet.updateList(mealList, user.getElimination(), user.getPrefer());
    }
private void initSpinner(){
    String[] day_table = {"1","2","3","4","5","6","7"};
    ArrayAdapter<String> adapter_sx = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, day_table);
    dayList.setAdapter(adapter_sx);
}


}
