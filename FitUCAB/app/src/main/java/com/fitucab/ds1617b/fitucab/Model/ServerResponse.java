package com.fitucab.ds1617b.fitucab.Model;

import java.util.ArrayList;

/**
 * Created by zedin on 03/07/17.
 */

public class ServerResponse {
    private int status;
    private ArrayList<Planification> data;
    private String message;

    public ServerResponse(int status, ArrayList data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    public ServerResponse() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Planification> getData() {
        return data;
    }

    public void setData(ArrayList<Planification> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
