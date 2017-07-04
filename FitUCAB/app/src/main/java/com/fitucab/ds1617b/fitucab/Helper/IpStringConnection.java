package com.fitucab.ds1617b.fitucab.Helper;

/**
 * Created by Alejandro Fernandez on 26/5/2017.
 * Esta clase será d edonde se va sacar el ip a donde se va a conectar
 * Absoultamente TODOS debemos usar esta clase para al momento de ejecutar se tenga el mismo string
 *
 */

public class IpStringConnection {
    

    private String _ip = "http://10.0.2.2:8080/fitucab/";

    public IpStringConnection() {

        this._ip = "http://10.0.2.2:8080/fitucab/";

    }

    public String getIp() {

        return _ip;

    }

    public void set_ip(String url){
        this._ip = url;
    }

}
