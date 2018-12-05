package com.example.alicja.aplikacjadietetyczna;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.daasuu.bl.BubbleLayout;
import com.daasuu.bl.BubblePopupHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FatLevelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FatLevelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FatLevelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FatLevelFragment() {
        // Required empty public constructor
    }

    PopupWindow popupWindow;
    BubbleLayout bubbleLayout;
    @BindView(R.id.man)
    RadioButton man_btn;
    @BindView(R.id.woman)
    RadioButton woman_btn;
    @BindView(R.id.your_whr_txt)
    TextView whrTitle;
    @BindView(R.id.whr_txt)
    TextView whrTxt;
    @BindView(R.id.type_txt)
    TextView whrType;
    @BindView(R.id.your_bf_txt)
    TextView bfTitle;
    @BindView(R.id.bf_txt)
    TextView bfTxt;

    @OnClick(R.id.info_draw)
    void onDrawableClick(View view){
        int[] location = new int[2];
        view.getLocationInWindow(location);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], view.getHeight() + location[1]);
    }
    public static FatLevelFragment newInstance(String param1, String param2) {
        FatLevelFragment fragment = new FatLevelFragment();
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
        View view = inflater.inflate(R.layout.fragment_fat_level, container, false);
        ButterKnife.bind(this, view);
        bubbleLayout = (BubbleLayout) LayoutInflater.from(getContext()).inflate(R.layout.sample_popup_layout, null);
        popupWindow = BubblePopupHelper.create(getContext(), bubbleLayout);
        TextView popupTxt=bubbleLayout.findViewById(R.id.popupText);
popupTxt.setText(getString(R.string.fat_popup));
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
    public double calcWHR(double waist, double hips){
        double WHR=waist/hips;
        if((woman_btn.isChecked() && WHR>0.88)||(man_btn.isChecked()&&WHR>1)){
whrType.setText(R.string.androidal);
        }
        else{
            whrType.setText(R.string.gynoidal);
        }
        return WHR;
    }
}
