package com.buildrepo.shopmoodz.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.adapter.CustomAdapter;
import com.buildrepo.shopmoodz.fragment.BrowseAd;
import com.buildrepo.shopmoodz.fragment.HelpFragment;
import com.buildrepo.shopmoodz.fragment.HomeFragment;
import com.buildrepo.shopmoodz.fragment.LoginFragment;
import com.buildrepo.shopmoodz.fragment.MyAds;
import com.buildrepo.shopmoodz.fragment.MyFavourites;
import com.buildrepo.shopmoodz.model.Category;
import com.buildrepo.shopmoodz.model.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    CustomAdapter customAdapter;

    public  ArrayList<Category> mainActivityProducts= new ArrayList<>();
    private HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(CheckNetwork.isInternetAvailable(this)) //returns true if internet available
//        {

        homeFragment=new HomeFragment();
        FragmentTransaction orderTransaction=getSupportFragmentManager().beginTransaction();
        orderTransaction.replace(R.id.container, homeFragment);
        orderTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.drawable.oppex_icon_logo);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.isChecked()) menuItem.setChecked(false);

                else menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    case  R.id.home:
                        Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
                        HomeFragment homeFragment=new HomeFragment();
                        FragmentTransaction orderTransaction=getSupportFragmentManager().beginTransaction();
                        orderTransaction.replace(R.id.container, homeFragment);
                        toolbar.setTitle("Oppex");
                        orderTransaction.commit();
                        return true;

                    case  R.id.browseAds:
                        Toast.makeText(getApplicationContext(), "Browse Ads Selected", Toast.LENGTH_SHORT).show();
                        BrowseAd browseAdFragment=new BrowseAd();
                        FragmentTransaction browseAdTransaction=getSupportFragmentManager().beginTransaction();
                        browseAdTransaction.replace(R.id.container, browseAdFragment);
                        toolbar.setTitle("All Categories");
                        browseAdTransaction.commit();
                        return true;

                    case R.id.submitAd:
                        Toast.makeText(getApplicationContext(), "Submit Free Ad Selected", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),SubmitFreeAds.class);
                        startActivity(i);
                        return true;


                    case R.id.myAd:
                        Toast.makeText(getApplicationContext(), "My Ads Selected", Toast.LENGTH_SHORT).show();
                        MyAds myAds=new MyAds();
                        FragmentTransaction myAdTransaction=getSupportFragmentManager().beginTransaction();
                        myAdTransaction.replace(R.id.container, myAds);
                        myAdTransaction.commit();
                        toolbar.setTitle("My Ads");
                        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        return true;

                    case R.id.rate_us:
                        Toast.makeText(getApplicationContext(), "Rate Us Selected", Toast.LENGTH_SHORT).show();
                        Intent rateIntent =  new Intent(getApplicationContext(), RateUs.class);
                        startActivity(rateIntent);
                        return true;

                    case R.id.myFav:
                        Toast.makeText(getApplicationContext(), "My Favourite Selected", Toast.LENGTH_SHORT).show();
                        MyFavourites myFavourites=new MyFavourites();
                        FragmentTransaction myFavTransaction=getSupportFragmentManager().beginTransaction();
                        myFavTransaction.replace(R.id.container, myFavourites);
                        myFavTransaction.commit();
                        toolbar.setTitle("My Favourite");
                        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        return true;

                    case R.id.login:
                        Toast.makeText(getApplicationContext(), "Login Selected", Toast.LENGTH_SHORT).show();
                        LoginFragment loginFragment=new LoginFragment();
                        FragmentTransaction loginTransaction=getSupportFragmentManager().beginTransaction();
                        loginTransaction.replace(R.id.container, loginFragment);
                        loginTransaction.commit();
                        toolbar.setTitle("Login");
                        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        return true;

                    case R.id.help:

                        Toast.makeText(getApplicationContext(), "Help Selected", Toast.LENGTH_SHORT).show();
                        HelpFragment helpFragment = new HelpFragment();
                        FragmentTransaction helpTransaction=getSupportFragmentManager().beginTransaction();
                        helpTransaction.replace(R.id.container, helpFragment);
                        helpTransaction.commit();
                        toolbar.setTitle("Help");
                        return true;


                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });


        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();


    }
//    else
//    {
//        InternetConnection internetConnection=new InternetConnection();
//        FragmentTransaction orderTransaction=getSupportFragmentManager().beginTransaction();
//        orderTransaction.replace(R.id.container, internetConnection);
//        orderTransaction.commit();
//        Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show();
//    }
//    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        LinearLayout ll = (LinearLayout)searchView.getChildAt(0);
//        LinearLayout ll2 = (LinearLayout)ll.getChildAt(2);
//        LinearLayout ll3 = (LinearLayout)ll2.getChildAt(1);
//
//        SearchView.SearchAutoComplete autoComplete = (SearchView.SearchAutoComplete)ll3.getChildAt(0);
//        searchView.setQueryHint("Search");
//      // set the hint text color
//        autoComplete.setHintTextColor(getResources().getColor(R.color.colorPrimaryDark));
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//
//                homeFragment.search(newText);
//
//
//                return true;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//
//        }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here.
//        switch(item.getItemId())
//        {
//            case R.id.action_search:
//                openSearch();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    private void openSearch() {
//        Toast.makeText(this, "Search pressed", Toast.LENGTH_SHORT).show();
//    }
//
//
//
//

}

