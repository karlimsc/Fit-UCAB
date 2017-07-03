package com.fitucab.ds1617b.fitucab.UI.Fragments.M07;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Food;
import com.fitucab.ds1617b.fitucab.Model.Planification;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * */
public class M07HomeFragment extends Fragment {
    private OnFragmentSwap _callBack;
    private Button _btn_crear;
    private View _view;
    private ManagePreferences manageId = new ManagePreferences();
    private ListView _lt_planification;
    private int idUsuario;

    public M07HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_m07_home, container, false);
        _btn_crear = (Button) _view.findViewById(R.id.btnCrear);
        _lt_planification = ( ListView ) _view.findViewById(R.id.lv_m07_planification);
        idUsuario = manageId.getIdUser(getContext());
        peticionPlanificacion( idUsuario );
        crearEvento();
        return _view;

    }


    /**
     * Metodo que se llama automaticamente cuando la la actividad anfitriona usa el fragmento.
     * @param activity Recibe la actividad anfitriona en la que va a mostrarse.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            _callBack = (OnFragmentSwap) activity;
        } catch (ClassCastException e) {


            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    /**
     * METODO PARA MOSTRAR LA LISTA DE ACTIVIDADES Y ENTRENAMIENTOS PLANIFICADAS POR EL USUARIO.
     * @param planification RECIBE UNA LISTA CON LA PLANIFICACION ENTERA DEL USUARIO QUE SE VA A
     *                      MOSTRAR
     */
    public void mostrarPlanificacion(final ArrayList<Planification> planification ){
        ArrayAdapter<Planification> adapter = new ArrayAdapter<Planification>( getContext() ,
                android.R.layout.simple_list_item_1, planification);
        _lt_planification.setAdapter( adapter );
        _lt_planification.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        dialogoAccion( planification.get(position) );

                return false;
            }
        } );
    }

    /**
     * METODO QUE MUESTRA UN DIALOGO PARA SABER QUE HACER SOBRE LA PLANIFICACION
     * @param planificacion
     */
    private void dialogoAccion( final Planification planificacion ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Que desea hacer");
        builder.setPositiveButton( "Eliminar" , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Si preciona el boton se elimina la planificacion
                        peticionEliminarPlanificacion( planificacion.get_planificationId());
                    }
                    })
                .setNegativeButton( "Editar" , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Hay que hacer un if para redirigir si es actividad o entrenamiento....
                        _callBack.onSwap("M07ActivityFragment", null);
                    }
                })
        .setNeutralButton( "Ver" , new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //abre un fragmento con la data de la planificacion
                FragmentManager fm = getFragmentManager();
                M07PlanificationFragment planificationFragment = new M07PlanificationFragment( planificacion );
                planificationFragment.show( getActivity().getFragmentManager() , "titulo" );
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*public void peticionEditarPlanificacion ( Planification planificacion ){
        //Colocar codigo para la peticion

    }*/

    public void peticionEliminarPlanificacion ( int idPlanificacion ){
        //Colocar codigo para la peticion
    }

    /**
     * METODO QUE REALIZA LA PETICION AL SERVICIO WEB DE LA PLANIFICACION DEL USUARIO.
     * @param idUsuario RECIBE EL ID DEL USUARIO QUE REQUIERE VER SU LISTA DE ACTIVIDADES
     *                  Y ENTRENAMIENTOS PLANIFICADOS.
     */
    public void peticionPlanificacion( int idUsuario){
        IpStringConnection jsonURL = new IpStringConnection();
        RequestQueue requestQueue = Volley.newRequestQueue( getContext() );
        //Revisar la URL cuando Jesus la haga....
        jsonURL.set_ip( jsonURL.getIp() + "M07_ServicesPlanification/getPlanification?userID=" +
        idUsuario );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        ArrayList<Planification> planifications = new ArrayList<>();
                        planifications = gson.fromJson(response,
                                new TypeToken<ArrayList<Planification>>(){}.getType());
                        mostrarPlanificacion( planifications );
                        //Toast.makeText( getContext() , respuesta.get("data") , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( getContext() , "Usted no posee planificaciones" , Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add( stringRequest );


    }

    private void  crearEvento(){

        _btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoEvento();
            }

        });

    }

    private void dialogoEvento() {

        final CharSequence[] items = {"Entrenamiento", "Actividad"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Evento a crear");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Toast toast = Toast.makeText(getContext(), "Haz elegido la opcion: " + items[item] , Toast.LENGTH_SHORT);
                toast.show();
                dialog.cancel();

                    if (items[item] == "Entrenamiento") {
                        _callBack.onSwap("M07TrainingFragment", null);
                    }
                else {
                        _callBack.onSwap("M07ActivityFragment", null);
                    }

            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }





}
