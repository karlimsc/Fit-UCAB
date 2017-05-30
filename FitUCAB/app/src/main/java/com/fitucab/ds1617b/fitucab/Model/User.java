package com.fitucab.ds1617b.fitucab.Model;

import com.fitucab.ds1617b.fitucab.Model.Registry;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Colmenares on 26/03/2017.
 */



public class User {

    @SerializedName("_id")
    private int _idUser;
    @SerializedName("_username")
    private String _username;
    @SerializedName("_password")
    private String _password;
    @SerializedName("_email")
    private String _email;
    @SerializedName("_phone")
    private String _phone;
    @SerializedName("_sex")
    private char _sex;
    @SerializedName("_birthdate")
    private String _birthdate;
    @SerializedName("_height")
    private float _height;
    @SerializedName("_weight")
    private float _weight;
    @SerializedName("_status")
    private String _status;
    @SerializedName("_registry")
    private Registry _registry;

    private int _point;



    /**
     * Constructor vacio
     */
    public User(){ }

    public User(int idUser){ }

    public User(int idUser, String username, String password){
        _idUser=idUser;
        _username=username;
        _password=password;
    }

    public User(String username, String password){
        _username=username;
        _password=password;
    }

    public User(int idUser, String username, String password,String email){
        _idUser=idUser;
        _username=username;
        _password=password;
        _email=email;
    }

    public User(int idUser, String email){
        _idUser=idUser;
        _email=email;
    }

    public User(String email){
        _email=email;
    }

    public User(int idUser, String username, String password,String email, String phone,
                char sex, String birthdate){
        _idUser=idUser;
        _username=username;
        _password=password;
        _email=email;
        _phone=phone;
        _sex=sex;
        _birthdate=birthdate;
    }

    public User(int idUser, String username, String password,String email, String phone,
                char sex, String birthdate, float height, float weight){
        _idUser=idUser;
        _username=username;
        _password=password;
        _email=email;
        _phone=phone;
        _sex=sex;
        _birthdate=birthdate;
        _height=height;
        _weight=weight;
    }

    public User(int idUser, String username, String password,String email, String phone,
                char sex, String birthdate, float height, float weight, int point){
        _idUser=idUser;
        _username=username;
        _password=password;
        _email=email;
        _phone=phone;
        _sex=sex;
        _birthdate=birthdate;
        _height=height;
        _weight=weight;
        _point=point;

    }

    public User(int idUser, String username, String password,String email, String phone,
                char sex, String birthdate, Registry registry){
        _idUser=idUser;
        _username=username;
        _password=password;
        _email=email;
        _phone=phone;
        _sex=sex;
        _birthdate=birthdate;
       _registry=registry;

    }
    public User(int _idUser, String _username, String _email, String _phone, char _sex, String _birthdate, float _height, float _weight) {
        this._idUser = _idUser;
        this._username = _username;
        this._email = _email;
        this._phone = _phone;
        this._sex = _sex;
        this._birthdate = _birthdate;
        this._height = _height;
        this._weight = _weight;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public int get_idUser() {
        return _idUser;
    }


    public void set_idUser(int _idUser) {
        this._idUser = _idUser;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
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

    public char get_sex() {
        return _sex;
    }

    public void set_sex(char _sex) {
        this._sex = _sex;
    }

    public String get_birthdate() {
        return _birthdate;
    }

    public void set_birthdate(String _birthdate) {
        this._birthdate = _birthdate;
    }

    public float get_height() {
        return _height;
    }

    public void set_height(float _height) {
        this._height = _height;
    }

    public float get_weight() {
        return _weight;
    }

    public void set_weight(float _weight) {
        this._weight = _weight;
    }

    public int get_point() {
        return _point;
    }

    public void set_point(int _point) {
        this._point = _point;
    }

    public Registry get_registry() {
        return _registry;
    }

    public void set_registry(Registry _registry) {
        this._registry = _registry;
    }

}