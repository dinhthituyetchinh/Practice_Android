package com.example.lap6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TinhToanFragment extends Fragment {

    EditText txtSoA, txtSoB;

    // TODO: Rename and change types of parameters
    private int soA;
    private int soB;

    public TinhToanFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tinh_toan, container, false);
        return view;
    }
}