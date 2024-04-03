package com.example.example01;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private final String Log_Tag = MainActivity.class.getSimpleName();
   // Button btnChangeActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(Log_Tag, "calling onCreate from MainActivity");
//
//        btnChangeActivity = findViewById(R.id.button1);
//        btnChangeActivity.setOnClickListener(e->{
//            toNextActivity();
//        });

        toNextActivity();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //setContentView(R.layout.activity_main);
        Log.d(Log_Tag, "calling onStart from MainActivity");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Log_Tag, "calling onResume from MainActivity");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Log_Tag, "calling onPause from MainActivity");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Log_Tag, "calling onStop from MainActivity");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Log_Tag, "calling onDestroy from MainActivity");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Log_Tag, "calling onRestart from MainActivity");

    }

   public void  toNextActivity()
   {
       Thread setTime = new Thread();
       try
       {
           sleep(3000);
       }
       catch (Exception e)
       {

       }
       finally
       {
           Log.d(Log_Tag, "calling Next Activity");
           Intent intent = new Intent(this, NextActivity.class);
           startActivity(intent);
       }

       setTime.start();
   }

}