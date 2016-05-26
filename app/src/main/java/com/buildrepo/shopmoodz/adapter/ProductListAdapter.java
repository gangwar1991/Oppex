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
import com.buildrepo.shopmoodz.model.Image;
import com.buildrepo.shopmoodz.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aman on 4/23/2016.
 */
public class ProductListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Product> products;
    private Context context;

    public ProductListAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }


    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductHolder productHolder=new ProductHolder();

        String rs= context.getString(R.string.Rs);

        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_product_list, parent, false);
            productHolder.productName=(TextView)convertView.findViewById(R.id.product_name);
            productHolder.productPrice=(TextView)convertView.findViewById(R.id.product_price);
            productHolder.imageView=(ImageView)convertView.findViewById(R.id.product_image);

            final Product m = products.get(position);
            ArrayList<Image>img=m.getImage();
            String imageurl="http://52.35.19.207/opex/img/";
            for (int i = 0; i < img.size(); i++) {
                imageurl = imageurl + img.get(i).getImage();
                Picasso.with(context).load(imageurl).into(productHolder.imageView);
            }
            productHolder.productName.setText(m.getTitle());
            productHolder.productPrice.setText(rs+ " "+m.getPrice());


        }


        return convertView;
    }


    public  class  ProductHolder{

        public TextView productName, productPrice;
        public ImageView imageView;

        }
}
