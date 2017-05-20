package Domain;


public class User {

    private int _id;
    private String _username;
    private String  _password;
    private String _email;
    private String _sex;
    private String _phone;

    public User(){};

    public User(int id,String user,String password, String email,String sex,String phone)
    {
        _id = id;
        _username = user;
        _password = password;
        _email = email;
        _sex = sex;
        _phone = phone;

    };
    public User(String user,String password, String email,String sex,String phone)
    {

        _username = user;
        _password = password;
        _email = email;
        _sex = sex;
        _phone = phone;

    };
    public User(String user,String password)
    {
        _username = user;
        _password = password;
    }
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



}
