package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.GPSTracker;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.Model.UserAuxiliar;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.media.CamcorderProfile.get;
import static java.lang.Thread.sleep;

/**
Este fragmento nos permite la busqueda de usuarios por cercania ,lo cual tenemos un perfil de informacion
 y dos botones en los cuales agregamos y declinamos amigos.

*/
public class M03FragmentNearMe extends Fragment{

    ManagePreferences manageId = new ManagePreferences();
    int userId;

    IpStringConnection ipString = new IpStringConnection();

    TextView nombre;
    TextView puntos;
    TextView sexo;
    TextView edad;
    TextView distancia;

    View rootView;

    /**
     Es llamado cuando el fragmento es creado por primera vez ,devuelve una View (rootView)desde
     este método que será la raíz del diseño de nuestro fragmento

     @param savedInstanceState es un Bundle que proporciona datos acerca de la instancia previa del fragmento.
     @param container Es el ViewGroup principal en el cual se insertará el diseño de nuestro fragmento.
     @param inflater este parametro sirve para 3 argumentos pero aqui solo lo utilizamos para el
     Viewgroup. Para que el sistema aplique parámetros de diseño a la vista de raíz
     del diseño agrandado, especificada por la vista principal a la que se integra.
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Boolean[] usersNear = {true};
        final ArrayList<UserAuxiliar> usuarios = new ArrayList<UserAuxiliar>();

        //se encarga de poner los atributos de diseño del ViewGroup padre
        rootView = inflater.inflate(R.layout.fragment_m03_near_me, container, false);
        userId = manageId.getIdUser(rootView.getContext());
        final Fragment fragment = this;
        final Button accept = (Button) rootView.findViewById(R.id.btnAdd);
        final Button decline = (Button) rootView.findViewById(R.id.btnDecline);
        accept.setEnabled(false);
        decline.setEnabled(false);

        GPSTracker gps = new GPSTracker(rootView.getContext());
        if(gps.canGetLocation())
        {

            final Date[] bDate = new Date[1];
            final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            double dlat = gps.getLatitude();
            double dlong = gps.getLongitude();

            String url = ipString.getIp()+"nearMe/setLocation?id="+userId+"&longitud="+dlong+"&latitud="+dlat;

            // Inicializamos el RequestQueue.
            RequestQueue queuereq = Volley.newRequestQueue(rootView.getContext());
            // Solicitar una respuesta de cadena desde la URL proporcionada.
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //dialogo de alerta de un error de conexion
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                    builder1.setMessage(R.string.et_03_errorconexion);
                    builder1.setCancelable(true);
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });

            // agregamos la solicitud al RequestQueue.
            queuereq.add(stringRequest);

            //Tomamos nombre,puntos,sexo,edad,distancia de listview .
            nombre = (TextView) rootView.findViewById(R.id.nearMeName);
            puntos = (TextView) rootView.findViewById(R.id.nearMePoints);
            sexo = (TextView) rootView.findViewById(R.id.nearMeSex);
            edad = (TextView) rootView.findViewById(R.id.nearMeAge);
            distancia = (TextView) rootView.findViewById(R.id.nearMeDistance);

            String urlNear = ipString.getIp()+"nearMe?id="+userId+"&longitud="+ Double.toString(dlong) +"&latitud="+ Double.toString(dlat) +"&rango=5";
            final Gson gson = new Gson();
            // Inicializamos el RequestQueue.
            final RequestQueue queue = Volley.newRequestQueue(rootView.getContext());
            // Solicitar una respuesta de cadena desde la URL proporcionada.
            final StringRequest stringRequestDecline = new StringRequest(Request.Method.GET, urlNear,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //setea los botones
                            accept.setEnabled(true);
                            decline.setEnabled(true);
                            ArrayList<UserAuxiliar> ap = gson.fromJson(response,new TypeToken<List<UserAuxiliar>>(){}.getType());

                            if ((ap != null) && (ap.size()==0)){
                                usersNear[0] = false;
                                nombre.setText(R.string.et_03_nohayusuariocercadeti);
                            }else {

                                for (int i = 0; i < ap.size(); i++) {
                                    usuarios.add(new UserAuxiliar(ap.get(i).get_id(), ap.get(i).get_username(), ap.get(i).get_point(), ap.get(i).get_sex(), ap.get(i).get_birthdate(), ap.get(0).get_distancia()));
                                }
                            }

                            if ((usuarios != null) && (usuarios.size()>0)) {
                                nombre.setText(usuarios.get(0).get_username());
                                puntos.setText(Integer.toString(usuarios.get(0).get_point()));
                                if (usuarios.get(0).get_sex().equals("m") || usuarios.get(0).get_sex().equals("M"))
                                    sexo.setText(R.string.et_03_hombre);
                                else
                                    sexo.setText(R.string.et_03_mujer);

                                try {
                                    bDate[0] = df.parse(usuarios.get(0).get_birthdate());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                    Calendar dob = Calendar.getInstance();
                                    Calendar today = Calendar.getInstance();

                                Long time= today.getTimeInMillis() / 1000 - bDate[0].getTime() / 1000;
                                int years = Math.round(time) / 31536000;
                                edad.setText(R.string.et_03_edad+Integer.toString(years));
                                distancia.setText(usuarios.get(0).get_distancia()+R.string.et_03_kmdeti);
                                }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                    builder1.setMessage(R.string.et_03_errorconexion);
                    builder1.setCancelable(true);
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });
            // Agregue la solicitud al RequestQueue.
            queue.add(stringRequestDecline);




            }
            //Se llama cuando se ha hecho clic en un accept
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (usuarios != null && usuarios.size() > 0) {
                        String url = ipString.getIp()+"friend/request?idRequester="+userId+"&idRequested=" + usuarios.get(0).get_id();

                        // Inicializamos el RequestQueue.
                        RequestQueue queuereq = Volley.newRequestQueue(rootView.getContext());
                        // Solicitar una respuesta de cadena desde la URL proporcionada.
                        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        usuarios.remove(0);
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //dialogo de alerta de un error de conexion
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                builder1.setMessage(R.string.et_03_errorconexion);
                                builder1.setCancelable(true);
                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                            }
                        });

                        // agregamos la solicitud al RequestQueue.
                        queuereq.add(stringRequest);
                        getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
                    }else
                        //para que no se pueda dar click cuando no tenga nearme
                        accept.setEnabled(false);

                        try {
                            sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();

                        try {
                            sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            });
        //Se llama cuando se ha hecho clic en un decline
            decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (usuarios != null && usuarios.size() > 0) {
                        String url = ipString.getIp()+"friend/request?idRequester="+ usuarios.get(0).get_id()+"&idRequested="+userId;
                        // Inicializamos el RequestQueue.
                        RequestQueue queuedec = Volley.newRequestQueue(rootView.getContext());
                        // Solicitar una respuesta de cadena desde la URL proporcionada.
                        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                            String url = ipString.getIp()+"friend/update?idUpdater="+userId+"&idUpdated=" + usuarios.get(0).get_id() + "&Action=Decline";
                                            // Inicializamos el RequestQueue.
                                            RequestQueue queuereq = Volley.newRequestQueue(rootView.getContext());
                                            // Solicitar una respuesta de cadena desde la URL proporcionada.


                                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                                    new Response.Listener<String>() {
                                                        @Override
                                                        public void onResponse(String response) {
                                                            usuarios.remove(0);
                                                        }
                                                    }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                                    builder1.setMessage(R.string.et_03_errorconexion +error.toString());
                                                    builder1.setCancelable(true);
                                                    AlertDialog alert11 = builder1.create();
                                                    alert11.show();
                                                }
                                            });
                                            // agregamos la solicitud al RequestQueue.
                                            queuereq.add(stringRequest);

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //dialogo de alerta de un error de conexion
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
                                builder1.setMessage(R.string.et_03_errorconexion);
                                builder1.setCancelable(true);
                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                            }
                        });
                        // agregamos la solicitud al RequestQueue.
                        queuedec.add(stringRequest);
                    }
                    else
                        decline.setEnabled(false);


                        try {
                            sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();

                        try {
                            sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            });


        return rootView;
    }


}