package com.example.alicja.aplikacjadietetyczna;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Statistics extends AppCompatActivity {
    private static final String TAG = "Statistics";
    PointsGraphSeries<DataPoint> xySeries;
    @BindView(R.id.numX)
    EditText mX;
    @BindView(R.id.numY)
    EditText mY;
    @BindView(R.id.btnAddPt)
    Button btnAdd;
    @BindView(R.id.scatterPlot)
    GraphView graphView;
    private ArrayList<XYValue> xyValueArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    ButterKnife.bind(this);
        xyValueArray = new ArrayList<>();

        init();
    }

    private void init() {
        xySeries = new PointsGraphSeries<>();



        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                if(!mX.getText().toString().equals("") && !mY.getText().toString().equals("") ){

                    double x = Double.parseDouble(mX.getText().toString());

                    double y = Double.parseDouble(mY.getText().toString());

                    Log.d(TAG, "onClick: Adding a new point. (x,y): (" + x + "," + y + ")" );

                    xyValueArray.add(new XYValue(x,y));

                    init();

                }else {

                    toastMessage("You must fill out both fields!");

                }

            }

        });



        //little bit of exception handling for if there is no data.

        if(xyValueArray.size() != 0){

            createScatterPlot();

        }else{

            Log.d(TAG, "onCreate: No data to plot.");

        }

    }



    private void createScatterPlot() {

        Log.d(TAG, "createScatterPlot: Creating scatter plot.");



        //sort the array of xy values

        xyValueArray = sortArray(xyValueArray);



        //add the data to the series

        for(int i = 0;i <xyValueArray.size(); i++){

            try{

                double x = xyValueArray.get(i).getX();

                double y = xyValueArray.get(i).getY();

                xySeries.appendData(new DataPoint(x,y),true, 1000);

            }catch (IllegalArgumentException e){

                Log.e(TAG, "createScatterPlot: IllegalArgumentException: " + e.getMessage() );

            }

        }



        //set some properties

        xySeries.setShape(PointsGraphSeries.Shape.RECTANGLE);

        xySeries.setColor(Color.BLUE);

        xySeries.setSize(20f);



        //set Scrollable and Scaleable

        graphView.getViewport().setScalable(true);

        graphView.getViewport().setScalableY(true);

        graphView.getViewport().setScrollable(true);

        graphView.getViewport().setScrollableY(true);



        //set manual x bounds

        graphView.getViewport().setYAxisBoundsManual(true);

        graphView.getViewport().setMaxY(150);

        graphView.getViewport().setMinY(-150);



        //set manual y bounds

        graphView.getViewport().setXAxisBoundsManual(true);

        graphView.getViewport().setMaxX(150);

        graphView.getViewport().setMinX(-150);



        graphView.addSeries(xySeries);

    }



    /**

     * Sorts an ArrayList<XYValue> with respect to the x values.

     * @param array

     * @return

     */

    private ArrayList<XYValue> sortArray(ArrayList<XYValue> array){

        /*

        //Sorts the xyValues in Ascending order to prepare them for the PointsGraphSeries<DataSet>

         */

        int factor = Integer.parseInt(String.valueOf(Math.round(Math.pow(array.size(),2))));

        int m = array.size() - 1;

        int count = 0;

        Log.d(TAG, "sortArray: Sorting the XYArray.");





        while (true) {

            m--;

            if (m <= 0) {

                m = array.size() - 1;

            }

            Log.d(TAG, "sortArray: m = " + m);

            try {

                //print out the y entrys so we know what the order looks like

                //Log.d(TAG, "sortArray: Order:");

                //for(int n = 0;n < array.size();n++){

                //Log.d(TAG, "sortArray: " + array.get(n).getY());

                //}

                double tempY = array.get(m - 1).getY();

                double tempX = array.get(m - 1).getX();

                if (tempX > array.get(m).getX()) {

                    array.get(m - 1).setY(array.get(m).getY());

                    array.get(m).setY(tempY);

                    array.get(m - 1).setX(array.get(m).getX());

                    array.get(m).setX(tempX);

                } else if (tempX == array.get(m).getX()) {

                    count++;

                    Log.d(TAG, "sortArray: count = " + count);

                } else if (array.get(m).getX() > array.get(m - 1).getX()) {

                    count++;

                    Log.d(TAG, "sortArray: count = " + count);

                }

                //break when factorial is done

                if (count == factor) {

                    break;

                }

            } catch (ArrayIndexOutOfBoundsException e) {


                break;

            }

        }

        return array;

    }



    /**

     * customizable toast

     * @param message

     */

    private void toastMessage(String message){

        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();

    }
    }
