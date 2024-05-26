package com.example.day13;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("pokemon.json")
    Call<List<Pokemon>> getListPokemon();

}
