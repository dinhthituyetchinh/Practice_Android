package com.example.exercise2_de2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService{
        @GET("filter.php")
        Call<DrinkResponse> getDrinksByCategory(@Query("c") String category);

        @GET("filter.php")
        Call<DrinkResponse> getDrinksByAlcoholic(@Query("a") String alcoholic);

        @GET("filter.php")
        Call<DrinkResponse> getDrinksByIngredient(@Query("i") String ingredient);
}
