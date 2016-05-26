package com.buildrepo.shopmoodz.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.buildrepo.shopmoodz.fragment.AdsFragment;
import com.buildrepo.shopmoodz.fragment.SearchesFragment;

/**
 * Created by Aman on 4/2/2016.
 */
public class FavPagerAdapter extends FragmentPagerAdapter {
    public static int int_items = 2 ;


    public FavPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new AdsFragment();
            case 1:
                return new SearchesFragment();
            default:
                break;
        }
        return null;
    }
    @Override
    public int getCount() {
        return int_items;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Ads";
            case 1:
                return "Searches";

        }
        return null;
    }
}
