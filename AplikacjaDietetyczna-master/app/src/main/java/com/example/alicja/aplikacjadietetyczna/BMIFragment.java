package com.example.alicja.aplikacjadietetyczna;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.daasuu.bl.ArrowDirection;
import com.daasuu.bl.BubbleLayout;
import com.daasuu.bl.BubblePopupHelper;
import com.example.alicja.aplikacjadietetyczna.Objects.BMI;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BMIFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BMIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BMIFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BMIFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BMIFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BMIFragment newInstance(String param1, String param2) {
        BMIFragment fragment = new BMIFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.weight_txt)
    EditText weight_txt;
    @BindView(R.id.height_txt)
    EditText height_txt;
    @BindView(R.id.bmi_txt)
    TextView bmi_txt;
    @BindView(R.id.your_bmi_txt)
    TextView your_bmi_txt;
    @BindView(R.id.bmi_info_txt)
    TextView bmi_info_txt;
PopupWindow popupWindow;
    BubbleLayout bubbleLayout;
    @OnClick(R.id.info_draw)
    void onDrawableClick(View view){
        int[] location = new int[2];
        view.getLocationInWindow(location);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], view.getHeight() + location[1]);
    }


    @OnClick(R.id.bmi_btn)
    void OnClick() {

        String weightStr = weight_txt.getText().toString();
        String heightStr = height_txt.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(getActivity(), this.getString(R.string.warning_data), Toast.LENGTH_LONG).show();
        } else if (Double.parseDouble(weightStr) <= 0 || Double.parseDouble(weightStr) <= 0) {
            Toast.makeText(getActivity(), this.getString(R.string.value_str), Toast.LENGTH_LONG).show();
        } else {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);
            BMI newBMI = new BMI();
            double bmi = newBMI.BMI_Count(weight, height);
            your_bmi_txt.setVisibility(View.VISIBLE);
            String bmiStr = String.format("%.2f", bmi);
            bmi_txt.setText(bmiStr);
            String bmi_text = BMI_text(bmi);
            bmi_info_txt.setText(bmi_text);

        }

    }

    public String BMI_text(double bmi) {

        if (bmi < 16) {
            return this.getString(R.string.starvation);
        } else if (bmi >= 16 && bmi <= 16.99) {
            return this.getString(R.string.emaciaton);
        } else if (bmi >= 17 && bmi <= 18.49) {
            return this.getString(R.string.low_weight);
        } else if (bmi >= 18.5 && bmi <= 24.99) {
            return this.getString(R.string.normal_weight);
        } else if (bmi >= 25 && bmi <= 29.99) {
            return this.getString(R.string.obeseI);
        } else if (bmi >= 30 && bmi <= 39.99) {
            return this.getString(R.string.obeseII);
        } else {
            return this.getString(R.string.obeseIII);
        }

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);
        ButterKnife.bind(this, view);
        bubbleLayout = (BubbleLayout) LayoutInflater.from(getContext()).inflate(R.layout.sample_popup_layout, null);
        popupWindow = BubblePopupHelper.create(getContext(), bubbleLayout);
        TextView popupTxt=bubbleLayout.findViewById(R.id.popupText);
        popupTxt.setText(getString(R.string.bmi_popup));

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
