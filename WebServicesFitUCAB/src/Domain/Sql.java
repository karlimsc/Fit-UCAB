package Domain;

import java.sql.*;

/**
 * Clase que maneja la conexion con la base de datos
 */
public class Sql {

    private static Connection conInstance;
    private Connection conn =bdConnect();
    private Statement _st;
    private ResultSet _rs;
    private static String BD_USER = "fitucab";
    private static String BD_PASSWORD = "fitucab";
    private static String BD_URL = "jdbc:postgresql://localhost/fitucabdb";
    private static String BD_CLASS_FOR_NAME = "org.postgresql.Driver";

    /**
     * Metodo para devolver una unica instancia de la conexion
     * @return instancia de la conexion
     */
    public static Connection getConInstance(){
        if (conInstance == null){
            conInstance = bdConnect();
        }
        return conInstance;
    }
    /**
    * Constructor que inicializa la conexion con la BD
     */

    public Sql() {
        conn = bdConnect();
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
            Class.forName(BD_CLASS_FOR_NAME);
            conn = DriverManager.getConnection(BD_URL,BD_USER, BD_PASSWORD);
        }
        catch ( ClassNotFoundException e )
        {
            e.printStackTrace();
        }
        catch ( SQLException e ){
            e.printStackTrace();
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        finally {
            return conn;
        }
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
    public ResultSet sql ( String query ) throws SQLException {

        try {
            _st = conn.createStatement();
            _rs  = _st.executeQuery( query );
        }
        catch ( NullPointerException e ){
            e.printStackTrace();
            System.err.println("NullPointerExceptionSql: " + e.getMessage());
            System.exit(1);
        }
        catch ( Exception e ){
            System.err.println("ExceptionSql: " + e.getMessage() + " , Query: " + query);
            e.printStackTrace();
            System.exit(1);
        }
        finally {
            conn.close();
            return _rs;
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
            _st = conn.createStatement();
            _rs  = _st.executeQuery( query );
        }
        catch ( NullPointerException e ){
            e.printStackTrace();
            System.err.println("NullPointerExceptionSql: " + e.getMessage());
            System.exit(1);
        }
        catch ( Exception e ){
            System.err.println("ExceptionSql: " + e.getMessage() + " , Query: " + query);
            e.printStackTrace();
            System.exit(1);
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
        return conn;
    }

}
