package com.fitucab.ds1617b.fitucab.Helper.Rest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by karo on 25/05/17.
 */

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;
    //private static final String STRING_CONN= "http://10.0.2.2:8888/Prueba_war_exploded/M05_ServicesSport/getMetSport?nameSpo=CAMINAR";
   // private static final String STRING_CONN= "http://10.0.2.2:8888/Prueba_war_exploded/M05_ServicesSport/getSport?idSpo=1";



    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }


    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    //public static String getStringConn() {
      //  return STRING_CONN;
   // }

}

