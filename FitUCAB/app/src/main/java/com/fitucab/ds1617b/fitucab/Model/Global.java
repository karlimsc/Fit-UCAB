package com.fitucab.ds1617b.fitucab.Model;

/**
 * Created by elberg on 26/05/17.
 */

public class Global {
    // Esta ip hay que cambiarlo segun la red que se va a usar
    public static String _ipVolleyConection = "192.168.250.3";

    public static String _puerto = "8080";

    // Lo normal s solo cambiar el 1er componente segun por el nombre del servicio que se este corriendo
    public static String _nameProyec = "untitled"+"_war_exploded";

    //El parametro completo que se pasa al Response de volley
    // OJO solo es necesario cambiar lo de arriba
    public  static String _url ="http://"+_ipVolleyConection+":"+_puerto+"/"+_nameProyec+"/";


}