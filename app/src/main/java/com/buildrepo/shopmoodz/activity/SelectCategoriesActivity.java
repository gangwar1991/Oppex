package com.buildrepo.shopmoodz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.adapter.CategoryListAdapter;
import com.buildrepo.shopmoodz.controller.AppController;
import com.buildrepo.shopmoodz.model.CategoriesList;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectCategoriesActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private ArrayList<CategoriesList> categoryList;
    CategoryListAdapter categoryAdapter;
    private ListView listView;
    private ArrayList<CategoriesList> selected=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_categories);


        categoryList= (ArrayList<CategoriesList>) getIntent().getSerializableExtra("categoryList");

        for (int i = 0; i < categoryList.size(); i++) {

                selected.add(categoryList.get(i));

        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        listView=(ListView)findViewById(R.id.categoryList);

         categoryAdapter=new CategoryListAdapter(getApplicationContext(), selected);
        listView.setAdapter(categoryAdapter);
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
