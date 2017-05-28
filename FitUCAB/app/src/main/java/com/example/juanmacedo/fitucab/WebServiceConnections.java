package com.example.juanmacedo.fitucab;

/**Clase WebServiceConnections
 *
 * para el manejo de las conexiones de las distintas funciones del WebService
 * @AUTHORS: Juan Macedo, Cesar Boza, Bryan Teixeira
 * @VERSION: 1.0
 */
public class WebServiceConnections {

    /**String de conexion para servicio que obtiene los logros de un usuario
     */
    public static String _dbGetAccomplishments = "http://192.168.1.8:8080/FitUcabService_war_exploded/db/obtener";

    /**String de conexion para servicio que obtiene los retos logrados y no logrados
     */
    public static String _dbGetGraphicStats ="http://192.168.1.8:8080/FitUcabService_war_exploded/dbgrafica/obtener";

    /**String de conexion para servicio que obtiene el nivel de un usuario
     */
    public static String _dbGetLevel ="http://192.168.1.8:8080/FitUcabService_war_exploded/dbnivel/obtener";


    /**Constructor vacio de la clase
    * @PARAMS: Null
     */
    public WebServiceConnections(){
    }

}
