package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, SecondActivity.class);
//        startActivity(intent);


        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);


            // Bước 3: Lấy id từ layout và setSupportActionBar(toolbar);
        toolbar = findViewById(R.id.toolBarTitle);
        setSupportActionBar(toolbar);
//        Đổi tiêu đề toolbar:
//          Cách 1:
//        Vào app/manifests/AndroidManifest.xml
//        Tại android:label="@string/app_name" ctrl + click
//        để vào strings.xml
//                <string name="app_name">Toolbar Example</string>
//                => Thay đổi cụm từ Toolbar Example theo tên mình muốn đặt cho toolbar
//          Cách 2:
        getSupportActionBar().setTitle("Hello");
        // Hiển thị icon
        toolbar.setNavigationIcon(R.drawable.back);
        //Bấm trở lại trên icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

//    Bước 4: Thêm menu vào toolbar
    // tại thư mục res => kích chuột phải => New/Android Resource Directory
    // Đổi resource type thành menu => finish
    //tại thư mục menu vừa tạo => kích chuột phải => New/ Menu Resource File
    //Qua file vừa tạo

    //Bước 5
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    // Bước 6


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if(item.getItemId() == R.id.itemIn)
      {
          Toast.makeText(MainActivity.this, "In", Toast.LENGTH_SHORT).show();
      }
      else  if(item.getItemId() == R.id.itemVanBan)
      {
          Toast.makeText(MainActivity.this, "Văn bản word", Toast.LENGTH_SHORT).show();
      }
      else
      {
          Toast.makeText(MainActivity.this, "Cắt", Toast.LENGTH_SHORT).show();
      }
        return super.onOptionsItemSelected(item);
    }
}