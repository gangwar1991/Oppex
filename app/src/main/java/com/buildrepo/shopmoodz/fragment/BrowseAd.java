package com.buildrepo.shopmoodz.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.adapter.ProductListAdapter;
import com.buildrepo.shopmoodz.controller.AppController;
import com.buildrepo.shopmoodz.model.Categories;
import com.buildrepo.shopmoodz.model.Category;
import com.buildrepo.shopmoodz.model.GetCategory;
import com.buildrepo.shopmoodz.model.Product;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BrowseAd extends Fragment {

    String url = "http://52.35.19.207/opex/Mobile/getCategory";
    ProductListAdapter listAdapter;
    private ListView listView;
    private FragmentActivity mConttext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_browse_ad, container, false);

        listView = (ListView) v.findViewById(R.id.product_list);
        mConttext=getActivity();
        category();
      //  setHasOptionsMenu(true);

        return v;

    }


    private void category() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("TAG", response.toString());

                try {
                    JSONObject jsonRootObject = new JSONObject(response);

                    GetCategory gc=new Gson().fromJson(jsonRootObject.toString(), GetCategory.class);
                    Log.e("categories", gc.toString());
                    ArrayList<Categories> categories= gc.getCategories();
                    ArrayList<Product> productArrayList= new ArrayList<>();

                    for (int i = 0; i < categories.size(); i++) {
                        for (int j = 0; j < categories.get(i).getProduct().size(); j++) {
                            productArrayList.add(categories.get(i).getProduct().get(j)) ;

                        }
                        listView.setAdapter(new ProductListAdapter(getActivity(), productArrayList));

                    }


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


}
