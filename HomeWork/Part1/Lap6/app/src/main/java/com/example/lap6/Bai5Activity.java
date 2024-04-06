package com.example.lap6;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bai5Activity extends AppCompatActivity {
    Toolbar toolbar; // Lưu ý import androidx.appcompat.widget.Toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai5);

        toolbar = findViewById(R.id.toolbarBai5);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Home");

        toolbar.setNavigationIcon(R.drawable.left_svgrepo_com);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bai5, menu);
        if(menu instanceof MenuBuilder)
        {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.itemAndroid)
        {
            Toast.makeText(Bai5Activity.this, "Android", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.itemProfile)
        {
            Toast.makeText(Bai5Activity.this, "Profile", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.itemDownload)
        {
            Toast.makeText(Bai5Activity.this, "Download", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.itemSetting)
        {
            Toast.makeText(Bai5Activity.this, "Setting", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Bai5Activity.this, "Exit", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
