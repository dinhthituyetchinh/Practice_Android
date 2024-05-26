package com.example.day11;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


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

////      Bai 2 _ Bước 2
////        Trường hợp không kết nối internet dducowj trên máy ảo vui lòng tắt máy ảo->
//        btn.setOnClickListener(e->{
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        getNetworkResource();
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                }
//            });
//            thread.start();
//        });

        //Bai 4 _
        btn.setOnClickListener(e->{
            getNetworkResourceRetrofit2();

            //getNetworkResourceRetrofit2_CaseDetailEmployee("1");
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

        // Bai 3 Lấy data kiểu JSON -> JAVA
        // Bai 3 _ Buoc 1 trong gradle-> dependencies
        //Bai 3 _ Buoc 2 tao class Employee
        // Bai 3 _ Buoc 3
        ObjectMapper objectMapper = new ObjectMapper();
        List<Employee> employees = objectMapper.readValue(result, new TypeReference<List<Employee>>(){});
        for(Employee employee : employees) {

            String name = employee.getName();
            String avatar = employee.getAvatar();
            String id = employee.getId();
            Calendar createdAt = employee.getCreatedAt();

            System.out.println("Name: " + name);
            System.out.println("Avatar: " + avatar);
            System.out.println("ID: " + id);
            System.out.println("Created At: " + createdAt.getTime());
        }

        Log.e("MainActivity", result);

    }

    protected void  getNetworkResourceRetrofit2()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://663589ee415f4e1a5e24c031.mockapi.io/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        DemoApi api = retrofit.create(DemoApi.class);
        api.getListEmployee().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                for (Employee emp :  response.body())
                {
                    Log.e("MainActivity ID", emp.getId());
                    Log.e("MainActivity Name", emp.getName());
                    Log.e("MainActivity Avatar", emp.getAvatar());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable throwable) {

            }
        });
    }

    protected void  getNetworkResourceRetrofit2_CaseDetailEmployee(String empID)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://663589ee415f4e1a5e24c031.mockapi.io/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        DemoApi api = retrofit.create(DemoApi.class);
        api.getEmployee(empID).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Log.e("MainActivity ID", response.body().getId());
                Log.e("MainActivity Name",  response.body().getName());
                Log.e("MainActivity Avatar", response.body().getAvatar());
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable throwable) {

            }
        });
    }
}