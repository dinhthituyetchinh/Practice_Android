package com.example.day11;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

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