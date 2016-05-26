package com.buildrepo.shopmoodz.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.adapter.ProductDetailAdapter;
import com.buildrepo.shopmoodz.controller.AppController;
import com.buildrepo.shopmoodz.model.Image;
import com.buildrepo.shopmoodz.model.PdDetail;
import com.buildrepo.shopmoodz.model.Product;
import com.google.gson.Gson;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aman on 4/28/2016.
 */
public class ProductDetail extends AppCompatActivity {


    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private String imageUrl;
    private String finalImage;
    ProductDetailAdapter mAdapter;


    String url="http://52.35.19.207/opex/mobile/product";
    private ViewPager mPager;
    private ArrayList<PdDetail> pdDetails= new ArrayList<>();

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));


        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter=new ProductDetailAdapter(ProductDetail.this, pdDetails);
        mPager.setAdapter(mAdapter);
        fetchImages();
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

    //Set circle indicator radius
       // indicator.setRadius(5 * density);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });

    }
    private void fetchImages() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonRootObject = new JSONObject(response);
                    JSONObject object=jsonRootObject.getJSONObject("Product");
                    PdDetail pdDetail=new Gson().fromJson(object.toString(), PdDetail.class);
                     Log.e("detail : ", pdDetail.toString());

                  //  mPager.setAdapter();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);

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
