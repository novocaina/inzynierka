package com.example.alicja.aplikacjadietetyczna;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.alicja.aplikacjadietetyczna.Objects.XYValue;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StatisticChartFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    LineGraphSeries<DataPoint> xySeries = new LineGraphSeries<>(new DataPoint[0]);
    DatabaseHelper myHelper;
    SQLiteDatabase sqLiteDatabase;
    DatePickerDialog.OnDateSetListener datePicker;
    SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yy");
    SimpleDateFormat gridDate = new SimpleDateFormat("dd.MM");
    String date;
    @BindView(R.id.numX)
    EditText mX;
    @BindView(R.id.btnAddPt)
    Button btnAdd;
    @BindView(R.id.btnDatePick)
    Button btnDate;
    @BindView(R.id.scatterPlot)
    GraphView graphView;

    private OnFragmentInteractionListener mListener;

    public StatisticChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticChartFragment newInstance(String param1, String param2) {
        StatisticChartFragment fragment = new StatisticChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private void xyInsert() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Date data = simpleDate.parse(date);
                    long xValues = data.getTime();
                    double yValues = Double.parseDouble(mX.getText().toString());
                    XYValue xyValue=new XYValue(xValues,yValues);
                    myHelper.insertXYValues(xyValue);
                    if(getDataPoint()!=null) {
                        xySeries.resetData(getDataPoint());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private DataPoint[] getDataPoint() {
        if(myHelper.getXYValuesCount()!=0) {
        DataPoint[] dataPoints = new DataPoint[myHelper.getXYValuesCount()];

    for (int i = 1; i <= myHelper.getXYValuesCount(); i++) {
        myHelper.getXYValue(i);
        dataPoints[i-1] = new DataPoint(myHelper.getXYValue(i).getX(), myHelper.getXYValue(i).getY());
    }
            return dataPoints;
}
       else return new DataPoint[]{new DataPoint(0,0)};
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_statistic_chart, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        myHelper = new DatabaseHelper(getContext());
        sqLiteDatabase = myHelper.getWritableDatabase();

        graphView.addSeries(xySeries);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValue) {
                if (isValue) {
                    return gridDate.format(new Date((long) value));
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

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
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
                date = day + "-" + month + "-" + year;

            }
        };

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
