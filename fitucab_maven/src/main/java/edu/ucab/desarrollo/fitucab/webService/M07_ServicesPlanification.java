package edu.ucab.desarrollo.fitucab.webService;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaorr on 30/06/17.
 */
@Path( "/M07_ServicesPlanification" )
public class M07_ServicesPlanification {

    Gson gson = new Gson();

    @GET
    public String holaMundoDecodeEncode(@QueryParam("prueba") String pruebita,
                                        @QueryParam("prueba2") int pruebita2){
        Map<String, Object> encodeando = new HashMap<String, Object>();
        encodeando.put("pruebita", pruebita );
        encodeando.put("pruebita2", pruebita2 );
        try {
            encodeando = decode(encodeando);
        } catch (UnsupportedEncodingException e) {
            return "-1";
        }
        //encodeando.put("decode", decode(pruebita));
        //encodeando.put("control", pruebita);
        return gson.toJson(encodeando);
    }

    // cree el encode por si acaso pero creo q no lo utilizaremos
    public Map<String, Object> encode(Map<String, Object> parametros) throws UnsupportedEncodingException {
        for (Map.Entry<String,Object> entry: parametros.entrySet()){
            entry.setValue(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
        }
        return parametros;
    }

    // llamar a este metodo justo despues de comprobar que los parametros no son nulos
    // decidir donde ponerlo
    public Map<String, Object> decode(Map<String, Object> parametros) throws UnsupportedEncodingException {
        for (Map.Entry<String,Object> entry: parametros.entrySet()){
            entry.setValue(URLDecoder.decode(entry.getValue().toString(), "UTF-8"));
        }
        return parametros;
    }

}
