/**
 * Created by andres on 17/04/17.
 */

package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Model.ArrayAuxiliar;
import com.fitucab.ds1617b.fitucab.Model.Person;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.Model.UsersAdapter;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;


public class M03FragmentAmigos extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_m03_friends, container, false);

        String url = "http://192.168.1.109:8080/WebServicesFitUcab_war_exploded/friend/getAll?id=2&Action=Friends";
        final Gson gson = new Gson();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(rootView.getContext());


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Person> ap = new ArrayList<Person>();

                        ap = gson.fromJson(response,new TypeToken<List<Person>>(){}.getType());

                        ArrayList<ArrayAuxiliar> arrayOfUsers = new ArrayList<ArrayAuxiliar>();
                        UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
                        ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
                        listView.setAdapter(adapter);
                        ArrayList<ArrayAuxiliar> usuarios = new ArrayList<ArrayAuxiliar>();

                        for(int i = 0;i<ap.size();i++){
                            usuarios.add(new ArrayAuxiliar(ap.get(i).getPersonusername(), 200,0));
                        }

                        adapter.addAll(usuarios);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ArrayList<ArrayAuxiliar> arrayOfUsers = new ArrayList<ArrayAuxiliar>();
                UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
                ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
                listView.setAdapter(adapter);
                ArrayList<ArrayAuxiliar> usuarios = new ArrayList<ArrayAuxiliar>();
                usuarios.add(new ArrayAuxiliar(error.toString(), 200,0));
                adapter.addAll(usuarios);
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);







        return rootView;
    }








}





