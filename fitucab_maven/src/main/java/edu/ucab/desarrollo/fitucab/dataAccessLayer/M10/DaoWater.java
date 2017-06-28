package edu.ucab.desarrollo.fitucab.dataAccessLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.webService.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Raul A on 6/28/2017.
 */
public class DaoWater extends Dao implements IDaoWater{

    public DaoWater( Entity entidad )
    {

    }

    /**
     * Metodo que agrega el vaso de agua y lo retorna
     * @param water
     * @return retorna el vaso de agua agregado
    **/
    public Water addWater(Entity water) throws SQLException {

        Water _water = null;
        SimpleDateFormat _sdf3 = new SimpleDateFormat("hh:mm:ss");
        Date fecha = new Date();
        String hora = _sdf3.format(fecha);
        ResultSet rs;
        //variables de entrada
            String dia = "";
            int glassType = 0;
            int fkp = 0;
        //fin variables de entrada
        try {
            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
             rs = queryExecute("Select res from m10_addwater('"+dia+"     "+hora+"',"+glassType+","+fkp+")");
            //recorro la consulta
            water = addWaterResult(rs);
            // end while que recorre la consulta

        } catch (Exception e) {
            if (e instanceof NullPointerException)
                _water.set_error("Error de apuntador nulo");
            else if (e instanceof SQLException)
                _water.set_error("Error en la conexion a base de datos");
        } finally {
            return _water;
        }
    }


    /**
     * Metodo que ejecuta el query a la base de datos
     * @param query
     * @return Resultado del query
     * @throws SQLException
     */
    public ResultSet queryExecute(String query) throws SQLException {
        Sql _sql = new Sql();
        //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
        return _sql.sql(query);
    }

    /**
     * Metodo que devuelve el vaso de agua ya agregado
     * @param rs
     * @return Retorna el vaso de agua agregado
     * @throws NullPointerException
     * @throws SQLException
     */
    public Water addWaterResult(ResultSet rs) throws NullPointerException, SQLException {
        Water water = null;
        SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm:ss");
        String hora = sdf3.format(new Date());
        while (rs.next()) {
            water = new Water();
            water.set_cantidad(rs.getInt("res"));
            water.set_time(hora);
        }// end while que recorre la consulta
        return water;
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
