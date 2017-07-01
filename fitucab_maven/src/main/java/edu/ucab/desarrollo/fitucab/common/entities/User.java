package edu.ucab.desarrollo.fitucab.common.entities;


import java.sql.Date;

/**
 * Clase de Usuario
 */
public class User extends Entity {


    private String _username;
    private String _password;
    private String _email;
    private String _sex;
    private String _phone;
    private Date _birthdate;
    private int _weight; // Se usan unicamente para uso del perfil de usuario
    private int _height; // Se usan unicamente para uso del perfil de usuario

    /**
     * Constructor vacio
     */
    public User() {
    }

    ;

    /**
     * Constructor para crear el usuario solo con el id
     *
     * @param id userId
     */
    public User(int id) {
        super(id);
    }

    /**
     * Constructor con todos los atributos
     *
     * @param id
     * @param user
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @param birthdate
     */
    public User(int id, String user, String password, String email, String sex, String phone, Date birthdate) {
        super(id);
        _username = user;
        _password = password;
        _email = email;
        _sex = sex;
        _phone = phone;
        _birthdate = birthdate;

    }

    ;

    /**
     * Constructor
     *
     * @param _username
     * @param _password
     * @param _email
     * @param _sex
     * @param _phone
     * @param _birthdate
     * @param _weight
     * @param _height
     */
    public User(String _username, String _password, String _email, String _sex, String _phone, Date _birthdate, int _weight, int _height) {
        this._username = _username;
        this._password = _password;
        this._email = _email;
        this._sex = _sex;
        this._phone = _phone;
        this._birthdate = _birthdate;
        this._weight = _weight;
        this._height = _height;
    }

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

    /***
     *
     * @param id
     * @param user
     * @param password
     */
    public User(int id, String user, String password) {
        super(id);
        _username = user;
        _password = password;


    }

    ;

    /**
     * Constructor sin el id del usuario
     *
     * @param user
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @param birthdate
     */
    public User(String user, String password, String email, String sex, String phone, Date birthdate) {

        _username = user;
        _password = password;
        _email = email;
        _sex = sex;
        _phone = phone;
        _birthdate = birthdate;
    }

    ;


    /**
     * Constructor para solo el username y el password
     *
     * @param user
     * @param password
     */
    public User(String user, String password) {
        _username = user;
        _password = password;
    }

    /**
     * Contructor para el username, password y el email
     *
     * @param user
     * @param password
     * @param _email
     */
    public User(String user, String password, String _email) {
        _username = user;
        _password = password;
        _email = _email;
    }

    /**
     * Constructor para solo el email
     *
     * @param email
     */
    public User(String email) {
        _email = email;
    }

    /**
     * Metodo que trae el nombre del usuario
     *
     * @return
     */
    public String getUser() {
        return _username;
    }

    /**
     * Metodo que asigna el valor del nombre del usuario
     *
     * @param user
     */
    public void setUser(String user) {
        this._username = user;
    }

    /**
     * Metodo que te trae un valor string password
     *
     * @return
     */
    public String getPassword() {
        return _password;
    }

    /**
     * Metodo que asigna un valor string password
     *
     * @param password
     */
    public void setPassword(String password) {
        this._password = password;
    }

    /**
     * Metodo trae un valor string email
     *
     * @return
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Metodo que asigna un valor tipo string de email
     * @param email
     */
    public void setEmail(String email)
    {
        this._email = email;
    }

    /**
     * Metodo que trae un valor string sex
     * @return
     */
    public String getSex()
    {
        return _sex;
    }

    /**
     * Metodo que asigna un valor string sex
     * @param sex
     */
    public void setSex(String sex)
    {
        this._sex = sex;
    }

    /**
     * Metodo que trae un valor string phone
     * @return
     */
    public String getPhone()
    {
        return _phone;
    }

    /**
     * Metodo que asigna un valor string phone
     * @param phone
     */
    public void setPhone(String phone)
    {
        this._phone=phone;
    }

    /**
     * Metodo que trae un valor tipo date birthdate
     * @return
     */
    public Date getBirthdate() {return _birthdate;}

    /**
     * Metodo que asigna un valor tipo date birthdate
     * @param _birthdate
     */
    public void setBirthdate(Date _birthdate) { this._birthdate = _birthdate;}

    /**
     * Metodo que trae un valor int weight
     * @return
     */
    public int getWeight() {
        return _weight;
    }

    /**
     * Metodo que asigna un valor int weight
     * @param peso
     */
    public void setWeight(int peso) {
        _weight = peso;
    }

    /**
     * Metodo que trae un valor int height
     * @return
     */
    public int getHeight() {
        return _height;
    }

    /**
     * Metodo que asigna un valor int height
     * @param altura
     */
    public void setHeight(int altura) {
        _height = altura;
    }

}
