package com.example.day13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PokemonDatabaseManager extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PokemonManager";
    private static final String TABLE_POKEMON = "Pokemon";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public PokemonDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_POKEMON + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POKEMON);

        onCreate(db);
    }

    public void InsertPokemon(Pokemon pokemon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pokemon.getName());
        values.put(KEY_ID, pokemon.getId());
        db.insert(TABLE_POKEMON, null, values);
        db.close();
    }
    public void createTableIfNotExists(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_POKEMON + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }


    public int getIDPokemon(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_POKEMON, new String[]{KEY_ID}, KEY_ID + "=?",
                new String[]{id}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
