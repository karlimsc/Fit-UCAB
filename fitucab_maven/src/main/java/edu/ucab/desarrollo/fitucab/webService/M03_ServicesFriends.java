package edu.ucab.desarrollo.fitucab.webService;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M03.GetFriendsCommand;
import org.slf4j.LoggerFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;

/**

 * Clase M03_ServicesFriends que maneja el modulo de amistad.
 * @author Daniel Da Silva, Luis Martinez, Anderson Gomez
 * @version 2.0

 */

@Path("/friend")

@Produces(MediaType.APPLICATION_JSON)
public class M03_ServicesFriends {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M03_ServicesFriends.class);
    private Gson _gson = new Gson();

    /**
     * Metodo que es llamado a traves del web service para solicitar una amistad
     * @param idMayor del usuario 1
     * @param idMenor del usuario 2
     * @return array con la amistad
     */

    @PUT
    @Path("/request")
    @Produces("plain/text")
    public String request(@QueryParam("idMayor") String idMayor, @QueryParam("idMenor") String idMenor) throws NoSuchMethodException {

        try {
            Dao dao = DaoFactory.instanceDaoFriendship();
            Command cmd = CommandsFactory.instanciateRequestFriendshipCmd(dao, idMayor, idMenor);
            cmd.execute();

        }
        catch (WebApplicationException e){

            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
            Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());

        }
        catch (Exception e){

            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
            Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());

        }

        return null; //no se agrego

    }

    /**
     * Metodo que es llamado a traves del web service para consulta la
     * todos los amigos
     * @param id del usuario
     * @param action para saber si posee amigos o no
     * @return array con todos los amigos
     */

    @GET
    @Path("/GetAll")
    @Produces("application/json")
    public String GetAll(@QueryParam("id") String id, @QueryParam("action") String action)
    {

        try
        {
            GetFriendsCommand cmd = CommandsFactory.instatiateGetFriendsCmd(id, action);
            cmd.execute();
            return cmd.returned;
        }
        catch ( Exception e )
        {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
            Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }

        return null;
    }

    @POST
    @Path("/update")
    @Produces("application/json")
    public String update(@QueryParam("idUpdater") String idUpdater,@QueryParam("idUpdated") String idUpdated,@QueryParam("Action") String Action) {

        try{
            Dao dao = DaoFactory.instanceDaoFriendship();
            Command cmd = CommandsFactory.instanciateUpdateFriendshipCmd(dao, idUpdater, idUpdated, Action);
            cmd.execute();

        }catch(Exception e){

        }
        return null;
    }




}
