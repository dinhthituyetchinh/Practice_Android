package com.example.day09_1;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    TextView tvDemo;
    private String dbName = "phonebook.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvDemo = findViewById(R.id.txtDemo);

//////        //Phần đọc - ghi file
//////         write(this, "test.txt", "test tinh nang ghi file");
////        String kq = read(this, "test.txt");
////        tvDemo.setText(kq);
//        // Đọc - ghi file ngoài thư mục gốc thì phải xin quyền
//        xinQuyen();


//        //Khởi tạo đối tượng
//        SharedPreferences sf = getSharedPreferences("test_sf", MODE_PRIVATE);
//          SharedPreferences.Editor editor = sf.edit();
//
//        // Ghi dữ liệu
//        editor.putString("name", "Peter");
//        editor.putInt("age", 21);
//
//        editor.commit();
//
//        // Đọc dữ liệu
//
//        //Test data đúng
//        tvDemo.setText(
//                sf.getString("name", "gia tri mac dinh")+ " - " +sf.getInt("age", 0));
//
////        //Test data sai
////        tv2.setText(
////                sf.getString("name_1", "gia tri mac dinh")+ " - " +sf.getInt("age_1", 0));



//        //        // Tạo DB
////        openDB();
////
//////        //Tạo bảng
////        createPhoneTable();
//
//        insert("Minh","0389123567");
////         insert("Bình","0123456789");
////
////        //Đọc dưới logcat
////        readContacts();
//




    }

    public void xinQuyen()
    {

        int kqRead =  checkSelfPermission(
                android.Manifest.permission.READ_EXTERNAL_STORAGE
        );
        int kqWrite  =  checkSelfPermission(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        );
        if(kqRead == PackageManager.PERMISSION_GRANTED && kqWrite == PackageManager.PERMISSION_GRANTED  )
        {
            Log.e("MainActivity", "Được cho phép");
            writeExternalFile("ghiTuNgoaiTHuMucGoc.txt", "file được ghi ở ngoài thư mục gốc 1");
            tvDemo.setText(readExternalFile("ghiTuNgoaiTHuMucGoc.txt"));

        }
        else {
            Log.e("MainActivity", "Không cho phép");
            //Ứng dụng muốn quyền này để làm gì đó => đi xin phép
            requestPermissions(
                    new String[]
                            {
                                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                            }, 123

            );
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123)
        {
            //Cách 1
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                Log.e("MainActivity", "Được cho phép");
                writeExternalFile("ghiTuNgoaiTHuMucGoc2.txt", "file được ghi ở ngoài thư mục gốc 2");
                tvDemo.setText(readExternalFile("ghiTuNgoaiTHuMucGoc2.txt"));
            }
            else {
                Log.e("MainActivity", "Không cho phép");
            }
            // Cách 2

//            if(Arrays.stream(grantResults).filter(p-> p != PackageManager.PERMISSION_GRANTED).count() > 0)
//            {
//                //Không được phép
//            }
//            else
//            {
//                // DDuocqj chp phép
//            }
        }
    }

    public void write(Context context, String fileName, String
            message) {
        File file = new File(context.getFilesDir(), fileName);
        try {
            PrintWriter pw = new PrintWriter(
                    new FileWriter(file)
            );
            pw.println(message);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        String s = "";
        try {
            Scanner scanner = new Scanner(
                    new FileReader(file)
            );
            while (scanner.hasNextLine()) {
                s += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }


    public static void writeExternalFile(String fileName, String message) {
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/" + fileName;
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filePath));
            writer.println(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readExternalFile(String fileName) {
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/" + fileName;
        String s = "";
        try {
            Scanner scanner = new Scanner(new FileReader(filePath));
            while(scanner.hasNextLine()) {
                s += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return s;
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