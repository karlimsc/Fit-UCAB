package edu.ucab.desarrollo.fitucab.common.entities;


import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
public class User extends Entity {

    @SerializedName("_username")
    private String _username;
    @SerializedName("_password")
    private String _password;
    @SerializedName("_email")
    private String _email;
    @SerializedName("_sex")
    private String _sex;
    @SerializedName("_phone")
    private String _phone;
    @SerializedName("_birthdate")
    private Date   _birthdate;
    @SerializedName("_weight")
    private int    _weight; // Se usan unicamente para uso del perfil de usuario
    @SerializedName("_height")
    private int    _height; // Se usan unicamente para uso del perfil de usuario
    @SerializedName("_status")
    private String _status;

    /**
     * Constructor vacio
     */
    public User(){};

    /**
     * Constructor para crear el usuario solo con el id
     * @param id userId
     */
    public User(int id){
        super.set_id(id);
    }

    /**
     * Constructor con todos los atributos
     * @param id
     * @param user
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @param birthdate
     */
    public User(int id,String user,String password, String email,String sex,String phone, Date birthdate)
    {
        super.set_id(id);
        _username = user;
        _password = password;
        _email = email;
        _sex = sex;
        _phone = phone;
        _birthdate = birthdate;

    };

    public User(int id,String _username, String _password, String _email, String _sex, String _phone, Date _birthdate,
                int _weight, int _height) {
        super.set_id(id);
        this._username = _username;
        this._password = _password;
        this._email = _email;
        this._sex = _sex;
        this._phone = _phone;
        this._birthdate = _birthdate;
        this._weight = _weight;
        this._height = _height;
    }

    /***
     *
     * @param id
     * @param user
     * @param password
     */
    public User(int id,String user,String password)
    {
        super.set_errorCode(id);
        _username = user;
        _password = password;



    };

    /**
     * Constructor
     *
     * @param id
     * @param _username
     * @param _email
     * @param _sex
     * @param _phone
     * @param _birthdate
     * @param _weight
     * @param _height
     */
    public User(int id, String _username, String _email, String _sex, String _phone, Date _birthdate, int _weight, int _height) {
        super(id);
        this._username = _username;
        this._email = _email;
        this._sex = _sex;
        this._phone = _phone;
        this._birthdate = _birthdate;
        this._weight = _weight;
        this._height = _height;
    }

    /**
     * Constructor sin el id del usuario
     * @param user
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @param birthdate
     */
    public User(String user, String password, String email, String sex, String phone, Date birthdate)
    {

        _username = user;
        _password = password;
        _email = email;
        _sex = sex;
        _phone = phone;
        _birthdate= birthdate;
    };



    /**
     * Constructor para solo el username y el password
     * @param user
     * @param password
     */
    public User(String user,String password)
    {
        _username = user;
        _password = password;
    }

    /**
     * Contructor para el username, password y el email
     * @param user
     * @param password
     * @param _email
     */
    public User(String user,String password, String _email)
    {
        _username = user;
        _password = password;
        _email = _email;
    }

    /**
     * Constructor para solo el email
     * @param email
     */
    public User(String email)
    {
        _email= email;
    }

    /**
     * Constructor para el status
     * @param _id
     * @param _status
     */

    public User(int _id, String _status) {
        super(_id);
        this._status = _status;
    }


    public int getId()
    {
        return super.get_id();
    }
    public void setId(int id)
    {
        super.set_id(id);
    }

    public String getUser()
    {
        return _username;
    }
    public void setUser(String user)
    {
        this._username = user;
    }

    public String getPassword()
    {
        return _password;
    }
    public void setPassword(String password)
    {
        this._password = password;
    }

    public String getEmail()
    {
        return _email;
    }
    public void setEmail(String email)
    {
        this._email = email;
    }

    public String getSex()
    {
        return _sex;
    }
    public void setSex(String sex)
    {
        this._sex = sex;
    }

    public String getPhone()
    {
        return _phone;
    }
    public void setPhone(String phone)
    {
        this._phone=phone;
    }

    public Date getBirthdate() {return _birthdate;}
    public void setBirthdate(Date _birthdate) { this._birthdate = _birthdate;}

    public int getWeight() {
        return _weight;
    }

    public void setWeight(int peso) {
        _weight = peso;
    }

    public int getHeight() {
        return _height;
    }

    public void setHeight(int altura) {
        _height = altura;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

}
