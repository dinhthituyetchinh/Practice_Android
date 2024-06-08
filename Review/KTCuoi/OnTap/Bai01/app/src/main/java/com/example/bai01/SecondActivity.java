package com.example.bai01;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private String dbName = "pokemon.db";
    SQLiteDatabase db = null;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        dbHelper = new DBHelper(this);
        try {
            dbHelper.checkAndCopyDatabase();

        }
        catch (Exception e)
        {
        }
        db= openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        xinQuyen();
    }

    public void xinQuyen()
    {
        int kqRead =  checkSelfPermission(
                //Use in Oreo don't fogot android.
                android.Manifest.permission.READ_EXTERNAL_STORAGE
        );
        int kqWrite  =  checkSelfPermission(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        );
        if(kqRead == PackageManager.PERMISSION_GRANTED && kqWrite == PackageManager.PERMISSION_GRANTED  )
        {
            Log.e("SecondActivity", "Được cho phép");
            List<Pokemon> pokemonList = dbHelper.getPokemonList();
            RecyclerView recyclerView = findViewById(R.id.listPokemon_RecyclerView);
            PokemonAdapter adapter = new PokemonAdapter(this, pokemonList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.notifyDataSetChanged();

        }
        else {

            requestPermissions(
                    new String[]
                            {
                                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                            }, 123

            );
            //Nhận kết quả đi xin phép
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                Log.e("SecondActivity", "Được cho phép");
                List<Pokemon> pokemonList = dbHelper.getPokemonList();
                RecyclerView recyclerView = findViewById(R.id.listPokemon_RecyclerView);
                PokemonAdapter adapter = new PokemonAdapter(this, pokemonList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter.notifyDataSetChanged();

            }
            else {
                Log.e("SecondActivity", "Không cho phép");
            }

        }
    }





}