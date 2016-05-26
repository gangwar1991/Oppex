package com.buildrepo.shopmoodz.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.adapter.SubCategoryAdapter;
import com.buildrepo.shopmoodz.controller.AppController;
import com.buildrepo.shopmoodz.fragment.BrowseAd;
import com.buildrepo.shopmoodz.model.Product;
import com.buildrepo.shopmoodz.model.SubCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectCategory extends AppCompatActivity {

    private Toolbar toolbar;
    ListView listView;
    private String id;
    private ArrayList<SubCategory> subCategoryList;
    SubCategoryAdapter subCategoryAdapter;
    ArrayList<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);


        subCategoryList= (ArrayList<SubCategory>) getIntent().getSerializableExtra("subCategory");
        listView=(ListView)findViewById(R.id.list_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select Category");
        toolbar.setSubtitle(getIntent().getStringExtra("name"));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        subCategoryAdapter=new SubCategoryAdapter(getApplicationContext(), subCategoryList);
        listView.setAdapter(subCategoryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BrowseAd browseAd=new BrowseAd();
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            }
        });


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
