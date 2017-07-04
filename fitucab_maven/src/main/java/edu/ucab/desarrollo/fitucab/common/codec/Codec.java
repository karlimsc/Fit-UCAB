package edu.ucab.desarrollo.fitucab.common.codec;
import edu.ucab.desarrollo.fitucab.common.exceptions.EncodingExeption;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Executable;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase usada para encodear y desencodear parametros a UTF-8
 */
public class Codec {

    /**
     *
     * @param parametros HashMap con los parametros a encodear
     * @return Lista de parametros encodeados
     * @throws EncodingExeption
     */
    public static Map<String, Object> encode(Map<String, Object> parametros) throws EncodingExeption {
        for (Map.Entry<String,Object> entry: parametros.entrySet()){
            try {
                entry.setValue(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new EncodingExeption(entry.getKey());
            }
        }
        return parametros;
    }

    /**
     *
     * @param parametros HashMap con los parametros a encodear
     * @return Lista de parametros encodeados
     * @throws EncodingExeption
     */
    // llamar a este metodo justo despues de comprobar que los parametros no son nulos
    // decidir donde ponerlo
    public static HashMap<String, Object> decode(HashMap<String, Object> parametros) throws EncodingExeption {
        for (Map.Entry<String,Object> entry: parametros.entrySet()){
            try {
                entry.setValue(URLDecoder.decode(entry.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new EncodingExeption(entry.getKey());
            }
        }
        return parametros;
    }
}
