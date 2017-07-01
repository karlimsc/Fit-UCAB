package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.*;

import java.sql.*;
import java.util.Date;

/**
 * Created by root on 29/06/17.
 */
public class DaoUser extends Dao implements IDaoUser {

    int id;
    Entity _user;
    Date birthdate;

    public DaoUser(int id) {
        this.id = id;
    }

    @Override
    public Entity read(int id){
        try {
            Connection conn = getBdConnect();
            ResultSet result = sql("select * from m02_consultarperfilid("+ id +")");

            while(result.next()){
                String usuario = result.getString( "usuario" );
                String email = result.getString( "email" );
                String sex = result.getString( "sex" );
                String phone = result.getString( "phone" );
                Date birthdate = result.getDate( "birthdate" );
                int  height = result.getInt( "height" );
                int weight = result.getInt("weight");
                _user = EntityFactory.createUser( id ,usuario, email, sex, phone, birthdate, height, weight);
            }
            return _user;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date Fecha(int id) {

        try {
            Connection conn = getBdConnect();
            ResultSet result = sql("select * from m02_consultarperfilid(" + id + ")");
            while (result.next()) {
                birthdate = result.getDate("birthdate");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return birthdate;
    }


    public Entity create(Entity e) throws AddException {
        return null;
    }

    public Entity read(Entity e) {
        return null;
    }

    public Entity update(Entity e) {
        return null;
    }

}
