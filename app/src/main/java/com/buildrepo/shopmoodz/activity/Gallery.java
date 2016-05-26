package com.buildrepo.shopmoodz.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.adapter.SectionsPagerAdapter;
import com.buildrepo.shopmoodz.model.ImagePicker;
import com.buildrepo.shopmoodz.utils.Config;
import com.buildrepo.shopmoodz.utils.ImageInternalFetcher;

import java.util.HashSet;
import java.util.Set;


public class Gallery extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = Gallery.class.getSimpleName();

    public static final String TAG_IMAGE_URI = "TAG_IMAGE_URI";

    private Set<ImagePicker> mSelectedImages;
    private LinearLayout mSelectedImagesContainer;
    private TextView mSelectedImageEmptyMessage;
    Toolbar mToolbar;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public ImageInternalFetcher mImageFetcher;

    private Button mCancelButtonView, mDoneButtonView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SectionsPagerAdapter adapter;
    private FrameLayout mFrameLayout;


    private static Config mConfig = new Config.Builder().build();

    public static void setConfig(Config config) {

        if (config == null) {
            throw new NullPointerException("Config cannot be passed null. Not setting config will use default values.");
        }

        mConfig = config;
    }

    public static Config getConfig() {
        return mConfig;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.mViewpager);

        adapter=new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });


        mSelectedImagesContainer = (LinearLayout) findViewById(R.id.selected_photos_container);
        mSelectedImageEmptyMessage = (TextView)findViewById(R.id.selected_photos_empty);
        mFrameLayout = (FrameLayout) findViewById(R.id.selected_photos_container_frame);
        mViewPager = (ViewPager) findViewById(R.id.mViewpager);
        mCancelButtonView = (Button) findViewById(R.id.action_btn_cancel);
        mDoneButtonView = (Button) findViewById(R.id.action_btn_done);

        mSelectedImages = new HashSet<ImagePicker>();
        mImageFetcher = new ImageInternalFetcher(this, 500);

        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        mCancelButtonView.setOnClickListener(this);
        mDoneButtonView.setOnClickListener(this);

    }

    public boolean addImage(ImagePicker image) {
        if(mSelectedImages.add(image)){
            View rootView = LayoutInflater.from(Gallery.this).inflate(R.layout.list_item_selected_thumbnail, null);
            ImageView thumbnail = (ImageView)rootView.findViewById(R.id.selected_photo);
            rootView.setTag(image.mUri);
            mImageFetcher.loadImage(image.mUri, thumbnail);
            mSelectedImagesContainer.addView(rootView, 0);

            int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60,
                    getResources().getDisplayMetrics());
            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(px, px));

            if(mSelectedImages.size() == 1){
                mSelectedImagesContainer.setVisibility(View.VISIBLE);
                mSelectedImageEmptyMessage.setVisibility(View.GONE);
            }
            return true;
        }
        return false;
    }

    public boolean removeImage(ImagePicker image) {
        if(mSelectedImages.remove(image)){
            for(int i = 0; i < mSelectedImagesContainer.getChildCount(); i++){
                View childView = mSelectedImagesContainer.getChildAt(i);
                if(childView.getTag().equals(image.mUri)){
                    mSelectedImagesContainer.removeViewAt(i);
                    break;
                }
            }

            if(mSelectedImages.size() == 0){
                mSelectedImagesContainer.setVisibility(View.GONE);
                mSelectedImageEmptyMessage.setVisibility(View.VISIBLE);
            }
            return true;
        }
        return false;
    }

    public boolean containsImage(ImagePicker image){
        return mSelectedImages.contains(image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id==R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        //cannot use switch statement since ADT 14 -.-
        if(view.getId() == R.id.action_btn_done){

            Uri[] uris = new Uri[mSelectedImages.size()];
            int i = 0;
            for(ImagePicker img : mSelectedImages)
                uris[i++] = img.mUri;

            Intent intent = new Intent();
            intent.putExtra(TAG_IMAGE_URI, uris);
            setResult(Gallery.RESULT_OK, intent);

        }
        else if(view.getId() == R.id.action_btn_cancel){
            setResult(Gallery.RESULT_CANCELED);
        }
        finish();
    }




    }

