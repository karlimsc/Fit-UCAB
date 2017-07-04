package edu.ucab.desarrollo.fitucab.webService;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.codec.Codec;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.common.entities.Response;
import edu.ucab.desarrollo.fitucab.common.exceptions.EncodingExeption;
import edu.ucab.desarrollo.fitucab.common.exceptions.ParametersNullException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.CreatePlanificationCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.DeletePlanificationCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.GetPlanificationByIdCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.UpdatePlanificationCommand;
import edu.ucab.desarrollo.fitucab.validation.ValidationWs;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.HashMap;


@Path( "/M07_ServicesPlanification" )
public class M07_ServicesPlanification {

    Gson gson = new Gson();
    Response response = new Response();
    private final int UNKNOWN_ERROR_STATUS = 500;
    private final String UNKNOWN_ERROR_MESSAGE = "Ha ocurrido un error durante la peticion";
    private ArrayList<Planification> jsonArray;
    private static org.slf4j.Logger _logger = LoggerFactory.getLogger(M07_ServicesPlanification.class);


    /**
     * Endpoint para insertar una planificacion
     * METHOD: GET
     * @param startDay INDICA EL DIA QUE COMIENZA LA PLANIFICACION
     * @param endDay INDICA EL DIA DE FIN DE LA PLANIFICACION, SI LA PLANIFICACION
     *               ES DE UN UN SOLO DIA, ESTE VALOR DEBE SER IGUAL A startDay
     * @param startTime INDICA LA HORA DEL DIA EN QUE COMIENZA LA ACTIVIDAD
     * @param duration INDICA LA DURACION DE LA ACTIVAD A REALIZAR
     * @param monday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS LUNES. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param tuesday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS MARTES. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param wednesday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS MIERCOLES. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param thursday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS JUEVES. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param friday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS VIERNES. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param saturday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS SABADO. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param sunday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS DOMINGO. ESTE PARAMETRO NO ES OBLIGATORIO
     * @return REGRESA UN JSON CON LA ESTRUCTURA DEL OBJETO RESPONSE
     * @see Response
     */

    @GET
    @Path( "/insertPlanification" )
    @Produces("application/json")
    public String createPlanification(@QueryParam("startDay") String startDay,
                                      @QueryParam("endDay") String endDay,
                                      @QueryParam("startTim") String startTime,
                                      @QueryParam("duration") String duration,
                                      @DefaultValue("-1") @QueryParam("userId") int userId,
                                      @DefaultValue("-1") @QueryParam("sportId") int sportId,
                                      @QueryParam("monday") boolean monday,
                                      @QueryParam("tuesday") boolean tuesday,
                                      @QueryParam("wednesday") boolean wednesday,
                                      @QueryParam("thursday") boolean thursday,
                                      @QueryParam("friday") boolean friday,
                                      @QueryParam("saturday") boolean saturday,
                                      @QueryParam("sunday") boolean sunday) {
        try {
            HashMap<String, Object> params = new HashMap<String, Object>(){ {
                put("startDay", startDay);
                put("endDay", endDay);
                put("startTime", startTime);
                put("duration", duration);
                put("userId", userId);
                put("sportId", sportId);

            }};
            jsonArray = new ArrayList<>();
            ValidationWs.validarParametrosNotNull(params);
            params.put("monday", monday);
            params.put("tuesday", tuesday);
            params.put("wednesday", wednesday);
            params.put("thursday", thursday);
            params.put("friday", friday);
            params.put("saturday", saturday);
            params.put("sunday", sunday);
            params = Codec.decode(params);

            monday = Boolean.parseBoolean(params.get("monday").toString());
            tuesday = Boolean.parseBoolean(params.get("tuesday").toString());
            wednesday = Boolean.parseBoolean(params.get("wednesday").toString());
            thursday = Boolean.parseBoolean(params.get("thursday").toString());
            friday = Boolean.parseBoolean(params.get("friday").toString());
            saturday = Boolean.parseBoolean(params.get("saturday").toString());
            sunday = Boolean.parseBoolean(params.get("sunday").toString());

            Entity planificationEntity = EntityFactory.createPlanification(params.get("startDay").toString(),
                    params.get("endDay").toString(), params.get("startTime").toString(), params.get("duration").toString(),
                    Integer.parseInt(params.get("userId").toString()), Integer.parseInt(params.get("sportId").toString()),
                    monday, tuesday, wednesday, thursday, friday, saturday, sunday);

            CreatePlanificationCommand cmd = CommandsFactory.instanciateCreatePlanificationCmd(planificationEntity);
            cmd.execute();

            response.setStatus(planificationEntity.get_errorCode());
            response.setMessage(planificationEntity.get_errorMsg());
        } catch (ParametersNullException e) {
            response.setStatus(e.getStatus());
            response.setMessage(e.getMessage());
        }
        catch (EncodingExeption e){
            response.setStatus(e.get_status());
            response.setMessage(e.getMessage());
        }
        catch (Exception e) {
            response.setStatus(UNKNOWN_ERROR_STATUS);
            response.setMessage(UNKNOWN_ERROR_MESSAGE);
        }

        finally {
            _logger.debug("Respuesta del servicio planification en el create", response);
            return gson.toJson(response);
        }


    }

