package com.fitucab.ds1617b.fitucab.UI.Fragments.M07;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.fitucab.ds1617b.fitucab.Helper.LocalDate;
import com.fitucab.ds1617b.fitucab.Helper.LocalTime;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Planification;
import com.fitucab.ds1617b.fitucab.Model.ServerResponse;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

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
                Planification planificationToDialog = new Planification();
                planificationToDialog = planification.get( position );
                dialogoAccion( planificationToDialog );
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
                        peticionEliminarPlanificacion( planificacion.get_id());
                    }
                    })
                .setNegativeButton( "Editar" , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Hay que hacer un if para redirigir si es actividad o entrenamiento....
                        if (planificacion.get_days()== null) {

                            _callBack.onSwapData("M07trainingFragment", null,planificacion);
                        }

                        else if (planificacion.get_days() != null){
                            _callBack.onSwapData("M07ActivityFragment", null, planificacion);
                        }
                    }
                })
        .setNeutralButton( "Ver" , new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (planificacion.get_days()== null) {

                    _callBack.onSwapData("M07trainingFragment", null,planificacion);
                }

                else if (planificacion.get_days() != null){
                    _callBack.onSwapData("M07ActivityFragment", null, planificacion);
                }


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
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.getIp() + "M07_ServicesPlanification/deletePlanification?planificationId="
                + idPlanificacion +"&userId=" +idUsuario);
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ServerResponse respuesta = new ServerResponse();
                        respuesta = gson.fromJson(response,new TypeToken<ServerResponse>(){}.getType());
                        ArrayList<Planification> planifications = new ArrayList<>();

                        Toast.makeText(_view.getContext(), "Planificacion eliminada correctamente", Toast.LENGTH_LONG);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(_view.getContext(), "Hola, no devolvio nada", Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);
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
        jsonURL.set_ip( jsonURL.getIp() + "M07_ServicesPlanification/getPlanification?userId=" +
        idUsuario );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        ServerResponse respuesta = new ServerResponse();
                        respuesta = gson.fromJson(response,
                                new TypeToken<ServerResponse>(){}.getType());
                        ArrayList<Planification> planifications = new ArrayList<>();
                        //planifications = respuesta.getData();
                        planifications = manejandoRespuesta( respuesta );
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

    public ArrayList<Planification> manejandoRespuesta(ServerResponse respuesta){
        ArrayList<Planification> planifications = new ArrayList<>();
        LocalTime lt;
        LocalDate ld;
        Planification planification;
        ArrayList<Planification> aux = new ArrayList<>();
        aux = respuesta.getData();
        for ( int i = 0; i < respuesta.getData().size()-1; i++ ){
            ld = new LocalDate();
            lt = new LocalTime();
            planification = new Planification();
            lt.setHour( Integer.valueOf(aux.get(i).get_startTime().getHour()) );
            lt.setMinute( Integer.valueOf(aux.get(i).get_startTime().getMinute() ));
            lt.setNano( Integer.valueOf(aux.get(i).get_startTime().getNano()) );
            lt.setSecond( Integer.valueOf(aux.get(i).get_startTime().getSecond() ));
            planification.set_startTime( lt );
            lt.setHour( Integer.valueOf(aux.get(i).get_duration().getHour()) );
            lt.setMinute( Integer.valueOf(aux.get(i).get_duration().getMinute() ));
            lt.setNano( Integer.valueOf(aux.get(i).get_duration().getNano()) );
            lt.setSecond( Integer.valueOf(aux.get(i).get_duration().getSecond() ));
            planification.set_duration( lt );
            ld.setYear( Integer.valueOf(aux.get(i).get_startDate().getYear()) );
            ld.setDay( Integer.valueOf(aux.get(i).get_startDate().getDay()) );
            ld.setMonth( Integer.valueOf(aux.get(i).get_startDate().getMonth() ));
            planification.set_startDate( ld );
            ld.setYear( Integer.valueOf(aux.get(i).get_endDate().getYear()) );
            ld.setDay( Integer.valueOf(aux.get(i).get_endDate().getDay()) );
            ld.setMonth( Integer.valueOf(aux.get(i).get_endDate().getMonth() ));
            planification.set_endDate( ld );

            planification.set_id( Integer.valueOf(aux.get(i).get_id()) );
            planification.set_sport( Integer.valueOf(aux.get(i).get_sport()) );
            planification.set_days(agregarDias(aux.get(i)));
            planifications.add( planification );

        }
        return planifications;
    }

    public boolean[] agregarDias(Planification planification ){
        boolean[] days = new boolean[7];
        for ( int i = 0; i <= planification.get_days().length; i++ ){
            days[i] = planification.get_days()[i];
        }
        return days;
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
