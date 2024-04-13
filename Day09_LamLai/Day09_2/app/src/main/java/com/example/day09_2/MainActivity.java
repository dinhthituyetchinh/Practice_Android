package com.example.day09_2;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver().query(
                Uri.parse("content://com.example.day09_1.DemoContentProvider/random-number"),
                null, null, null, null
        );
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                Log.e("MainActivity", "Du lieu la: "+ cursor.getInt(0));
            }
        }
    }
}