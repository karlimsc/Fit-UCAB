package edu.ucab.desarrollo.fitucab.webService;



import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Water;


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
public class M10_ServicesHidration {

    private Gson _gson = new Gson();

    private SimpleDateFormat _sdf3 = new SimpleDateFormat("hh:mm:ss");

    private Water _water;
    private Sql _sql = new Sql();
    private ResultSet _rs;
    private String hora;
    Date fecha = new Date();



    /**
     * Metodo que es llamado a traves del web service para agregar un vaso a la base de dato
     *
     * @param dia
     * @param glassType
     * @param fkp
     * @return la cantidad de vasos tomados  que tiene ese dia
     */
    @GET
    @Path("/addWater")
    @Produces("application/json")
    public String addWater(@QueryParam("time") String dia, @QueryParam("glasstype") int glassType
            , @QueryParam("fkp") int fkp) {
        hora = _sdf3.format(fecha);
        try {
            //llamo a la funcion sql para que se conecte a la base de dato y traiga la consulta
            _rs = _sql.sql("Select res from m10_addwater('" + dia + "     " + hora + "'," + glassType + "," + fkp + ")");

            //recorro la consulta
            while (_rs.next()) {
                _water = new Water();
                _water.set_cantidad(_rs.getInt("res"));
                _water.set_time(hora);

            }// end while que recorre la consulta

        } catch (SQLException e) {
            _water = new Water();
            return _gson.toJson(_water);
        } catch (NullPointerException e) {

            return _gson.toJson(_water);
        } catch (Exception e) {

            return _gson.toJson(_water);
        }


        return _gson.toJson(_water);

    }
}