package com.buildrepo.shopmoodz.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Response;
import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.activity.ProductDetail;
import com.buildrepo.shopmoodz.model.Image;
import com.buildrepo.shopmoodz.model.PdDetail;
import com.buildrepo.shopmoodz.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Aman on 4/28/2016.
 */
public class ProductDetailAdapter extends PagerAdapter {

    private ArrayList<PdDetail> productArrayList;
    private Context context;
    private LayoutInflater inflater;

    public ProductDetailAdapter(Context context, ArrayList<PdDetail> images) {
        this.context = context;
        this.productArrayList=images;
    }
    public void setList(ArrayList<PdDetail> images) {
        this.productArrayList=images;
    }


    @Override
    public int getCount() {
        return productArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);

        final PdDetail pd=productArrayList.get(position);

        ArrayList<Image>img=pd.getImages();
        String imageurl="http://52.35.19.207/opex/img/";
        for (int i = 0; i < img.size(); i++) {
            imageurl=imageurl + img.get(i).getImage();
            Picasso.with(context).load(imageurl).into(imageView);
        }
        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }



}
