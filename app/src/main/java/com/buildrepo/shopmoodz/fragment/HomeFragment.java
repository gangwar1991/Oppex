package com.buildrepo.shopmoodz.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.activity.SubmitFreeAds;
import com.buildrepo.shopmoodz.adapter.CustomAdapter;
import com.buildrepo.shopmoodz.adapter.CategoriesAdapter;
import com.buildrepo.shopmoodz.controller.AppController;
import com.buildrepo.shopmoodz.model.Categories;
import com.buildrepo.shopmoodz.model.Category;
import com.buildrepo.shopmoodz.model.GetCategory;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private static RecyclerView recyclerView, carRecyclerView,mobileRecycleView, realestateRecycleView;
    private TextView titleText,mobliTV, vehiclesTV;

    Context mConttext;
    Button submitAd;
    Button more;
    private GetCategory gc;
    private ArrayList<Categories> categories;
    CustomAdapter customAdapter;
    private String seach="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.fragment_home, container, false);
               submitAd=(Button)rootView.findViewById(R.id.submitAd);



        more=(Button)rootView.findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(getActivity(), )
            }
        });

        titleText=(TextView)rootView.findViewById(R.id.titleText);
        mobliTV=(TextView)rootView.findViewById(R.id.titleTV);
        vehiclesTV=(TextView)rootView.findViewById(R.id.titleTV1);

            /* recyclerview for categories*/

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        mConttext=getActivity();


 /* recyclerview for real estate*/

        realestateRecycleView= (RecyclerView) rootView.findViewById(R.id.recycler_product);
        realestateRecycleView.setHasFixedSize(true);
        realestateRecycleView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.HORIZONTAL, false));

    /* recyclerview for mobiles*/

        mobileRecycleView = (RecyclerView) rootView.findViewById(R.id.recycler_product1);
        mobileRecycleView.setHasFixedSize(true);
        mobileRecycleView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.HORIZONTAL, false));

    /* recyclerview for Vehicles*/
        carRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_product2);
        carRecyclerView.setHasFixedSize(true);
        carRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.HORIZONTAL, false));


        return rootView;
    }

    String url="http://52.35.19.207/opex/Mobile/getCategory";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        submitAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SubmitFreeAds.class);
                startActivity(intent);
            }
        });

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                     gc=new Gson().fromJson(jsonObject.toString(), GetCategory.class);
                    Log.e("categories : ", gc.toString());
                     categories= gc.getCategories();
                    ArrayList<Category> catList=new ArrayList<>();
                    for (int i = 0; i < categories.size(); i++) {
                        catList.add(categories.get(i).getCategory());
                        if (i==2) {
                            Categories cat=categories.get(i);
                            Category category=   cat.getCategory();
                            titleText.setText(category.getName());
                            realestateRecycleView.setAdapter(new CustomAdapter(getActivity(), cat.getProduct()));
                        }
                        else if(i==1){
                            Categories cat=categories.get(i);
                            Category category=   cat.getCategory();
                            mobliTV.setText(category.getName());
                            mobileRecycleView.setAdapter(new CustomAdapter(getActivity(), cat.getProduct()));
                        }
                        else if(i==0){
                            Categories cat=categories.get(i);
                            Category category=   cat.getCategory();
                            vehiclesTV.setText(category.getName());
                            carRecyclerView.setAdapter(new CustomAdapter(getActivity(), cat.getProduct()));

                        }
                    }
                    recyclerView.setAdapter(new CategoriesAdapter(getActivity(),categories, catList));

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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);

//        SearchManager searchManager =
//                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        LinearLayout ll = (LinearLayout)searchView.getChildAt(0);
        LinearLayout ll2 = (LinearLayout)ll.getChildAt(2);
        LinearLayout ll3 = (LinearLayout)ll2.getChildAt(1);

        SearchView.SearchAutoComplete autoComplete = (SearchView.SearchAutoComplete)ll3.getChildAt(0);
        searchView.setQueryHint("Search");
        autoComplete.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        // set the hint text color
        autoComplete.setHintTextColor(getResources().getColor(R.color.colorPrimaryDark));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(customAdapter!=null){
                    customAdapter.setSearch(newText.toString());
                }

                return false;
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_search:
                // Handle this selection
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}