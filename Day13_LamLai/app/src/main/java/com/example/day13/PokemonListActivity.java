package com.example.day13;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import adapter.PokemonAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PokemonListActivity extends AppCompatActivity {

    PokemonAdapter pokemonAdapter;
    RecyclerView recyclerView;
    List<Pokemon> originalItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        loadListPokemon();

        recyclerView = findViewById(R.id.listPokemon_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    protected void  loadListPokemon()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://omo-api.doc.motdev.vn/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        api.getListPokemon().enqueue(new Callback<List<Pokemon>>() {

            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                originalItems = response.body();
                pokemonAdapter = new PokemonAdapter(PokemonListActivity.this, originalItems);
                recyclerView.setAdapter(pokemonAdapter);
                for (Pokemon pokemon :  response.body())
                {

                    Log.e("PokemonListActivity ID", pokemon.getId());
                    Log.e("PokemonListActivity Name", pokemon.getName());
                    Log.e("PokemonListActivity Img", pokemon.getImage());
                    Log.e("PokemonListActivity Type", pokemon.getTypes().stream().count()+"");


                }

            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable throwable) {
                System.out.println("failedddd" + throwable.getMessage());
            }
        });
    }
}