package edu.ucab.desarrollo.fitucab.Test.M07_Test;

import edu.ucab.desarrollo.fitucab.common.Registry;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M07.DaoPlanification;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by jaorr on 03/07/17.
 */
public class DaoPlanificationGetTest extends  Configuracion {

    private final int QUERY_OK = 200;
    private final int NOT_FOUND = 404;
    private static Connection conn;
    private Entity planification;
    private ArrayList<Planification> listPlanification;


    @BeforeClass
    public static void setUpDB() throws SQLException {
        conn = bdConnect();
        setUpPerson(conn);
        setUpSport(conn);
        setUpPlanification(conn);
        conn.close();
        conn = null;
        Registry.BD_URL= Configuracion.BD_URL;
    }

    @AfterClass
    public static void tearDownDB() throws SQLException{
        conn = bdConnect();
        tearDownPerson(conn);
        tearDownSport(conn);
        tearDownPlanification(conn);
        conn.close();
        conn = null;
    }

    @Before
    public void setUp(){
        conn = bdConnect();
        planification = new Planification(1);
        listPlanification = new ArrayList<>();
    }

    @After
    public void tearDown() throws SQLException {
       // conn.close();
        //conn = null;
        planification = null;
        Registry.BD_CLASS_FOR_NAME= Configuracion.BD_CLASS_FOR_NAME;
    }

    @Test
    public void getPlanificationById() throws  Exception {

        DaoPlanification dao = new DaoPlanification();
        listPlanification = dao.getPlanificationByUser(planification);
        assertEquals(listPlanification.get(1).get_errorCode(),QUERY_OK);
        assertEquals(listPlanification.get(1).get_errorMsg(),"Busqueda realizada exitosamente");
        assertEquals(listPlanification.size(), 2);

    }

    @Test
    public void getPlanificationNoEncontrado() throws Exception {

        DaoPlanification dao = new DaoPlanification();
        planification = new Planification(5);
        listPlanification =  dao.getPlanificationByUser(planification);
        assertEquals(listPlanification.get(0).get_errorCode(),NOT_FOUND);
        assertEquals(listPlanification.get(0).get_errorMsg(),"El usuario no tiene registros asociados");
        assertEquals(listPlanification.size(), 1);

    }

    @Test
    public void getPlanificationConExcepcion() throws Exception {

        DaoPlanification dao = new DaoPlanification();
        Registry.BD_CLASS_FOR_NAME= Configuracion.BD_CLASS_FOR_NAME_FALSA;
        listPlanification = dao.getPlanificationByUser(planification);
        assertEquals(listPlanification.get(0).get_errorCode(),551);
        assertEquals(listPlanification.size(), 1);
    }


}
