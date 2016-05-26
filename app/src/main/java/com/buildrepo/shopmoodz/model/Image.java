package com.buildrepo.shopmoodz.model;

/**
 * Created by Aman on 4/25/2016.
 */
public class Image {

    String image;
    String product_id, id;

    public Image(String finalImage) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
