package com.fitucab.ds1617b.fitucab.UI.Fragments.M07;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;
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
import com.fitucab.ds1617b.fitucab.Model.Planification;
import com.fitucab.ds1617b.fitucab.Model.ServerResponse;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * A simple {@link Fragment} subclass.
 **/
public class M07ActivityFragment extends Fragment {
    private ImageButton _btn_m07_fechaInicio;
    private EditText _tv_m07_fechaInicio;
    private ImageButton _btn_m07_fechaFin;
    private EditText _tv_m07_fechaFin;
    private ImageButton _btn_m07_horaInicio;
    private EditText _tv_m07_horaInicio;
    private ImageButton _btn_m07_horaFin;
    private EditText _tv_m07_horaFin;
    private CheckBox _cb_m07_checklunes;
    private CheckBox _cb_m07_checkmartes;
    private CheckBox _cb_m07_checkmiercoles;
    private CheckBox _cb_m07_checkjueves;
    private CheckBox _cb_m07_checkviernes;
    private CheckBox _cb_m07_checksabado;
    private CheckBox _cb_m07_checkdomingo;
    private Button _btn_m07_declinar;
    private Button _btn_m07_aceptar;

    private OnFragmentSwap _callBack;
    private View _view;
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int min;

    private Planification planification;
    private ManagePreferences manageId = new ManagePreferences();
    private int idUsuario;

    public M07ActivityFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public M07ActivityFragment(Planification planification){
     this.planification = planification;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        _view = inflater.inflate(R.layout.fragment_m07_activity, container, false);

        _btn_m07_fechaInicio = (ImageButton)_view.findViewById(R.id.btn_m07_fechaInicio);
        _tv_m07_fechaInicio = (EditText)_view.findViewById(R.id.tv_m07_fechaInicio);
        _btn_m07_fechaFin = (ImageButton)_view.findViewById(R.id.btn_m07_fechaFin);
        _tv_m07_fechaFin = (EditText)_view.findViewById(R.id.tv_m07_fechaFin);
        _btn_m07_horaInicio = (ImageButton)_view.findViewById(R.id.btn_m07_horaInicio);
        _tv_m07_horaInicio = (EditText)_view.findViewById(R.id.tv_m07_horaInicio);
        _btn_m07_horaFin = (ImageButton)_view.findViewById(R.id.btn_m07_horaFin);
        _tv_m07_horaFin = (EditText)_view.findViewById(R.id.tv_m07_horaFin);
        _cb_m07_checklunes = (CheckBox)_view.findViewById(R.id.cb_m07_checklunes);
        _cb_m07_checkmartes = (CheckBox)_view.findViewById(R.id.cb_m07_checkmartes);
        _cb_m07_checkmiercoles = (CheckBox)_view.findViewById(R.id.cb_m07_checkmiercoles);
        _cb_m07_checkjueves = (CheckBox)_view.findViewById(R.id.cb_m07_checkjueves);
        _cb_m07_checkviernes = (CheckBox)_view.findViewById(R.id.cb_m07_checkviernes);
        _cb_m07_checksabado = (CheckBox)_view.findViewById(R.id.cb_m07_checksabado);
        _cb_m07_checkdomingo = (CheckBox)_view.findViewById(R.id.cb_m07_checkdomingo);
        _btn_m07_declinar = (Button)_view.findViewById(R.id.btnDecline);
        _btn_m07_aceptar = (Button)_view.findViewById(R.id.btnAdd);
        idUsuario = manageId.getIdUser(getContext());
        //_btn_m07_horaInicio.setOnClickListener((View.OnClickListener) _view);
        //_btn_m07_horaFin.setOnClickListener((View.OnClickListener) _view);
        agregarFechaInicio();
        agregarFechaFin();
        agregarHoraInicio();
        agregarHoraFin();
        buttonDeclinar();
        buttonAceptar();
        if ( planification != null ){
            cargarEvento( planification );
        }


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

    public void agregarFechaInicio(){
        //_btn_m07_fechaInicio = ( ImageButton ) _view.findViewById( R.id.btn_m07_fechaInicio );
        _btn_m07_fechaInicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        _tv_m07_fechaInicio.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    }
                }
                        ,dia,mes,ano);
                datePickerDialog.show();
            }
        });
    }
    public void agregarFechaFin(){
        _btn_m07_fechaFin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        _tv_m07_fechaFin.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    }
                }
                        ,dia,mes,ano);
                datePickerDialog.show();
            }
        });
    }
    public void agregarHoraInicio(){
        _btn_m07_horaInicio.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                min = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                _tv_m07_horaInicio.setText(hourOfDay + ":" + minute+":0");
                            }
                        }, hora, min, false);
                timePickerDialog.show();
            }
        });
    }
    public void agregarHoraFin(){
        _btn_m07_horaFin.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                min = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                _tv_m07_horaFin.setText(hourOfDay + ":" + minute+":"+0);
                            }
                        }, hora, min, false);
                timePickerDialog.show();
            }
        });
    }

    private void  buttonDeclinar(){

        _btn_m07_declinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M07HomeFragment", null);
            }

        });

    }

    private void buttonAceptar(){
        _btn_m07_aceptar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                addEvent( idUsuario );

                Toast.makeText( getContext() , "Se creo el evento" , Toast.LENGTH_LONG).show();

            }

        });
    }

    private void  cargarEvento(Planification planification){

        boolean frecuencia[];

        _tv_m07_fechaInicio.setText(String.valueOf(planification.get_startDate()));
        _tv_m07_fechaFin.setText(String.valueOf(planification.get_endDate()));
        _tv_m07_fechaInicio.setText(String.valueOf(planification.get_startTime()));
        _tv_m07_horaFin.setText(String.valueOf(planification.get_duration()));
        frecuencia = planification.get_days();

           if ( frecuencia[0] == true) {
               _cb_m07_checklunes.setChecked(true);

           }
            else if (frecuencia[1] == true) {
               _cb_m07_checkmartes.setChecked(true);

           }
           else if (frecuencia[2] == true) {
               _cb_m07_checkmiercoles.setChecked(true);

           }
           else if (frecuencia[3] == true) {
               _cb_m07_checkjueves.setChecked(true);

           }
           else if (frecuencia[4] == true) {
               _cb_m07_checkviernes.setChecked(true);

           }
           else if (frecuencia[5] == true) {
               _cb_m07_checksabado.setChecked(true);

           }
           else if (frecuencia[6] == true) {
               _cb_m07_checkdomingo.setChecked(true);

           }

    }

    public void addEvent(int usuario)
     {
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.getIp() + "M07_ServicesPlanification/insertPlanification?startDay="+ _tv_m07_fechaInicio.toString()+
                "&endDay="+_tv_m07_fechaFin.toString()+"&startTim="+ _tv_m07_horaInicio+"&duration="+
                _tv_m07_horaFin+"userId=" + usuario+ "&sportId="+1+ "&monday="+ _cb_m07_checklunes.isChecked()+
                "&tuesday="+  _cb_m07_checkmartes.isChecked()+"&wednesday="+ _cb_m07_checkmiercoles.isChecked()+
        "&thursday="+_cb_m07_checkjueves.isChecked()+"&friday="+_cb_m07_checkviernes.isChecked()+
                "&saturday="+_cb_m07_checksabado.isChecked()+"&sunday="+_cb_m07_checkdomingo.isChecked());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ServerResponse respuesta = new ServerResponse();
                        respuesta = gson.fromJson(response,new TypeToken<ServerResponse>(){}.getType());
                        ArrayList<Planification> planifications = new ArrayList<>();
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
}