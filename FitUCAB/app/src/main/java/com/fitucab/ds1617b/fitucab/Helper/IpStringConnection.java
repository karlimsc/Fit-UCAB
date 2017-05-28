package com.fitucab.ds1617b.fitucab.Helper;

/**
 * Created by Alejandro Fernandez on 26/5/2017.
 * Esta clase será d edonde se va sacar el ip a donde se va a conectar
 * Absoultamente TODOS debemos usar esta clase para al momento de ejecutar se tenga el mismo string
 *
 */

public class IpStringConnection {
    private String _ip = "http://200.84.40.227:8080/WebServicesFitUCAB_war_exploded/";


    public IpStringConnection() {

        this.set_ip("http://200.84.40.227:8080/WebServicesFitUCAB_war_exploded/");

    }

    public String get_ip() {
        return _ip;
    }

    public void set_ip(String _ip) {
        this._ip = _ip;
    }
}
