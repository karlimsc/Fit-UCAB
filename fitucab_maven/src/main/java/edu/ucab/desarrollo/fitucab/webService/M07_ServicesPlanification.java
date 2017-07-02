package edu.ucab.desarrollo.fitucab.webService;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.codec.Codec;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.CreatePlanificationCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.DeletePlanificationCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.GetPlanificationByIdCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.UpdatePlanificationCommand;
import edu.ucab.desarrollo.fitucab.validation.ValidationWs;

import javax.ws.rs.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaorr on 30/06/17.
 */
@Path( "/M07_ServicesPlanification" )
public class M07_ServicesPlanification {

    Gson gson = new Gson();
    private ArrayList<Planification> jsonArray;

    /**
     *
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
     * @return REGRESA UN JSON CON UNA ESTRUCTURA A DEFINIR
     */

    @POST
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

            jsonArray.add((Planification) planificationEntity);
            return gson.toJson(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.toString();
        }


    }


    @PUT
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

            jsonArray.add((Planification) planificationEntity);
            return gson.toJson(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.toString();
        }


    }

    @DELETE
    @Produces("application/json")
    public String updatePlanification(@DefaultValue("-1") @QueryParam("planificationId") int planificationId,
                                      @DefaultValue("-1") @QueryParam("userId") int userId) {
        try {
            HashMap<String, Object> params = new HashMap<String, Object>(){ {
                put("planificationId", planificationId);
                put("userId", userId);
            }};
            jsonArray = new ArrayList<>();
            ValidationWs.validarParametrosNotNull(params);
            params = Codec.decode(params);

            Entity planificationEntity = EntityFactory.createPlanification(Integer.parseInt(params.get("planificationId").toString()),
                    Integer.parseInt(params.get("userId").toString()));

            DeletePlanificationCommand cmd = CommandsFactory.instanciateDeletePlanificationCmd(planificationEntity);
            cmd.execute();

            jsonArray.add((Planification) planificationEntity);
            return gson.toJson(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.toString();
        }


    }

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

            return gson.toJson(cmd.get_listPlanification());
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.toString();
        }


    }


}
