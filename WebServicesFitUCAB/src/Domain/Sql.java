package Domain;

import java.sql.*;

/**
 * Clase que maneja la conexion con la base de datos
 */
public class Sql {

    private Connection _conn;
    private Statement _st;
    private ResultSet _rs;

    /**
     * Constructor que inicializa la conexion con la BD
     */
    public Sql() {
        _conn = bdConnect();
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
    public Connection bdConnect(){

        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/fitucabdb";
            _conn = DriverManager.getConnection(url,"fitucab", "fitucab");
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
            return _conn;
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
            _st = _conn.createStatement();
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
            _conn.close();
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
            _st = _conn.createStatement();
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
        return _conn;
    }
}
