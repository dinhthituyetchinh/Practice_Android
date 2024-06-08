package com.example.day13;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        recyclerView = findViewById(R.id.listPokemon_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        search = findViewById(R.id.search_bar);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pokemonAdapter != null && originalItems != null) {
                    if(s.length() == 0 )
                    {
                        pokemonAdapter.updateList(originalItems);
                    }
                    else {
                        List<Pokemon> filteredList = new ArrayList<>();
                        for (Pokemon item : originalItems) {
                            if (item.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                                filteredList.add(item);
                            }
                        }
                        pokemonAdapter.updateList(filteredList);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loadListPokemon();
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
                recyclerView = findViewById(R.id.listPokemon_RecyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PokemonListActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

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