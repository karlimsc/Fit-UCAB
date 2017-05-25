/**
 * Created by andres on 17/04/17.
 */

package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.content.DialogInterface;
import android.os.Bundle;

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
        ArrayList<ArrayAuxiliar> arrayOfUsers = new ArrayList<ArrayAuxiliar>();
        final UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
        final ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
        final ArrayList<ArrayAuxiliar> usuarios = new ArrayList<ArrayAuxiliar>();
        listView.setAdapter(adapter);

        usuarios.add(new ArrayAuxiliar(0,"", 650,5));

        String urlreq = "http://192.168.1.109:8080/WebServicesFitUcab_war_exploded/friend/getAll?id=2&Action=Requests";
        final Gson gsonreq = new Gson();

        // Instantiate the RequestQueue.
        RequestQueue queuereq = Volley.newRequestQueue(rootView.getContext());


        // Request a string response from the provided URL.
        StringRequest stringRequestreq = new StringRequest(Request.Method.GET, urlreq,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Person> apreq = gsonreq.fromJson(response,new TypeToken<List<Person>>(){}.getType());

                        //ArrayList<ArrayAuxiliar> arrayOfUsers = new ArrayList<ArrayAuxiliar>();
                        //UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
                        //ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
                        //listView.setAdapter(adapter);
                        //ArrayList<ArrayAuxiliar> usuarios = new ArrayList<ArrayAuxiliar>();

                        for(int i = 0;i<apreq.size();i++){
                            usuarios.add(new ArrayAuxiliar(apreq.get(i).getPersonid(),apreq.get(i).getPersonusername(), 200,4));
                        }

                        //adapter.addAll(usuarios);


                        usuarios.add(new ArrayAuxiliar(0,"", 650,6));


                        String url = "http://192.168.1.109:8080/WebServicesFitUcab_war_exploded/friend/getAll?id=2&Action=Friends";
                        final Gson gson = new Gson();

                        // Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(rootView.getContext());


                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        ArrayList<Person> ap = gson.fromJson(response,new TypeToken<List<Person>>(){}.getType());

                                        //ArrayList<ArrayAuxiliar> arrayOfUsers = new ArrayList<ArrayAuxiliar>();
                                        //UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
                                        //ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
                                        //listView.setAdapter(adapter);
                                        //ArrayList<ArrayAuxiliar> usuarios = new ArrayList<ArrayAuxiliar>();

                                        for(int i = 0;i<ap.size();i++){
                                            usuarios.add(new ArrayAuxiliar(ap.get(i).getPersonid(),ap.get(i).getPersonusername(), 200,4));
                                        }

                                        adapter.addAll(usuarios);


                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //ArrayList<ArrayAuxiliar> arrayOfUsers = new ArrayList<ArrayAuxiliar>();
                                //UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
                                //ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
                                //listView.setAdapter(adapter);
                                //ArrayList<ArrayAuxiliar> usuarios = new ArrayList<ArrayAuxiliar>();
                                usuarios.add(new ArrayAuxiliar(0,error.toString(), 200,4));
                                //adapter.addAll(usuarios);
                            }
                        });
                        // Add the request to the RequestQueue.
                        queue.add(stringRequest);





                        //adapter.addAll(usuarios);






                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //ArrayList<ArrayAuxiliar> arrayOfUsers = new ArrayList<ArrayAuxiliar>();
                //UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
                //ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
                //listView.setAdapter(adapter);
                //ArrayList<ArrayAuxiliar> usuarios = new ArrayList<ArrayAuxiliar>();
                usuarios.add(new ArrayAuxiliar(0,error.toString(), 200,4));
                //adapter.addAll(usuarios);
            }
        });
        // Add the request to the RequestQueue.
        queuereq.add(stringRequestreq);


        return rootView;
    }








}





