package com.sagar.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sagar.materialdesigndemo.TabOneFragment;

/**
 * Created by Sagar on 6/25/2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new TabOneFragment();
            case 1:
                return new TabOneFragment();
            case 2:
                return new TabOneFragment();
            case 3:
                return new TabOneFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Tab 1";
            case 1:
                return "Tab 2";
            case 2:
                return "Tab 3";
            case 3:
                return "Tab 4";
        }
        return null;
    }
}
