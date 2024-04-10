package com.example.lap6;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Bai3Activity extends AppCompatActivity implements SendMessage
{
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBai3);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" +R.id.viewPager + ":" + 1; // Lưu ý không được cách chỗ switcher: với dấu nháy(switcher: " => lỗi)

        Fragment_two f = (Fragment_two)
                getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
    }

}