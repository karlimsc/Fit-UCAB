package edu.ucab.desarrollo.fitucab.Test.M01_Test;

import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
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
@RunWith(Arquillian.class)
public class DaoUsersTest {
    static Connection conn;
    static Dao _dao;
    static User _user;




    @Before
    public void setUp() throws Exception {
        _user = new User(60,"naomi","123",
                "@gmail","f","00000",
                Date.valueOf("12-12-12"),
                12,12);

    }

    @BeforeClass
    private static void testOnLine(){
        try {
            conn = _dao.getConInstance();
        }
        catch (Exception e){

        }
    }


    @AfterClass
    private static void testOffLine(){
        try {
            conn.close();
        }
        catch (Exception e){

        }
    }

    @After
    @Ignore
    public void tearDown() throws Exception {
    }

    @Test
    @Ignore
    public void login() throws Exception {
    }

    @Test
    public void create() throws Exception {

        CallableStatement cstmt =conn.prepareCall("{?=call M01_REGISTRAR(?,?,?,?,?,?,?,?)}");
        //Parametro de salida
        cstmt.registerOutParameter(1, Types.INTEGER);

        //Parametros de entrada
        cstmt.setString(2, _user.getUser());
        cstmt.setString(3, _user.getUser());
        cstmt.setString(4, _user.getEmail());
        cstmt.setString(5, _user.getSex());
        cstmt.setString(6, _user.getPhone());
        cstmt.setDate(7, _user.getBirthdate());
        cstmt.setInt(8, _user.getWeight());
        cstmt.setInt(9, _user.getHeight());
        cstmt.execute();

        CallableStatement cs = conn.prepareCall("{?=call M01_INICIARSESION(?,?)}");

        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, "naomi");
        cs.setString(3, "123");
        cs.execute();

        int id = cs.getInt(1);

        assertEquals(60,id);
    }

    @Test
    @Ignore
    public void testEmail() throws Exception {
    }

    @Deployment
    @Ignore
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DaoUser.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
