package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Diet;
import com.fitucab.ds1617b.fitucab.Model.Food;
import com.fitucab.ds1617b.fitucab.Model.Moment;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Response.ErrorListener;
import static com.android.volley.Response.Listener;

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
    private java.sql.Date _fecha_actual;
    private ArrayList<Food> _foods;
    private String _momentoInsert;
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
        _fecha_actual = new java.sql.Date(lnMilisegundos);
        PeticionCaloriasHoy("josea2r", String.valueOf(_fecha_actual));
        PeticionAgregarMomentos();
        ingresarAlimentoADieta();

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

    public void ingresarAlimentoADieta(){
        _btn_m11_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresarDieta();
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
        currentText.setText(nombreAlimento);
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
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Gson gson = new Gson();
                            Diet aux = gson.fromJson(response, Diet.class);
                            ArrayList<Diet> _caloriashoy = new ArrayList<>();
                            _caloriashoy = aux.jsonArray;

                            LlenaCaloriaHoy(_caloriashoy);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new ErrorListener() {
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


   /* public void PeticionEliminarAlimento(String usuario, String _momento) {
        RequestQueue requestQueue = Volley.newRequestQueue( _view.getContext() );
        //No se que hacer, tanto este como el metodo de abajo, tienen el mismo llamado.
        //REVISAR.
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.get_ip() + "M11_Diet/deleteDiet?moment="
                + _momento + "&username=" + usuario );
        //String jsonURL = "http://201.210.245.191:8080/WebServicesFitUCAB_war_exploded/M11_Diet" +
        //"/eliminar_alimento_dieta?momento=" + _momento + "&username=" + usuario;
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, jsonURL.get_ip(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        HashMap<String, String> _caloriashoy = new HashMap<>();
                        _caloriashoy = gson.fromJson(response, new TypeToken<HashMap<String, String>>() {
                        }.getType());


                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(_view.getContext(), "Hola, no devolvio nada", Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);
    }

    public void PeticionDietaMomento(String usuario, String _fecha_actual, final String _momento) {
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        //Leer comentario en el metodo de arriba "PeticionEliminarAlimento" con respecto a la peticion
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.get_ip() + "M11_Diet/getMomentFood?moment=" + _momento
                + "&date=" + _fecha_actual + "&username=" + usuario);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.get_ip(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ArrayList<Diet> _dieta = new ArrayList<>();
                        _dieta = gson.fromJson(response, new TypeToken<ArrayList<Diet>>() {
                        }.getType());
                        for (int i = 0; i < (_dieta.size()); i++) {
                            String _nombreAlimento = _dieta.get(i).get_food();
                            String _caloriaAlimento = String.valueOf(_dieta.get(i).get_calorie());
                            Food food = new Food();
                            food.set_FoodCalorie(_caloriaAlimento);
                            food.set_FoodName(_nombreAlimento);
                            _foods.add(food);
                            LlenaTablaBD(_nombreAlimento, _caloriaAlimento, _dieta.get(i).get_id(),
                                    _momento);
                        }


                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(_view.getContext(), "Hola, no devolvio nada", Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);
    }*/


    public void PeticionAgregarMomentos() {
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.getIp() + "M11_Moment" );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Moment aux = gson.fromJson(response, Moment.class);
                        ArrayList<Moment> _momento = new ArrayList<>();
                        _momento =  aux.jsonArray;
                        LlenarSpinner(_momento);


                        _spinner_comida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                Object item = parent.getItemAtPosition(pos);

                                Date utilDate = new Date(); //fecha actual
                                long lnMilisegundos = utilDate.getTime();
                                java.sql.Date _fecha_actual = new java.sql.Date(lnMilisegundos);
                                _momentoInsert = item.toString();
                               // PeticionDietaMomento("josea2r",String.valueOf(_fecha_actual),item.toString());


                            }
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });


                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(_view.getContext(), "Hola, no devolvio nada", Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);
    }

    public void ingresarDieta(){
        String alimento = "Tomates";
        int idUser = 1;
        idUser = ManagePreferences.getIdUser(getContext());
        insertarAlimentoPersonalizado(alimento, "120", "128",idUser, getContext() );
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        //El ID del usuario debe traerse del tlf.
        jsonURL.set_ip( jsonURL.getIp() + "M11_Diet/insertOneDiet/?idUser=" + 1 + "&dietCalorie="
                + 128 + "&foodName=" + alimento  + "&moment=DESAYUNO"  );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
try {
    Map<String, String> respuesta = new HashMap<>();
    Diet aux1 = gson.fromJson(response, Diet.class);
    respuesta = aux1.getResponse();
    Toast.makeText( getContext() , respuesta.get("data") , Toast.LENGTH_LONG);
}
catch (Exception e)
{
    e.printStackTrace();
}


                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText( getContext() , "Hola, no devolvio nada" , Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);

    }

    public void insertarAlimentoPersonalizado ( String nombreAlimento , String peso , String caloria ,
                                                int usuarioID , final Context inflater ){
        RequestQueue requestQueue = Volley.newRequestQueue( inflater );
        IpStringConnection jsonURL = new IpStringConnection();
        ArrayList<Food> foodJson = new ArrayList<>();
        foodJson.add(new Food());
        foodJson.get(0).set_foodCalorie( caloria );
        foodJson.get(0).set_foodName( nombreAlimento );
        foodJson.get(0).set_foodWeight( peso );
        foodJson.get(0).set_id(1); //idUser
        foodJson.get(0).set_foodPersonalized(true);
        jsonURL.set_ip( jsonURL.getIp() + "M11_Food/insertOnePersonalizedFood?foodName="+
                foodJson.get(0).get_foodName() + "&foodCalorie=" + foodJson.get(0).get_foodCalorie()
                + "&foodWeight=" + foodJson.get(0).get_foodCalorie() + "&foodDinner=" + foodJson.get(0).get_foodPersonalized() +
                "&idUser=" + usuarioID );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Map<String, String> respuesta = new HashMap<>();
                        Food aux = gson.fromJson( response,Food.class);
                        try {
                            respuesta = aux.getResponse();
                            Toast.makeText( inflater , respuesta.get("data") , Toast.LENGTH_LONG);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( inflater , "Hola, no devolvio nada" , Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add( stringRequest );
    }

    public void LlenarSpinner (ArrayList<Moment> _momento){

        ArrayList<String> lables = new ArrayList<String>();

        for (int i = 0; i < _momento.size(); i++) {
            lables.add(_momento.get(i).get_description());
        }


        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lables);


        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item  );


        _spinner_comida.setAdapter(spinnerAdapter);


    }

