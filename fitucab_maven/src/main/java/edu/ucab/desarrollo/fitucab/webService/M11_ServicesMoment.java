package edu.ucab.desarrollo.fitucab.webService;


import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Moment;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.MomentCommand;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by jaorr on 22/05/17.
 */

@Path("M11_Moment")
public class M11_ServicesMoment {

    private Connection conn = Sql.getConInstance();
    private Gson gson = new Gson();
    private String response;
    private ArrayList<Moment> jsonArray;
    private ArrayList<Moment> jsonArray1;
    private  Entity respuesta ;


    /**
     * Funcion que devulve los momentos del dia registrados en la BD
     * @return Lista de momentos en formato json
     */
    @GET
    @Produces("application/json")
    public String obtenerMomentos() {


        Entity EntityMoment = EntityFactory.getMoments();
        MomentCommand cmd = CommandsFactory.getMoment(EntityMoment);

        try {
            cmd.execute();
            respuesta = (Moment) cmd.Respuesta;

        } catch (ListAllException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (ListByIdException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (NoSuchMethodException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (SQLException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (BdConnectException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (Exception e) {
            respuesta .set_errorMsg(e.getMessage());
        }


        return gson.toJson(respuesta);
    }


}
