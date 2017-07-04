package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Food;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;

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
        String username;
        username = ManagePreferences.getUsername(getContext());
        PeticionAlimentos(username);
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
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.getIp() + "M11_Food/getPersonalizedList?username=" + usuario );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();

                        ArrayList<Food> foods = new ArrayList<>();
                        try {
                            Food aux = gson.fromJson(response, Food.class);
                            foods = aux.jsonArray;
                            LlenaTablaAlimentos(foods);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
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
            fila.setId(alimentos.get(i).get_id());
            _gl_m11_listaAlimento.addView(fila);
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT , TableLayout.LayoutParams.WRAP_CONTENT);
            AgregaColumna( alimentos.get(i).get_foodName(), fila , params);
            AgregaColumna( String.valueOf(alimentos.get(i).get_foodWeight()), fila , params);
            AgregaColumna( String.valueOf(alimentos.get(i).get_foodCalorie()), fila , params);
            final String alimento = alimentos.get(i).get_foodName();
            final int pesoAlimento = Integer.valueOf(alimentos.get(i).get_foodWeight());
            final int caloriaAlimento = Integer.valueOf(alimentos.get(i).get_foodCalorie());
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
                                    FragmentManager fm = getFragmentManager();
                                    M11FooddialogFragment dialogFragment =
                                            new M11FooddialogFragment( alimento , String.valueOf(pesoAlimento) ,
                                                    String.valueOf(caloriaAlimento), fila.getId() );
                                    dialogFragment.show( getActivity().getFragmentManager() , "titulo" );
                                    //editarAlimentoPersonalizad( alimento , pesoAlimento ,
                                    //      caloriaAlimento , 1);
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
                                    int idUser = 0;
                                    idUser = ManagePreferences.getIdUser(getContext());
                                    eliminarAlimentoPersonalizado( idUser , alimento );
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

    /**
     * Metodo que realiza la llamada a un dialogo para insertar un alimento personalizado
     */
    public void addAlimento() {

        _btn_m11_agregar = ( ImageButton ) _view.findViewById( R.id.btn_m11_agregar );
        _btn_m11_agregar.setOnClickListener(new View.OnClickListener() {
            //Para detecta que se a presionado el boton agregar

            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                M11FooddialogFragment dialogFragment = new M11FooddialogFragment("", "","",0);
                dialogFragment.show( getActivity().getFragmentManager() , "titulo" );
                //Muestrar el Dialog personalizado para agregar los alimentos.
            }
        });

    }

    /**
     * Metodo que se utiliza para eliminar algun alimento personalizado del usuario
     * @param usuarioID Representa el ID del usuario
     * @param nombreAlimento Representa el nombre del alimento que va a ser eliminado
     */
    public void eliminarAlimentoPersonalizado( int usuarioID , String nombreAlimento ){
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.getIp() + "M11_Food/deletePersonalizedFood?foodName="
                + nombreAlimento + "&idUser=" + usuarioID );
        StringRequest stringRequest = new StringRequest( Request.Method.DELETE, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Food aux = gson.fromJson( response , Food.class);
                        ArrayList<String> respuesta = new ArrayList<>();
                        try {
                            respuesta = (ArrayList<String>) aux.getResponse();
                        }catch (Exception e )
                        {
                            e.printStackTrace();
                        }

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
}