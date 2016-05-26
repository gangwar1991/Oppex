package com.buildrepo.shopmoodz.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Aman on 5/20/2016.
 */
public class GetState implements Serializable {

    ArrayList<State> states;

    public ArrayList<State> getStates() {
        return states;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }
}
