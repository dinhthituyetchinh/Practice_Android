package com.example.lap6;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bai4Activity extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai4);

        toolbar = findViewById(R.id.toolbarView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MenuToolbarDemo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemShare)
        {
            Toast.makeText(Bai4Activity.this, "Share", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.itemSearch)
        {
            Toast.makeText(Bai4Activity.this, "Search", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.itemSetting)
        {
            Toast.makeText(Bai4Activity.this, "Setting", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.itemOpen)
        {
            Toast.makeText(Bai4Activity.this, "Open", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Bai4Activity.this, "Delete", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}