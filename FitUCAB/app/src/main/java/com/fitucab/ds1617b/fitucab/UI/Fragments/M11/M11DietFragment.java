package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Diet;
import com.fitucab.ds1617b.fitucab.Model.Food;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.R.attr.x;

/**
 * A simple {@link Fragment} subclass.
 */

public class M11DietFragment extends Fragment {


    private TextView _tv_m11_cantidadCalorias;
    private AutoCompleteTextView _tv_m11_nombreAlimento;
    private EditText _tv_m11_peso;
    private ImageButton _btn_m11_agregar;
    private Spinner _spinner_comida;
    private ImageButton _btn_m11_aceptar;
    private ImageButton _btn_m11_rechazar;
    private ImageButton _btn_m11__diet_borrar;
    private TableLayout _tl_m11_listaDieta;
    private View _view;
    private int _contadorAlimentos;
    private OnFragmentSwap _callBack;

    public M11DietFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo que se llama automaticamente cuando la la actividad anfitriona usa el fragmento.
     *
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        _view = inflater.inflate(R.layout.fragment_m11_diet, container, false);


        _tv_m11_cantidadCalorias = (TextView) _view.findViewById(R.id.tv_m11_cantidadCalorias);
        _tv_m11_nombreAlimento = (AutoCompleteTextView) _view.findViewById(R.id.tv_m11_nombreAlimento);
        _tv_m11_peso = (EditText) _view.findViewById(R.id.tv_m11_peso);
        _btn_m11_agregar = (ImageButton) _view.findViewById(R.id.btn_m11_agregar);
        _spinner_comida = (Spinner) _view.findViewById(R.id.spinner_comida);
        _btn_m11_aceptar = (ImageButton) _view.findViewById(R.id.btn_m11_aceptar);
        _btn_m11_rechazar = (ImageButton) _view.findViewById(R.id.btn_m11_rechazar);
        _tl_m11_listaDieta = (TableLayout) _view.findViewById(R.id.tl_m11_listaDieta);
        setAlimento();
        AddListenerBack();
        java.util.Date utilDate = new java.util.Date(); //fecha actual
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date _fecha_actual = new java.sql.Date(lnMilisegundos);
        PeticionCaloriasHoy("PEDRO", String.valueOf(_fecha_actual));

        return _view;
    }

    /**
     * Metodo que inicializa el listener para agregar alimentos a la dieta.
     */
    public void setAlimento() {
        _btn_m11_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAlimento = _tv_m11_nombreAlimento.getText().toString();
                String pesoAlimento = _tv_m11_peso.getText().toString();
                LlenaTabla(nombreAlimento, pesoAlimento);
            }
        });
    }

    /**
     * Metodo que inicializa el listener para volver atras y cancelar la carga de la dieta.
     */
    public void AddListenerBack() {
        _btn_m11_rechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M11FoodhomeActivity", null);
                Toast.makeText(_view.getContext(), "FUNCIONA", Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * Metodo que realiza la carga de el alimento a la lista de la dieta
     *
     * @param nombreAlimento Recibe nombre del alimento a cargar,
     * @param pesoAlimento   Recibe el peso del alimento a cargar.
     */
    public void LlenaTabla(String nombreAlimento, String pesoAlimento) {
        final TableRow tableRow = new TableRow(getContext());
        _tl_m11_listaDieta.addView(tableRow);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TextView currentText = new TextView(getContext());
        currentText.setText(nombreAlimento + "             " + pesoAlimento);
        currentText.setTextSize(currentText.getText().length());
        currentText.setTextColor(Color.BLACK);
        currentText.setId(_contadorAlimentos);//Un ID para fila del TableRow
        tableRow.setLayoutParams(params);
        tableRow.addView(currentText);
        AgregaColumna(pesoAlimento, tableRow, params);
        currentText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Confirmacion para eliminar alimento de la tabla de la die
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("¿Desea continuar con la Eliminacion?")
                        .setTitle("Advertencia")
                        .setCancelable(false)
                        //Acciones para cuando dice no
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        //Acciones para cuando dice si
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Elimina el alimento del TableRow
                                _tl_m11_listaDieta.removeView(tableRow);


                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    /**
     * Metodo para agregar columna con el peso del alimento al TableLayout.
     *
     * @param pesoAlimento Recibe el peso del alimento.
     * @param tableRow     Recibe la fila sobre la cual se creara la columna.
     * @param params       Recibe el Layout.
     */
    public void AgregaColumna(String pesoAlimento, TableRow tableRow, TableLayout.LayoutParams params) {
        TextView currentText = new TextView(getContext());
        currentText.setText(pesoAlimento);
        currentText.setTextSize(pesoAlimento.length());
        currentText.setTextColor(Color.BLACK);
        currentText.setId(_contadorAlimentos);
        tableRow.setLayoutParams(params);
        tableRow.addView(currentText);
    }

    public void PeticionCaloriasHoy(String usuario, String _fecha_actual) {
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.getIp() + "M11_Diet/getCalorieByDate?date="
                + _fecha_actual + "&username=" + usuario );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ArrayList<Diet> _caloriashoy = new ArrayList<>();
                        _caloriashoy = gson.fromJson(response, new TypeToken<ArrayList<Diet>>() {
                        }.getType());
                        LlenaCaloriaHoy(_caloriashoy);

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

    public void LlenaCaloriaHoy(ArrayList<Diet> _caloriashoy) {


        _tv_m11_cantidadCalorias.setText(String.valueOf( _caloriashoy.get(0).get_calorie()));

        }


    public void PeticionEliminarAlimento(String usuario, String _momento) {
        RequestQueue requestQueue = Volley.newRequestQueue( _view.getContext() );
        //No se que hacer, tanto este como el metodo de abajo, tienen el mismo llamado.
        //REVISAR.
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.getIp() + "M11_Diet/deleteDiet?moment="
                + _momento + "&username=" + usuario );
        //String jsonURL = "http://201.210.245.191:8080/WebServicesFitUCAB_war_exploded/M11_Diet" +
                //"/eliminar_alimento_dieta?momento=" + _momento + "&username=" + usuario;
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ArrayList<String> _caloriashoy = new ArrayList<>();
                        _caloriashoy = gson.fromJson(response, new TypeToken<ArrayList<String>>() {
                        }.getType());


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

    public void PeticionDietaMomento(String usuario, Date _fecha_actual, String _momento) {
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        //Leer comentario en el metodo de arriba "PeticionEliminarAlimento" con respecto a la peticion
        String jsonURL = "http://201.210.245.191:8080/WebServicesFitUCAB_war_exploded/M11_Diet" +
                "/eliminar_alimento_dieta?momento=" + _momento + "&fecha=" + _fecha_actual + "&username" + usuario;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ArrayList<String> _caloriashoy = new ArrayList<>();
                        _caloriashoy = gson.fromJson(response, new TypeToken<ArrayList<String>>() {
                        }.getType());


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

