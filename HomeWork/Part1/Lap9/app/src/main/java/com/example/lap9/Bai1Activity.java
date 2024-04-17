package com.example.lap9;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import static com.example.lap9.DBHandler.DB_NAME;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Bai1Activity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai1);

        textView = findViewById(R.id.tvShow);

            DBHandler dbHandler = new DBHandler(this);
//        dbHandler.onCreate(openDB());
//
//        Product product = new Product(1, "SamSung", 5);
//        dbHandler.addProducts(product);
//
//        Product product2 = new Product(2, "Iphone", 10);
//        dbHandler.addProducts(product2);

//           Product product3 = new Product(3, "Vivo", 20);
//           dbHandler.addProducts(product3);

//        //Cập nhật dữ liệu
//        Product product3 = new Product(3, "Vivo", 50);
//        dbHandler.updateProduct(product3);


////        Xoá dữ liệu
//        dbHandler.delete(3);




//// Đọc một dòng data theo ID
//       Product readOneObj =  dbHandler.getProduct(1);
//
//        System.out.println("ID: " +  readOneObj.getId());
//        System.out.println("Name: "+ readOneObj.getName());
//        System.out.println("Quantity: "+readOneObj.getQuantity());


//        //Đọc toàn bộ data trong bảng cách 1 trả về ArrayList
//        ArrayList<Product> arrayList = dbHandler.getAllProducts();
//
//        for (Product product: arrayList)
//        {
//            System.out.println("ID: " +  product.getId());
//            System.out.println("Name: "+ product.getName());
//            System.out.println("Quantity: "+product.getQuantity());
//        }


//        //Đọc toàn bộ data trong bảng cách 2 trả về void
        readContacts();

    }

    private SQLiteDatabase openDB() {
        return openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null); // Tham số thứ 3 luôn luôn là null
    }

    private void readContacts() {
        SQLiteDatabase db = openDB();
        String sql = "select * from products";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            while (csr.moveToNext()) {
                System.out.println("ID: " + csr.getInt(0));
                System.out.println("Name: " + csr.getString(1));
                System.out.println("Quantity: " + csr.getInt(2));
            }
        }
        db.close();
    }
}