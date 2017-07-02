package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cesareduardo on 02/07/2017.
 */
public class DaoUpdatePerfil extends Dao implements IDaoUpdatePerfil {
    int _id;
    String _username;
    String _phone;
    String _email;
    private Connection _conn;

    public DaoUpdatePerfil(int id, String username, String phone, String email) {
        this._id = id;
        this._username = username;
        this._phone = phone;
        this._email = email;
    }

    @Override
    public boolean read() {
        try {
            if (!_username.equals("")) {
                UpdateName(_username);
            }
            if (!_email.equals("")) {
                UpdateEmail(_email);
            }
            if (!_phone.equals("")) {
                UpdatePhone(_phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (BdConnectException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void UpdateName(String name) throws BdConnectException, SQLException {
      String updatename = name;
        try {
            _conn = Dao.getBdConnect();
            Statement st = _conn.createStatement();
            ResultSet _result = st.executeQuery("select m02_modperfilname(" + _id + ", '" + updatename + "')");

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (BdConnectException e) {
            e.printStackTrace();
        }

    }

    public void UpdateEmail(String email) {
        String updatemail = email;
        try {
            _conn = Dao.getBdConnect();
            Statement st = _conn.createStatement();
            ResultSet _result = st.executeQuery("select m02_modperfilmail(" + _id + ", '" + updatemail + "')");

            } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (BdConnectException e1) {
            e1.printStackTrace();
        }
    }

    public void UpdatePhone(String phone) {
        String updatephone = phone;
        try {
            _conn = Dao.getBdConnect();
            Statement st = _conn.createStatement();
            ResultSet _result = st.executeQuery("select m02_modperfilphone(" + _id + ", '" + updatephone + "')");

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (BdConnectException e1) {
            e1.printStackTrace();
        }
    }



    public Entity create(Entity e) throws AddException {
        return null;
    }

    @Override
    public Entity read(Entity e) throws CreateHomeException, SQLException, BdConnectException {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }


    @Override
    public void Create(Entity e) {

    }
}
