package com.example.example;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters
    private String fName;
    private int yOld;

    public BlankFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String fullName, int yearOld)
    {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString("full_name", fullName);
        args.putInt("year_old", yearOld);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fName = getArguments().getString("full_name");
            yOld = getArguments().getInt("year_old");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ((TextView) view.findViewById(R.id.txtFragmentFullName)).setText(fName);
        ((TextView) view.findViewById(R.id.txtFragmentYearOld)).setText(String.valueOf(yOld));

        return view;
    }
}