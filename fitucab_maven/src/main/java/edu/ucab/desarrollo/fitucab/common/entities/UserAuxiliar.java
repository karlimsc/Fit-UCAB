package edu.ucab.desarrollo.fitucab.common.entities;


public class UserAuxiliar extends Entity{

    private String _username;
    private int _point;
    private int _type;
    private String _email;
    private String _phone;
    private String _sex;
    private String _birthdate;
    private String _longitud;
    private String _latitud;
    private String _distancia;



    public UserAuxiliar() {
    }

    public UserAuxiliar(String nombre, String email, String phone){
        _username = nombre;
        _email = email;
        _phone = phone;
    }

    public UserAuxiliar(int _id, String _username, int _point, int _type, String _email, String _phone, String _sex) {
        super.set_id(_id);
        this._username = _username;
        this._point = _point;
        this._type = _type;
        this._email = _email;
        this._phone = _phone;
        this._sex = _sex;
    }

    public UserAuxiliar(int id, String nombre, int puntaje, int type){
        super.set_id(id);
        _username = nombre;
        _point = puntaje;
        _type = type;
    }

    public String get_username()
    {
        return _username;
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

    public String get_sex() {
        return _sex;
    }

    public void set_sex(String _sex) {
        this._sex = _sex;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_birthdate() { return _birthdate; }

    public void set_birthdate(String _birthdate) {
        this._birthdate = _birthdate;
    }

    public String get_longitud() {
        return _longitud;
    }

    public void set_longitud(String _longitud) {
        this._longitud = _longitud;
    }

    public String get_latitud() {
        return _latitud;
    }

    public void set_latitud(String _latitud) {
        this._latitud = _latitud;
    }

    public String get_distancia() { return _distancia; }

    public void set_distancia(String _distancia) { this._distancia = _distancia; }
}
