package WebServicesClasses;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;
import java.util.ArrayList;
import Domain.*;


/**
 * Created by estefania on 14/05/2017.
 */

@Path("/M05_ServicesSport")
public class M05_ServicesSport {
    private Connection conn = getConInstance();

    Gson gson = new Gson();


    @GET

    @Path("/insertSport")

    @Produces("application/json")
    /**
     *  Agrega a la lista de deportes disponibles del usuario el id seleccionado
     *  @param idPer
     *  @param idSpo
     *  @return
     */

    public  String insertSport(@QueryParam("idPer") Integer nombreUsu,
                               @QueryParam("idSpo") Integer nombreDep){

        String query = "select * from M05_insertarDeporte('"+nombreUsu+"','"+nombreDep+"')" ;

        try{
  
            Statement    st = conn.createStatement();
            ResultSet    rs =  st.executeQuery(query);

            return gson.toJson("Agregado");

        } catch (Exception e) {
            return  e.getMessage();
        }

    }



    @GET

    @Path("/getSport")

    @Produces("application/json")
    /**
     * 
	 * Extrae el nombre de los deportes en funcion del id
     * @param idSpo
     * @return
     */

    public String getSport(@QueryParam("idSpo") Integer id){


        Sport resultado = null;

        //Declarando la sentencia de la funcion de obtenerDatosDeporte que devuelve todos los datos de los deportes
        String query = "select * from M05_obtenerdatosdeporte('"+id+"')";

        try{

            Statement    st = conn.createStatement();
            ResultSet    rs =  st.executeQuery(query);

            while(rs.next()){

                Integer numero = rs.getInt(   "iddeporte");
                String  nombre = rs.getString("nombredeporte");

                resultado=new Sport(numero,nombre);


            }

            return gson.toJson(resultado);

        } catch (Exception e) {

            return e.getMessage();

        }
    }

    @GET

    @Path("/getMetSport")

    @Produces("application/json")
    /**Devuelve a traves del nombre del deporte el met correspondiente al mismo
     * Con el fin de realizar el calculo de las calorias quemadas en la actividad
     * @param nameSpo
     */

    public String getMet(@QueryParam("nameSpo") String nombreDep ){

        Sport resultado = null;

        //Declarando la sentencia de lafuncion obtenerMetDeporte que devuelve el met correspondiente al deporte consultado
        String query = "select * from M05_obtenermetdeporte('"+nombreDep.toUpperCase()+"')";

        try{
            Statement    st = conn.createStatement();
            ResultSet    rs =  st.executeQuery(query);

            while(rs.next()){

                Float mets=rs.getFloat(  "metdeporte");
                resultado=new Sport(mets);
            }

            return gson.toJson(resultado);

        } catch (Exception e) {

            return e.getMessage();

        }
    }


    @GET

    @Path("/getSportsUser")

    @Produces("application/json")
    /**
     * Devuelve el conjunto de deportes registrados que tiene un usuario
     * @param idPer
     * @return
     */

    public String getSportsUser(@QueryParam("idPer") Integer id ){


        
        
        String query = "select nombredeporte from M05_obtenerdeportesusuario('"+id+"')";

        try{
            ArrayList<Sport> listaDeportes= new ArrayList<Sport>();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){
                Sport resultado = new Sport();
                resultado.setName(rs.getString(  "nombredeporte"));
                listaDeportes.add(resultado.getName());
            }

            return gson.toJson(listaDeportes);

        } catch (Exception e) {

            return e.getMessage();

        }
    }

    @GET

    @Path("/deleteSport")

    @Produces("application/json")

    /**
     * Elimina un deporte de la lista de disponibles del usuario
     * @param idPer
     * @param idSpo
     * @return
     */

    public  String deleteSport(@QueryParam("idPer") Integer nombreUsu,
                               @QueryParam("idSpo") Integer nombreDep){

        Boolean respuesta;

        String query = "select  M05_eliminarDeporte('"+nombreUsu+"','"+nombreDep+"')" ;

        try{
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            return gson.toJson(true);

        } catch (Exception e) {
            return  e.getMessage();
        }

    }

    /**
     *  Obtiene el id del deporte a traves del nombre
     *
     * @param name
     * @return
     */

    public int obtenerIdSport(String name){


        String query = "select M05_obteneriddeporte('"+name.toUpperCase()+"');";

       Sport resultado= new Sport();

        try{
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){

                resultado.setId(rs.getInt("iddeporte"));
            }

            return resultado.getId();

        } catch (Exception e) {
            return 0;
        }
    }

   

}

