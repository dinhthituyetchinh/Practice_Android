package com.example.bai01;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pokemon.db";
    private static final int DATABASE_VERSION = 1;
    private final String dbPath;
    private final Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Không cần tạo bảng vì cơ sở dữ liệu đã được import sẵn
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nếu có cập nhật cơ sở dữ liệu
    }

    public void checkAndCopyDatabase() {
        if (!isDatabaseExist()) {
            this.getReadableDatabase();
            copyDatabase();
        }
    }

    private boolean isDatabaseExist() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            Log.e("Database Check", "Database does not exist.");
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDatabase() {
        try {
            InputStream input = context.getAssets().open(DATABASE_NAME);
            OutputStream output = new FileOutputStream(dbPath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            input.close();

            Log.e("Copy Database", "Database copied successfully.");
        } catch (Exception e) {
            Log.e("Copy Database", "Error copying database: " + e.getMessage());
        }
    }

    public List<Pokemon> getPokemonList() {
        List<Pokemon> pokemonList = new ArrayList<>();
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            db = this.getReadableDatabase();
            String sql = "SELECT pokemons.id, pokemons.name, pokemons.image, types.name, pokemons.color " +
                    "FROM pokemons " +
                    "JOIN pokemon_types ON pokemon_types.id_pokemon = pokemons.id " +
                    "JOIN types ON pokemon_types.id_type = types.id";

            cursor = db.rawQuery(sql, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    String name = cursor.getString(1);
                    String image = cursor.getString(2);
                    String type = cursor.getString(3);
                    String color = cursor.getString(4);

                    Pokemon pokemon = new Pokemon();
                    pokemon.setId(id);
                    pokemon.setName(name);
                    pokemon.setImage(image);
                    pokemon.setTypes(Arrays.asList(type.split(",")));
                    pokemon.setBackgroundColor(color);

                    pokemonList.add(pokemon);
                }
                cursor.close();
            }
        } catch (SQLException e) {
            Log.e("DBHelper", "Error retrieving Pokemon list: " + e.getMessage());
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return pokemonList;
    }
}
