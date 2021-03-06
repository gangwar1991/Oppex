package com.buildrepo.shopmoodz.fragment;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.activity.Gallery;
import com.buildrepo.shopmoodz.model.ImagePicker;

import java.io.IOException;


public class CameraFragment extends Fragment implements SurfaceHolder.Callback, Camera.ShutterCallback, Camera.PictureCallback {

    private static final String TAG = CameraFragment.class.getSimpleName();

    Camera mCamera;
    SurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;
    ImageButton mTakePictureBtn;
    private boolean previewing=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);

        mSurfaceView = (SurfaceView)rootView.findViewById(R.id.surfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);

        mTakePictureBtn = (ImageButton) rootView.findViewById(R.id.take_picture);
        mTakePictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTakePictureBtn.isEnabled()){
                    mTakePictureBtn.setEnabled(false);
                    mCamera.takePicture(CameraFragment.this, null, CameraFragment.this);
                }
            }
        });
        return rootView;
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        if(previewing){
            mCamera.stopPreview();
            previewing = false;
        }

        if (mCamera != null){
            try {
                mCamera.setPreviewDisplay(surfaceHolder);
                mCamera.setDisplayOrientation(90);
                mCamera.startPreview();
                previewing = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mCamera = Camera.open();

    }



    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }

    }

    /*@Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {
        if(!mTakePictureBtn.isEnabled())
            mTakePictureBtn.setEnabled(true);
    }*/

    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {
        mTakePictureBtn.setEnabled(true);
        Bitmap picture = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        //rotates the image to portrate
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        picture = Bitmap.createBitmap(picture, 0, 0, picture.getWidth(), picture.getHeight(), matrix, true);

        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), picture, "" , "");
        Uri contentUri = Uri.parse(path);
        ImagePicker image = getImageFromContentUri(contentUri);
        ((Gallery)getActivity()).addImage(image);
    }


    public ImagePicker getImageFromContentUri(Uri contentUri) {

        String[] cols = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.ImageColumns.ORIENTATION
        };
         // can post image
        Cursor cursor = getActivity().getContentResolver().query(contentUri, cols, null, null, null);
        cursor.moveToFirst();
        Uri uri = Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
        int orientation = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION));
        return new ImagePicker(uri, orientation);
    }

    @Override
    public void onShutter() {

    }
}
