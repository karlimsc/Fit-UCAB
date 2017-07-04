package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Food;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.getPersonalizedListCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.insertUnAlimentoCommand;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by charbel on 03/07/2017.
 */
public class M11_Test_DaoFoodTest {
    private Sql _sql = new Sql();
    private ResultSet _rs;



    @Test
    public void insertOnePersFood() throws Exception {

        Entity EntityFood = EntityFactory.getFoodall
                ("kibecrudo", "111", "123", Boolean.TRUE, 1);

        insertUnAlimentoCommand cmd = CommandsFactory.insertarAlimentoCmd(EntityFood);


        cmd.execute();
        Food respuesta = (Food) cmd.Respuesta;
        String valor = null;
        _rs = _sql.sql("select * from food where foodname = 'Pruebas'; ");

        //recorro la consulta
        while( _rs.next() )
        {

             valor= _rs.getString("foodname");


        }// end while que recorre la consulta
        assertEquals( valor,"kibecrudo");

        _sql.sql("delete from food where foodname = 'kibecrudo'; ");

    }

    @Test
    public void getPersonalizedLis() throws SQLException, NoSuchMethodException, ListByIdException, ListAllException, BdConnectException {


        Entity EntityFood = EntityFactory.getUsername("josea2r");
        getPersonalizedListCommand cmd = CommandsFactory.getPersoFoodCmd(EntityFood);


        cmd.execute();
        Food respuesta = (Food) cmd.Respuesta;
        ArrayList<Food> jsonArray = respuesta.jsonArray;
        assertEquals(jsonArray.get(0).get_foodName(), "Tomat1e");


    }


}
