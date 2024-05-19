package com.example.day12;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.day12.databinding.DemoListItemBinding;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<Employee> employees;
    RecyclerViewAdapter recyclerViewAdapter;
    DemoListItemBinding demoListItemBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        demoListItemBinding = DemoListItemBinding.inflate(getLayoutInflater());
        setContentView(demoListItemBinding.getRoot());


        employees = new ArrayList<>();
        Employee dt = new Employee("NV01", "Taylor", "swift@gmail.com");
        Employee dt2 = new Employee("NV02", "Susan", "susan@gmail.com");
        Employee dt3 = new Employee("NV05", "Richard", "richard@gmail.com");

        employees.add(dt);
        employees.add(dt2);
        employees.add(dt3);

    }
}