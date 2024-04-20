package com.example.example_day10;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView tvDemo;
    Button btnDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        tvDemo = findViewById(R.id.lblShow1);
        btnDemo = findViewById(R.id.btnShow1);

        btnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoAsyncTask demoAsyncTask = new DemoAsyncTask(tvDemo);
                demoAsyncTask.execute();

//                //Tại một thời điểm chỉ có 1 AsyncTask được chạy, nếu muốn chạy nhiều AsyncTask
//                //trong một thời điểm
//                demoAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                // Nhớ cmt emoAsyncTask.execute(); khi chạy demoAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }
}