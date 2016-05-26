package com.buildrepo.shopmoodz.model;

import java.util.ArrayList;

/**
 * Created by Aman on 4/25/2016.
 */
public class Test {

    int id;
    String name, image;
//    ArrayList<Category> categoryArrayList=new ArrayList<>();
    ArrayList<Product> productArrayList=new ArrayList<>();
    ArrayList<SubCategory> subCategoryArrayList=new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public ArrayList<Category> getCategoryArrayList() {
//        return categoryArrayList;
//    }
//
//    public void setCategoryArrayList(ArrayList<Category> categoryArrayList) {
//        this.categoryArrayList = categoryArrayList;
//    }

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public ArrayList<SubCategory> getSubCategoryArrayList() {
        return subCategoryArrayList;
    }

    public void setSubCategoryArrayList(ArrayList<SubCategory> subCategoryArrayList) {
        this.subCategoryArrayList = subCategoryArrayList;
    }
}
