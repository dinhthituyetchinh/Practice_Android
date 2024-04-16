package com.example.lap8;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bai1Activity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnReadTxt, btnWrite;
    TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai1);

        toolbar = findViewById(R.id.toolbarBai1);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("ReadFileDemo");

        btnReadTxt = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnSave);
        tvContent = findViewById(R.id.txtData);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write(Bai1Activity.this, "test.txt", "Xin chào các bạn");
            }
        });

        btnReadTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = read(Bai1Activity.this, "test.txt");
                tvContent.setText(result);
            }
        });
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
}