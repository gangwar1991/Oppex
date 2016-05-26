package com.buildrepo.shopmoodz.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.controller.AppController;
import com.buildrepo.shopmoodz.model.CategoriesList;
import com.buildrepo.shopmoodz.utils.Config;
import com.buildrepo.shopmoodz.utils.ImageInternalFetcher;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SubmitFreeAds extends ActionBarActivity {


    private static final String TAG = SubmitFreeAds.class.getSimpleName();
    private Toolbar toolbar;
    private String url="http://52.35.19.207/opex/Mobile/getchldcategory";
    private String imageUrl="http://52.35.19.207/opex/img/";
    private static final int INTENT_REQUEST_GET_IMAGES = 6;
    private LinearLayout linearLayout;


    private ViewGroup mSelectedImagesContainer;
    HashSet<Uri> mMedia = new HashSet<Uri>();
    private ArrayList<CategoriesList> categoryList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_free_ads);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.cross));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        mSelectedImagesContainer = (ViewGroup) findViewById(R.id.selected_photos_container);

        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
         linearLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i=new Intent(SubmitFreeAds.this, SelectCategoriesActivity.class);
                   i.putExtra("categoryList", categoryList);
                   startActivity(i);
             }
         });

        View getImages = findViewById(R.id.camera);

        getImages.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getImages();
            }
        });
        categories();

    }

    private void categories() {

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object=new JSONObject(response);


                    JSONArray jsonArray= object.getJSONArray("Categories");


                    for (int i = 0; i< jsonArray.length(); i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        CategoriesList categoriesList=new Gson().fromJson(jsonObject.toString(), CategoriesList.class);
                        categoryList.add(categoriesList);
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



    private void getImages() {
        Intent intent = new Intent(getBaseContext(), Gallery.class);
        Config config = new Config.Builder()
                .setTabBackgroundColor(R.color.white)    // set tab background color. Default white.
                .setTabSelectionIndicatorColor(R.color.blue)
                .setCameraButtonColor(R.color.orange)
                .setSelectionLimit(6)    // set photo selection limit. Default unlimited selection.
                .build();
        Gallery.setConfig(config);
        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resuleCode, Intent intent) {
        super.onActivityResult(requestCode, resuleCode, intent);

        if (resuleCode == this.RESULT_OK) {
            if (requestCode == INTENT_REQUEST_GET_IMAGES || requestCode == INTENT_REQUEST_GET_IMAGES) {
                Parcelable[] parcelableUris = intent.getParcelableArrayExtra(Gallery.TAG_IMAGE_URI);

                if (parcelableUris == null) {
                    return;
                }

                // Java doesn't allow array casting, this is a little hack
                Uri[] uris = new Uri[parcelableUris.length];
                System.arraycopy(parcelableUris, 0, uris, 0, parcelableUris.length);

                if (uris != null) {
                    for (Uri uri : uris) {
                        Log.i(TAG, " uri: " + uri);
                        mMedia.add(uri);
                    }

                    showMedia();
                }
            }
        }
    }

    private void showMedia() {
        // Remove all views before
        // adding the new ones.
        mSelectedImagesContainer.removeAllViews();

        Iterator<Uri> iterator = mMedia.iterator();
        ImageInternalFetcher imageFetcher = new ImageInternalFetcher(this, 500);
        while (iterator.hasNext()) {
            Uri uri = iterator.next();

            // showImage(uri);
            Log.i(TAG, " uri: " + uri);
            if (mMedia.size() >= 1) {
                mSelectedImagesContainer.setVisibility(View.VISIBLE);
            }

            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_layout, null);

            // View removeBtn = imageHolder.findViewById(R.id.remove_media);
            // initRemoveBtn(removeBtn, imageHolder, uri);
            ImageView thumbnail = (ImageView) imageHolder.findViewById(R.id.media_image);

            if (!uri.toString().contains("content://")) {
                // probably a relative uri
                uri = Uri.fromFile(new File(uri.toString()));
            }

            imageFetcher.loadImage(uri, thumbnail);

            mSelectedImagesContainer.addView(imageHolder);

            // set the dimension to correctly
            // show the image thumbnail.
            int wdpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
            int htpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(wdpx, htpx));
        }
    }



     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                alertDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void alertDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Need any help in Posting an Ad?");

        AlertDialog.Builder builder = alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(SubmitFreeAds.this, "You clicked yes button", Toast.LENGTH_LONG).show();

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SubmitFreeAds.this);
                dialogBuilder.setMessage("Contact our Customer Care");
                  dialogBuilder.setView(R.layout.text_view);
                dialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();

                    }
                });

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();

            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }






    }

