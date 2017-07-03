package edu.ucab.desarrollo.fitucab.common.entities;

import java.util.ArrayList;


public class Response {
    private int status;
    private ArrayList data;
    private String message;

    public Response(int status, ArrayList data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Response() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
