package com.example.lap6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter
{
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       Fragment fragment = null;
       if(position == 0)
       {
           fragment = new Fragment_one();
       }else if(position == 1)
       {
           fragment = new Fragment_two();
       }
       return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position == 0)
        {
            title = "Tab - 1";
        }else if(position == 1)
        {
            title = "Tab - 2";
        }
        return title;
    }
}

