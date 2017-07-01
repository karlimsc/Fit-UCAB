package edu.ucab.desarrollo.fitucab.common;

/**
 * Comando Registro
 */
public class Registry
{

    public static String BD_USER = "fitucab";
    public static String BD_PASSWORD = "fitucab";
    public static String BD_URL = "jdbc:postgresql://localhost/FitUcabDB";
    public static String BD_CLASS_FOR_NAME = "org.postgresql.Driver";


    public static final int RESULT_CODE_OK = 200;
    public static final int RESULT_CODE_FAIL = 500;

    public static final String ERROR_PARAM_WS = "Error en el parametro {} recibido por el servicio web";
    public static final String RESULT_CODE_FAIL_MSG = "Ha ocurrido un error generico";

}
