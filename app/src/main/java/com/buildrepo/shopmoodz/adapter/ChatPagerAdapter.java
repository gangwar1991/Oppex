package com.buildrepo.shopmoodz.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.buildrepo.shopmoodz.fragment.AllFragment;
import com.buildrepo.shopmoodz.fragment.BuyingFragment;
import com.buildrepo.shopmoodz.fragment.SellingFragment;

/**
 * Created by Aman on 4/6/2016.
 */
public class ChatPagerAdapter extends FragmentPagerAdapter {
    public static int int_items = 3 ;


    public ChatPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new AllFragment();
            case 1:
                return new BuyingFragment();
            case 2:
                return new SellingFragment();
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
                return "All";
            case 1:
                return "Buying";
            case 2:
                return "Selling";
        }
        return null;
    }
}

