package WebServicesClasses;


import Domain.Sql;
import Domain.Water;
import com.google.gson.Gson;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;
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
    private SimpleDateFormat _sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private SimpleDateFormat _sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat _sdf3 = new SimpleDateFormat("hh:mm:ss");
    private SimpleDateFormat _sdf1 = new SimpleDateFormat("dd/MM");
    private ArrayList<Water> _array = new ArrayList();
    private Water _water;
    private Sql _sql = new Sql();
    private ResultSet _rs;
    private String hora;
    Date fecha = new Date();
    private String aux;
    private Integer _res;


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
                hora = _sdf3.format(fecha);
        try {
            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
           _rs = _sql.sql("Select res from m10_addwater('"+dia+"     "+hora+"',"+glassType+","+fkp+")");

            //recorro la consulta
            while( _rs.next() )
            {
                _water = new Water();
                _water.set_cantidad( _rs.getInt("res"));
                _water.set_time(hora);

            }// end while que recorre la consulta

        } catch (SQLException e) {
            _water = new Water();
            return _gson.toJson(  _water);
        }
        catch (NullPointerException e)
        {

            return _gson.toJson(  _water );
        }
        catch (Exception e)
        {

            return _gson.toJson( _water );
        }


        return  _gson.toJson( _water );

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
            _rs = _sql.sql("Select * from M10_GetListFecha("+fkp+" ,'"+dia+"')");

            while(_rs.next())
            {
                //se agarran los valores de la consulta y se crea un objeto tipo water
                _water = new Water( _sdf.format(_rs.getTimestamp("GLASSTIME")).toString()
                        ,_rs.getInt("GLASSTYPE"));

                // se guardan los datos en un arraylist de tipo water
                _array.add(_water);

            } //end while que recorre la consulta

        } catch (SQLException e) {

            _water = new Water("sql");

            return _gson.toJson(  _array.add(_water) );
        }
        catch (NullPointerException e)
        {
            _water = new Water("null");
        }
        catch (Exception e)
        {

            return _gson.toJson(  _array.add(_water) );
        }
        //se devuelve el arraylist
        return  _gson.toJson( _array );


    }

    /**
     * Metodo que es llamado a traves del web service para consulta la
     * cantidad de vasos tomados y cantidad de agua en dia
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
            _rs = _sql.sql("Select * from M10_GetWaterGlass("+fkp+" ,'"+dia+"')");

            while(_rs.next())
            {
                //se agarran los valores de la consulta y se crea un objeto tipo water
                _water = new Water(_rs.getInt("sumG")
                        ,_rs.getInt("countg"));


                // se guardan los datos en un arraylist de tipo water


            } //end while que recorre la consulta

        } catch (SQLException e) {

            return _gson.toJson(  _water );
        }
        catch (NullPointerException e)
        {

            return _gson.toJson(  _water );
        }
        catch (Exception e)
        {

            return _gson.toJson(  _water );
        }
        //se devuelve el arraylist
        return  _gson.toJson( _water);


    }


    @GET
    @Path("/GetFechaInt")
    @Produces("application/json")
    public String GetFechaInt( @QueryParam("time") String dia , @QueryParam("fkp") int fkp)
    {
        try {

            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            _rs = _sql.sql("Select * from M10_Fechainter ("+fkp+" ,'"+dia+"')");

            while(_rs.next())
            {
                //se agarran los valores de la consulta y se crea un objeto tipo water


                _water = new Water (_sdf.format(_rs.getDate("GLASSTIME")).toString(),
                                    _rs.getInt("sumg"),_rs.getInt("count"));

                // se guardan los datos en un arraylist de tipo water
                _array.add(_water);

            } //end while que recorre la consulta

        } catch (SQLException e) {

            return _gson.toJson(  _array.add(_water) );
        }
        catch (NullPointerException e)
        {

            return _gson.toJson(  _array.add(_water) );
        }
        catch (Exception e)
        {

            return _gson.toJson(  _array.add(_water) );
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
            _rs = _sql.sql("Select * from M10_DeletWaterLast('"+dia+"' ,"+fkp+")");

            while(_rs.next())
            {
                _water = new Water();
                //se agarran los valores de la consulta y se crea un objeto tipo water
                _water.set_cantidad(_rs.getInt("res"));



            } //end while que recorre la consulta

        } catch (SQLException e) {

            return _gson.toJson( _water );
        }

        catch (NullPointerException e)
        {

            return _gson.toJson( _water );
        }
        catch (Exception e)
        {

            return _gson.toJson(  _water );
        }

        //se devuelve el arraylist
        return  _gson.toJson( _water );


    }

    @GET
    @Path("/DeletWaterTm")
    @Produces("application/json")
    public String DeletWaterTm( @QueryParam("time") String dia , @QueryParam("fkp") int fkp)
    {

        try {

            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            _rs = _sql.sql("Select * from M10_DeletWaterTm('"+dia+"' ,"+fkp+")");

            while(_rs.next())
            {
                //se agarran los valores de la consulta y se crea un objeto tipo water
                _water.set_cantidad(_rs.getInt("res"));

            } //end while que recorre la consulta

        } catch (SQLException e)
        {

            return _gson.toJson(  _water );
        }
        catch (NullPointerException e)
        {

            return _gson.toJson(  _water  );
        }
        catch (Exception e)
        {

            return _gson.toJson(  _water );
        }
        //se devuelve el arraylist
        return  _gson.toJson( _array );


    }


    @GET
    @Path("/getFecha")
    @Produces({"application/json"})
    public String GetFecha(@QueryParam("fkp") int fkp) {

        _water=_gson.fromJson(GetWater(_sdf2.format(fecha),fkp),Water.class);
        _water.set_time(_sdf2.format(fecha));
        return _gson.toJson(_water);


    }




}
