package com.buildrepo.shopmoodz.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.activity.AboutUs;
import com.buildrepo.shopmoodz.activity.TermsofUse;


public class HelpFragment extends Fragment {

    private TextView helpTV, termsTV, aboutTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_help, container, false);

        helpTV=(TextView)rootView.findViewById(R.id.helpTV);
        termsTV=(TextView)rootView.findViewById(R.id.termsTV);
        aboutTV=(TextView)rootView.findViewById(R.id.aboutTV);

        helpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                startActivity(i);
            }
        });

        termsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), TermsofUse.class);
                startActivity(intent);
            }
        });


        aboutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent=new Intent(getContext(), AboutUs.class);
                startActivity(aboutIntent);

            }
        });

        return rootView;
    }
}
