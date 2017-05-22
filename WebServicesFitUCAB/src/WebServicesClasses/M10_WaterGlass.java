package WebServicesClasses;

import Domain.User;
import Domain.Water;
import com.google.gson.Gson;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 *
 * Clase para el manejo de la hidratacion
 */
@Path("/M10_WaterGlass")
public class M10_WaterGlass
{

    private Gson _gson = new Gson();
    private Connection conn =bdConnect();
    private ArrayList<Water> _array = new ArrayList<>() ;
    private Water _water;
    private ResultSet _rs;
    private Statement _st;
    private Integer _res;

    public ResultSet  sql (String query)
    {

        try {
                 _st = conn.createStatement();
                 _rs  = _st.executeQuery(query);
                 conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return _rs;
    }


    /**
     * Metodo que es llamado a traves del web service para agregar un vaso a la base de dato
     * @param dia
     * @param glassType
     * @param fkp
     * @return la cantidad de vasos tomados  que tiene ese dia
     */
    @GET
    @Path("/addWater")
    @Produces("application/json")
    public String addWater(@QueryParam("time") String dia , @QueryParam("glasstype") int glassType
            , @QueryParam("fkp") int fkp)
    {
                
        try {
            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            ResultSet rs = sql("Select res from m10_addwater("+dia+","+glassType+","+fkp+")");

            //recorro la consulta
            while( rs.next() )
            {
                _water = new Water();
                _water.set_cantidad( rs.getInt("res") );

                _array.add( _water );
            }// end while que recorre la consulta
        } catch ( SQLException e) {
            e.printStackTrace();
            return _gson.toJson( e );
        }
        return  _gson.toJson( _array );
    }



    /**
     * Metodo que es llamado a traves del web service para consulta la
     * el historial de vasos de agua tomados
     * @param dia
     * @param fkp
     * @return array con fecha, hora y tamaño del vaso de agua
     */
    @GET
    @Path("/GetList")
    @Produces("application/json")
    public String GetListDate( @QueryParam("time") String dia , @QueryParam("fkp") int fkp)
    {

        try {

            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            _rs = sql("Select * from M10_GetListFecha("+fkp+" ,"+dia+")");

            while(_rs.next())
            {
                //se agarran los valores de la consulta y se crea un objeto tipo water
                _water = new Water( _rs.getTimestamp("GLASSTIME").toString()
                        ,_rs.getInt("GLASSTYPE"));

                // se guardan los datos en un arraylist de tipo water
                _array.add(_water);

            } //end while que recorre la consulta

        } catch (SQLException e) {
            e.printStackTrace();
            return _gson.toJson( e );
        }
        //se devuelve el arraylist
        return  _gson.toJson( _array );


    }

    /**
     * Metodo que es llamado a traves del web service para consulta la
     * cantidad de vasos tomados y cantidad de agua en  dia
     * @param dia
     * @param fkp
     * @return array con cantidad de agua total y cantidad de vasos
     */
    @GET
    @Path("/GetWater")
    @Produces("application/json")
    public String GetWater( @QueryParam("time") String dia , @QueryParam("fkp") int fkp)
    {

        try {

            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            _rs = sql("Select * from M10_GetWaterGlass("+fkp+" ,"+dia+")");

            while(_rs.next())
            {
                //se agarran los valores de la consulta y se crea un objeto tipo water
                _water = new Water(_rs.getInt("sumG")
                        ,_rs.getInt("countg"));

                // se guardan los datos en un arraylist de tipo water
                _array.add(_water);

            } //end while que recorre la consulta

        } catch (SQLException e) {
            e.printStackTrace();
            return _gson.toJson( e );
        }
        //se devuelve el arraylist
        return  _gson.toJson( _array );


    }


    @GET
    @Path("/GetFechaInt")
    @Produces("application/json")
    public String GetFechaInt( @QueryParam("time") String dia , @QueryParam("fkp") int fkp)
    {
        try {

            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            _rs = sql("Select * from M10_Fechainter ("+fkp+" ,"+dia+")");

            while(_rs.next())
            {
                //se agarran los valores de la consulta y se crea un objeto tipo water


                _water = new Water (_rs.getDate("glasstime").toString(),
                                    _rs.getInt("sumg"),_rs.getInt("count"));

                // se guardan los datos en un arraylist de tipo water
                _array.add(_water);

            } //end while que recorre la consulta

        } catch (SQLException e) {
            e.printStackTrace();
            return _gson.toJson( e );
        }
        //se devuelve el arraylist
        return  _gson.toJson( _array );

    }


    @GET
    @Path("/DeletLast")
    @Produces("application/json")
    public String DeletLast( @QueryParam("time") String dia , @QueryParam("fkp") int fkp)
    {

        try {

            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            _rs = sql("Select * from M10_DeletWaterLast("+dia+" ,"+fkp+")");

            while(_rs.next())
            {
                //se agarran los valores de la consulta y se crea un objeto tipo water
                _water.set_cantidad(_rs.getInt("res"));

                // se guardan los datos en un arraylist de tipo water
                _array.add(_water);

            } //end while que recorre la consulta

        } catch (SQLException e) {
            e.printStackTrace();
            return _gson.toJson( e );
        }
        //se devuelve el arraylist
        return  _gson.toJson( _array );


    }

    public Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/FitUcabDB";
            conn = DriverManager.getConnection(url,"postgres", "root");
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




}
