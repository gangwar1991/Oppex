package com.buildrepo.shopmoodz.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Aman on 4/5/2016.
 */
public class Product implements Serializable{

    String id, title, price, subcategory_id, category_id;

    ArrayList<Image>  Image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public ArrayList<Image> getImage() {
        return Image;
    }

    public void setImage(ArrayList<Image> image) {
        Image = image;
    }
}
