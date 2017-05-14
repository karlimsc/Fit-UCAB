package Domain;

/**
 * Created by root on 14/05/17.
 */
public class User {

    private int _id;
    private String  _user;
    private String  _password;
    private String _email;
    private String _sex;
    private String _phone;
    private int _ingreso;

    public User(){};

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
        return _user;
    }
    public void setUser(String user)
    {
        this._user = user;
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

    public int getIngreso()
    {
        return _ingreso;
    }
    public void setIngreso(int ingreso)
    {
        this._ingreso = ingreso;
    };

}
