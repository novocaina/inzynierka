package com.example.alicja.aplikacjadietetyczna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DietInfo extends AppCompatActivity {
    @BindView(R.id.weight_txt)

    EditText weight_txt;

    @BindView(R.id.height_txt)

    EditText height_txt;

    @BindView(R.id.age_txt)

    EditText age_txt;

    @BindView(R.id.sex_list)

    Spinner sex_list;

    @BindView(R.id.activity_list)

    Spinner activity_list;

    @BindView(R.id.target_list)

    Spinner target_list;

    @BindView(R.id.pref_list)

    Spinner pref_list;

    ListItemHelper db;

    String sex, target, preference;

    double weight, height, cpm, pal;

    int age;



    @OnClick(R.id.save_btn)
    void OnClick() {
        String weightStr = weight_txt.getText().toString();
        String heightStr = height_txt.getText().toString();
        String ageStr = age_txt.getText().toString();



        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {



            Toast.makeText(DietInfo.this, this.getString(R.string.warning_data), Toast.LENGTH_LONG).show();

        } else if (Double.parseDouble(weightStr) <= 0 || Double.parseDouble(weightStr) <= 0 || Integer.parseInt(ageStr) <= 0) {

            Toast.makeText(DietInfo.this, this.getString(R.string.value_str), Toast.LENGTH_LONG).show();

        } else {

            weight = Double.parseDouble(weightStr);

            height = Double.parseDouble(heightStr);

            age = Integer.parseInt(ageStr);

            CPM newCPM = new CPM();

            cpm = (newCPM.Count_CPM(weight, height, age, sex, pal));

            User user = new User(cpm, target, preference);

            SaveDataInDataBase(user);

            Toast.makeText(DietInfo.this,this.getString(R.string.success),Toast.LENGTH_LONG).show();

        }





    }



    public void SaveDataInDataBase(User user) {

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

        db = new ListItemHelper(this);



        String[] sex_table = {this.getString(R.string.woman), this.getString(R.string.man)};

        String[] act_table = {this.getString(R.string.activity_1), this.getString(R.string.activity_2),

                this.getString(R.string.activity_3), this.getString(R.string.activity_4), this.getString(R.string.activity_5)};

        String[] target_table = {this.getString(R.string.reduction), this.getString(R.string.cons_weight), this.getString(R.string.mass)};

        String[] pref_table = {this.getString(R.string.none), this.getString(R.string.veget)};

        ArrayAdapter<String> adapter_sx = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sex_table);

        sex_list.setAdapter(adapter_sx);

        ArrayAdapter<String> adapter_act = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, act_table);

        activity_list.setAdapter(adapter_act);

        ArrayAdapter<String> adapter_trg = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, target_table);

        target_list.setAdapter(adapter_trg);

        ArrayAdapter<String> adapter_pref = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pref_table);

        pref_list.setAdapter(adapter_pref);



        sex_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {



            @Override

            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {





                switch ((int) position) {

                    case 0:

                        sex = "k";

                        break;

                    case 1:

                        sex = "m";

                        break;



                }

            }



            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {



            }



        });

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

                        target = "r";

                        break;

                    case 1:

                        target = "u";

                        break;

                    case 2:

                        target = "m";

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

                        preference = "n";

                        break;

                    case 1:

                        preference = "w";

                        break;



                }

            }



            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {



            }



        });
    }
}
