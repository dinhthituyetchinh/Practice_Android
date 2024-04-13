package com.example.example;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        tv2 = findViewById(R.id.txtDemo2);
        //Khởi tạo đối tượng
        SharedPreferences sf = getSharedPreferences("test_sf", MODE_PRIVATE);
      //  SharedPreferences.Editor editor = sf.edit();
//
//        // Ghi dữ liệu
//        editor.putString("name", "Peter");
//        editor.putInt("age", 21);
//
        //commit sẽ cho chúng ta biết dữ liệu có được lưu thành công hay không
//        editor.commit();
        //apply sẽ cho chúng ta sẽ không biết dữ liệu có được lưu thành công hay không
        //Sài apply thì không sài commit và ngược lại, chỉ sa một cái
       // editor.apply();


        // Đọc dữ liệu

        //Test data đúng
//        tv2.setText(
//                sf.getString("name", "gia tri mac dinh")+ " - " +sf.getInt("age", 0));

        //Test data sai
        tv2.setText(
                sf.getString("name_1", "gia tri mac dinh")+ " - " +sf.getInt("age_1", 0));
    }
}