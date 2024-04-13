package com.example.example;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    TextView txtDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtDemo = findViewById(R.id.txtDemo);

        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);


       // write(this, "test.txt", "test tinh nang ghi file");
//        String kq = read(this, "test.txt");
//        txtDemo.setText(kq);
     //   xinQuyen();
    }

    public void xinQuyen()
    {
        //        int kq =  checkSelfPermission(
//                //Use in Oreo don't fogot android.
//                android.Manifest.permission.CALL_PHONE
//        );
//        if(kq == PackageManager.PERMISSION_GRANTED)
//        {
//            Log.e("MainActivity", "Được cho phép");
//        }
//        else {
//            Log.e("MainActivity", "Không cho phép");
//            //Ứng dụng muốn quyền này để làm gì đó => đi xin phép
//            //Bước 1: Khai báo quyền trong Manifest:  <uses-permission android:name="android.permission.CALL_PHONE" />
//            //Bước 2: Đi xin phép
//            requestPermissions(
//                    new String[]
//                            {
//                                    android.Manifest.permission.CALL_PHONE
//                            }, 123
//            );
//            //Nhận kết quả đi xin phép
//    }

            int kqRead =  checkSelfPermission(
            //Use in Oreo don't fogot android.
            Manifest.permission.READ_EXTERNAL_STORAGE
            );
            int kqWrite  =  checkSelfPermission(
                    //Use in Oreo don't fogot android.
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            );
        if(kqRead == PackageManager.PERMISSION_GRANTED && kqWrite == PackageManager.PERMISSION_GRANTED  )
        {
            Log.e("MainActivity", "Được cho phép");
            writeExternalFile("ghiTuNgoaiTHuMucGoc.txt", "file được ghi ở ngoài thư mục gốc 1");
            txtDemo.setText(readExternalFile("ghiTuNgoaiTHuMucGoc.txt"));

        }
        else {
            Log.e("MainActivity", "Không cho phép");
            //Ứng dụng muốn quyền này để làm gì đó => đi xin phép
            //Bước 1: Khai báo quyền trong Manifest:  <uses-permission android:name="android.permission.CALL_PHONE" />
            //Bước 2: Đi xin phép
            requestPermissions(
                    new String[]
                            {
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                            }, 123

            );
            //Nhận kết quả đi xin phép
        }
   }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123)
        {
//            //Check kết quả quyền call phone
//            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//                Log.e("MainActivity", "Được cho phép");
//            }
//            else {
//                Log.e("MainActivity", "Không cho phép");
//            }

            //Cách 1
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                Log.e("MainActivity", "Được cho phép");
                writeExternalFile("ghiTuNgoaiTHuMucGoc2.txt", "file được ghi ở ngoài thư mục gốc 2");
                txtDemo.setText(readExternalFile("ghiTuNgoaiTHuMucGoc2.txt"));
            }
            else {
                Log.e("MainActivity", "Không cho phép");
            }
            // Cách 2

//            if(Arrays.stream(grantResults).filter(p-> p != PackageManager.PERMISSION_GRANTED).count() > 0)
//            {
//                //Không được phép
//            }
//            else
//            {
//                // DDuocqj chp phép
//            }
        }
    }

    public void write(Context context, String fileName, String
            message) {
        File file = new File(context.getFilesDir(), fileName);
        try {
            PrintWriter pw = new PrintWriter(
                    new FileWriter(file)
            );
            pw.println(message);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        String s = "";
        try {
            Scanner scanner = new Scanner(
                    new FileReader(file)
            );
            while (scanner.hasNextLine()) {
                s += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }


    public static void writeExternalFile(String fileName, String message) {
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/" + fileName;
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filePath));
            writer.println(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readExternalFile(String fileName) {
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/" + fileName;
        String s = "";
        try {
            Scanner scanner = new Scanner(new FileReader(filePath));
            while(scanner.hasNextLine()) {
                s += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }

}