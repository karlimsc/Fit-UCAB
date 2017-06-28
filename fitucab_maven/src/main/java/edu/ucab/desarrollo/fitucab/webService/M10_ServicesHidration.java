package edu.ucab.desarrollo.fitucab.webService;



import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M10.AddWaterCommand;


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


        Entity createWaterObject = EntityFactory.createWater(glassType,fkp,dia);
        AddWaterCommand cmd = CommandsFactory.instatiateAddWaterCmd(createWaterObject);

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