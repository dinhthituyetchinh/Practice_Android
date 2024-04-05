package com.example.example02;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    public void  toSecondActivity()
    {
        Thread setTime = new Thread();

        try {
            sleep(5000);
        }
        catch (Exception e)
        {

        }
        finally {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
        setTime.start();
    }
}