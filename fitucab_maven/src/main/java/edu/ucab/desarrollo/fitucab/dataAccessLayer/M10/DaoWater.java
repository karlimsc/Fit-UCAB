package edu.ucab.desarrollo.fitucab.dataAccessLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.webService.Sql;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DaoWater extends Dao implements IDaoWater{

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(DaoWater.class);

    public DaoWater( Entity entidad )
    {

    }

    /**
     * metodo que agrega el vaso de agua y lo retorna
     * @param water
     * @return retorna el vaso de agua agregado
     * @throws SQLException
     */

    public Entity create(Entity water) throws  SQLException{
        Water _water = EntityFactory.createWater();
        Water waterEntrada = (Water) water;

        SimpleDateFormat _sdf3 = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat _sdf2 = new SimpleDateFormat("MM/dd/yyyy");
        Date fecha = new Date();
        String hora = _sdf3.format(fecha);
        ResultSet rs;
        //variables de entrada
        String dia = _sdf2.format(fecha);
        int glassType = waterEntrada.get_glasstype();
        int fkp = waterEntrada.get_fkPerson();
        //fin variables de entrada
        try {
            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
             rs = queryExecute("Select res from m10_addwater('"+dia+" "+hora+"',"+glassType+","+fkp+")");
            //recorro la consulta
            _water = addWaterResult(rs);
            // end while que recorre la consulta

        } catch (Exception e) {

            if (e instanceof NullPointerException)
                _water.set_error("Error de apuntador nulo");
            else if (e instanceof SQLException)
                _water.set_error("Error en la conexion a base de datos");

            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } finally {
            return _water;
        }
    }

    /**
     * Metodo que devuelve el historial de vasos de agua
     * @param water
     * @return retorna la lista de vasos de agua
     */
    public ArrayList<Water> getList(Entity water)  throws  SQLException {

        ArrayList<Water> waterList = new ArrayList<Water>();
        ResultSet rs;
        Water waterEntrada = (Water) water;

        //variables de entrada
        String dia = waterEntrada.get_time();
        int fkp = waterEntrada.get_fkPerson();
        //fin variables de entrada

        try {
            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            rs = queryExecute("Select * from M10_GetListFecha("+fkp+" ,'"+dia+"')");
            waterList = getWaterList(rs);
        } catch (Exception e) {
            if (e instanceof SQLException) {
                Water _water = EntityFactory.createWater();
                _water.set_error("Error en la conexion a base de datos");
                waterList.add(_water);
            }
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } finally {
            return waterList;
        }
    }

    /**
     * Metodo que devuelve un agua
     * @param water
     * @return Retorna un agua
     */
    public Entity getWater(Entity water) throws SQLException{
        Water _water = EntityFactory.createWater();
        ResultSet rs;
        Water waterEntrada = (Water) water;
        //variables de entrada
        String dia = waterEntrada.get_time();
        int fkp = waterEntrada.get_fkPerson();
        //fin variables de entrada

        try {
            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            rs = queryExecute("Select * from M10_GetWaterGlass("+fkp+" ,'"+dia+"')");
            _water = getWaterItem(rs);
        } catch (Exception e) {
            if (e instanceof SQLException)
                _water.set_error("Error en la conexion a base de datos");

            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } finally {
            return _water;
        }

    }



    /**
     * Metodo que elimina la ultima agua
     * @param water
     * @return Retorna un agua
     */
    public Entity deleteLast(Entity water) throws SQLException{
        Water _water = EntityFactory.createWater();
        ResultSet rs;
        Water waterEntrada = (Water) water;
        //variables de entrada
        String dia = waterEntrada.get_time();
        int fkp = waterEntrada.get_fkPerson();
        //fin variables de entrada

        try {
            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            rs = queryExecute("Select * from M10_DeletWaterLast('"+dia+"' ,"+fkp+")");
            _water = deletLastItem(rs);
        } catch (Exception e) {
            if (e instanceof SQLException)
                _water.set_error("Error en la conexion a base de datos");
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
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
        Water water = EntityFactory.createWater();
        SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm:ss");
        String hora = sdf3.format(new Date());
        while (rs.next()) {

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
        Water water;
        ArrayList<Water> arrayWater = new ArrayList();
        SimpleDateFormat _sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        while(rs.next())
        {
            //se agarran los valores de la consulta y se crea un objeto tipo water
            water = EntityFactory.createWater(_sdf.format(rs.getTimestamp("GLASSTIME")).toString()
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
        Water water = EntityFactory.createWater();
        while(rs.next())
        {
            //se agarran los valores de la consulta y se crea un objeto tipo water
            water = EntityFactory.createWater(rs.getInt("sumG")
                    ,rs.getInt("countg"));
            // se guardan los datos en un arraylist de tipo water
        } //end while que recorre la consulta
        return water;
    }


    public Water deletLastItem(ResultSet rs) throws SQLException {
        Water water = EntityFactory.createWater();
        while(rs.next())
        {
            //se agarran los valores de la consulta y se crea un objeto tipo water
            water.set_cantidad(rs.getInt("res"));

        }
        return water;
    }


    public Entity read(Entity e) {
        return null;
    }

    public Entity update(Entity e) {
        return null;
    }
}
