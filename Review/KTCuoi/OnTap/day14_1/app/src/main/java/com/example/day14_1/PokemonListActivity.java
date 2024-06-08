package com.example.day14_1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


import adapter.PokemonAdapter;
import database.DBHelper;
import database.DatabaseHelper;
import model.Pokemon;

public class PokemonListActivity extends AppCompatActivity {

    PokemonAdapter pokemonAdapter;
    RecyclerView recyclerView;
    List<Pokemon> originalItems;

    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;
    private String DATABASE_NAME = "pokemon.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);


        processCopy();
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        recyclerView = findViewById(R.id.listPokemon_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        originalItems = new ArrayList<>();
        pokemonAdapter = new PokemonAdapter(PokemonListActivity.this, originalItems);
        recyclerView.setAdapter(pokemonAdapter);



//        try (Cursor c = database.query("pokemons", null, null, null, null, null, null)) {
//            if (c != null && c.moveToFirst()) {
//                int idIndex = c.getColumnIndexOrThrow("id");
//                int nameIndex = c.getColumnIndexOrThrow("name");
//                int imageIndex = c.getColumnIndexOrThrow("image");
//                int typesIndex = c.getColumnIndexOrThrow("types");
//                int backgroundColorIndex = c.getColumnIndexOrThrow("color");
//
//                while (!c.isAfterLast()) {
//                    String id = c.getString(idIndex);
//                    String name = c.getString(nameIndex);
//                    String image = c.getString(imageIndex);
//                    String typesJson = c.getString(typesIndex);
//                    String backgroundColor = c.getString(backgroundColorIndex);
//
//                    List<String> types = new Gson().fromJson(typesJson, new TypeToken<List<String>>() {}.getType());
//
//                    Pokemon pokemon = new Pokemon(id, name, image, types, backgroundColor);
//                    originalItems.add(pokemon);
//                    c.moveToNext();
//                }
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, "Error loading data: " + e.getMessage(), Toast.LENGTH_LONG).show();
//            Log.e("PokemonListActivity", "Error loading data", e);
//        }

 //       pokemonAdapter.notifyDataSetChanged();


    }


    private void loadPokemonData(DatabaseHelper databaseHelper) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pokemons", null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
//                String types = cursor.getString(cursor.getColumnIndex("types"));
                String backgroundColor = cursor.getString(cursor.getColumnIndex("background_color"));

                List<String> typeList = new ArrayList<>();
                for (String type : types.split(",")) {
                    typeList.add(type.trim());
                }

                pokemonList.add(new Pokemon(id, name, image, typeList, backgroundColor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }
//    private void showEditDialog(Pokemon pokemon) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(PokemonListActivity.this);
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_pokemon, null);
//        EditText editText = view.findViewById(R.id.editTextPokemonName);
//        editText.setText(pokemon.getName());
//
//        builder.setView(view)
//                .setTitle("Edit Pokémon")
//                .setPositiveButton("Confirm", (dialog, which) -> {
//                    String newName = editText.getText().toString();
//                    if (!newName.isEmpty()) {
//                        pokemon.setName(newName);
//                        updatePokemonName(pokemon);
//                        pokemonAdapter.notifyDataSetChanged();
//                    }
//                })
//                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
//                .create()
//                .show();
//    }
//    private void updatePokemonName(Pokemon pokemon) {
//        DatabaseHelper databaseHelper = new DatabaseHelper(this);
//        SQLiteDatabase db = databaseHelper.getWritableDatabase();
//        db.execSQL("UPDATE Pokemon SET name = ? WHERE id = ?", new Object[]{pokemon.getName(), pokemon.getId()});
//    }
    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists())
        {
            try{CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
    public void CopyDataBaseFromAsset() {
        // TODO Auto-generated method stub
        try {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);
            // Path to the just created empty db
            String outFileName = getDatabasePath();
            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();
            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            // Truyền bytes dữ liệu từ input đến output
            int size = myInput.available();
            byte[] buffer = new byte[size];
            myInput.read(buffer);
            myOutput.write(buffer);
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private SQLiteDatabase openDB() {
        return openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null); // Tham số thứ 3 luôn luôn là null
    }

    private void closeDB(SQLiteDatabase db) {
        db.close();
    }




}