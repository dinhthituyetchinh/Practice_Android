package com.example.lap6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Bai6Activity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnFirst, btnSecond;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai6);

        toolbar = (Toolbar) findViewById(R.id.toolbarBai6);
        setSupportActionBar(toolbar);

       fm = getSupportFragmentManager();
       btnFirst = (Button) findViewById(R.id.btnfirstFragment);

       btnSecond = (Button) findViewById(R.id.btnsecondFragment);

        final int[] count = {0};
       btnFirst.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (count[0] > 0)
               {
                   Fragment fragment = fm.findFragmentById(R.id.frameLayoutBai6);
                   FragmentTransaction fragmentTransaction_clear = fm.beginTransaction();
                   fragmentTransaction_clear.remove(fragment);
                   fragmentTransaction_clear.commit();
               }

               FragmentTransaction fragmentTransaction_first = fm.beginTransaction();
               fragmentTransaction_first.add(R.id.frameLayoutBai6, new FirstFragment());
               fragmentTransaction_first.commit();

               count[0]++;

           }
       });

       btnSecond.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Fragment fragment = fm.findFragmentById(R.id.frameLayoutBai6);
               FragmentTransaction fragmentTransaction_clear = fm.beginTransaction();
               fragmentTransaction_clear.remove(fragment);
               fragmentTransaction_clear.commit();


               FragmentTransaction fragmentTransaction_first = fm.beginTransaction();
               fragmentTransaction_first.add(R.id.frameLayoutBai6, new SecondFragment());
               fragmentTransaction_first.commit();




           }
       });
    }
}