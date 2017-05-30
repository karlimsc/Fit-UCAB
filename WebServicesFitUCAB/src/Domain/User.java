package Domain;


import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

/**
 * Clase Usuario del Modulo 1
 */

@XmlRootElement
public class User {

    private int _id;
    private String _username;
    private String  _password;
    private String _email;
    private String _sex;
    private String _phone;
    private Date _birthdate;
    private String _status;
    private Registry _registry;

    private int _weight; // Se usan unicamente para uso del perfil de usuario
    private int _height; // Se usan unicamente para uso del perfil de usuario


    /**
     * Constructor vacio
     */
    public User(){};

    /**
     * Constructor para crear el usuario solo con el id
     * @param id userId
     */
    public User(int id){
        _id=id;
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
        _id = id;
        _username = user;
        _password = password;
        _email = email;
        _sex = sex;
        _phone = phone;
        _birthdate = birthdate;

    };

    public User(int id,String user,String password, String email,String sex,String phone,
                Registry registry)
    {
        _id = id;
        _username = user;
        _password = password;
        _email = email;
        _sex = sex;
        _phone = phone;
        //_birthdate= birthdate;
        _registry= registry;

    };

    /***
     *
     * @param id
     * @param user
     * @param password
     */
    public User(int id,String user,String password)
    {
        _id = id;
        _username = user;
        _password = password;


    };

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


    public int getId()
    {
        return _id;
    }

    public void setId(int id)
    {
        this._id = id;
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

    public void set_status(String _status) {
        this._status = _status;
    }


    //ESTO LO HIZO EL MODULO DE PERFIL
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
    //FIN DE ESTO LO HIZO EL MODULO DE PERFIL



}
