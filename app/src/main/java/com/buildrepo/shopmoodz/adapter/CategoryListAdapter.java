package com.buildrepo.shopmoodz.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.model.CategoriesList;
import com.buildrepo.shopmoodz.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aman on 5/12/2016.
 */
public class CategoryListAdapter extends BaseAdapter {

    private Activity activity;
    private List<CategoriesList> categoriesLists;
    private Context context;
    private String imageUrl="http://52.35.19.207/opex/img/";

    public CategoryListAdapter(Context context, ArrayList<CategoriesList> categoriesLists) {
        this.context = context;
        this.categoriesLists = categoriesLists;
    }



    @Override
    public int getCount() {
        return categoriesLists.size();
    }

    @Override
    public Object getItem(int position) {
        return categoriesLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryHolder productHolder=new CategoryHolder();

        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list, parent, false);
            productHolder.categoryName=(TextView)convertView.findViewById(R.id.category_name);
            productHolder.categoryImage=(ImageView)convertView.findViewById(R.id.category_image);
            productHolder.arrowImage=(ImageView)convertView.findViewById(R.id.category_arrow_image);

            final CategoriesList m = categoriesLists.get(position);

            Picasso.with(context)
                    .load(m.getImage())
                    .into(productHolder.categoryImage);

            productHolder.categoryName.setText(m.getCategory());


        }


        return convertView;
    }


    public  class  CategoryHolder{

        public TextView categoryName;
        public ImageView categoryImage, arrowImage,finalImage;

    }
}
