package edu.ucab.desarrollo.fitucab.Test.M07_Test;

import edu.ucab.desarrollo.fitucab.common.Registry;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M07.DaoPlanification;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.MockSettings;
import org.mockito.MockingDetails;
import org.mockito.stubbing.Answer;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;


/**
 * Created by jaorr on 03/07/17.
 */
public class DaoPlanificationDeleteTest extends Configuracion {

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
        setUpPlanification(conn);
        planification = new Planification(1, LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-02"),
                LocalTime.parse("08:00:00"), LocalTime.parse("00:30:00"), new boolean[7], 1, 1);
    }

    @After
    public void tearDown() throws SQLException {
        Registry.BD_CLASS_FOR_NAME = Configuracion.BD_CLASS_FOR_NAME;
        tearDownPlanification(conn);
        conn.close();
        conn = null;
        planification = null;
    }


    @Test
    public void deletePlafication() throws  Exception {
        DaoPlanification dao = new DaoPlanification();
        planification = dao.delete(planification);
        assertEquals(planification.get_errorCode(),QUERY_OK);
        assertEquals(planification.get_errorMsg(),"Data eliminada exitosamente");
    }

    @Test
    public void deletePlaficationNoEncontrado() throws  Exception {
        DaoPlanification dao = new DaoPlanification();
        planification.set_id(5);
        planification = dao.delete(planification);
        assertEquals(planification.get_errorCode(),NOT_FOUND);
        assertEquals(planification.get_errorMsg(),"No se encontro el registro el registro a eliminar");
    }

    @Test
    public void deletePlaficationExcepcion() throws  Exception {
        DaoPlanification dao = new DaoPlanification();
        Registry.BD_CLASS_FOR_NAME= Configuracion.BD_CLASS_FOR_NAME_FALSA;
        planification = dao.delete(planification);
        assertEquals(planification.get_errorCode(),551);

    }

}
