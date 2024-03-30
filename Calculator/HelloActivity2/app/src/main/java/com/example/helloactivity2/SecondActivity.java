package com.example.helloactivity2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        String data = getIntent().getStringExtra("DATA_1");


        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("DATA_2");
        Log.i(TAG, data);
    }
}