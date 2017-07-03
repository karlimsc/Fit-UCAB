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

import static org.junit.Assert.assertEquals;

/**
 * Created by jaorr on 03/07/17.
 */
public class DaoPlanificationUpdateTest extends Configuracion {

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
        planification = new Planification(1, LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-02"),
                LocalTime.parse("08:00:00"), LocalTime.parse("23:00:00"), new boolean[7], 1, 1);
    }

    @After
    public void tearDown() throws SQLException {
        conn.close();
        conn = null;
        planification = null;
        Registry.BD_CLASS_FOR_NAME= Configuracion.BD_CLASS_FOR_NAME;
    }


    @Test
    public void updatePlanification() throws Exception {
        DaoPlanification dao = new DaoPlanification();
        planification = dao.update(planification);
        assertEquals(planification.get_errorCode(),QUERY_OK);
        assertEquals(planification.get_errorMsg(),"Se actualizo el registro exitosamente");
    }

    @Test
    public void updatePlanificationNoEncontrado() throws Exception {

        DaoPlanification dao = new DaoPlanification();
        //Registry.BD_CLASS_FOR_NAME= Configuracion.BD_CLASS_FOR_NAME_FALSA;
        planification.set_id(5);
        planification = dao.update(planification);
       // assertEquals(planification.get_errorCode(),551);
        assertEquals(planification.get_errorCode(),NOT_FOUND);
        assertEquals(planification.get_errorMsg(),"No se encontro el registro que desea actualizar");

    }

    @Test
    public void updatePlanificationConExcepcion() throws Exception {

        DaoPlanification dao = new DaoPlanification();
        Registry.BD_CLASS_FOR_NAME= Configuracion.BD_CLASS_FOR_NAME_FALSA;
        planification = dao.update(planification);
        assertEquals(planification.get_errorCode(),551);
    }


}
