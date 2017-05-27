package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Food;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
* A simple {@link Fragment} subclass.
 **/
public class M11FoodFragment extends Fragment {

    private ImageButton _btn_m11_agregar;
    private ImageButton _btn_m11_borrar;
    private TableLayout _gl_m11_listaAlimento;
    private View _view;
    private OnFragmentSwap _callBack;

    public M11FoodFragment() {
        // Required empty public constructor
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _view = inflater.inflate(R.layout.fragment_m11_food, container, false);

        _btn_m11_agregar = (ImageButton)_view.findViewById(R.id.btn_m11_agregar);
        _gl_m11_listaAlimento = (TableLayout) _view.findViewById(R.id.gl_m11_listaAlimento);
        //Aqui va el usuario como variable.....
        PeticionAlimentos("PEDRO");
        addAlimento(); //para agregar los personalizados

        // Inflate the layout for this fragment
        return _view;
    }

    /**
     * Metodo que se encarga de hacer las peticiones a el Servicio Web para traer los alimentos
     * personalizados del usuario
     * @param usuario Recibe el usuario que hará la peticion.
     */
    public void PeticionAlimentos(String usuario)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        //OJO esta no es la consulta en si, hay que colocar la que es de los personalizados......
        //La haré mañana temprano.
        String jsonURL = "http://201.210.245.191:8080/WebServicesFitUCAB_war_exploded/M11_Food" +
                "/obtener_todoas_alimentos_auto?username=" + usuario;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ArrayList<Food> foods = new ArrayList<>();
                        foods = gson.fromJson(response, new TypeToken<ArrayList<Food>>(){}.getType());
                        LlenaTablaAlimentos(foods);
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
     * Metodo de llenado de la tabla, este metodo se usa para llenar la tabla sobre la cual
     * estaran todos los alimentos de los usuarios.
     * @param alimentos Recibe un arreglo del Servicio Web, del tipo Food.
     */
    public void LlenaTablaAlimentos(final ArrayList<Food> alimentos )
    {
        for (int i = 0 ; i < alimentos.size() ; i++ ){
            final TableRow fila = new TableRow(getContext());
            fila.setId(alimentos.get(i).getId());
            _gl_m11_listaAlimento.addView(fila);
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT , TableLayout.LayoutParams.WRAP_CONTENT);
            AgregaColumna( alimentos.get(i).getFoodName(), fila , params);
            AgregaColumna( String.valueOf(alimentos.get(i).getFoodWeight()), fila , params);
            AgregaColumna( String.valueOf(alimentos.get(i).getFoodCalorie()), fila , params);

            final String alimento = alimentos.get(i).getFoodName();
            final int pesoAlimento = Integer.valueOf(alimentos.get(i).getFoodWeight());
            final int caloriaAlimento = Integer.valueOf(alimentos.get(i).getFoodCalorie());
            fila.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    //Confirmacion para eliminar alimento de la tabla de la die
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("¿Que acción desea realizar?")
                            .setTitle("Acción")
                            .setCancelable(false)
                            //Acciones para cuando dice no
                            .setNegativeButton("Editar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //El id del usuario debe ser variable
                                    editarAlimentoPersonalizad( alimento , pesoAlimento ,
                                            caloriaAlimento , 1);
                                }
                            })
                            .setNeutralButton("Volver", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                }
                            })
                            //Acciones para cuando dice si
                            .setPositiveButton("Eliminar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //El id del usuario debe ser variable.
                                    eliminarAlimentoPersonalizado( 1 , alimento );
                                    //Elimina el alimento del TableRow
                                    _gl_m11_listaAlimento.removeView(fila);


                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }

    /**
     * Metodo que agrega las columnas de cada fila de la tabla de alimentos.
     * @param atributo Recibe el atributo que va a agregar a la vista.
     * @param fila Recibe la fila sobre la cual se va a agregar.
     * @param params Recibe el Layout sobre el cual se agregara.
     */
    public void AgregaColumna ( String atributo , TableRow fila, TableLayout.LayoutParams params )
    {
        TextView currentText = new TextView(getContext());
        currentText.setText(atributo);
        //currentText.setTextSize(alimento.length());
        currentText.setTextSize(13); // Tamaño generico para todos(de otra forma se ve muy pequeño
        currentText.setMinHeight(25);
        currentText.setMinWidth(15);
        currentText.setTextColor(Color.BLACK);
        fila.setLayoutParams(params);
        fila.addView(currentText);
    }

    public void addAlimento() {

        _btn_m11_agregar = ( ImageButton ) _view.findViewById( R.id.btn_m11_agregar );
        _btn_m11_agregar.setOnClickListener(new View.OnClickListener() {
            //Para detecta que se a presionado el boton agregar

            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                M11FooddialogFragment dialogFragment = new M11FooddialogFragment();
                dialogFragment.show( getActivity().getFragmentManager() , "titulo" );
                //Muestrar el Dialog personalizado para agregar los alimentos.
            }
        });

    }
    public void eliminarAlimentoPersonalizado( int usuarioID , String nombreAlimento ){
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        //OJO esta no es la consulta en si, hay que colocar la que es de los personalizados......
        //La haré mañana temprano.
        String jsonURL = "http://201.210.245.191:8080/WebServicesFitUCAB_war_exploded/M11_Food" +
                "/eliminar_personalizado?alimento=" + nombreAlimento + "&usuario=" + usuarioID;
        StringRequest stringRequest = new StringRequest( Request.Method.DELETE, jsonURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ArrayList<String> respuesta = new ArrayList<>();
                        respuesta = gson.fromJson( response ,
                                new TypeToken<ArrayList<String>>(){}.getType() );
                        Toast mensaje = new Toast(_view.getContext());
                        mensaje.setText("El alimento fue eliminado correctamente");
                        mensaje.setDuration(Toast.LENGTH_LONG);
                        mensaje.setGravity( Gravity.CENTER , 0 , 0);
                        mensaje.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( _view.getContext(), "Hola, no devolvio nada", Toast.LENGTH_LONG );
                    }
                });
        requestQueue.add( stringRequest );

    }

    public void editarAlimentoPersonalizad( String nombreAlimento , int peso , int caloria ,
                                            int usuarioID){
        RequestQueue requestQueue = Volley.newRequestQueue( _view.getContext() );
        //OJO esta no es la consulta en si, hay que colocar la que es de los personalizados......
        //La haré mañana temprano.
        String jsonURL = "http://201.210.245.191:8080/WebServicesFitUCAB_war_exploded/M11_Food" +
                "/eliminar_personalizado?alimento=" + nombreAlimento + "&peso" + peso + "&calorie" +
                caloria + "&usuario=" + usuarioID;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ArrayList<String> respuesta = new ArrayList<>();
                        respuesta = gson.fromJson( response,
                                new TypeToken<ArrayList<String>>(){}.getType() );
                        Toast mensaje = new Toast(_view.getContext());
                        mensaje.setText( "El alimento fue actualizado correctamente" );
                        mensaje.setDuration( Toast.LENGTH_LONG );
                        mensaje.setGravity( Gravity.CENTER , 0 , 0);
                        mensaje.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( _view.getContext(), "Hola, no devolvio nada" , Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add( stringRequest );

    }
}
