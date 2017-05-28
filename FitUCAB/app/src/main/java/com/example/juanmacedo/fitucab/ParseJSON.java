package com.example.juanmacedo.fitucab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Clase parseJSON para manejar los objetos de tipo JSON obtenidos del
 *  WebService del Modulo 9 Gestion de Gamificacion
 *
 * @AUTHORS: Juan Macedo, Cesar Boza, Bryan Teixeira
 * @VERSION: 1.0
 */

public class ParseJSON {

    //Declaracion de atributos
    public static final String JSON_ARRAY = "reto";
    public static final String KEY_ID = "id";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_PUNTAJE = "puntaje";
    public static final String KEY_NIVEL = "nivel";
    public static String[] _ids;
    public static String[] _names;
    public static String[] _descriptions;
    public static String[] _score;
    public static String[] _level;
    private JSONArray users = null;
    private String json;

    /**
     * Constructor de la clase ParseJSON que asigna un string
     * @param _jsonString string de la clase
     */
    public ParseJSON(String _jsonString) {
        this.json = _jsonString;
    }

    /**
     * VOID parseJSON para manejar los JSON
     * @PARAMS: Null
     * @RETURN: Null
     */
    protected void parseJSON() {
        JSONObject _jsonObject = null;
        int _quantity = 0;
        try {
            _jsonObject = new JSONObject(json);
            users = _jsonObject.getJSONArray(JSON_ARRAY);
            _ids = new String[_jsonObject.length()];
            _names = new String[_jsonObject.length()];
            _score = new String[_jsonObject.length()];
            _descriptions = new String[_jsonObject.length()];

            for (int i = 0; i < _jsonObject.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                _ids[i] = jo.getString(KEY_ID);
                _names[i] = jo.getString(KEY_NOMBRE);
                _descriptions[i] = jo.getString(KEY_DESCRIPCION);
                _score[i] = jo.getString(KEY_PUNTAJE);
                _quantity++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
