package com.example.alicja.aplikacjadietetyczna;

import android.content.Context;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.alicja.aplikacjadietetyczna.Adapter.MealAdapter;
import com.example.alicja.aplikacjadietetyczna.Objects.DailyMeal;



import java.io.Serializable;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MealFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";


    private Serializable mParam1;

    private OnFragmentInteractionListener mListener;

    public MealFragment() {
        // Required empty public constructor
    }


    public static MealFragment newInstance(ArrayList<DailyMeal> param1) {
        MealFragment fragment = new MealFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getSerializable(ARG_PARAM1);
        }

    }

    @BindView(R.id.meal_list)
    RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal, container, false);
        ButterKnife.bind(this, view);
        setRecyclerToFragment();

        return view;
    }

    private void setRecyclerToFragment() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        MealAdapter mealRecyclerAdapter = new MealAdapter((ArrayList<DailyMeal>) mParam1, getContext());
        recyclerView.setAdapter(mealRecyclerAdapter);

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
