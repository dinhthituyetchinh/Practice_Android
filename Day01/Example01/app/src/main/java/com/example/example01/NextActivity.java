package com.example.example01;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends AppCompatActivity
{
    private final String Log_Tag = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Log.d(Log_Tag, "calling onCreate from NextActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Log_Tag, "calling onStart from NextActivity");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Log_Tag, "calling onResume from NextActivity");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Log_Tag, "calling onPause from NextActivity");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Log_Tag, "calling onStop from NextActivity");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Log_Tag, "calling onDestroy from NextActivity");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Log_Tag, "calling onRestart from NextActivity");

    }
}