    /**
     * Endpoint para actualizar una planificacion
     * METHOD: GET
     * @param planificationId ID DEL REGISTRO EN LA BASE DE DATOS
     * @param userId ID DEL USUARIO AL QUE PERTENCE LA PLNIFICACION
     * @param sportId ID DEL SPORT AL QUE ESTA ASOCIADA LA PLANIFICACION
     * @param startDay INDICA EL DIA QUE COMIENZA LA PLANIFICACION
     * @param endDay INDICA EL DIA DE FIN DE LA PLANIFICACION, SI LA PLANIFICACION
     *               ES DE UN UN SOLO DIA, ESTE VALOR DEBE SER IGUAL A startDay
     * @param startTime INDICA LA HORA DEL DIA EN QUE COMIENZA LA ACTIVIDAD
     * @param duration INDICA LA DURACION DE LA ACTIVAD A REALIZAR
     * @param monday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS LUNES. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param tuesday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS MARTES. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param wednesday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS MIERCOLES. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param thursday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS JUEVES. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param friday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS VIERNES. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param saturday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS SABADO. ESTE PARAMETRO NO ES OBLIGATORIO
     * @param sunday PARAMETRO BOOLEANO QUE INDICA SI LA ACTIVIDAD SE REALIZARA
     *               LOS DOMINGO. ESTE PARAMETRO NO ES OBLIGATORIO
     * @return REGRESA UN JSON CON LA ESTRUCTURA DEL OBJETO RESPONSE
     * @see Response
     */

    @GET
    @Path( "/updatePlanification" )
    @Produces("application/json")
    public String updatePlanification(@DefaultValue("-1") @QueryParam("planificationId") int planificationId,
                                      @QueryParam("startDay") String startDay,
                                      @QueryParam("endDay") String endDay,
                                      @QueryParam("startTim") String startTime,
                                      @QueryParam("duration") String duration,
                                      @DefaultValue("-1") @QueryParam("userId") int userId,
                                      @DefaultValue("-1") @QueryParam("sportId") int sportId,
                                      @QueryParam("monday") boolean monday,
                                      @QueryParam("tuesday") boolean tuesday,
                                      @QueryParam("wednesday") boolean wednesday,
                                      @QueryParam("thursday") boolean thursday,
                                      @QueryParam("friday") boolean friday,
                                      @QueryParam("saturday") boolean saturday,
                                      @QueryParam("sunday") boolean sunday) {
        try {
            HashMap<String, Object> params = new HashMap<String, Object>(){ {
                put("planificationId", planificationId);
                put("startDay", startDay);
                put("endDay", endDay);
                put("startTime", startTime);
                put("duration", duration);
                put("userId", userId);
                put("sportId", sportId);

            }};
            jsonArray = new ArrayList<>();
            ValidationWs.validarParametrosNotNull(params);
            params.put("monday", monday);
            params.put("tuesday", tuesday);
            params.put("wednesday", wednesday);
            params.put("thursday", thursday);
            params.put("friday", friday);
            params.put("saturday", saturday);
            params.put("sunday", sunday);
            params = Codec.decode(params);

            monday = Boolean.parseBoolean(params.get("monday").toString());
            tuesday = Boolean.parseBoolean(params.get("tuesday").toString());
            wednesday = Boolean.parseBoolean(params.get("wednesday").toString());
            thursday = Boolean.parseBoolean(params.get("thursday").toString());
            friday = Boolean.parseBoolean(params.get("friday").toString());
            saturday = Boolean.parseBoolean(params.get("saturday").toString());
            sunday = Boolean.parseBoolean(params.get("sunday").toString());

            Entity planificationEntity = EntityFactory.createPlanification(Integer.parseInt(params.get("planificationId").toString()),
                    params.get("startDay").toString(), params.get("endDay").toString(), params.get("startTime").toString(),
                    params.get("duration").toString(), Integer.parseInt(params.get("userId").toString()),
                    Integer.parseInt(params.get("sportId").toString()), monday, tuesday, wednesday, thursday, friday,
                    saturday, sunday);

            UpdatePlanificationCommand cmd = CommandsFactory.instanciateUpdatePlanificationCmd(planificationEntity);
            cmd.execute();

            response.setStatus(planificationEntity.get_errorCode());
            response.setMessage(planificationEntity.get_errorMsg());
        } catch (ParametersNullException e) {
            response.setStatus(e.getStatus());
            response.setMessage(e.getMessage());
        }
        catch (EncodingExeption e){
            response.setStatus(e.get_status());
            response.setMessage(e.getMessage());
        }
        catch (Exception e) {
            response.setStatus(UNKNOWN_ERROR_STATUS);
            response.setMessage(UNKNOWN_ERROR_MESSAGE);
        }

        finally {
            _logger.debug("Respuesta del servicio planification en el update", response);
            return gson.toJson(response);
        }


    }


