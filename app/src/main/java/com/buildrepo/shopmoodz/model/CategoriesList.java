package com.buildrepo.shopmoodz.model;

import java.io.Serializable;

/**
 * Created by Aman on 5/12/2016.
 */
public class CategoriesList implements Serializable {

    int id;
    String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;
}
