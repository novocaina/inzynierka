package com.example.alicja.aplikacjadietetyczna;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsActivity extends AppCompatActivity {
    private static final String TAG = "StatisticsActivity";
    LineGraphSeries<DataPoint> xySeries = new LineGraphSeries<>(new DataPoint[0]);
    DatabaseHelper myHelper;
    SQLiteDatabase sqLiteDatabase;
    DatePickerDialog.OnDateSetListener datePicker;
    SimpleDateFormat simpleDate = new SimpleDateFormat("hh:mm:ss a");
    String date;
    @BindView(R.id.numX)
    EditText mX;
    @BindView(R.id.numY)
    EditText mY;
    @BindView(R.id.btnAddPt)
    Button btnAdd;
    @BindView(R.id.btnDatePick)
    Button btnDate;
    @BindView(R.id.scatterPlot)
    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        myHelper = new DatabaseHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();

        graphView.addSeries(xySeries);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValue) {
                if (isValue) {
                    return simpleDate.format(new Date((long) value));
                } else {
                    return super.formatLabel(value, isValue);
                }
            }
        });
        xySeries.resetData(getDataPoint());
        xyInsert();
        btnDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(StatisticsActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        datePicker,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                date = day + "/" + month + "/" + year;

            }
        };


    }

    private void xyInsert() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long xValues = new Date().getTime();
                double yValues = Double.parseDouble(mX.getText().toString());

                myHelper.insertPoints(xValues, yValues);
                xySeries.resetData(getDataPoint());

            }
        });
    }

    private DataPoint[] getDataPoint() {
        String[] columns = {"xValues", "yValues"};
        Cursor cursor = sqLiteDatabase.query("series", columns, null, null, null, null, null);
        DataPoint[] dataPoints = new DataPoint[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            dataPoints[i] = new DataPoint(cursor.getLong(0), cursor.getDouble(1));
        }
        return dataPoints;
    }
}
