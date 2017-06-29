package edu.ucab.desarrollo.fitucab.dataAccessLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.webService.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Raul A on 6/28/2017.
 */
public class DaoWater extends Dao implements IDaoWater{

    public DaoWater( Entity entidad )
    {

    }

    /**
     * etodo que agrega el vaso de agua y lo retorna
     * @param water
     * @return retorna el vaso de agua agregado
     * @throws SQLException
     */
    public Entity addWater(Entity water) throws SQLException {

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
            _water = addWaterResult(rs);
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
     * Metodo que devuelve el historial de vasos de agua
     * @param water
     * @return retorna la lista de vasos de agua
     */
    public ArrayList<Water> getList(Entity water) {
        ArrayList<Water> waterList = new ArrayList<Water>();
        ResultSet rs;

        //variables de entrada
        String dia = "";
        int fkp = 0;
        //fin variables de entrada

        try {
            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            rs = queryExecute("Select * from M10_GetListFecha("+fkp+" ,'"+dia+"')");
            waterList = getWaterList(rs);
        } catch (Exception e) {
            if (e instanceof SQLException) {
                Water _water = new Water("Error en la conexion a base de datos");
                waterList.add(_water);
            }
        } finally {
            return waterList;
        }
    }

    /**
     * Metodo que devuelve un agua
     * @param entity
     * @return Retorna un agua
     */
    public Water getWater(Entity entity){
        Water water = new Water();
        ResultSet rs;

        //variables de entrada
        String dia = "";
        int fkp = 0;
        //fin variables de entrada

        try {
            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            rs = queryExecute("Select * from M10_GetWaterGlass("+fkp+" ,'"+dia+"')");
            water = getWaterItem(rs);
        } catch (Exception e) {
            if (e instanceof SQLException)
                water.set_error("Error en la conexion a base de datos");
        } finally {
            return water;
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


    /**
     * Metodo que agrega el historico de vasos de agua a una lista
     * @param rs
     * @return Retorna la lista con los vasos de agua
     * @throws SQLException
     */
    public ArrayList<Water> getWaterList(ResultSet rs) throws SQLException {
        Water water = null;
        ArrayList<Water> arrayWater = new ArrayList();
        SimpleDateFormat _sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        while(rs.next())
        {
            //se agarran los valores de la consulta y se crea un objeto tipo water
            water = new Water(_sdf.format(rs.getTimestamp("GLASSTIME")).toString()
                ,rs.getInt("GLASSTYPE"));
            // se guardan los datos en un arraylist de tipo water
            arrayWater.add(water);
        } //end while que recorre la consulta
        return arrayWater;
    }

    /**
     * Metodo que retorna un agua
     * @param rs
     * @return Retorna un agua
     * @throws SQLException
     */
    public Water getWaterItem(ResultSet rs) throws SQLException {
        Water water = new Water();
        while(rs.next())
        {
            //se agarran los valores de la consulta y se crea un objeto tipo water
            water = new Water(rs.getInt("sumG")
                    ,rs.getInt("countg"));
            // se guardan los datos en un arraylist de tipo water
        } //end while que recorre la consulta
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
