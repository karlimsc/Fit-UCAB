package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.Registry;

import java.sql.*;

public abstract class Dao implements IDao

{

    private static Connection conInstance;
    private Connection _conn = getBdConnect();
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
    protected static Connection getBdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName(Registry.BD_CLASS_FOR_NAME);
            conn = DriverManager.getConnection(Registry.BD_URL, Registry.BD_USER, Registry.BD_PASSWORD);
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

}