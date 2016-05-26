package com.buildrepo.shopmoodz.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.buildrepo.shopmoodz.R;
import com.buildrepo.shopmoodz.controller.AppController;
import com.buildrepo.shopmoodz.model.City;
import com.buildrepo.shopmoodz.model.GetState;
import com.buildrepo.shopmoodz.model.State;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolBar;
    private static final String REGISTER_URL="http://52.35.19.207/opex/mobile/UserRegistration";

    private EditText fNameET, lNameET,passwordET,cPasswordET, emailET, cEmailET, telephoneET, mobileET, addressET, countryET;
    private Spinner state_Spinner;
    private Spinner city_Spinner;
    private Button signUpBT;
    private Context context;
    private static final String TAG = "StateSpinner";
    private ArrayAdapter<GetState> adapter;
    private ArrayAdapter<State>  stateAdapter;
    private ArrayAdapter<City>  cityAdapter;
    ArrayList<City> cities;
    private String stateUrl="http://52.35.19.207/opex/Mobile/getState";
    private GetState gs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        toolBar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        fNameET=(EditText)findViewById(R.id.fName);
        lNameET=(EditText)findViewById(R.id.lName);
        passwordET=(EditText)findViewById(R.id.password);
        cPasswordET=(EditText)findViewById(R.id.cPassword);
        emailET=(EditText)findViewById(R.id.emailId);
        cEmailET=(EditText)findViewById(R.id.cEmailId);
        telephoneET=(EditText)findViewById(R.id.telephone);
        mobileET=(EditText)findViewById(R.id.mobile);
        addressET=(EditText)findViewById(R.id.address);
        countryET=(EditText)findViewById(R.id.countryET);
        countryET.setText("India");

        state_Spinner=(Spinner)findViewById(R.id.stateSpinner);
        city_Spinner=(Spinner)findViewById(R.id.citySpinner);

          setState_Spinner();



        signUpBT=(Button)findViewById(R.id.signUp);

        signUpBT.setOnClickListener(this);
    }
    private void registerUser(){
        final String firstname = fNameET.getText().toString().trim();
        final String lastname = lNameET.getText().toString().trim();
        final String email = emailET.getText().toString().trim();
        final String confirmEmail = cEmailET.getText().toString().trim();
        final String password = passwordET.getText().toString().trim();
        final String confirmPassword = cPasswordET.getText().toString().trim();
        final String telephoneNo = telephoneET.getText().toString().trim();
        final String mobileNo = mobileET.getText().toString().trim();
        final String address = addressET.getText().toString().trim();

        final JSONObject object= new JSONObject();
        try {

            JSONArray jsonArray=new JSONArray();
            JSONObject object1=new JSONObject();


            object1.putOpt("firstName", firstname);
            object1.putOpt("lastName", lastname);
            object1.putOpt("emailId", email);
            object1.putOpt("confirmEmail",confirmEmail);
            object1.putOpt("password", password);
            object1.putOpt("confrimPassword",confirmPassword);
            object1.putOpt("telephoneNo", telephoneNo);
            object1.putOpt("mobileNo",mobileNo);
            object1.putOpt("address", address);

            jsonArray.put(object1);
            object.put("user", jsonArray);
         //   Log.e("response", object.toString());
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, REGISTER_URL, object, new Response.Listener<JSONObject>() {
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

    public void setState_Spinner(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, stateUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                     gs=new Gson().fromJson(jsonObject.toString(), GetState.class);
                     Log.e("categories : ", gs.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == signUpBT){
            registerUser();
        }
    }
}
