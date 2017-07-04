package edu.ucab.desarrollo.fitucab.Test.M07_Test;

import edu.ucab.desarrollo.fitucab.common.Registry;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M07.DaoPlanification;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DaoPlanificationCreateTest extends Configuracion {


    private final int QUERY_OK = 200;
    private final int NOT_FOUND = 404;
    private static Connection conn;
    private Entity planification;
    private Entity comparacion;


    @BeforeClass
    public static void setUpDB() throws SQLException {
      conn = bdConnect();
      setUpPerson(conn);
      setUpSport(conn);
      conn.close();
      conn = null;
      Registry.BD_URL= Configuracion.BD_URL;
    }

    @AfterClass
    public static void tearDownDB() throws SQLException{
        conn = bdConnect();
        tearDownPerson(conn);
        tearDownSport(conn);
        conn.close();
        conn = null;
    }

    @Before
    public void setUp(){
        conn = bdConnect();
        planification = new Planification(LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-02"),
                LocalTime.parse("08:00:00"), LocalTime.parse("00:30:00"), new boolean[7], 1, 1);
    }

    @After
    public void tearDown() throws SQLException {
        conn.close();
        conn = null;
        planification = null;
        Registry.BD_CLASS_FOR_NAME= Configuracion.BD_CLASS_FOR_NAME;
    }


    @Test
    public void createPlanification() throws Exception {
        DaoPlanification dao = new DaoPlanification();
        planification = dao.create(planification);
        assertEquals(planification.get_errorCode(),QUERY_OK);
        assertEquals(planification.get_errorMsg(),"Data insertada exitosamente");
    }

    @Test
    public void createPlanificationConExcepcion() throws Exception {


        DaoPlanification dao = new DaoPlanification();
        Registry.BD_CLASS_FOR_NAME= Configuracion.BD_CLASS_FOR_NAME_FALSA;
        planification = dao.create(planification);
        assertEquals(planification.get_errorCode(),551);

    }


}
