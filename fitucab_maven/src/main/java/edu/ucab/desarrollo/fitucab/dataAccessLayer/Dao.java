package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.Registry;
import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Patron Data Access Object
 */
public abstract class Dao implements IDao
{
    private static Logger logger = LoggerFactory.getLogger( Dao.class );


    private static Connection conn = null;
    private static Statement _st;
    private static ResultSet _rs;
    private static Connection conInstance;
    /*private Connection _conn = getBdConnect();
    private Statement _st;
    private ResultSet _rs;

    /**
     * Metodo que realiza la conexion con la base de datos
     * @return Conexion hecha a la base de datos
     * @throws ClassNotFoundException Si la clase no es encontrada
     * @throws SQLException Problemas con sql
     * @throws Exception
     * @see Connection
     * @see Statement
     */
    protected static Connection getBdConnect() throws BdConnectException
    {

        try
        {
            Class.forName( Registry.BD_CLASS_FOR_NAME );
            conn = DriverManager.getConnection( Registry.BD_URL, Registry.BD_USER, Registry.BD_PASSWORD );
        }
        catch ( ClassNotFoundException e )
        {
            logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
            throw new BdConnectException( e );
        }
        catch ( SQLException e )
        {
            logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
            throw new BdConnectException( e );
        }
        return conn;
    }

    public Entity read(int id) {
        return null;
    }

    public Entity read(Entity user) {
        return null;
    }

    protected static ResultSet sql (String query) throws SQLException , NullPointerException {

        try {
            _st = conn.createStatement();
            _rs  = _st.executeQuery( query );

        }
        catch ( NullPointerException e ){
            e.printStackTrace();
            System.err.println("NullPointerExceptionSql: " + e.getMessage());
        }

        finally {
            closeConnection();
            return _rs;
        }
    }


    /**
     *
     */
    protected static void closeConnection()
    {
        try
        {
            conn.close();
        }
        catch ( SQLException e )
        {
            logger.error( "Metodo: {} {}", "getBdConnect", e.toString() );
        }
    }

}
