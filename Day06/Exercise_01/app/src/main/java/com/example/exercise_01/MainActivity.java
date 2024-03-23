package com.example.exercise_01;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise_01.adapters.DemoCustomAdapter;
import com.example.exercise_01.adapters.DemoRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView lvDemo;
    Button btnAdd;
   // ArrayAdapter <String> adapter;
    SimpleAdapter simpleAdapter;
    ArrayList<HashMap<String, String>> itemHashMap;
   // DemoCustomAdapter demoCustomAdapter;
    DemoRecyclerViewAdapter demoRecyclerViewAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       // lvDemo = findViewById(R.id.lv_demo);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // adapter.add("Nguyễn Văn C");
                HashMap<String, String> item3 = new HashMap<>();
                item3.put("fullname", "Lê Văn C");
                item3.put("age", "20");
                item3.put("class", "12DHTH10");

              //  itemHashMap.add(item3);
//                simpleAdapter = new SimpleAdapter(
//                        MainActivity.this, itemHashMap, android.R.layout.two_line_list_item,
//                        new String[]{"fullname", "age"},
//                        new int[] {android.R.id.text1, android.R.id.text2}
//                );

              //  lvDemo.setAdapter(simpleAdapter);

               // demoCustomAdapter.addItem(item3);
            }
        });
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//        adapter.add("Trần Văn A");
//        adapter.add("Trần Văn B");
//
//        lvDemo.setAdapter(adapter);

        itemHashMap = new ArrayList<>();
        HashMap<String, String> item1 = new HashMap<>();
        item1.put("fullname", "Hồ Thanh Hải");
        item1.put("age", "20");
        item1.put("class", "12DHTH13");

        HashMap<String, String> item2 = new HashMap<>();
        item2.put("fullname", "Lê Chí Bảo");
        item2.put("age", "20");
        item2.put("class", "12DHTH13");

        itemHashMap.add(item1);
        itemHashMap.add(item2);
//
//        simpleAdapter = new SimpleAdapter(
//          MainActivity.this, itemHashMap, android.R.layout.two_line_list_item,
//                new String[]{"fullname", "age"},
//                new int[] {android.R.id.text1, android.R.id.text2}
//        );
//        lvDemo.setAdapter(simpleAdapter);


//        demoCustomAdapter = new DemoCustomAdapter(this, itemHashMap);
//        lvDemo.setAdapter(demoCustomAdapter);

        demoRecyclerViewAdapter = new DemoRecyclerViewAdapter(this, itemHashMap);
        recyclerView = findViewById(R.id.rec_demo);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(demoRecyclerViewAdapter);

    }



}