package com.buildrepo.shopmoodz.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.activity.SignUp;
import com.buildrepo.shopmoodz.controller.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private TextView signUp;
    private EditText emailET, password;
    private static final String LOGIN_URL="http://52.35.19.207/opex/mobile/setLogin";


    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    private Button loginBT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_login, container, false);

        emailET=(EditText)rootView.findViewById(R.id.emailET);
        password=(EditText)rootView.findViewById(R.id.passwordET);
        loginBT=(Button)rootView.findViewById(R.id.loginBT);

        loginBT.setOnClickListener(this);



        signUp=(TextView)rootView.findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(), SignUp.class);
                startActivity(i);
            }
        });

        return rootView;
    }

    private void loginUser() {
        final String emailID = emailET.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        final JSONObject object= new JSONObject();
        try {
            JSONArray jsonArray=new JSONArray();
            JSONObject object1=new JSONObject();

            object1.putOpt("emailId", emailID);
            object1.putOpt("password", pass);

            jsonArray.put(object1);
            object.put("login", jsonArray);
            Log.e("response", object.toString());
            JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, LOGIN_URL, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    System.out.println(response);

                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }



            });
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void onClick(View v) {
        if(v == loginBT){
            loginUser();
        }
    }
}