/*
    public void SeleccionMomento(){

        String momentoL = _spinner_comida.getSelectedItem().toString();

        java.util.Date utilDate = new java.util.Date(); //fecha actual
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date _fecha_actual = new java.sql.Date(lnMilisegundos);
        Log.wtf("Error",momentoL);



        PeticionDietaMomento("PEDRO",String.valueOf(_fecha_actual),momentoL);
    }


    public void LlenaTablaBD(String nombreAlimento , String caloriaAlimento , int idDieta ,
                             String momento){
        Food food = new Food();
        food.set_FoodCalorie(caloriaAlimento);
        food.set_FoodName(nombreAlimento);
        _foods.add(food);
        final TableRow tableRow = new TableRow(getContext());
        tableRow.setId(idDieta);
        _tl_m11_listaDieta.addView(tableRow);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TextView currentText = new TextView(getContext());
        currentText.setText(nombreAlimento);
        currentText.setTextSize(currentText.getText().length());
        currentText.setTextColor(Color.BLACK);
        tableRow.setLayoutParams(params);
        tableRow.addView(currentText);
        AgregaColumnaBD(caloriaAlimento, tableRow, params);
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


    public void AgregaColumnaBD(String caloriaAlimento, TableRow tableRow, TableLayout.LayoutParams params) {
        TextView currentText = new TextView(getContext());
        currentText.setText(caloriaAlimento);
        currentText.setTextSize(caloriaAlimento.length());
        currentText.setTextColor(Color.BLACK);
        tableRow.setLayoutParams(params);
        tableRow.addView(currentText);
    }*/


}