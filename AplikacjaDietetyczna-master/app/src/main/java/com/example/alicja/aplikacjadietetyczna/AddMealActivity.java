package com.example.alicja.aplikacjadietetyczna;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alicja.aplikacjadietetyczna.Objects.DailyMeal;

import org.json.JSONException;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMealActivity extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;
    @BindView(R.id.name_edit)
    EditText name_text;
    @BindView(R.id.ingredients_edit)
    EditText ingredients_text;
    @BindView(R.id.recipe_edit)
    EditText recipe_text;
    @BindView(R.id.url_edit)
    EditText url_text;
    @BindView(R.id.calories_edit)
    EditText calories_text;
    @BindView(R.id.proteins_edit)
    EditText proteins_text;
    @BindView(R.id.carbohydrates_edit)
    EditText carbohydrates_text;
    @BindView(R.id.fat_edit)
    EditText fat_text;
    @BindView(R.id.portions_edit)
    EditText portions_text;
    @BindView(R.id.type_list)
    Spinner type_spinner;
    @BindView(R.id.radio_none)
    RadioButton r_none;
    @BindView(R.id.radio_vege)
    RadioButton r_vege;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    String meal, kind;

    @OnClick(R.id.add_btn)
    void OnClick() {
        String name = name_text.getText().toString();
        String ingredients = ingredients_text.getText().toString();
        String recipe = recipe_text.getText().toString();
        String url = url_text.getText().toString();
        String portions = portions_text.getText().toString();
        if (name.isEmpty() || calories_text.getText().toString().isEmpty() || ingredients.isEmpty() || recipe.isEmpty() || url.isEmpty() || proteins_text.getText().toString().isEmpty() || carbohydrates_text.getText().toString().isEmpty() || fat_text.getText().toString().isEmpty() || portions.isEmpty()) {
            Toast.makeText(AddMealActivity.this, this.getString(R.string.warning_data), Toast.LENGTH_LONG).show();
        } else {
            if (r_none.isChecked()) {
                kind = "n";
            }
            if (r_vege.isChecked()) {
                kind = "w";
            }
            double calories = Double.parseDouble(calories_text.getText().toString());
            double proteins = Double.parseDouble(proteins_text.getText().toString());
            double carbo = Double.parseDouble(carbohydrates_text.getText().toString());
            double fat = Double.parseDouble(fat_text.getText().toString());
            DatabaseHelper db = new DatabaseHelper(this);
            db.insertUserMeal(new DailyMeal(name, ingredients, meal, kind, portions, recipe, url, calories, proteins, carbo, fat, getResources().getString(R.string.url_image)));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        ButterKnife.bind(this);
        setSpinner();
        setDrawerMenu();
    }

    private void setSpinner() {
        String[] type_table = {this.getString(R.string.breakfast), this.getString(R.string.second_breakfast),
                this.getString(R.string.dinner), this.getString(R.string.dessert), this.getString(R.string.supper)};
        ArrayAdapter<String> adapter_type = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type_table);
        type_spinner.setAdapter(adapter_type);

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch ((int) l) {

                    case 0:
                        meal = "sn";
                        break;
                    case 1:
                        meal = "ds";
                        break;
                    case 2:
                        meal = "ob";
                        break;
                    case 3:
                        meal = "pd";
                        break;
                    case 4:
                        meal = "kl";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
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
                DrawerMenu.selectedItemDrawer(item, AddMealActivity.this, drawerLayout);
                return true;
            }
        });
    }
}

