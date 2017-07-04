package edu.ucab.desarrollo.fitucab.common;
/**                                                                 
 * Comando Registro
 */
public class Registry
{


    public static String BD_USER = "fitucab";
    public static String BD_PASSWORD = "fitucab";
    public static String BD_URL = "jdbc:postgresql://localhost/fitucabdb";
    public static String BD_CLASS_FOR_NAME = "org.postgresql.Driver";


    public static final int RESULT_CODE_OK = 200;
    public static final int RESULT_CODE_FAIL = 500;

    public static final String ERROR_PARAM_WS = "Error en el parametro {} recibido por el servicio web";
    public static final String RESULT_CODE_FAIL_MSG = "Ha ocurrido un error generico";

    public static final int _level1 = 1000;
    public static final int _level2 = 2200;
    public static final int _level3 = 3600;
    public static final int _level4 = 5200;
    public static final int _level5 = 7000;
    public static final int _level6 = 9000;
    public static final int _level7 = 11200;
    public static final int _level8 = 13600;
    public static final int _level9 = 16200;
    public static final int _level10 = 19000;

    public static String RECOVERY_EMAIL_USERNAME = "fitucabprueba2@gmail.com";
    public static String RECOVERY_EMAIL_PASS = "ucab2017";
}
