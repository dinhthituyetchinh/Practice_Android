package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    String dbName;
    String dbPath;
    Context context;
    public DBHelper( Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        dbName = "pokemon.db";
        dbPath = "data/data/" +context.getPackageName()+"/database/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void checkDB()
    {
        try {
            String path = dbPath + dbName;
            SQLiteDatabase.openDatabase(path, null, 0);

        }catch (Exception e)
        {

        }
        this.getReadableDatabase();
    }

    public void copyDB()
    {
        try {
            InputStream io = context.getAssets().open(dbName);
            String fileName = dbPath + dbName;
            OutputStream outputStream = new FileOutputStream(fileName);
            int length;
            byte[] buffer = new byte[1024];
            while ((length = io.read(buffer)) > 0)
            {
                outputStream.write(buffer, length, 0);
            }
            io.close();
            outputStream.flush();
            outputStream.close();
        }catch (Exception e)
        {

        }
    }

    public void  openDB()
    {
        String path = dbPath + dbName;
        SQLiteDatabase.openDatabase(path, null, 0);
    }
}
