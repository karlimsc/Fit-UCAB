package edu.ucab.desarrollo.fitucab.dataAccessLayer.M10;import com.google.gson.internal.bind.SqlDateTypeAdapter;import edu.ucab.desarrollo.fitucab.common.entities.Entity;import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;import edu.ucab.desarrollo.fitucab.common.entities.Water;import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;import edu.ucab.desarrollo.fitucab.common.entities.Sql;import javafx.util.converter.TimeStringConverter;import org.slf4j.LoggerFactory;import java.sql.*;import java.text.SimpleDateFormat;import java.util.ArrayList;import java.util.Date;import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;public class DaoWater extends Dao implements IDaoWater{    final static org.slf4j.Logger logger = LoggerFactory.getLogger(DaoWater.class);    public DaoWater( Entity entidad )    {    }    /**     * metodo que agrega el vaso de agua y lo retorna     * @param water     * @return retorna el vaso de agua agregado     * @throws SQLException     */    public Entity create(Entity water) throws  SQLException{        logger.debug("Debug: Agregando agua->DAO");        Water _water = EntityFactory.createWater();        Water waterEntrada = (Water) water;        SimpleDateFormat _sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");        SimpleDateFormat _sdf2 = new SimpleDateFormat("dd/MM/yyyy");        SimpleDateFormat _sdf3 = new SimpleDateFormat("hh:mm:ss");        Date fecha = new Date();        String hora = _sdf3.format(fecha);        ResultSet rs;        //variables de entrada        String dia = _sdf2.format(fecha);        int glassType = waterEntrada.get_glasstype();        int fkp = waterEntrada.get_fkPerson();        //fin variables de entrada        try {            String sqlDia = dia+" "+hora;            java.sql.Timestamp tstp = new Timestamp(_sdf.parse(sqlDia).getTime());            Connection conn = Dao.getBdConnect();            CallableStatement stm = conn.prepareCall("{? = Call M10_AddWater(?,?) }");            // PreparedStatement stm = conn.prepareStatement(query);            stm.setTimestamp(1, tstp);            stm.setInt(2,glassType);            stm.setInt(3,fkp);            rs = stm.executeQuery();            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta            //rs = queryExecute("Select res from m10_addwater('"+dia+" "+hora+"',"+glassType+","+fkp+")");            //recorro la consulta            _water = addWaterResult(rs);            // end while que recorre la consulta        } catch (Exception e) {            if (e instanceof NullPointerException)                _water.set_error("Error de apuntador nulo");            else if (e instanceof SQLException)                _water.set_error("Error en la conexion a base de datos");            else                _water.set_error(e.getMessage());            MessageException error = new MessageException(e, this.getClass().getSimpleName(),                    Thread.currentThread().getStackTrace()[1].getMethodName());            logger.error("Error: ", error.toString());        } finally {            return _water;        }    }    /**     * Metodo que devuelve el historial de vasos de agua     * @param water     * @return retorna la lista de vasos de agua     */    public ArrayList<Water> getList(Entity water)  throws  SQLException {        logger.debug("Debug: Obteniendo lista->DAO");        SimpleDateFormat _sdf1 = new SimpleDateFormat("yyyy/MM/dd");        SimpleDateFormat _sdf2 = new SimpleDateFormat("dd/MM/yyyy");        ArrayList<Water> waterList = new ArrayList<Water>();        ResultSet rs;        Water waterEntrada = (Water) water;        //variables de entrada        String dia = waterEntrada.get_time();        int fkp = waterEntrada.get_fkPerson();        //fin variables de entrada        try {            String sqlDia = dia;            Date dateAux = _sdf2.parse(sqlDia);            sqlDia = _sdf1.format(dateAux);            dateAux = _sdf1.parse(sqlDia);            java.sql.Date tstp = new java.sql.Date(dateAux.getTime());            Connection conn = Dao.getBdConnect();            CallableStatement stm = conn.prepareCall("{? = Call M10_GetListFecha(?) }");            // PreparedStatement stm = conn.prepareStatement(query);            stm.setInt(1,fkp);            stm.setDate(2, tstp);            rs = stm.executeQuery();            //rs = queryExecute("Select * from M10_GetListFecha("+fkp+" ,'"+dia+"')");            waterList = getWaterList(rs);        } catch (Exception e) {            if (e instanceof SQLException) {                Water _water = EntityFactory.createWater();                _water.set_error("Error en la conexion a base de datos");                waterList.add(_water);            }            MessageException error = new MessageException(e, this.getClass().getSimpleName(),                    Thread.currentThread().getStackTrace()[1].getMethodName());            logger.error("Error: ", error.toString());        } finally {            return waterList;        }    }    /**     * Metodo que devuelve un agua     * @param water     * @return Retorna un agua     */    public Entity getWater(Entity water) throws SQLException{        logger.debug("Debug: Obteniendo agua->DAO");        SimpleDateFormat _sdf1 = new SimpleDateFormat("yyyy/MM/dd");        SimpleDateFormat _sdf2 = new SimpleDateFormat("dd/MM/yyyy");        Water _water = EntityFactory.createWater();        ResultSet rs;        Water waterEntrada = (Water) water;        //variables de entrada        String dia = waterEntrada.get_time();        int fkp = waterEntrada.get_fkPerson();        //fin variables de entrada        try {            String sqlDia = dia;            Date dateAux = _sdf2.parse(sqlDia);            sqlDia = _sdf1.format(dateAux);            dateAux = _sdf1.parse(sqlDia);            java.sql.Date tstp = new java.sql.Date(dateAux.getTime());            Connection conn = Dao.getBdConnect();            CallableStatement stm = conn.prepareCall("{? = Call M10_GetWaterGlass(?) }");            // PreparedStatement stm = conn.prepareStatement(query);            stm.setInt(1, fkp);            stm.setDate(2, tstp);            rs = stm.executeQuery();            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta            //rs = queryExecute("Select * from M10_GetWaterGlass("+fkp+" ,'"+dia+"')");            _water = getWaterItem(rs);        } catch (Exception e) {            if (e instanceof SQLException)                _water.set_error("Error en la conexion a base de datos");            MessageException error = new MessageException(e, this.getClass().getSimpleName(),                    Thread.currentThread().getStackTrace()[1].getMethodName());            logger.error("Error: ", error.toString());        } finally {            return _water;        }    }    /**     * Metodo que elimina la ultima agua     * @param water     * @return Retorna un agua     */    public Entity deleteLast(Entity water) throws SQLException{        logger.debug("Debug: Eliminando agua->DAO");        SimpleDateFormat _sdf1 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");        SimpleDateFormat _sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");        Water _water = EntityFactory.createWater();        ResultSet rs;        Water waterEntrada = (Water) water;        //variables de entrada        String dia = waterEntrada.get_time();        int fkp = waterEntrada.get_fkPerson();        //fin variables de entrada        try {            String sqlDia = dia +" 00:00:00";            Date dateAux = _sdf2.parse(sqlDia);            sqlDia = _sdf1.format(dateAux);            dateAux = _sdf1.parse(sqlDia);            java.sql.Timestamp tstp = new Timestamp(dateAux.getTime());            Connection conn = Dao.getBdConnect();            CallableStatement stm = conn.prepareCall("{? = Call M10_DeletWaterLast(?) }");            // PreparedStatement stm = conn.prepareStatement(query);            stm.setTimestamp(1, tstp);            stm.setInt(2, fkp);            rs = stm.executeQuery();            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta            //rs = queryExecute("Select * from M10_DeletWaterLast('"+dia+"' ,"+fkp+")");            _water = deletLastItem(rs);        } catch (Exception e) {            if (e instanceof SQLException)                _water.set_error("Error en la conexion a base de datos");            MessageException error = new MessageException(e, this.getClass().getSimpleName(),                    Thread.currentThread().getStackTrace()[1].getMethodName());            logger.error("Error: ", error.toString());        } finally {            return _water;        }    }    /**     * Metodo que ejecuta el query a la base de datos     * @param query     * @return Resultado del query     * @throws SQLException     */    public ResultSet queryExecute(String query) throws SQLException {        logger.debug("Debug: Ejecutando query->DAO");        Sql _sql = new Sql();        //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta        return _sql.sql(query);    }    /**     * Metodo que devuelve el vaso de agua ya agregado     * @param rs     * @return Retorna el vaso de agua agregado     * @throws NullPointerException     * @throws SQLException     */    public Water addWaterResult(ResultSet rs) throws NullPointerException, SQLException {        logger.debug("Debug: Agregando agua->ResultSetDAO");        Water water = EntityFactory.createWater();        SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm:ss");        String hora = sdf3.format(new Date());        while (rs.next()) {            water.set_cantidad(rs.getInt("res"));            water.set_time(hora);        }// end while que recorre la consulta        return water;    }    /**     * Metodo que agrega el historico de vasos de agua a una lista     * @param rs     * @return Retorna la lista con los vasos de agua     * @throws SQLException     */    public ArrayList<Water> getWaterList(ResultSet rs) throws SQLException {        logger.debug("Debug: Obteniendo lista->ResultSetDAO");        Water water;        ArrayList<Water> arrayWater = new ArrayList();        SimpleDateFormat _sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");        while(rs.next())        {            //se agarran los valores de la consulta y se crea un objeto tipo water            water = EntityFactory.createWater(_sdf.format(rs.getTimestamp("GLASSTIME")).toString()                ,rs.getInt("GLASSTYPE"));            // se guardan los datos en un arraylist de tipo water            arrayWater.add(water);        } //end while que recorre la consulta        return arrayWater;    }    /**     * Metodo que retorna un agua     * @param rs     * @return Retorna un agua     * @throws SQLException     */    public Water getWaterItem(ResultSet rs) throws SQLException {        logger.debug("Debug: Obteniendo agua->ResultSetDAO");        Water water = EntityFactory.createWater();        while(rs.next())        {            //se agarran los valores de la consulta y se crea un objeto tipo water            water = EntityFactory.createWater(rs.getInt("sumG")                    ,rs.getInt("countg"));            // se guardan los datos en un arraylist de tipo water        } //end while que recorre la consulta        return water;    }    public Water deletLastItem(ResultSet rs) throws SQLException {        logger.debug("Debug: Eliminando agua->ResultSetDAO");        Water water = EntityFactory.createWater();        while(rs.next())        {            //se agarran los valores de la consulta y se crea un objeto tipo water            water.set_cantidad(rs.getInt("res"));        }        return water;    }    public Entity read(Entity e) {        return null;    }    public Entity update(Entity e) {        return null;    }}