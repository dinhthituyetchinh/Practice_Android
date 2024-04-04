package com.example.example01;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toSecondActivity();
    }

    public void toSecondActivity()
    {
        Thread setTime = new Thread();

        try {
            sleep (3000);
        }
        catch (Exception E)
        {

        }
        finally {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
        setTime.start();
    }
}