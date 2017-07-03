package com.fitucab.ds1617b.fitucab.UI.Activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ItemList;
import com.fitucab.ds1617b.fitucab.Helper.ListAdapter;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.Helper.ParseJSON;
import com.fitucab.ds1617b.fitucab.Helper.CustomList;

import java.util.ArrayList;

/**
 * Clase M09GamificationActivity que maneja la actividad principal del Modulo 9 Gestion de Gamificacion
 * @EXTENDS: AppCompatActivity
 * @IMPLEMENTS: View.OnClickListener
 * @AUTHORS: Juan Macedo, Cesar Boza, Bryan Teixeira
 * @VERSION: 1.0
 */
public class M09GamificationActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaracion de atributos de la clase
    private ArrayList<ItemList> arrayItem= null;
    private ListAdapter adapter = null;
    private String TAG= "FitUCAB";
    private TextView _textViewLevel;
    private TextView _scoreTV;
    private int _totalPoints;
    private int _calculateLevel = 0;
    private static int _accomplished;
    private static int _notAccomplished;
    public IpStringConnection _ip = new IpStringConnection();
    private String _acomplishmentsURL = _ip.getIp()+"M09_ServicesGamifications/getChallenges";
    private String _levelURL = _ip.getIp()+"M09_ServicesGamifications/getScores";
    private ListView _listAccomplishments;
    private String _error;
    private ManagePreferences _user = new ManagePreferences();
    private String _paramVolley = "/";


    /**
     * Metodo onCreate que genera la actividad M09GamificationActivity
     *
     * @param _savedInstanceState
     */
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        setContentView(R.layout.activity_m09_gamification);
        _scoreTV = (TextView) findViewById(R.id._pointsTV);
        _listAccomplishments = (ListView) findViewById(R.id._listXML);
        //LLAMAR FUNCION DE VOLLEY QUE BUSCA EN BASE DE DATOS QUE TRAE EL NIVEL
        levelRequest(this);
        //LLAMAR FUNCION DE VOLLEY QUE BUSCA EN BASE DE DATOS QUE TRAE LOS LOGROS
        scoreRequest(this);
        //Creamos los listener en caso de que se desee lanzar la actividad LevelGraphic
        //Listener de la imagen
        ImageView _perfil = (ImageView) findViewById(R.id._profileImage);
        _perfil.setOnClickListener(this);
        //Listener del TextView del texto del nombre
        TextView _nombreUsuarioVista = (TextView) findViewById(R.id._nameLabelTV);
        _nombreUsuarioVista.setOnClickListener(this);
        //Listener del TextView del texto del nivel
        TextView _nivelVista = (TextView) findViewById(R.id._levelLabelTV);
        _nivelVista.setOnClickListener(this);
        //Listener del TextView del texto del puntaje
        TextView _puntajeVista = (TextView) findViewById(R.id._pointsTV);
        _puntajeVista.setOnClickListener(this);
    }


    /**
     * VOID scoreRequest: Hace la llamada a traves del WebService y obtiene un JSON
     *
     * @param _context en el cual se llenara los datos obtenidos
     */
    public void scoreRequest(final Context _context) {

        //SharedPreferences _preferences = PreferenceManager.getDefaultSharedPreferences(_context);
        //int _userID = _preferences.getInt("idUser", _user.get_idUser());
        //cableado mientras SharedPreferences no funciona
        int _userID = 2;
        //Colocamos la direccion correctamente para realizar la peticion con parametros
        _acomplishmentsURL= _acomplishmentsURL+_paramVolley+""+_userID;
        JsonArrayRequest _stringRequest = new JsonArrayRequest(Request.Method.GET,_acomplishmentsURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray _response) {
                        Log.d("onResponse()", _response.toString());
                        scoreJSON(_response,_context);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onResponse()", error.toString());
            }
        });

        RequestQueue _request = Volley.newRequestQueue(this);
        _request.add(_stringRequest);
        Log.d("objetooo", _stringRequest.toString());
    }

    /**
     * VOID scoreJSON Metodo que maneja el JSON obtneido del void scoreRequest
     *
     * @param _json    parametro que obtiene como resultado de la busqueda del webservice
     * @param _context contexto donde se vaciara los datos obtenidos
     */
    private void scoreJSON(JSONArray _json, Context _context) {

        try {
            arrayItem = new ArrayList<>();
          for (int i=0; i< _json.length(); i++){
              JSONObject json= _json.getJSONObject(i);
              arrayItem.add(new ItemList(json.getString("score"),json.getString("name"),json.getString("description")));
          }
            adapter = new ListAdapter(arrayItem, getApplicationContext());
            _listAccomplishments.setAdapter(adapter);}
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * VOID que llamara al WebService para obtener el nivel
     *
     * @param _context variable de tipo Context donde se vaciara
     */
    public void levelRequest(final Context _context) {

        //SharedPreferences _preferences = PreferenceManager.getDefaultSharedPreferences(_context);
        //int _userID = _preferences.getInt("idUser", _user.get_idUser());
        //cableado mientras SharedPreferences no funciona
        int _userID = 2;
        //Colocamos la direccion correctamente para realizar la peticion con parametros
        _levelURL= _levelURL+_paramVolley+""+_userID;
        StringRequest _stringRequest = new StringRequest(_levelURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String _response) {
                        Log.d("onResponse()", _response.toString());
                        levelJSON(_response,_context);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onResponse()", error.toString());
            }
        });

        RequestQueue _request = Volley.newRequestQueue(this);
        _request.add(_stringRequest);
        Log.d("objetooo", _stringRequest.toString());

    }

    /**
     * VOID que manejara el JSON que se obtiene del metodo levelRequest
     *
     * @param _json    obtenido del metodo levelRequest
     * @param _context contexto en el cual se vaciara los datos obtenidos del WebService
     */
    private void levelJSON(String _json, Context _context) {
        JSONObject _jsonObject = null;
        try {
            _jsonObject= new JSONObject(_json);
            _calculateLevel = Integer.parseInt(_jsonObject.getString("level"));
            _scoreTV = (TextView) ((Activity) _context).findViewById(R.id._pointsTV);
            _scoreTV.setText("Points: " +_jsonObject.getString("score") );
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            Log.e(_error, "Valor NULL");
        } catch (NumberFormatException e) {
            Log.e(_error, "No es un numero");
        }catch (Exception e){
            Log.e(_error, "Excepcion no identificada en metodo levelJSON");
        }
        _textViewLevel = (TextView) ((Activity) _context).findViewById(R.id._levelLabelTV);
        _textViewLevel.setText("Level: " + _calculateLevel);
    }


    /**
     * VOID onClick el cual estara escuchando todos los posibles clicks en las variables que se declaro
     * el listener
     * @param _v de tipo View
     */
    @Override
    public void onClick(View _v) {

        //Todos los diferentes listeners que se activaron para los clicks
        if ((_v.getId() == R.id._profileImage) || (_v.getId() == R.id._nameLabelTV)
                || (_v.getId() == R.id._levelLabelTV) || (_v.getId() == R.id._pointsTV)) {

            //Se crea un intent para el cambio hacia nueva actividad LevelGraph
            Intent _myIntent = new Intent(M09GamificationActivity.this, M09LevelGraphActivity.class);
            _myIntent.putExtra("nivel", String.valueOf(_calculateLevel));
            startActivity(_myIntent);
        }
    }
}