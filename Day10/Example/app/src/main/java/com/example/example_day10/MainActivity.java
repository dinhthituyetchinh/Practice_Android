package com.example.example_day10;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tvDemo;
    Button btnDemo;
    DemoThread demoThread;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvDemo = findViewById(R.id.lblShow);
        btnDemo = findViewById(R.id.btnShow);

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);


//        handler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(@NonNull Message msg) {
//                Log.e("MainActivity", "Nhận dữ liệu");
//                int i = msg.getData().getInt("data");
//                tvDemo.setText(String.valueOf(i));
//                return true;
//            }
//        });
//        btnDemo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                demoThread = new DemoThread(handler);
//            demoThread.start();
//
//
//            }
//        });
    }
}