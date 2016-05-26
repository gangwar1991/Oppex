package com.buildrepo.shopmoodz.model;

import android.net.Uri;

/**
 * Created by Aman on 5/6/2016.
 */
public class ImagePicker {

    public Uri mUri;
    public int mOrientation;

    public ImagePicker(Uri uri, int orientation){
        mUri = uri;
        mOrientation = orientation;
    }
}
