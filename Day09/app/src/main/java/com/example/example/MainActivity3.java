package com.example.example;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {


    private String dbName = "phonebook.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
// Tạo trong bộ nhớ không cần yêu cầu cấp quyền, tạo ngoài bộ nhớ phải yêu cầu cấp quyền
//        // Tạo DB
//        openDB();
//
////        //Tạo bảng
//        createPhoneTable();

        insert("Minh","0389123567");
//         insert("Bình","0123456789");
//
//        //Đọc dưới logcat
//        readContacts();
    }

    private SQLiteDatabase openDB() {
        return openOrCreateDatabase(dbName, MODE_PRIVATE, null); // Tham số thứ 3 luôn luôn là null
    }

    private void closeDB(SQLiteDatabase db) {
        db.close();
    }

    private void createPhoneTable() {
        SQLiteDatabase db = openDB();
        String sql = "create table if not exists tblPhonebook(id integer PRIMARY KEY autoincrement, name text, phone text);";
        db.execSQL(sql);
        closeDB(db);
    }

    private void insert(String name, String phone) {
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("phone", phone);
        db.insert("tblPhonebook", null, cv);
        closeDB(db);
    }

    private void readContacts() {
        SQLiteDatabase db = openDB();
        String sql = "select * from tblPhonebook";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            while (csr.moveToNext()) {
                System.out.println("ID: " + csr.getInt(0));
                System.out.println("Name: " + csr.getString(1));
                System.out.println("Phone: " + csr.getString(2));
            }
        }
        closeDB(db);
    }

    private void update(int id, String name, String phone) {
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("phone", phone);
        int row = db.update("tblPhonebook", cv, "id = ?", new String[]{ String.valueOf(id) });
        //Dấu ? đại diện cho phần tử mà mình đưa vào
        Log.d(getLocalClassName(), "Affected rows: " + String.valueOf(row));
        db.close();
    }

    private void deleteContact(int id) {
        SQLiteDatabase db = openDB();
        int row = db.delete("tblPhonebook", "id = ?", new String[]{ String.valueOf(id) });
        Log.d(getLocalClassName(), "Affected rows: " + String.valueOf(row));
        db.close();
    }

}