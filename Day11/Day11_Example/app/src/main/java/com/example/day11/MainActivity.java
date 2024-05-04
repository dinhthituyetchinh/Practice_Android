package com.example.day11;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    Button btn;
    private android.content.Context Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnDemo);
//       Bai1 _ Bước 3
        //btn.setOnClickListener(e->checkInternetConnection());

//      Bai 2 _ Bước 2
//        Trường hợp không kết nối internet dducowj trên máy ảo vui lòng tắt máy ảo->
        btn.setOnClickListener(e->{
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        getNetworkResource();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            thread.start();
        });
    }

//   Bai1 _ Bước 1. Khai báo quyền trong manifest
//   Bai1 _ Bước 2: Viết hàm checkInternetConnection()
    private boolean checkInternetConnection() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            return false;
        }
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show();
        return true;
    }

    //Bai 2 _ Bước 1
    protected  void getNetworkResource() throws IOException {
        URL url = null;
//        url = new URL("https://www.vietcombank.com.vn/");
        url = new URL("https://663589ee415f4e1a5e24c031.mockapi.io/hello/Employees");
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
        int statusCode = httpURLConnection.getResponseCode();
       // Log.e("MainActivity", String.valueOf(statusCode));
        String result = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))
                .lines().parallel().collect(
                        Collectors.joining("\n")
                );
        Log.e("MainActivity", result);

    }
}