package com.example.alicja.aplikacjadietetyczna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMealActivity extends AppCompatActivity {

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
    String meal,kind;
    @OnClick(R.id.add_btn)

    void OnClick() {
        String name=name_text.getText().toString();
        String calories=calories_text.getText().toString();
        String ingredients=ingredients_text.getText().toString();
        String recipe=recipe_text.getText().toString();
        String url=url_text.getText().toString();
        String proteins=proteins_text.getText().toString();
        String carbo=carbohydrates_text.getText().toString();
        String fat=fat_text.getText().toString();
        String portions=portions_text.getText().toString();
        if(name.isEmpty()||calories.isEmpty()||ingredients.isEmpty()||recipe.isEmpty()||url.isEmpty()||proteins.isEmpty()||carbo.isEmpty()||fat.isEmpty()||portions.isEmpty())
        {
            Toast.makeText(AddMealActivity.this, this.getString(R.string.warning_data), Toast.LENGTH_LONG).show();
        }
        else{
            if(r_none.isChecked()){
                kind="n";
            }
            if(r_vege.isChecked()){
                kind="w";
            }
            String json=JSONHelper.readJSONFromAsset(this);
            try {
                JSONHelper.addObjectToJSON(json,name,calories,meal,kind,ingredients,recipe,url,proteins,carbo,fat,portions);
                Toast.makeText(AddMealActivity.this, this.getString(R.string.add_success), Toast.LENGTH_LONG).show();
            } catch (Exception e){
                Toast.makeText(AddMealActivity.this, this.getString(R.string.add_fail), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        ButterKnife.bind(this);
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
}

