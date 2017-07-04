package edu.ucab.desarrollo.fitucab.common.entities;

import java.util.ArrayList;

/**
 * Clase que se usa para la respuesta del servicio web
 */

public class Response {
    private int status;
    private ArrayList data;
    private String message;

    /**
     * Constructor con todos los parametros
     * @param status codigo de la ejecucion
     * @param data Informacion a devolver
     * @param message Mensaje descriptivo de la ejecucion
     */

    public Response(int status, ArrayList data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    /**
     * Constructor vacio
     */
    public Response() {

    }

    /**
     * Getter del status de la ejecucio
     * @return status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Setter del status de la ejecucio
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Getter de la informacion que se desea devolver
     * @return ArrayList de la data
     */
    public ArrayList getData() {
        return data;
    }

    /**
     * Setter de la informacion que se desea devolver
     * @param data
     */
    public void setData(ArrayList data) {
        this.data = data;
    }

    /**
     * Getter del mensaje descriptivo
     * @return menssage
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter del mensaje descriptivo
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
