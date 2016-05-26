package com.buildrepo.shopmoodz.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.adapter.FavPagerAdapter;
import com.buildrepo.shopmoodz.adapter.TabPagerAdapter;


public class MyFavourites extends Fragment {


  ViewPager viewPager;
    TabLayout tabLayout;
    Spinner sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_my_favourites, container, false);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new FavPagerAdapter(getChildFragmentManager()));
        setHasOptionsMenu(true);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return rootView;

    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem item = menu.findItem(R.id.action_search);
//        item.setVisible(false);
//    }
}