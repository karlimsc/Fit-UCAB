package com.fitucab.ds1617b.fitucab.Helper.Rest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by karo on 25/05/17.
 */

public class M05_RequestList{
    private String url;
    private static Context mContext;
    String res;

    public M05_RequestList(Context mContext, String url){
        this.mContext = mContext;
        this.url = url;
    }

    public String makeRequest()
    {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, VolleySingleton.getStringConn(), (String)null,
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("RESPONSE",response.toString());
                        res = response.toString();
                        Log.i("RES",res);

                       // mTxtDisplay.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        res="no trajo nada";

                    }
                });
// Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(mContext).addToRequestQueue(jsObjRequest);

        return res;
    }
}
