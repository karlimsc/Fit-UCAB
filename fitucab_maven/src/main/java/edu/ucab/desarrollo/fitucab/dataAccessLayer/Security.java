package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import sun.misc.BASE64Encoder;

import javax.ws.rs.QueryParam;
import java.io.UnsupportedEncodingException;


public final class Security {

    private static Security _singlInstance;


    /**
     * Fabrica de la clase Seguridad.
     * @return Devuelve la instanciacion de la clase.
     */
    public static Security get_singlInstance(){
        if (_singlInstance==null)
            _singlInstance = new Security();
        return _singlInstance;
    }


    private static String DEFAULT_ENCODING1="UTF-8";
    /**
     * Funcion encargada de realizar la encriptación de un password
     * @param password El password a ser encriptado
     * @return String encriptado con BASE64
     */
    public static String encryptPassword(String password) {
        //Instanciamos un encriptador BASE64
        BASE64Encoder enc = new BASE64Encoder();
        try {
            //Utilizando la codificacion por defecto (UTF-8) encriptamos el string
            return enc.encode(password.getBytes(DEFAULT_ENCODING1));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
