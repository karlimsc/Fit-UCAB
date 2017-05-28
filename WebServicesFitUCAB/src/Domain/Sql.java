package Domain;

import java.sql.*;

/**
 * Created by charbel on 25/05/2017.
 */
public class Sql {

    private Connection conn =bdConnect();
    private Statement _st;
    private ResultSet _rs;




    private Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/fitucabdb";
            conn = DriverManager.getConnection(url,"fitucab", "fitucab");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(2);
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
