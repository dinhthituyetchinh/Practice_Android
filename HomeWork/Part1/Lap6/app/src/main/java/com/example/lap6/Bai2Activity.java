package com.example.lap6;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Bai2Activity extends AppCompatActivity {
    Toolbar toolbar;
    Button btn;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2);

        toolbar = findViewById(R.id.toolbarBai2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("FragmentDemo");

        fragmentManager = getSupportFragmentManager();
        btn = (Button) findViewById(R.id.btnAdd);
        btn.setOnClickListener(e->{
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayoutBai2, new Fragment1());
            fragmentTransaction.commit();
        });
    }
}