package com.buildrepo.shopmoodz.model;

import java.util.ArrayList;

/**
 * Created by Aman on 4/5/2016.
 */
public class Categories {


   public Category Category;
    ArrayList<SubCategory> SubCategory;
    ArrayList<Product>  Product;

    public Category getCategory() {
        return Category;
    }

    public void setCategory(Category category) {
        Category = category;
    }

    public ArrayList<SubCategory> getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(ArrayList<SubCategory> subCategory) {
        SubCategory = subCategory;
    }

    public ArrayList<Product> getProduct() {
        return Product;
    }

    public void setProduct(ArrayList<Product> product) {
        Product = product;
    }
}
