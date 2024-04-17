package com.example.lap9;


import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {

   public static final String DB_NAME = "productsDB";
   private static final int DB_VERSION = 1;
   private static final String TABLE_NAME = "products";

   private static String KEY_ID = "id";
   private static final String KEY_NAME = "name";
   private static final String KEY_QUANTITY = "quantity";

   public static String path = "/data/data/com.example.lap9/databases/productsDB";
    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_products_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s INTEGER)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_QUANTITY);
        db.execSQL(create_products_table);
        Log.e("Bai1Activity", "Tạo bảng thành công");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_products_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_products_table);
        Log.e("Bai1Activity", "Xoá bảng thành công");
        onCreate(db);
    }

    //Add 1 line data

    public void addProducts (Product product)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, product.getName());
        contentValues.put(KEY_QUANTITY, product.getQuantity());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        Log.e("Bai1Activity", "Thêm một dữ liệu vào bảng thành công");
    }

    //Read 1 line with param is id

    public Product getProduct(int pID)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID+"=?",
                new String[]
                        {
                                String.valueOf(pID)
                        }, null, null, null);

        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        Product product =
                new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
        return  product;
    }

    // Read all data from table

    public ArrayList<Product> getAllProducts()
    {
        ArrayList<Product> productArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false)
        {
            Product product =
                    new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            productArrayList.add(product);
            cursor.moveToNext();
        }
        return productArrayList;
    }
//Updates data table
    public void updateProduct(Product product)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getName());
        values.put(KEY_QUANTITY, product.getQuantity());
        db.update(TABLE_NAME, values, KEY_ID="?", new String[]
                {
                        String.valueOf(product.getId())
                });
        db.close();
        Log.e("Bai1Activity", "Cập nhật dữ liệu của bảng thành công");

    }

    //Xoá một record
    public void delete(int ID)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, KEY_ID+ "= ?", new String[]
                {
                        String.valueOf(ID)
                });
        db.close();
    }
}
