package com.example.alicja.aplikacjadietetyczna;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alicja.aplikacjadietetyczna.Objects.CPM;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CPMFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CPMFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CPMFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    @BindView(R.id.weightcpm_txt)
    EditText weightCPM_txt;
    @BindView(R.id.heightcpm_txt)
    EditText heightCPM_txt;
    @BindView(R.id.age_txt)
    EditText age_txt;
    @BindView(R.id.cpm_txt)
    TextView cpm_txt;
    @BindView(R.id.your_cpm_txt)
    TextView your_cpm_txt;
    @BindView(R.id.activity_list)
    Spinner activity_list;
    @BindView(R.id.man)
    RadioButton man_radio;
    @BindView(R.id.woman)
    RadioButton woman_radio;

    String sex;
    double pal;


    @OnClick(R.id.cpm_btn)
    void OnClick() {

        String weightStr = weightCPM_txt.getText().toString();
        String heightStr = heightCPM_txt.getText().toString();
        String ageStr = age_txt.getText().toString();


        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(getActivity(), this.getString(R.string.warning_data), Toast.LENGTH_LONG).show();

        } else if (Double.parseDouble(weightStr) <= 0 || Double.parseDouble(weightStr) <= 0 || Integer.parseInt(ageStr) <= 0) {
            Toast.makeText(getActivity(), this.getString(R.string.value_str), Toast.LENGTH_LONG).show();
        } else {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);
            int age = Integer.parseInt(ageStr);
            if(woman_radio.isChecked()){
                sex="k";
            }
            else{
                sex="m";
            }
            your_cpm_txt.setVisibility(View.VISIBLE);
            CPM newCPM = new CPM();
            double cpm = newCPM.Count_CPM(weight, height, age, sex, pal);
            String cpmStr = String.format("%.2f", cpm);
            cpm_txt.setText(cpmStr);

        }


    }

    public CPMFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CPMFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CPMFragment newInstance(String param1, String param2) {
        CPMFragment fragment = new CPMFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cpm, container, false);
        ButterKnife.bind(this, view);

        String[] act_table = {this.getString(R.string.activity_1), this.getString(R.string.activity_2),
                this.getString(R.string.activity_3), this.getString(R.string.activity_4), this.getString(R.string.activity_5)};
        ArrayAdapter<String> adapter_act = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, act_table);
        activity_list.setAdapter(adapter_act);


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

        return view;
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
