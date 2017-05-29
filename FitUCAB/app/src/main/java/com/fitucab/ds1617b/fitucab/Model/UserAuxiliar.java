package com.fitucab.ds1617b.fitucab.Model;

import static android.R.attr.type;
import static com.fitucab.ds1617b.fitucab.R.id.puntaje;

/**
 * Created by Raul A on 5/13/2017.
 */

public class UserAuxiliar {

    private int _id;
    private String _username;
    private int _point;
    private int _type;
    private String _email;
    private String _phone;
    private String _sex;



    public UserAuxiliar(String nombre, String email, String phone){
        _username = nombre;
        _email = email;
        _phone = phone;
    }

    public UserAuxiliar(String nombre, String email, String phone, String sex){
        _username = nombre;
        _email = email;
        _phone = phone;
        _sex = sex;
    }

    public UserAuxiliar(String nombre, String email, String phone, int type){
        _username = nombre;
        _email = email;
        _phone = phone;
        _type = type;
    }

    public UserAuxiliar(int id, String nombre, int puntaje, int type){
        _id = id;
        _username = nombre;
        _point = puntaje;
        _type = type;
    }

    public UserAuxiliar(int id, String nombre){
        _id = id;
        _username = nombre;
    }

    public String get_username()
    {
        return _username;
    }

    public int get_id()
    {
        return _id;
    }

    public void set_id(int id)
    {
        this._id = id;
    }

    public void set_username(String _username)
    {
        this._username = _username;
    }

    public int get_point()
    {
        return _point;
    }

    public void set_point(int _point)
    {
        this._point = _point;
    }

    public int get_type()
    {
        return _type;
    }

    public void set_type(int _type)
    {
        this._type = _type;
    }
}
