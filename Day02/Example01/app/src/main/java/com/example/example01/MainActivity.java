package com.example.example01;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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