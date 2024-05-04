package com.example.day11;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DemoApi {
    @GET("/hello/Employees/")
    Call<List<Employee>> getListEmployee();

    @GET("/hello/Employees/{id}")
    Call<Employee> getEmployee(@Path("id") String id);
}
