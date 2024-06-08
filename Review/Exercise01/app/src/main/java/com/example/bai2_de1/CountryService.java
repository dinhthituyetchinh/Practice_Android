package com.example.bai2_de1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {
    @GET("v3.1/independent?status=true&fields=languages,capital,name,population,continents,flags")
    Call<List<Country>> getCountries();
}
