package edu.ucab.desarrollo.fitucab.webService;



import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M10.*;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;



/**
 *
 * Clase para el manejo de la hidratacion
 */
@Path("/M10_WaterGlass")
public class M10_ServicesHidration {

    private Gson _gson = new Gson();


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

        //VALIDAR DATOS DE ENTRADA...


        Entity WaterObject = EntityFactory.createWater(glassType,fkp,dia);
        AddWaterCommand cmd = CommandsFactory.instatiateAddWaterCmd(WaterObject);

        try
        {
            cmd.execute();

            return _gson.toJson(cmd.returned);
        }
        catch ( Exception e )
        {
            return _gson.toJson(cmd.returned);
        }


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
        Entity WaterObject = EntityFactory.createWater(fkp,dia);
        GetListDateCommand  cmd = CommandsFactory.instatiateGetListDateCmd(WaterObject);

        try
        {
            cmd.execute();

            return _gson.toJson(cmd.returned);
        }
        catch ( Exception e )
        {
            return _gson.toJson(cmd.returned);
        }


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

        Entity WaterObject = EntityFactory.createWater(fkp,dia);
        GetWaterCommand cmd = CommandsFactory.instatiateGetWaterCmd(WaterObject);

        try
        {
            cmd.execute();

            return _gson.toJson(cmd.returned);
        }
        catch ( Exception e )
        {
            return _gson.toJson(cmd.returned);
        }



    }

    /**
     * falta document
     * @param dia
     * @param fkp
     * @return
     */
    @GET
    @Path("/GetFechaInt")
    @Produces("application/json")
    public String GetFechaInt( @QueryParam("time") String dia , @QueryParam("fkp") int fkp)
    {

        Entity WaterObject = EntityFactory.createWater(fkp,dia);
        GetFechaIntCommand cmd = CommandsFactory.instatiateGetFechaIntCmd(WaterObject);

        try
        {
            cmd.execute();

            return _gson.toJson(cmd.returned);
        }
        catch ( Exception e )
        {
            return _gson.toJson(cmd.returned);
        }



    }

    /**
     * falta document
     * @param dia
     * @param fkp
     * @return
     */
    @GET
    @Path("/DeletLast")
    @Produces("application/json")
    public String DeletLast( @QueryParam("time") String dia , @QueryParam("fkp") int fkp)
    {

        Entity WaterObject = EntityFactory.createWater(fkp,dia);
        DeletLastCommand cmd = CommandsFactory.instatiateDeletLastCmd(WaterObject);

        try
        {
            cmd.execute();

            return _gson.toJson(cmd.returned);
        }
        catch ( Exception e )
        {
            return _gson.toJson(cmd.returned);
        }


    }

    /**
     * falta documt
     * @param dia
     * @param fkp
     * @return
     */
    @GET
    @Path("/DeletWaterTm")
    @Produces("application/json")
    public String DeletWaterTm( @QueryParam("time") String dia , @QueryParam("fkp") int fkp)
    {

        Entity WaterObject = EntityFactory.createWater(fkp,dia);
        DeletWaterTmCommand cmd = CommandsFactory.instatiateDeletWaterTmCmd(WaterObject);

        try
        {
            cmd.execute();

            return _gson.toJson(cmd.returned);
        }
        catch ( Exception e )
        {
            return _gson.toJson(cmd.returned);
        }


    }

    /**
     * falta document
     * @param fkp
     * @return
     */

    @GET
    @Path("/getFecha")
    @Produces({"application/json"})
    public String GetFecha(@QueryParam("fkp") int fkp) {

        Entity WaterObject = EntityFactory.createWater(fkp);
        GetFechaCommand cmd = CommandsFactory.instatiateGetFechaCmd(WaterObject);

        try
        {
            cmd.execute();

            return _gson.toJson(cmd.returned);
        }
        catch ( Exception e )
        {
            return _gson.toJson(cmd.returned);
        }


    }

}