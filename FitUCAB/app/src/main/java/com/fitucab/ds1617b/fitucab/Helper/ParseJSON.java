package com.fitucab.ds1617b.fitucab.Helper;

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
    public static final String JSON_ARRAY = "_challenge";
    public static final String KEY_ID = "_id";
    public static final String KEY_NOMBRE = "_name";
    public static final String KEY_DESCRIPCION = "_description";
    public static final String KEY_PUNTAJE = "_score";
    public static final String KEY_NIVEL = "_level";
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
    public void parseJSON() {
        JSONObject _jsonObject = null;
        int _quantity = 0;
        try {
            _jsonObject = new JSONObject(json);
            users = _jsonObject.getJSONArray(JSON_ARRAY);
            _ids = new String[users.length()];
            _names = new String[users.length()];
            _score = new String[users.length()];
            _descriptions = new String[users.length()];

            for (int i = 0; i <= users.length(); i++) {
                int prueba;
                prueba=_jsonObject.length();

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
