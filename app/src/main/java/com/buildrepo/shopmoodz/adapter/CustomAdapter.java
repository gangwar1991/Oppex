package com.buildrepo.shopmoodz.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.activity.ProductDetail;
import com.buildrepo.shopmoodz.model.Image;
import com.buildrepo.shopmoodz.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aman on 4/12/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CarHolder> {

    private Context context;
    private ArrayList<Product> product;
    String search = "";

    public CustomAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.product = products;
    }

    public void setSearch(String newText){

        this.search=newText;

        notifyDataSetChanged();

    }


    @Override
    public CustomAdapter.CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ViewGroup group=(ViewGroup)inflater.inflate(R.layout.product_list, parent, false);
        CarHolder carHolder=new CarHolder(group);
        return carHolder;
    }

    @Override
    public void onBindViewHolder(CustomAdapter.CarHolder holder, int position) {

        if(search!=null && !search.equals("")){
            int actual=-1;
            int match= -1;
            for (int i = 0; i <product.size() ; i++) {
                actual++;
                String name= product.get(i).getTitle();
                if (name.toLowerCase(Locale.ENGLISH).contains(search.toLowerCase(Locale.ENGLISH))){
                    match++;
                }
                if (match==position){
                    break;
                }
                position=actual;
            }
        }

        final CarHolder mainHolder=(CarHolder)holder;
        String rs= context.getString(R.string.Rs);
        final Product pd=product.get(position);
        ArrayList<Image>img=pd.getImage();
        String imageurl="http://52.35.19.207/opex/img/";
        for (int i = 0; i < img.size(); i++) {
            imageurl=imageurl + img.get(i).getImage();
            Picasso.with(context).load(imageurl).into(mainHolder.imageView);
        }

        mainHolder.productName.setText(pd.getTitle());
        mainHolder.productPrice.setText(rs+" "+pd.getPrice());
        mainHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductDetail.class);
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
        if (product==null)
            return 0;
        if (search==null || search.equals(""))
        return product.size();

        int c = 0;
        for (int i = 0; i <product.size() ; i++) {
           String name = product.get(i).getTitle();
            if (name.toLowerCase(Locale.ENGLISH).contains(search.toLowerCase(Locale.ENGLISH)))
                c++;

        }

        return c;
    }

    public  class  CarHolder extends RecyclerView.ViewHolder {
        // View holder for gridview recycler view as we used in listview
        public TextView productName, productPrice;
        public ImageView imageView;
        public CardView  cardView;

        public CarHolder(View view) {
            super(view);
            // Find all views ids

            this.productName = (TextView) view.findViewById(R.id.product_name);
            this.productPrice=(TextView)view.findViewById(R.id.product_price);
            this.imageView = (ImageView) view.findViewById(R.id.list_image);
            this.cardView = (CardView) view.findViewById(R.id.card_view);


        }



    }
}
