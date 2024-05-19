package com.example.day12;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.day12.databinding.ActivityMain3Binding;
import com.example.day12.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {

    ActivityMain3Binding binding;
    DemoViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = new DemoViewModel();
        vm.setDemoText("Day la DATA BINDING");
        binding.setVm(vm);

    }

}