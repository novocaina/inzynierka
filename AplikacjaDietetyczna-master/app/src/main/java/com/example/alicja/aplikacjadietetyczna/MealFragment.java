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
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.example.alicja.aplikacjadietetyczna.Adapter.MealAdapter;
import com.example.alicja.aplikacjadietetyczna.Objects.DailyMeal;
import com.example.alicja.aplikacjadietetyczna.Objects.Upload;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MealFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MealFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
    private DatabaseReference databaseRef;
    private ArrayList<Upload> uploads;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal, container, false);
        ButterKnife.bind(this, view);
        FirebaseApp.initializeApp(getContext());

        setRecyclerToFragment();

        return view;
    }

    private void setRecyclerToFragment() {
        uploads = new ArrayList<>();

        databaseRef = FirebaseDatabase.getInstance().getReference();

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    for (DailyMeal meal : (ArrayList<DailyMeal>)mParam1) {
                        if (postSnapshot.getValue(Upload.class).getName().equals(meal.getName().replaceAll(" ", ""))) {
                            Upload upload = postSnapshot.getValue(Upload.class);
                            uploads.add(upload);
                        }
                        else {
                            uploads.add(new Upload("example","https://upload.wikimedia.org/wikipedia/commons/4/4e/Italian_cuisine_background-1932466_1920.jpg"));
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        MealAdapter mealRecyclerAdapter = new MealAdapter((ArrayList<DailyMeal>) mParam1, getContext(),uploads);
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
