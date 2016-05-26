package com.buildrepo.shopmoodz.model;

import java.io.Serializable;

/**
 * Created by Aman on 5/24/2016.
 */
public class State implements Serializable {

    String id;
    String statename;
    String country_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }
}
