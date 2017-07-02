package edu.ucab.desarrollo.fitucab.Test.M01_Test;

import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import org.junit.*;

import java.sql.Connection;



public class DaoUserTest {
    static Connection conn;
    static Dao _dao;

    //Strings de conexion para las consultas;
    static String _sqlInsert="http://localhost:8080/" +
            "fitucab_war_exploded/M01_ServicesUser1/" +
            "insertRegistry?username=elberg&password=1234&" +
            "email=prueba@gmail.com&sex=f&phone=04122723838&" +
            "birthdate=2013-02-14&weight=68&height=165";

    static String _sqlRead="http://localhost:8080/" +
            "fitucab_war_exploded/M01_ServicesUser1/" +
            "login_user?username=elberg&password=1234";

    @Ignore
    protected void setUp(){

    }


    @Before
    private static void testOnLine(){
        try {
            conn = _dao.getConInstance();
        }
        catch (Exception e){

        }
    }


    @After
    private static void testOffLine(){
        try {
            conn.close();
        }
        catch (Exception e){

        }
    }



    @Test
    @Ignore
    public void testCreateUser(){

    }


    @Test
    public void testReadUser(){

    }
}
