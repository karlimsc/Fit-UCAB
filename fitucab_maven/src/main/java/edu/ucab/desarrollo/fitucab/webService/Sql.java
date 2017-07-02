package edu.ucab.desarrollo.fitucab.webService;




import edu.ucab.desarrollo.fitucab.common.Registry;

import java.sql.*;

public class Sql {

    //TODO: ESTA CLASE NO SE DEBE USAR.. PENDIENTE

    private static Connection conInstance;
    private Connection _conn =bdConnect();
    private Statement _st;
    private ResultSet _rs;

    /**
     * Metodo para devolver una unica instancia de la conexion
     * @return instancia de la conexion
     */
    public static Connection getConInstance(){

        conInstance = bdConnect();

        return conInstance;
    }



    /**
     * Metodo que realiza la conexion con la base de datos
     * @return Conexion hecha a la base de datos
     * @throws ClassNotFoundException Si la clase no es encontrada
     * @throws SQLException Problemas con sql
     * @throws Exception
     * @see Connection
     * @see Statement
     */
    private static Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName(Registry.BD_CLASS_FOR_NAME);
            conn = DriverManager.getConnection(Registry.BD_URL,Registry.BD_USER, Registry.BD_PASSWORD);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * Metodo que realiza un query a la base de datos con devolucion
     * Realizar preferiblemente antes de bdConnect
     * @param query
     * @return Tabla que representa la consulta del query
     * @throws SQLException Error en SQL
     * @throws Exception
     * @see ResultSet
     */
    public ResultSet sql (String query) throws SQLException , NullPointerException {

        try {
            _st = _conn.createStatement();
            _rs  = _st.executeQuery( query );

        }
        catch ( NullPointerException e ){
            e.printStackTrace();
            System.err.println("NullPointerExceptionSql: " + e.getMessage() + " , Query: " + query);
            return null;
        }

        finally {
            _conn.close();
            return _rs;
        }
    }

    /**
     * Metodo que realiza un query a la base de datos sin devolucion
     * Realizar preferiblemente antes de bdConnect
     * @param query
     * @return boolean
     * @throws SQLException Error en SQL
     * @throws Exception
     * @see boolean
     */
    public boolean sqlNoReturn ( String query ) throws SQLException {
        boolean salida = false;
        try {
            _st = _conn.createStatement();
            salida  = _st.execute( query );

        }
        catch ( NullPointerException e ){
            e.printStackTrace();
            return false;
        }
        finally {

            _conn.close();
            return salida;
        }

    }

    /**
     * Metodo que se conecta a la base de datos sin cerrar la conexion
     * @param query Consulta a la base de datos
     * @return Tabla que representa la consulta del query
     * @throws SQLException
     */
    public ResultSet sqlConn ( String query ) throws SQLException {

        try {
            _st = _conn.createStatement();
            _rs  = _st.executeQuery( query );
        }
        catch ( NullPointerException e ){
            e.printStackTrace();
            System.err.println("NullPointerExceptionSql: " + e.getMessage());
        }
        catch ( Exception e ){
            System.err.println("ExceptionSql: " + e.getMessage() + " , Query: " + query);
            e.printStackTrace();
        }
        finally {
            return _rs;
        }

    }

    /**
     * Metodo para cerrar la conexion
     * @param conn conexion activa
     * @throws SQLException Error al cerrar la conexion
     */
    public void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    public Connection getConn() {
        return _conn;
    }


    /**
     * metodo estatico para cerrar
     * la conexion a la base de datos
     * @param conn
     */

    public static void bdClose(Connection conn) {
        try{
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}