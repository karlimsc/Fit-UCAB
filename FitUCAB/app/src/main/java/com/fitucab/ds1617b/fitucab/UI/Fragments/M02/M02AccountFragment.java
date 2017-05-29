package com.fitucab.ds1617b.fitucab.UI.Fragments.M02;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.M02Exception;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class M02AccountFragment extends Fragment {

    private View _view;
    private EditText _et_m02_username;
    private EditText _et_m02_email;
    private EditText _et_m02_phone;
    private EditText _et_m02_sexo;
    private String TAG= "FitUCAB";
    private User user;
    private IpStringConnection ip= new IpStringConnection();
    public M02AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view= inflater.inflate(R.layout.fragment_m02_account, container, false);
        initComponentes(_view);
        return _view;

    }

    private void initComponentes(View view) {
        _et_m02_email = (EditText) view.findViewById(R.id.et_m02_email);
        _et_m02_phone = (EditText) view.findViewById(R.id.et_m02_phone);
        _et_m02_username = (EditText) view.findViewById(R.id.et_m02_username);
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
            throw new M02Exception();
        }catch (M02Exception e){
            e.toString();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void setJsonView(JSONObject response) {
        try {

            String username= response.getString("user");
            String email= response.getString("email");
            String phone = response.getString("phone");
            _et_m02_username.setText(username);
            _et_m02_phone.setText(phone);
            _et_m02_email.setText(email);


            throw new M02Exception();
        }catch (M02Exception e){
            e.toString();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){

            e.printStackTrace();
        }catch (Exception e){

            e.printStackTrace();
        }

    }
}
