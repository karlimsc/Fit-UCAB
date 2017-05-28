package com.example.juanmacedo.fitucab;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Clase Gamification que maneja la actividad principal del Modulo 9 Gestion de Gamificacion
 * @EXTENDS: AppCompatActivity
 * @IMPLEMENTS: View.OnClickListener
 * @AUTHORS: Juan Macedo, Cesar Boza, Bryan Teixeira
 * @VERSION: 1.0
 */
public class Gamification extends AppCompatActivity implements View.OnClickListener {

    //Declaracion de atributos de la clase
    private TextView _textViewLevel;
    private TextView _scoreTV;
    private int _totalPoints;
    private int _calculateLevel = 0;
    private static int _accomplished;
    private static int _notAccomplished;
    public WebServiceConnections _webService = new WebServiceConnections();
    private String _acomplishmentsURL = _webService._dbGetAccomplishments;
    private String _levelURL = _webService._dbGetLevel;
    private ListView _listAccomplishments;
    private String _error;


    /**
     * Metodo onCreate que genera la actividad Gamification
     *
     * @param _savedInstanceState
     */
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        setContentView(R.layout.activity_gamification);
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
        StringRequest _stringRequest = new StringRequest(_acomplishmentsURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String _response) {
                        Log.d("onResponse()", _response.toString());
                        //LE PASO LA ACTIVIDAD PARA INSTANCIAR PUNTAJE CON SU VALOR
                        scoreJSON(_response, _context);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError _error) {
                Log.e("onResponse()", _error.toString());
            }
        });
        //INSTANCIAR PETICION AL VOLLEY
        RequestQueue _request = Volley.newRequestQueue(this);
        _request.add(_stringRequest);
        Log.d("Request impreso", _stringRequest.toString());
    }

    /**
     * VOID scoreJSON Metodo que maneja el JSON obtneido del void scoreRequest
     *
     * @param _json    parametro que obtiene como resultado de la busqueda del webservice
     * @param _context contexto donde se vaciara los datos obtenidos
     */
    private void scoreJSON(String _json, Context _context) {

        ParseJSON _pj = new ParseJSON(_json);
        _pj.parseJSON();
        String[] _score = ParseJSON._score;
        int _collectPoints = 0;
        for (int i = 0; i < ParseJSON._score.length; i++) {
            try {
                _collectPoints = _collectPoints + Integer.parseInt(_score[i].toString());
                _totalPoints = _collectPoints;
            } catch (NullPointerException e) {
                Log.e(_error, "Valor NULL");
            } catch (NumberFormatException e) {
                Log.e(_error, "No es un numero");
            }
        }
        try {
            String _totalScore = String.valueOf(_totalPoints);
            _scoreTV = (TextView) ((Activity) _context).findViewById(R.id._pointsTV);
            _scoreTV.setText("Points: " + _totalScore);
        } catch (NullPointerException e) {
            Log.e(_error, "Valor NULL");
        } catch (NumberFormatException e) {
            Log.e(_error, "No es un numero");
        }catch (Exception e){
            Log.e(_error, "Excepcion no identificada en metodo scoreJSON");
        }
        //GENERO LA LISTA CON SUS CONTENIDOS
        CustomList _cl = new CustomList(this, ParseJSON._ids, ParseJSON._names, ParseJSON._descriptions, ParseJSON._score);
        _listAccomplishments.setAdapter(_cl);
    }

    /**
     * VOID que llamara al WebService para obtener el nivel
     *
     * @param _context variable de tipo Context donde se vaciara
     */
    public void levelRequest(final Context _context) {

        StringRequest _stringRequest = new StringRequest(_levelURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String _response) {
                        Log.d("onResponse()", _response.toString());
                        //Llamada para manejar el JSON obtenido del WebService
                        levelJSON(_response, _context);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onResponse()", error.toString());
            }
        });
        //INSTANCIAR PETICION AL VOLLEY
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(_stringRequest);
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
            _jsonObject = new JSONObject(_json);
            _calculateLevel = Integer.parseInt(_jsonObject.getString("nivel"));
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
            Intent _myIntent = new Intent(Gamification.this, LevelGraph.class);
            _myIntent.putExtra("nivel", String.valueOf(_calculateLevel));
            startActivity(_myIntent);
        }
    }
}