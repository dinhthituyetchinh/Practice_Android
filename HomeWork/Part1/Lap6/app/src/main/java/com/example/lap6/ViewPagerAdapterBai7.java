package com.example.lap6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapterBai7 extends FragmentPagerAdapter
{
    public ViewPagerAdapterBai7(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0)
        {
            fragment = new TinhToanFragment();
        }else if(position == 1)
        {
            fragment = new HistoryFragment();
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
            title = "1 - CALCULATOR";
        }else if(position == 1)
        {
            title = "2 - HISTORY";
        }
        return title;
    }
}
