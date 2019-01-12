package com.example.alicja.aplikacjadietetyczna;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alicja.aplikacjadietetyczna.Objects.BMI;
import com.example.alicja.aplikacjadietetyczna.Objects.CPM;
import com.example.alicja.aplikacjadietetyczna.Objects.DailyMeal;
import com.example.alicja.aplikacjadietetyczna.Objects.MyDiet;
import com.example.alicja.aplikacjadietetyczna.Objects.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DietInfoActivity extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.weight_txt)
    EditText weight_txt;
    @BindView(R.id.height_txt)
    EditText height_txt;
    @BindView(R.id.age_txt)
    EditText age_txt;
    @BindView(R.id.activity_list)
    Spinner activity_list;
    @BindView(R.id.target_list)
    Spinner target_list;
    @BindView(R.id.pref_list)
    Spinner pref_list;
    @BindView(R.id.milk_check)
    CheckBox milk_check;
    @BindView(R.id.eggs_check)
    CheckBox eggs_check;
    @BindView(R.id.tomato_check)
    CheckBox tomato_check;
    @BindView(R.id.potato_check)
    CheckBox potato_check;
    @BindView(R.id.soy_check)
    CheckBox soy_check;
    @BindView(R.id.wheat_check)
    CheckBox wheat_check;
    @BindView(R.id.nuts_check)
    CheckBox nuts_check;
    @BindView(R.id.chocolate_check)
    CheckBox chocolate_check;
    @BindView(R.id.man)
    RadioButton man_radio;
    @BindView(R.id.woman)
    RadioButton woman_radio;
    @BindView(R.id.save_btn)
    Button save_btn;
    private DatabaseHelper db;
    private String sex;
    private String target;
    private String preference;
    private String elimination;
    private double weight;
    private double height;
    private double cpm;
    private double pal;
    private int age;


    @OnClick(R.id.save_btn)
    void OnClick() {
        String weightStr = weight_txt.getText().toString();
        String heightStr = height_txt.getText().toString();
        String ageStr = age_txt.getText().toString();
        BMI bmi = new BMI();
        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(DietInfoActivity.this, this.getString(R.string.warning_data), Toast.LENGTH_LONG).show();
        } else if (Double.parseDouble(weightStr) <= 40 || Double.parseDouble(weightStr) >= 120 || Double.parseDouble(heightStr) <= 0 || Double.parseDouble(heightStr) >= 210 || Integer.parseInt(ageStr) <= 10) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.app_requirements)
                    .setMessage(R.string.diet_warning)
                    .setNegativeButton(R.string.close_window, null)
                    .create();
            dialog.show();
        } else if (bmi.BMI_Count(Double.parseDouble(weightStr), Double.parseDouble(heightStr)) < 17) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.app_requirements)
                    .setMessage(R.string.reduction_alert)
                    .setNegativeButton(R.string.close_window, null)
                    .create();
            dialog.show();
        } else {
            weight = Double.parseDouble(weightStr);
            height = Double.parseDouble(heightStr);
            age = Integer.parseInt(ageStr);
            if (woman_radio.isChecked()) {
                sex = getString(R.string.woman);
            } else {
                sex = getString(R.string.man);
            }
            CPM newCPM = new CPM();
            cpm = newCPM.Count_CPM(weight, height, age, sex, pal);
            elimination = eliminateIngredients();
            if(cpm>3000 || elimination.length()!=0 || preference==getString(R.string.veget)){
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.info)
                        .setMessage(R.string.diet_alert)
                        .setNegativeButton(R.string.close_window, null)
                        .create();
                dialog.show();
            }
            User user = new User(cpm, weight, height, target, preference, sex, pal, age, elimination);
            SaveDataInDataBase(user);
            Toast.makeText(DietInfoActivity.this, this.getString(R.string.success), Toast.LENGTH_LONG).show();
        }
        if (save_btn.getText().toString().equals((getResources().getString(R.string.edit_data)))) {
            MyDiet myDiet = new MyDiet();
            ArrayList<DailyMeal> meals = myDiet.initMealList(this);
            myDiet.SaveUserList(db, meals);
            Toast.makeText(DietInfoActivity.this, this.getString(R.string.success), Toast.LENGTH_LONG).show();
        }


    }

    private String eliminateIngredients() {
        String eliminate = "";
        if (milk_check.isChecked()) {
            eliminate += "mlek,";
        }
        if (eggs_check.isChecked()) {
            eliminate += "jaj,";
        }
        if (nuts_check.isChecked()) {
            eliminate += "orze,";
        }
        if (wheat_check.isChecked()) {
            eliminate += "pszen,";
        }
        if (potato_check.isChecked()) {
            eliminate += "ziemniak,";
        }
        if (tomato_check.isChecked()) {
            eliminate += "pomidor,";
        }
        if (chocolate_check.isChecked()) {
            eliminate += "czekolad,";
        }
        if (soy_check.isChecked()) {
            eliminate += "soi,soj,";
        }
        return eliminate;
    }


    private void SaveDataInDataBase(User user) {
        if (db.getUserCount() == 0) {
            db.insertUser(user);

        } else {
            db.updateUser(user);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_info);
        ButterKnife.bind(this);
        setDrawerMenu();
        setSpinners();
        db = new DatabaseHelper(this);
        if (db.getUserCount() != 0) {
            setValues();
        }
    }

    private void setSpinners() {
        String[] act_table = {this.getString(R.string.activity_1), this.getString(R.string.activity_2),
                this.getString(R.string.activity_3), this.getString(R.string.activity_4), this.getString(R.string.activity_5)};

        String[] pref_table = {this.getString(R.string.none), this.getString(R.string.veget)};
        String[] target_table = {this.getString(R.string.reduction), this.getString(R.string.cons_weight), this.getString(R.string.mass)};
        ArrayAdapter<String> adapter_act = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, act_table);
        activity_list.setAdapter(adapter_act);
        ArrayAdapter<String> adapter_trg = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, target_table);
        target_list.setAdapter(adapter_trg);
        ArrayAdapter<String> adapter_pref = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pref_table);
        pref_list.setAdapter(adapter_pref);


        activity_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {
                switch ((int) position) {
                    case 0:
                        pal = 1.2;
                        break;
                    case 1:
                        pal = 1.375;
                        break;
                    case 2:
                        pal = 1.55;
                        break;
                    case 3:
                        pal = 1.725;
                        break;
                    case 4:
                        pal = 1.9;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        target_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {
                switch ((int) position) {
                    case 0:
                        target = getString(R.string.reduction);
                        break;
                    case 1:
                        target = getString(R.string.cons_weight);
                        break;
                    case 2:
                        target = getString(R.string.mass);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        pref_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {
                switch ((int) position) {
                    case 0:
                        preference = getString(R.string.none);
                        break;
                    case 1:
                        preference = getString(R.string.veget);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void setValues() {
        weight_txt.setText(String.valueOf(db.getUser().getWeight()));
        height_txt.setText(String.valueOf(db.getUser().getHeight()));
        age_txt.setText(String.valueOf(db.getUser().getAge()));
        String[] target_table = {getResources().getString(R.string.reduction), getResources().getString(R.string.cons_weight), getResources().getString(R.string.mass)};
        String[] pref_table = {getResources().getString(R.string.none), getResources().getString(R.string.veget)};
        User user = db.getUser();
        String dbSex = user.getSex();
        if (dbSex.equals(getString(R.string.man))) {
            man_radio.setChecked(true);
        }
        String dbTarget = db.getUser().getGoal();
        setSpinner(target_table, dbTarget, target_list);
        String dbPref = db.getUser().getPrefer();
        setSpinner(pref_table, dbPref, pref_list);
        double[] palTab = new double[]{1.2, 1.375, 1.55, 1.75, 1.9};
        for (int i = 0; i < palTab.length; i++) {
            if (db.getUser().getActivity() == palTab[i]) {
                activity_list.setSelection(i);
            }
        }
        save_btn.setText(getResources().getString(R.string.edit_data));
    }

    private void setSpinner(String[] tab, String name, Spinner spinner) {
        for (int i = 0; i < tab.length; i++) {
            if (name.equals(tab[i])) {
                spinner.setSelection(i);
            }
        }
    }

    private void setDrawerMenu() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                DrawerMenu.selectedItemDrawer(item, DietInfoActivity.this, drawerLayout);
                return true;
            }
        });
    }
}
