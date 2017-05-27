/**
 * Created by andres on 17/04/17.
 */

package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Model.UserAuxiliar;

import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.Model.UsersAdapter;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class M03FragmentAmigos extends Fragment {

    ListView listView;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_m03_friends, container, false);
        ArrayList<UserAuxiliar> arrayOfUsers = new ArrayList<UserAuxiliar>();
        final UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
        listView = (ListView) rootView.findViewById(R.id.friendsList);
        final ArrayList<UserAuxiliar> usuarios = new ArrayList<UserAuxiliar>();
        listView.setAdapter(adapter);
        listView.setLongClickable(true);
        registerForContextMenu(listView);

        usuarios.add(new UserAuxiliar(0,"", 650,5));

        String urlreq = "http://192.168.1.101:8080/WebServicesFitUCAB_war_exploded/friend/getAll?id=2&Action=Requests";
        final Gson gsonreq = new Gson();

        // Instantiate the RequestQueue.
        RequestQueue queuereq = Volley.newRequestQueue(rootView.getContext());


        // Request a string response from the provided URL.
        StringRequest stringRequestreq = new StringRequest(Request.Method.GET, urlreq,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<User> apreq = gsonreq.fromJson(response,new TypeToken<List<User>>(){}.getType());

                        //ArrayList<UserAuxiliar> arrayOfUsers = new ArrayList<UserAuxiliar>();
                        //UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
                        //ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
                        //listView.setAdapter(adapter);
                        //ArrayList<UserAuxiliar> usuarios = new ArrayList<UserAuxiliar>();

                        for(int i = 0;i<apreq.size();i++){
                            usuarios.add(new UserAuxiliar(apreq.get(i).get_idUser(),apreq.get(i).get_username(), apreq.get(i).get_point(),4));
                        }

                        //adapter.addAll(usuarios);


                        usuarios.add(new UserAuxiliar(0,"", 650,6));


                        String url = "http://192.168.1.101:8080/WebServicesFitUCAB_war_exploded/friend/getAll?id=2&Action=Friends";
                        final Gson gson = new Gson();

                        // Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(rootView.getContext());


                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        ArrayList<User> ap = gson.fromJson(response,new TypeToken<List<User>>(){}.getType());

                                        //ArrayList<UserAuxiliar> arrayOfUsers = new ArrayList<UserAuxiliar>();
                                        //UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
                                        //ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
                                        //listView.setAdapter(adapter);
                                        //ArrayList<UserAuxiliar> usuarios = new ArrayList<UserAuxiliar>();

                                        for(int i = 0;i<ap.size();i++){
                                            usuarios.add(new UserAuxiliar(ap.get(i).get_idUser(),ap.get(i).get_username(), ap.get(i).get_point(),7));
                                        }

                                        adapter.addAll(usuarios);


                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //ArrayList<UserAuxiliar> arrayOfUsers = new ArrayList<UserAuxiliar>();
                                //UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
                                //ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
                                //listView.setAdapter(adapter);
                                //ArrayList<UserAuxiliar> usuarios = new ArrayList<UserAuxiliar>();
                                usuarios.add(new UserAuxiliar(0,error.toString(), 0,4));
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
                //ArrayList<UserAuxiliar> arrayOfUsers = new ArrayList<UserAuxiliar>();
                //UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
                //ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
                //listView.setAdapter(adapter);
                //ArrayList<UserAuxiliar> usuarios = new ArrayList<UserAuxiliar>();
                usuarios.add(new UserAuxiliar(0,error.toString(), 200,4));
                //adapter.addAll(usuarios);
            }
        });
        // Add the request to the RequestQueue.
        queuereq.add(stringRequestreq);


        return rootView;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

        menu.setHeaderTitle("menu amigos");
        UserAuxiliar user = (UserAuxiliar) listView.getItemAtPosition(info.position);
        if(user.get_type() == 7){
            menu.add(1, user.get_id(), 0, "Bloquear");
            int id = 2;
        }
        else if(user.get_type() == 4){
            menu.add(2, user.get_id(), 0, "Aceptar");
            menu.add(3, user.get_id(), 0, "Declinar");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getGroupId()) {
            case 1:
                //BLOQUEAR
                String url = "http://192.168.1.101:8080/WebServicesFitUCAB_war_exploded/friend/update?idUpdater=2&idUpdated="+Integer.toString(item.getItemId())+"&Action=Block";
                final Gson gson = new Gson();

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(rootView.getContext());


                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("1")) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                    builder1.setMessage("Usuario Bloqueado");
                                    builder1.setCancelable(true);
                                    AlertDialog alert11 = builder1.create();
                                    alert11.show();
                                }
                                else{
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                    builder1.setMessage("No Bloqueado");
                                    builder1.setCancelable(true);
                                    AlertDialog alert11 = builder1.create();
                                    alert11.show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                        builder1.setMessage("Error en la conexion:"+error.toString());
                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);

                getFragmentManager().beginTransaction().detach(this).attach(this).commit();

                return true;
            case 2:
                //ACEPTAR
                String urlAccept = "http://192.168.1.101:8080/WebServicesFitUCAB_war_exploded/friend/update?idUpdater=2&idUpdated="+Integer.toString(item.getItemId())+"&Action=Accept";
                final Gson gsonAccept = new Gson();

                // Instantiate the RequestQueue.
                RequestQueue queueAccept = Volley.newRequestQueue(rootView.getContext());


                // Request a string response from the provided URL.
                StringRequest stringRequestAccept = new StringRequest(Request.Method.POST, urlAccept,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("1")) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                    builder1.setMessage("Usuario Agregado");
                                    builder1.setCancelable(true);
                                    AlertDialog alert11 = builder1.create();
                                    alert11.show();
                                }
                                else{
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                    builder1.setMessage("Usuario no Agregado");
                                    builder1.setCancelable(true);
                                    AlertDialog alert11 = builder1.create();
                                    alert11.show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                        builder1.setMessage("Error en la conexion:"+error.toString());
                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                });
                // Add the request to the RequestQueue.
                queueAccept.add(stringRequestAccept);



                getFragmentManager().beginTransaction().detach(this).attach(this).commit();

                return true;
            case 3:
                //DECLINAR
                String urlDecline = "http://192.168.1.101:8080/WebServicesFitUCAB_war_exploded/friend/update?idUpdater=2&idUpdated="+Integer.toString(item.getItemId())+"&Action=Decline";
                final Gson gsonDecline = new Gson();

                // Instantiate the RequestQueue.
                RequestQueue queueDecline = Volley.newRequestQueue(rootView.getContext());


                // Request a string response from the provided URL.
                StringRequest stringRequestDecline = new StringRequest(Request.Method.POST, urlDecline,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("1")) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                    builder1.setMessage("Usuario Declinado");
                                    builder1.setCancelable(true);
                                    AlertDialog alert11 = builder1.create();
                                    alert11.show();
                                }
                                else{
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                    builder1.setMessage("Usuario no Declinado");
                                    builder1.setCancelable(true);
                                    AlertDialog alert11 = builder1.create();
                                    alert11.show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                        builder1.setMessage("Error en la conexion:"+error.toString());
                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                });
                // Add the request to the RequestQueue.
                queueDecline.add(stringRequestDecline);

                getFragmentManager().beginTransaction().detach(this).attach(this).commit();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}





