/**
 * Created by andres on 17/04/17.
 */

package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.Model.UserAuxiliar;
import com.fitucab.ds1617b.fitucab.Model.Person;
import com.fitucab.ds1617b.fitucab.Model.UsersAdapter;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class M03FragmentLibreta extends Fragment {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    ListView listView;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        if (ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.READ_CONTACTS},PERMISSIONS_REQUEST_READ_CONTACTS
            );
        } else {
            Log.e("DB", "PERMISSION GRANTED");
        }

        rootView = inflater.inflate(R.layout.fragment_m03_contacts, container, false);
        ArrayList<UserAuxiliar> arrayOfUsers = new ArrayList<UserAuxiliar>();
        final UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
        listView = (ListView) rootView.findViewById(R.id.contactsList);
        listView.setAdapter(adapter);
        final ArrayList<UserAuxiliar> usuarios = new ArrayList<UserAuxiliar>();
        listView.setLongClickable(true);
        registerForContextMenu(listView);

        // Get the ContentResolver
        ContentResolver cr = rootView.getContext().getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        // Move the cursor to first. Also check whether the cursor is empty or not.
        if (cursor.moveToFirst()) {
            // Iterate through the cursor
            do {
                // Get the contacts name
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor phones = cr.query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + " = " + contactId, null, null);
                phones.moveToFirst();
                String hasPhone = phones.getString(phones.getColumnIndex(Phone.HAS_PHONE_NUMBER));
                String phoneNumber = "0";
                String emailAddress = "";
                if ( hasPhone.equalsIgnoreCase("1"))
                    hasPhone = "true";
                else
                    hasPhone = "false" ;

                if (Boolean.parseBoolean(hasPhone))
                {
                    phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phoneNumber = phoneNumber.replaceAll("[()-]","");
                    phoneNumber = phoneNumber.replaceAll(" ","");
                    phones.close();
                }


                Cursor emails = cr.query(CommonDataKinds.Email.CONTENT_URI, null, CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null);
                while (emails.moveToNext())
                {
                    emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                }
                emails.close();

                usuarios.add(new UserAuxiliar(name,emailAddress, phoneNumber));

                /*AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                builder1.setMessage(name);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        emailAddress,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        phoneNumber,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();*/

              //adapter.addAll(usuarios);
                phones.close();
                emails.close();
            } while (cursor.moveToNext());
        }

        Gson gson = new Gson();

        String contacts = gson.toJson(usuarios);
        usuarios.clear();
        String contactsEncoded = "";
        try {
            contactsEncoded = URLEncoder.encode(contacts, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://192.168.1.101:8080/WebServicesFitUCAB_war_exploded/contact/getContacts?id=2&contacts=" + contactsEncoded;
        //String url = "http://192.168.1.101:8080/WebServicesFitUCAB_war_exploded/contact/getContacts?id=2&contacts=[{%22_email%22:%22andresfra92@gmail.com%22,%22_username%22:%22Andres%20Rubio%22,%22_phone%22:%224145589633%22,%22_point%22:0,%22_type%22:0,%22_id%22:0},{%22_email%22:%22%22,%22_username%22:%22Pepe%20Grillo%22,%22_phone%22:%2204141150083%22,%22_point%22:0,%22_type%22:0,%22_id%22:0},{%22_email%22:%22fmeeksr@weebly.com%22,%22_username%22:%22Fabiano%20Meeks%22,%22_phone%22:%2204141150083%22,%22_point%22:0,%22_type%22:0,%22_id%22:0}]";
        final Gson gsonresp = new Gson();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(rootView.getContext());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<User> ap = gsonresp.fromJson(response,new TypeToken<List<User>>(){}.getType());
                        boolean withApp = true;
                        for(int i = 0;i<ap.size();i++) {
                            if (ap.get(i).get_idUser() == -1){
                                usuarios.add(new UserAuxiliar(0, "", 0, 2));
                            }
                            else if (ap.get(i).get_idUser() == -2) {
                                usuarios.add(new UserAuxiliar(0, "", 0, 3));
                                withApp = false;
                            }else if (withApp) {
                                usuarios.add(new UserAuxiliar(ap.get(i).get_idUser(), ap.get(i).get_username(),ap.get(i).get_point(),0));
                            }else
                                usuarios.add(new UserAuxiliar(ap.get(i).get_username(), ap.get(i).get_email(), ap.get(i).get_phone(),1));
                        }
                        adapter.addAll(usuarios);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                usuarios.add(new UserAuxiliar(0,error.toString(), 0,4));
                adapter.addAll(usuarios);
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);



        cursor.close();

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

        menu.setHeaderTitle("menu libreta");
        UserAuxiliar user = (UserAuxiliar) listView.getItemAtPosition(info.position);
        if(user.get_type() == 0){
            menu.add(0, user.get_id(), 0, "Agregar");
        }
        else if(user.get_type() == 1){
            menu.add(1, user.get_id(), 0, "Invitar");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getGroupId()) {
            case 0:
                String url = "http://192.168.1.101:8080/WebServicesFitUCAB_war_exploded/friend/request?idRequester=2&idRequested="+Integer.toString(item.getItemId());
                final Gson gson = new Gson();

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(rootView.getContext());


                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Ya Existe esta amistad.")) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                    builder1.setMessage("Ya Existe esta amistad.");
                                    builder1.setCancelable(true);
                                    AlertDialog alert11 = builder1.create();
                                    alert11.show();
                                }
                                else{
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                    builder1.setMessage("Peticion enviada");
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
                return true;
            case 1:
                    //ENVIAR NOTIFICACION PARA DESCARGAR LA APP
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}

