//package edu.ucab.desarrollo.fitucab.Test.M01_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.*;
import org.junit.runner.RunWith;

import java.net.URI;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Types;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

/**
 * Created by elberg on 02/07/17.
 */

public class DaoUsersTest {
    static Connection conn;
    static Dao _dao;
    static Entity _user;




    public void limpiar() throws Exception {
        try{

            CallableStatement cs = conn.prepareCall("{=call M01_ELIMINARUSER(?)}");
            cs.setString(1, "naomi");
            cs.execute();
            conn.close();

        }
        catch (Exception e){

        }
    }

    @Test
    @Ignore
    public void login() throws Exception {
    }

    @Test
    public void create() throws Exception {
        conn = _dao.getConInstance();
        _user = new User(60,"naomi","123",
                "@gmail","f","00000",
                Date.valueOf("2010-12-12"),
                12,12);

        DaoUser login = (DaoUser) DaoFactory.instanciateDaoUser(_user);
        login.login(_user);

        //Se trae el ultimo usuario registrado
        CallableStatement cstmt =conn.prepareCall("{?=call M01_LASTUSER()}");
        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.execute();

        int id_insert = cstmt.getInt(1);
        System.out.printf("ID registro = "+id_insert);

        //Busca el usuario que fue registrado
        CallableStatement cs = conn.prepareCall("{?=call M01_INICIARSESION(?,?)}");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, "naomi");
        cs.setString(3, "123");
        cs.execute();

        int id_login = cs.getInt(1);
        System.out.printf("ID Login = "+id_login);
        limpiar();
        assertEquals(id_insert,id_login);
    }

    @Test
    @Ignore
    public void testEmail() throws Exception {
    }

}
