package com.buildrepo.shopmoodz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.buildrepo.shopmoodz.controller.AppController;
import com.buildrepo.shopmoodz.model.Test;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Aman on 4/25/2016.
 */
public class TestFragment extends Fragment {

    private ArrayList<Test> categoriesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String url= "http://52.35.19.207/happening_offers/Mobile/getCategory";

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    JSONArray jsonArray= jsonObject.getJSONArray("categories");
                    categoriesList=new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object= jsonArray.getJSONObject(i);
                        Test test =new Gson().fromJson(object.toString(), Test.class);
                        categoriesList.add(test);
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
