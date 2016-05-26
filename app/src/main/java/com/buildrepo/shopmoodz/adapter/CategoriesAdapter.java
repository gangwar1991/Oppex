package com.buildrepo.shopmoodz.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.activity.SelectCategory;
import com.buildrepo.shopmoodz.attr.CircularImageView;
import com.buildrepo.shopmoodz.model.Categories;
import com.buildrepo.shopmoodz.model.Category;
import com.buildrepo.shopmoodz.model.SubCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aman on 4/5/2016.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyHolder> {

    private Activity activity;
    private Context context;
    private ArrayList<Categories> categories;
    private ArrayList<Category> categoryList;
    private String categoryId;


    public CategoriesAdapter(Context context, ArrayList<Categories> categories, ArrayList<Category> categoryList) {
        this.context = context;
        this.categories = categories;
        this.categoryList = categoryList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_list, parent, false);
        MyHolder listHolder = new MyHolder(view);

        return listHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        MyHolder mainHolder = (MyHolder) holder;
        final Category m = categoryList.get(position);
        categoryId= m.getId();
        final String imageurl="http://52.35.19.207/opex/img/";
        Picasso.with(context)
                .load(imageurl+m.getImage())
                .into(mainHolder.imageview);

        mainHolder.title.setText(m.getName());
        mainHolder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SelectCategory.class);
                intent.putExtra("category_id", m.getId());
                intent.putExtra("subCategory", categories.get(position).getSubCategory());
                intent.putExtra("name", m.getName());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder  {
        // View holder for gridview recycler view as we used in listview
        public TextView title;
        public CircularImageView imageview;



        public MyHolder(final View view) {
            super(view);
            // Find all views ids

            this.title = (TextView) view.findViewById(R.id.titleName);
            this.imageview = (CircularImageView) view.findViewById(R.id.item_image);
        }
    }
}