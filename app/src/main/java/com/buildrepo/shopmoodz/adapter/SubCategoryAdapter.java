package com.buildrepo.shopmoodz.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aman on 4/24/2016.
 */
public class SubCategoryAdapter extends BaseAdapter {

    private Activity activity;
    private List<SubCategory> subCategory;
    private Context context;


    public SubCategoryAdapter(Context context, ArrayList<SubCategory> subCategory) {
        this.context = context;
        this.subCategory = subCategory;
    }


    @Override
    public int getCount() {
        return subCategory.size();
    }

    @Override
    public Object getItem(int position) {
        return subCategory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SubCategoryHolder subCategoryHolder;


        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_list, parent, false);
            subCategoryHolder = new SubCategoryHolder();
            subCategoryHolder.subCategoryName = (TextView) convertView.findViewById(R.id.subCategoryTV);
            subCategoryHolder.ads = (TextView) convertView.findViewById(R.id.ads);
            convertView.setTag(subCategoryHolder);
        } else
            subCategoryHolder = (SubCategoryHolder) convertView.getTag();


        final SubCategory category = subCategory.get(position);
        subCategoryHolder.subCategoryName.setText(category.getName());
        subCategoryHolder.ads.setText(category.getAds());
        return convertView;
    }

    public class SubCategoryHolder {

        public TextView subCategoryName, ads;

    }
}
