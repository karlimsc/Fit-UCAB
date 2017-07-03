package edu.ucab.desarrollo.fitucab.dataAccessLayer.M03;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import org.slf4j.LoggerFactory;
import edu.ucab.desarrollo.fitucab.webService.Sql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que maneja la informacion de la cercania
 * @author Daniel Da Silva, Luis Martinez, Anderson Gomez
 * @version 2.0
 */
public class DaoNearMe extends Dao implements IDaoNearMe{

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(DaoNearMe.class);
    private Connection _conn;

    public DaoNearMe( Entity entidad )
    {

    }

    /**
     * constructor que inicia la conexión a BD
     *
     */
    public DaoNearMe()
    {
        try {
            //hacemos la conexión a BD
            _conn = Dao.getBdConnect();
        } catch (BdConnectException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
    }

    /**
     * Metodo que establece la ubicacion de un usuario
     * @param id es la entidad
     * @param longitud es la longitud en la que se encuentra
     * @param latitud es la latitud en la que se encuentra
     * @return retorna la amistad ya agregada
     * @throws SQLException
     */
    public void setLocation(String id, String longitud, String latitud){
        String query="";
        String queryVerificar="Select * from public.geo where (fk_personid="+id+");";
        ResultSet verificar = null;
        Sql baseSetLocationVerify = new Sql();
        try {
            verificar = baseSetLocationVerify.sql(queryVerificar);

            if (verificar.isBeforeFirst()) {

                //Ya existe el GEO, UPDATE
                query = "UPDATE public.geo SET geolongitud = '"+longitud+"', geolatitud = '"+latitud+"' WHERE fk_personid = "+id+";";

            }else {
                //No existe el geo, Insert
                query = "INSERT INTO public.geo (fk_personid,geolongitud,geolatitud) VALUES ("+id+","+longitud+","+latitud+")";


            }
        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }


        Sql baseSetLocation = new Sql();
        Boolean rs = null;
        try {
            rs = baseSetLocation.sqlNoReturn(query);

        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }

    }

    @Override
    public Entity create(Entity e) throws SQLException {
        return null;
    }

    @Override
    public Entity read(Entity e) throws CreateHomeException, SQLException, BdConnectException {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }
}
