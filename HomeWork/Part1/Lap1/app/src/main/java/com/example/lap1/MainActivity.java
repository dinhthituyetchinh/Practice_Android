package com.example.lap1;

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, Bai1Activity.class);
//        startActivity(intent);
//        Intent intent = new Intent(this, Bai2Activity.class);
//        startActivity(intent);
        Intent intent = new Intent(this, Bai3Activity.class);
        startActivity(intent);
    }
}