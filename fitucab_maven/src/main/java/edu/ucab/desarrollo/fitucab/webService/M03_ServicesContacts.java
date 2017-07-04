package edu.ucab.desarrollo.fitucab.webService;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M03.GetContactsCommand;
import org.slf4j.LoggerFactory;
import javax.ws.rs.*;

/**

 * Clase M03_ServicesContacts que maneja el modulo de contactos.
 * @author Daniel Da Silva, Luis Martinez, Anderson Gomez
 * @version 2.0

 */

@Path("/contact")
public class M03_ServicesContacts {
    Gson _gson = new Gson();
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M03_ServicesContacts.class);

    @GET
    @Path("/getContacts")
    @Produces("application/json")
    public String getContacts(@QueryParam("id") String id,@QueryParam("contacts") String contacts) {

        GetContactsCommand cmd = CommandsFactory.instatiateGetContactsCmd(id, contacts);
        try
        {
            cmd.execute();
            return cmd.returned;
        }
        catch (Exception e)
        {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }
        return null;

    }
}
