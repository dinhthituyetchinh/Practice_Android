package com.example.a2001215640_dinhthituyetchinh_kt;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toNextActivity();
    }

    public void toNextActivity()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run() {

                Intent mInHome = new Intent(MainActivity.this, ListMemberActivity.class);
                MainActivity.this.startActivity(mInHome);
                MainActivity.this.finish();
            }
        }, 3000);

    }
}