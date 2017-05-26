package com.example.juanmacedo.fitucab;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by juanmacedo on 18/05/17.
 */

public class ParseJSON {
    public static String[] ids;
    public static String[] names;
    public static String[] descripciones;
    public static String [] puntaje;
    public static int[] nivel;

    public static final String JSON_ARRAY = "reto";
    public static final String KEY_ID = "id";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_PUNTAJE = "puntaje";


    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
        Log.e("HOLAAAAA", json.toString());
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);
            ids = new String[jsonObject.length()];
            names = new String[jsonObject.length()];
            puntaje = new String[jsonObject.length()];
            descripciones = new String[jsonObject.length()];
            int cantidad = 0;
            for(int i=0;i<jsonObject.length();i++)
            {
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                names[i] = jo.getString(KEY_NOMBRE);
                descripciones[i] = jo.getString(KEY_DESCRIPCION);
                puntaje[i] = jo.getString(KEY_PUNTAJE);

                cantidad++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
