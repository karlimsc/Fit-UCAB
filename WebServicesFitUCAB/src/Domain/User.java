package Domain;


public class User {

    private int _id;
    private String  _user;
    private String  _password;
    private String _email;
    private String _sex;
    private String _phone;

    public User(){};

    public User(int id,String user,String password, String email,String sex,String phone)
    {
        _id = id;
        _user = user;
        _password = password;
        _email = email;
        _sex = sex;
        _phone = phone;

    };
    public User(String user,String password, String email,String sex,String phone)
    {

        _user = user;
        _password = password;
        _email = email;
        _sex = sex;
        _phone = phone;

    };
    public User(String user,String password)
    {
        _user = user;
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



}