    /**
     * Endpoint para eliminar una planificacion
     * METHOD: DELETE
     * @param planificationId ID DEL REGISTRO EN LA BASE DE DATOS
     * @param userId ID DEL USUARIO AL QUE PERTENCE LA PLANIFICACION
     * @return REGRESA UN JSON CON LA ESTRUCTURA DEL OBJETO RESPONSE
     * @see Response
     */
    @DELETE
    @Path( "/deletePlanification" )	
    @Produces("application/json")
    public String deletePlanification(@DefaultValue("-1") @QueryParam("planificationId") int planificationId,
                                      @DefaultValue("-1") @QueryParam("userId") int userId) {
        try {
            HashMap<String, Object> params = new HashMap<String, Object>() {
                {
                    put("planificationId", planificationId);
                    put("userId", userId);
                }
            };
            jsonArray = new ArrayList<>();
            ValidationWs.validarParametrosNotNull(params);
            params = Codec.decode(params);

            Entity planificationEntity = EntityFactory.createPlanification(Integer.parseInt(params.get("planificationId").toString()),
                    Integer.parseInt(params.get("userId").toString()));

            DeletePlanificationCommand cmd = CommandsFactory.instanciateDeletePlanificationCmd(planificationEntity);
            cmd.execute();
            response.setStatus(planificationEntity.get_errorCode());
            response.setMessage(planificationEntity.get_errorMsg());
        } catch (ParametersNullException e) {
            response.setStatus(e.getStatus());
            response.setMessage(e.getMessage());
        }
        catch (EncodingExeption e){
            response.setStatus(e.get_status());
            response.setMessage(e.getMessage());
        }
        catch (Exception e) {
            response.setStatus(UNKNOWN_ERROR_STATUS);
            response.setMessage(UNKNOWN_ERROR_MESSAGE);
        }

        finally {
            _logger.debug("Respuesta del servicio planification en el delete", response);
            return gson.toJson(response);
        }
    }


    /**
     * Endpoint para obtener las planificaciones pertenecientes a un usuario
     * METHOD: GET
     * @param userId ID DEL USUARIO AL QUE PERTENCE LA PLANIFICACION
     * @return REGRESA UN JSON CON LA ESTRUCTURA DEL OBJETO RESPONSE
     * @see Response
     */
    @GET
    @Produces("application/json")
    public String getPlanification(@DefaultValue("-1") @QueryParam("userId") int userId) {
        try {
            HashMap<String, Object> params = new HashMap<String, Object>(){ {
                put("userId", userId);
            }};
            jsonArray = new ArrayList<>();
            ValidationWs.validarParametrosNotNull(params);
            params = Codec.decode(params);

            Entity planificationEntity = EntityFactory.createPlanification(Integer.parseInt(params.get("userId").toString()));

            GetPlanificationByIdCommand cmd = CommandsFactory.instanciateGetPlanificationByIdCmd(planificationEntity);
            cmd.execute();

            response.setStatus(planificationEntity.get_errorCode());
            response.setMessage(planificationEntity.get_errorMsg());
            response.setData(cmd.get_listPlanification());
        } catch (ParametersNullException e) {
            response.setStatus(e.getStatus());
            response.setMessage(e.getMessage());
        }
        catch (EncodingExeption e){
            response.setStatus(e.get_status());
            response.setMessage(e.getMessage());
        }
        catch (Exception e) {
            response.setStatus(UNKNOWN_ERROR_STATUS);
            response.setMessage(UNKNOWN_ERROR_MESSAGE);
        }

        finally {
            _logger.debug("Respuesta del servicio planification en el obtener", response);
            return gson.toJson(response);
        }


    }


}
