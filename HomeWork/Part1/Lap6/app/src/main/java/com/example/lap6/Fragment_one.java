package com.example.lap6;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.service.carrier.CarrierMessagingService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Fragment_one extends Fragment {
    SendMessage sm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable  ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnPassData = (Button) view.findViewById(R.id.btnPassData);
        final EditText inData =(EditText) view.findViewById(R.id.inMessage);

        btnPassData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.sendData(inData.getText().toString().trim());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            sm = (SendMessage) getActivity();
        }catch (ClassCastException e)
        {
            throw  new ClassCastException("Error in retrieving data. Please try again");
        }
    }
}