package Domain;

import java.sql.*;

public class Sql {

    private static Connection conInstance;
    private Connection conn =bdConnect();
    private Statement _st;
    private ResultSet _rs;
    private static String BD_USER = "postgres";
    private static String BD_PASSWORD = "root";
    private static String BD_URL = "jdbc:postgresql://localhost/FitUcabDB";
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
     * Metodo que realiza la conexion contra la bd
     * @return
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

    public ResultSet sql (String query) throws SQLException , NullPointerException {

        _st = conn.createStatement();
        _rs  = _st.executeQuery(query);
        conn.close();
        return _rs;
    }

}
