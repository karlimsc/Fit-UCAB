package Domain;

import java.sql.*;

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
            _st = conn.createStatement();
            _rs  = _st.executeQuery( query );

        }
        catch ( NullPointerException e ){
            e.printStackTrace();
            System.err.println("NullPointerExceptionSql: " + e.getMessage());
        }

        finally {
            conn.close();
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
            _st = conn.createStatement();
            salida  = _st.execute( query );

        }
        catch ( NullPointerException e ){
            e.printStackTrace();
        }
        finally {

            conn.close();
            return salida;
        }

    }

}