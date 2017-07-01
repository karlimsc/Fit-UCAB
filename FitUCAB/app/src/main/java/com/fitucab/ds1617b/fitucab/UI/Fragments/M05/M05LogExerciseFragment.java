package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.Model.Sport;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Clase que manja el fragmento de Log Activity
 */

public class M05LogExerciseFragment extends Fragment implements
        View.OnClickListener
{
    //Formato de la fecha
    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private OnFragmentSwap _callBack;

    Gson gson = new Gson();
    ArrayList<Sport> _sports = new ArrayList<Sport>();

    EditText _cal, _dur;

    View _view;
    TextView _tv_date, _tv_time;

    Button _btn_resg;
    Spinner _spin;

    String nombreDeport,_idDeporte;

    // Valores para los parametros  de fecha
    int _year, _month, _day;
    //Valorea para la hora
    int _hour, _min;

    private Activit activity = new Activit();
    private int userID;
    private ManagePreferences mP = new ManagePreferences();

    public M05LogExerciseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_m05_log_activity, container, false);
        setupViewValues();
        return _view;
    }

    // Para innstanciar los componentes OJO: hAY QUE PONER EL _view
    private void setupViewValues() {
        userID = mP.getIdUser(getContext());
        userID =1;
        _tv_date = (TextView) _view.findViewById(R.id.tv_m05_date);
        _tv_time = (TextView) _view.findViewById(R.id.tv_m05_time);
        _cal = (EditText) _view.findViewById(R.id.et_m05_calories);
        _dur = (EditText) _view.findViewById(R.id.et_m05_uptime);
        _btn_resg = (Button) _view.findViewById(R.id.btn_m05_manualregister);
        _spin = (Spinner) _view.findViewById(R.id.spn_tipe);


        listenerActionButton();
        _tv_date.setOnClickListener(this);
        _tv_time.setOnClickListener(this);
        // Para llenar el spinner
        makeRequestSpinner();
    }

    /**
     * Metodo escucha la acciondel botton
     */

    public void listenerActionButton(){
        _btn_resg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreDeport= _spin.getSelectedItem().toString();
                makeIdSport();
                makeInsertLog();
            }
        });
    }


    /**
     * Metodo sobreescrito para que muestre dialos de fecha  y hora
     * @param v View donde lanza el dialogo
     */
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v == _tv_date) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            _year = c.get(Calendar.YEAR);
            _month = c.get(Calendar.MONTH);
            _day = c.get(Calendar.DAY_OF_MONTH);
            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            //_tvdisplaydate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, monthOfYear);
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            _tv_date.setText(format.format(calendar.getTime()));

                        }
                    }, _year, _month, _day);
            datePickerDialog.show();
        }
        if (v == _tv_time) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            _hour = c.get(Calendar.HOUR_OF_DAY);
            _min = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            _tv_time.setText(hourOfDay + ":" + minute);
                        }
                    }, _hour, _min, false);
            timePickerDialog.show();
        }
    }


    /**
     * Metodo devuelve la id por nombre del deporte
     */

    public void makeIdSport()
    {                    //Esta es la consulta para insertar una actividad
        String consult = M05UrlConsul._urlIdSport+nombreDeport;

        final StringRequest stringRequest = new StringRequest
                (Request.Method.GET, consult,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Limpia los textos
                                Sport deporte = gson.fromJson(response,Sport.class);
                                _idDeporte = String.valueOf(deporte.getId());
                                Log.e("ID DEL DEPOOOOORTEEE",response);
                                Log.e("ID DEL DEPOOOOORTEEE",_idDeporte);
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getContext(), R.string._tst_m05_messagereload,
                                Toast.LENGTH_SHORT).show();

                    }
                });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    /**
     * Hace el Insert
     */

    public void makeInsertLog()
    {                    //Esta es la consulta para insertar una actividad


        String consult = M05UrlConsul._urlInsertAct(String.valueOf(_tv_time.getText()),
                                                     "12:12",
                                                     String.valueOf(_tv_date.getText()),
                                                     "",
                                                     String.valueOf(_cal.getText()),
                                                     null,
                                                     null,
                                                     String.valueOf(userID),
                                                     _idDeporte);
        final StringRequest stringRequest = new StringRequest
                (Request.Method.GET, consult,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                               //Limpia los textos
                                _tv_date.setText("");
                                _tv_time.setText("");
                                _cal.setText("");
                                _dur.setText("");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getContext(), R.string._tst_m05_messagereload,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    /**
     * Llena el spinner
     */

    public void makeRequestSpinner()
    {
        // Lo que se encuentra en null es lugar inicio y fin y kilometros
        IpStringConnection baseIp = new IpStringConnection();
        String URL = baseIp.getIp() + "M05_ServicesSport/getSportsUser?idPer="+userID;


        final StringRequest stringRequest = new StringRequest
                (Request.Method.GET, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                ArrayList<String> at = new ArrayList<String>();
                                ArrayList<String> tab = new ArrayList<String>();
                                at = gson.fromJson(response,
                                        new TypeToken<List<String>>(){}.getType());
                                Log.e("AQUIIIIIII", response);
                                /*String[] deportes = new String[30];
                                ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                                        android.R.layout.simple_spinner_dropdown_item, deportes);*/
                                ArrayAdapter <CharSequence> adapter =
                                        new ArrayAdapter <CharSequence> (getContext(), android.R.layout.simple_spinner_item );
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                //adapter.addAll(deportes);

                                for (int i = 0; i < at.size(); i++ ){
                                    //  deportes[i] = at.get(i);
                                    adapter.add(at.get(i));

                                }
                                _spin.setAdapter(adapter);

                                //Muestra un mensaje al usuario que no posee elemntos en la lista
                                if (at.size()== 0){
                                    Toast.makeText(getContext(), R.string._tst_m05_messagefoundactivity,
                                            Toast.LENGTH_SHORT).show();
                                    //para que no escucheel 1er item en caso de que no tenga actiadas

                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getContext(), R.string._tst_m05_messagereload,
                                Toast.LENGTH_SHORT).show();

                    }
                });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public String createActivity(){
        try {
            activity.set_endtime(String.valueOf(_tv_time.getText()));
            activity.set_date(String.valueOf(_tv_date.getText()));
            activity.set_calor(Float.valueOf(String.valueOf(_cal.getText())));
            activity.set_name(nombreDeport);

            return "Objeto Creado";
        }
        catch (Exception e){
            return "NO CREÓ EL ACTIVITY" + e.toString();
        }
    }


}