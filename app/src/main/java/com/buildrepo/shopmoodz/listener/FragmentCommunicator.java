package com.buildrepo.shopmoodz.listener;

import com.buildrepo.shopmoodz.model.Product;

import java.util.ArrayList;

/**
 * Created by Aman on 5/12/2016.
 */
public interface FragmentCommunicator {
    void sendProduct(ArrayList<Product> productList);
}
