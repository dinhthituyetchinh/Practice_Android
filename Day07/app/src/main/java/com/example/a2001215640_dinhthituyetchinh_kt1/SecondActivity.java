package com.example.a2001215640_dinhthituyetchinh_kt1;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;


public class SecondActivity extends AppCompatActivity {
    //Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
       // btnAdd = findViewById(R.id.btnAdd);

//        btnAdd.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                // adapter.add("Nguyễn Văn C");
//                HashMap<String, String> item3 = new HashMap<>();
//                item3.put("id", "M012");
//                item3.put("Name", "Đinh Th Tuyết Chinh");
//                item3.put("email", "12DHTH10");
//
//            }
//        });

//        itemHashMap = new ArrayList<>();
//        HashMap<String, String> item1 = new HashMap<>();
//        item1.put("fullname", "Hồ Thanh Hải");
//        item1.put("age", "20");
//        item1.put("class", "12DHTH13");
//
//        HashMap<String, String> item2 = new HashMap<>();
//        item2.put("fullname", "Lê Chí Bảo");
//        item2.put("age", "20");
//        item2.put("class", "12DHTH13");
//
//        itemHashMap.add(item1);
//        itemHashMap.add(item2);
//
//        demoRecyclerViewAdapter = new DemoRecyclerViewAdapter(this, itemHashMap);
//        recyclerView = findViewById(R.id.recyclerV1);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//
//        recyclerView.addItemDecoration(dividerItemDecoration);
//        recyclerView.setAdapter(demoRecyclerViewAdapter);
    }



}