package com.buildrepo.shopmoodz.model;

import java.util.ArrayList;

/**
 * Created by Aman on 5/18/2016.
 */
public class PdDetail {

    String id, title;
    ArrayList<Image> images;



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

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
}


