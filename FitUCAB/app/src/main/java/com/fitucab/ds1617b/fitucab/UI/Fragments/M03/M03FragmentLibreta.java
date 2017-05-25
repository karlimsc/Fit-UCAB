/**
 * Created by andres on 17/04/17.
 */

package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.content.DialogInterface;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Model.ArrayAuxiliar;
import com.fitucab.ds1617b.fitucab.Model.Person;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.Model.UsersAdapter;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Activities.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static android.R.attr.phoneNumber;
import static android.R.attr.type;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.google.gson.internal.UnsafeAllocator.create;
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
        ArrayList<ArrayAuxiliar> arrayOfUsers = new ArrayList<ArrayAuxiliar>();
        final UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
        listView = (ListView) rootView.findViewById(R.id.contactsList);
        listView.setAdapter(adapter);
        final ArrayList<ArrayAuxiliar> usuarios = new ArrayList<ArrayAuxiliar>();
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

                usuarios.add(new ArrayAuxiliar(name,emailAddress, Long.parseLong(phoneNumber)));

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
        // Close the cursor

        Gson gson = new Gson();

        String contacts = gson.toJson(usuarios);
        usuarios.clear();
        String puto = "http://192.168.1.109:8080/WebServicesFitUcab_war_exploded/contacts/getContacts?id=2&contacts=[{%20%22personid%22:%203,%20%22personusername%22:%20%22Lon%20Parselon%22,%20%22personpassword%22:%20%22123456%22,%20%22personemail%22:%20%22mape@gmail.com%22,%20%22personsex%22:%20%22Femenino%22,%20%22personphone%22:%20%2204125217789%22,%20%22personingreso%22:%200%20},%20{%20%22personid%22:%201,%20%22personusername%22:%20%22Andres%20Rubio%22,%20%22personpassword%22:%20%22123456%22,%20%22personemail%22:%20%22andresfra92@gmail.com%22,%20%22personsex%22:%20%22Masculino%22,%20%22personphone%22:%20%2204123150284%22,%20%22personingreso%22:%201%20},%20{%20%22personid%22:%207,%20%22personusername%22:%20%22Juanciron%22,%20%22personpassword%22:%20%22123456%22,%20%22personemail%22:%20%22fsdfs@gmail.com%22,%20%22personsex%22:%20%22Femenino%22,%20%22personphone%22:%20%2204141f41145689%22,%20%22personingreso%22:%200%20},%20{%20%22personid%22:%206,%20%22personusername%22:%20%22Raul%2014io%22,%20%22personpassword%22:%20%22123456%22,%20%22personemail%22:%20%22sdfff@gmail.com%22,%20%22personsex%22:%20%22Masculino%22,%20%22personphone%22:%20%226512424651%22,%20%22personingreso%22:%201%20},{%20%22personid%22:%204,%20%22personusername%22:%20%22Emilio%20Monse%C3%B1or%22,%20%22personpassword%22:%20%22123456%22,%20%22personemail%22:%20%22emm@gmail.com%22,%20%22personsex%22:%20%22Masculino%22,%20%22personphone%22:%20%22651651%22,%20%22personingreso%22:%201%20}]";
        //String url = "http://192.168.1.109:8080/WebServicesFitUcab_war_exploded/contacts/getContacts?id=2&contacts=" + puto;

        final Gson gsonresp = new Gson();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(rootView.getContext());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, puto,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Person> ap = gsonresp.fromJson(response,new TypeToken<List<Person>>(){}.getType());
                        boolean withoutApp = true;
                        for(int i = 0;i<ap.size();i++) {
                            if (ap.get(i).getPersonid() == -1){
                                usuarios.add(new ArrayAuxiliar(0, "", 0, 2));
                            }
                            else if (ap.get(i).getPersonid() == -2) {
                                usuarios.add(new ArrayAuxiliar(0, "", 0, 3));
                                withoutApp = false;
                            }else if (withoutApp) {
                                usuarios.add(new ArrayAuxiliar(ap.get(i).getPersonid(), ap.get(i).getPersonusername(), 200, 0));

                            }else
                                usuarios.add(new ArrayAuxiliar(ap.get(i).getPersonid(), ap.get(i).getPersonusername(), 200, 1));
                        }
                        adapter.addAll(usuarios);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                usuarios.add(new ArrayAuxiliar(0,error.toString(), 200,4));
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

        menu.setHeaderTitle("menu");
        ArrayAuxiliar user = (ArrayAuxiliar) listView.getItemAtPosition(info.position);
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
                String url = "http://192.168.1.109:8080/WebServicesFitUcab_war_exploded/friend/request?idRequester=2&idRequested=4";
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

