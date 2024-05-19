package com.example.day12;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.day12.databinding.ActivityMainBinding;
import com.example.day12.databinding.FragmentBlankBinding;

public class MainActivity extends AppCompatActivity {

//    Bước 1 trong build.gradle
//    Bước 2: tạo View Binding với class
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        binding.tvDemo.setText("View Binding in text View - MainActivity");
        setContentView(binding.getRoot());

        replaceBlankFrangment();
    }

    public void replaceBlankFrangment()
    {
        BlankFragment f = BlankFragment.newInstance();
        FragmentTransaction  fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(
            binding.frameFragment.getId(),
            f
        );
        fragmentTransaction.commit();
    }

}