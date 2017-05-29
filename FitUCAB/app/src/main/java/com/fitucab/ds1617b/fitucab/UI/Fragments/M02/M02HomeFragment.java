package com.fitucab.ds1617b.fitucab.UI.Fragments.M02;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class M02HomeFragment extends Fragment {

    private View _view;
    private TextView _tv_m02_home_calorias;
    private TextView _tv_m02_home_water;
    private TextView _tv_m02_home_peso;
    private ImageView _water;
    private ImageView _fitness;
    private ImageView _Account;
    private ImageView _Activitys;
    private String TAG= "FitUCAB";
    private User user;
    private IpStringConnection ip= new IpStringConnection();
    Fragment fragmentToSwap = null;



    public M02HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view= inflater.inflate(R.layout.fragment_m02_home, container, false);
        initComponentes(_view);
        return _view;
    }

    private void initComponentes(View view) {
        _tv_m02_home_calorias = (TextView) view.findViewById(R.id.tv_m02_home_calorias);
        _tv_m02_home_water = (TextView) view.findViewById(R.id.tv_m02_home_water);
        _tv_m02_home_peso = (TextView) view.findViewById(R.id.tv_m02_home_peso);
        _water= (ImageView) view.findViewById(R.id.water);
        _fitness= (ImageView) view.findViewById(R.id.fitness);
        _Account = (ImageView) view.findViewById(R.id.Account);
        _Activitys= (ImageView) view.findViewById(R.id.Activitys);
        _Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentToSwap = new M02AccountFragment();
                fragmentTransaction.replace(R.id.flContent_m02_home, fragmentToSwap).commit();
            }
        });
        _water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //            Intent myintent = new Intent(M02HomeActivity.this, M10WaterGlassActivity.class);
//            startActivity(myintent);
            }
        });
        _fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //            Intent myintent = new Intent(M02HomeActivity.this, M06TrainingActivity.class);
//            startActivity(myintent);
            }
        });

        toAskWebService();


    }

    private void toAskWebService() {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            int id= preferences.getInt("idUser",0);
            RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
            String webUrl= ip.getIp()+"M02Users/"+id;
            Log.i(TAG, "toAskWebService: "+webUrl);
            JsonObjectRequest jsonrequest= new  JsonObjectRequest(Request.Method.GET, webUrl, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(TAG, "onResponse: "+response.toString());
                    setJsonView(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, " ERROR"+ error.toString());
                }
            });
            requestQueue.add(jsonrequest);
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void setJsonView(JSONObject response) {
        try {

            String username= response.getString("user");
            int weight= response.getInt("weight");
            Log.i(TAG, "setJsonView: "+weight);
            _tv_m02_home_peso.setText(weight+" Kg");


        }catch (JSONException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){

            e.printStackTrace();
        }catch (Exception e){

            e.printStackTrace();
        }

    }


}
