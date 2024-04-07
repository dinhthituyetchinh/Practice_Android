package com.example.lap7;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bai1Activity extends AppCompatActivity {
    Toolbar toolbar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai1);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("MenuToolbarDemo");

        textView = (TextView) findViewById(R.id.tvCM);
        //Gán context menu cho text view (MUỐN HIỆN CONTEXT MENU KÍCH CHUỘT PHẢI VÀO TEXT VIEW)
        registerForContextMenu(textView);
        // Có vẻ trên text view hơi khó ấn có thể thử ấn vào button
        Button btn = (Button) findViewById(R.id.btnContextView);
        registerForContextMenu(btn);
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        if(menu instanceof MenuBuilder)
        {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId() == R.id.itemSearch)
       {
           Toast.makeText(Bai1Activity.this, "Search", Toast.LENGTH_SHORT).show();
       } else if(item.getItemId() == R.id.itemSetting)
       {
           Toast.makeText(Bai1Activity.this, "Setting", Toast.LENGTH_SHORT).show();
       }
        return super.onOptionsItemSelected(item);
    }

// Xử lý của context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
       if(item.getItemId() == R.id.itemInsert)
       {
           Toast.makeText(this, "Insert", Toast.LENGTH_SHORT).show();
       }else if(item.getItemId() == R.id.itemDelete)
       {
           Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
       }else if(item.getItemId() == R.id.itemSwitch)
       {
           Toast.makeText(this, "Switch", Toast.LENGTH_SHORT).show();
       }
       else if(item.getItemId() == R.id.itemAdd)
       {
           Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
       }
       else
       {
           Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show();

       }
        return super.onContextItemSelected(item);
    }
}