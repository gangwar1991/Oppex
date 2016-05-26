package com.buildrepo.shopmoodz.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.buildrepo.shopmoodz.fragment.CameraFragment;
import com.buildrepo.shopmoodz.fragment.GalleryFragment;

import java.util.Locale;

public class SectionsPagerAdapter extends FragmentPagerAdapter {


    public SectionsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch(position){
            case 0:
                return new CameraFragment();
            case 1:
                return new GalleryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "Take a photo";//getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return "Gallery";//getString(R.string.title_section2).toUpperCase(l);
        }
        return null;
    }
}