package com.buildrepo.shopmoodz.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.buildrepo.shopmoodz.fragment.ActiveAdsFragment;
import com.buildrepo.shopmoodz.fragment.InActvieAdsFragment;
import com.buildrepo.shopmoodz.fragment.PendingAdsFragment;
import com.buildrepo.shopmoodz.fragment.RemovedAdsFragments;


public class TabPagerAdapter extends FragmentPagerAdapter {
    public static int int_items = 4 ;


    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new PendingAdsFragment();
            case 1:
                return new ActiveAdsFragment();
            case 2:
                return new InActvieAdsFragment();
            case 3:
                return new RemovedAdsFragments();
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
                return "Pending Ads";
            case 1:
                return "Active Ads";
            case 2:
                return "InActive Ads";
            case 3:
                return "Removed Ads";
        }
        return null;
    }
}
