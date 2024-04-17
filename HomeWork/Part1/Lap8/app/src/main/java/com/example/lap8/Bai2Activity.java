package com.example.lap8;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bai2Activity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnSave, btnRead;
    EditText txtName, txtClass;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2);

        toolbar = findViewById(R.id.toolbarBai2);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.android);
        getSupportActionBar().setTitle("DataStorage_SV_Lop");

        btnRead = findViewById(R.id.btnLoad);
        btnSave = findViewById(R.id.btnAdd);

        txtName = findViewById(R.id.txtName);
        txtClass = findViewById(R.id.txtClass);
        textView = findViewById(R.id.tvShow);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xinQuyenRead();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xinQuyenWrite();
            }
        });
    }


    public void xinQuyenWrite()
    {
        int kqRead = checkSelfPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
        );

        int kqWrite = checkSelfPermission(
          Manifest.permission.WRITE_EXTERNAL_STORAGE
        );

        if(kqRead == PackageManager.PERMISSION_GRANTED && kqWrite == PackageManager.PERMISSION_GRANTED)
        {
            Log.e("Bai2Activity", "Được cho phép");
            writeExternalFile("SinhVien.txt", txtClass.getText() +"\n"+txtName.getText());
        }
    }

    public void xinQuyenRead()
    {
        int kqRead = checkSelfPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
        );

        int kqWrite = checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        );

        if(kqRead == PackageManager.PERMISSION_GRANTED && kqWrite == PackageManager.PERMISSION_GRANTED)
        {
            Log.e("Bai2Activity", "Được cho phép");
            textView.setText(readExternalFile("SinhVien.txt"));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
        {
            Log.e("Bai2Activity", "Được cho phép");
            writeExternalFile("SinhVien.txt", txtClass.getText() +"\n"+txtName.getText());
            textView.setText(readExternalFile("SinhVien.txt"));
        }
        else {
            Log.e("Bai2Activity", "Không cho phép");
        }
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