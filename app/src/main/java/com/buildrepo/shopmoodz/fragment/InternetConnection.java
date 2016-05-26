package com.buildrepo.shopmoodz.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.buildrepo.shopmoodz.R;


public class InternetConnection extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_internet_connection, container, false);

        Button interNtBT=(Button)view.findViewById(R.id.interNetBT);
          interNtBT.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  HomeFragment homeFragment=new HomeFragment();
                  FragmentTransaction orderTransaction=getFragmentManager().beginTransaction();
                  orderTransaction.replace(R.id.container, homeFragment);
                  orderTransaction.commit();
              }
          });

        return view;
    }

}