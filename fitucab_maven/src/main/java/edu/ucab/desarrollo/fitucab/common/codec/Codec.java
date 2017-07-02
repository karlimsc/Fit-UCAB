package edu.ucab.desarrollo.fitucab.common.codec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaorr on 30/06/17.
 */
public class Codec {

    // cree el encode por si acaso pero creo que no lo utilizaremos
    public static Map<String, Object> encode(Map<String, Object> parametros) throws UnsupportedEncodingException {
        for (Map.Entry<String,Object> entry: parametros.entrySet()){
            entry.setValue(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
        }
        return parametros;
    }

    // llamar a este metodo justo despues de comprobar que los parametros no son nulos
    // decidir donde ponerlo
    public static HashMap<String, Object> decode(HashMap<String, Object> parametros) throws UnsupportedEncodingException {
        try {
            for (Map.Entry<String,Object> entry: parametros.entrySet()){
                entry.setValue(URLDecoder.decode(entry.getValue().toString(), "UTF-8"));
            }
        }

        catch (Exception e) {
            //incluir logs
            //agregar exception de null
        }
        return parametros;
    }
}
