package com.buildrepo.shopmoodz.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.adapter.ChatPagerAdapter;
import com.buildrepo.shopmoodz.adapter.TabPagerAdapter;

public class MyChat extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ChatPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chat);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.cross));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewpager);

        adapter=new ChatPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filtering, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
