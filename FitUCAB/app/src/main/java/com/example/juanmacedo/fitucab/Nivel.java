package com.example.juanmacedo.fitucab;


import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Bryan Teixeira on 26/5/2017.
 */

public class Nivel {

    //Atributos para el manejo de puntajes especificos de cada nivel
    private int _nivel1= 1000;
    private int _nivel2= 2200;
    private int _nivel3= 3600;
    private int _nivel4= 5200;
    private int _nivel5= 7000;
    private int _nivel6= 9000;
    private int _nivel7= 11200;
    private int _nivel8= 13600;
    private int _nivel9= 16200;
    private int _nivel10= 19000;
    private int _puntosTotales= 0;
    int _nivel=0;
    public WebServiceConnections _servicioWeb = new WebServiceConnections();
    private String URL = _servicioWeb._dbGetAccomplishments;
    public int _busquedaPuntos;

    //Constructor vacio de la clase
    public Nivel (){
            }



    //------------------METODOS DE LA CLASE

    /**
    * VOID pasoNivel Metodo para determinar si con los puntos recien obtenidos, el jugador
    *   pasa de nivel y si es asi, enviar una notificacion
    * @PARAMS : _idUsuario, int _puntosParaSumar
     */
    public void pasoNivel(int _idUsuario, int _puntosParaSumar ){

        //Declaracion de atributos del metodo
        int _puntosTotales;
        String _asunto= "¡Pasaste de nivel!";
        String _mensaje;
        int _puntosAcumulados=0;
        int _modulo9 =9;

        //Llamada al metodo que obtiene todos los puntos ganados actualmente por el jugador
        //_puntosAcumulados= LLAMADA METODO
        _puntosTotales= _puntosAcumulados + _puntosParaSumar;

        if ((_puntosAcumulados < _nivel1 )&&(_puntosTotales>= _nivel2 )) {

            _mensaje = "¡Felicitaciones por haber alcanzado el nivel 2! ¡Sigue asi!";
            //MandoNotificacion
            //sendMail( String receptor, _asunto , _mensaje, _modulo9 );

        }
        else if ((_puntosAcumulados < _nivel2 )&&(_puntosTotales>= _nivel3 )) {

            _mensaje = "¡Felicitaciones por haber alcanzado el nivel 3! ¡Si se puede!";
            //MandoNotificacion
            //sendMail( String receptor, _asunto , _mensaje, _modulo9 );

        }
        else if ((_puntosAcumulados < _nivel3 )&&(_puntosTotales>= _nivel4 )) {

            _mensaje = "¡Felicitaciones por haber alcanzado el nivel 4! ¡Excelente trabajo!";
            //MandoNotificacion
            //sendMail( String receptor, _asunto , _mensaje, _modulo9 );

        }
        else if ((_puntosAcumulados < _nivel4 )&&(_puntosTotales>= _nivel5 )) {

            _mensaje = "¡Felicitaciones por haber alcanzado el nivel 5! ¡Estas a a mitad del camino!";
            //MandoNotificacion
            //sendMail( String receptor, _asunto , _mensaje, _modulo9 );

        }
        else if ((_puntosAcumulados < _nivel5 )&&(_puntosTotales>= _nivel6 )) {

            _mensaje = "¡Felicitaciones por haber alcanzado el nivel 6! ¡Estas entre los mejores!";
            //MandoNotificacion
            //sendMail( String receptor, _asunto , _mensaje, _modulo9 );

        }
        else if ((_puntosAcumulados < _nivel6 )&&(_puntosTotales>= _nivel7 )) {

            _mensaje = "¡Felicitaciones por haber alcanzado el nivel 7! ¡El estadio te aclama!";
            //MandoNotificacion
            //sendMail( String receptor, _asunto , _mensaje, _modulo9 );

        }
        else if ((_puntosAcumulados < _nivel7 )&&(_puntosTotales>= _nivel8 )) {

            _mensaje = "¡Felicitaciones por haber alcanzado el nivel 8! ¡Dominando el mundo!";
            //MandoNotificacion
            //sendMail( String receptor, _asunto , _mensaje, _modulo9 );

        }
        else if ((_puntosAcumulados < _nivel8 )&&(_puntosTotales>= _nivel9 )) {

            _mensaje = "¡Felicitaciones por haber alcanzado el nivel 9! ¡Imparable!";
            //MandoNotificacion
            //sendMail( String receptor, _asunto , _mensaje, _modulo9 );

        }
        else if ((_puntosAcumulados < _nivel9 )&&(_puntosTotales>= _nivel10 )) {

            _mensaje = "¡Felicitaciones por haber alcanzado el nivel 4! ¡Eres el mejor de todos!";
            //MandoNotificacion
            //sendMail( String receptor, _asunto , _mensaje, _modulo9 );

        }
    }

}

