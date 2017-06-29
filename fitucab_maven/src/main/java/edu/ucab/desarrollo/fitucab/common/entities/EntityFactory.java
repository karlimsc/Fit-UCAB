package edu.ucab.desarrollo.fitucab.common.entities;

import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.LinkedList;

/**
 * Fabrica de Entidades
 */
public class EntityFactory
{

    /**
     * Metodo que instancia la clase paara crear el objeto user
     *   @param  username
     *   @param  password
     *   @param  email
     *   @param  sex
     *   @param  phone
     *   @param  bday
     *   @param  weight
     *   @param  height
     */

    static public Entity createUser(String username, String password, String email, String sex, String phone,
                                    Date bday, int weight, int height){

        return new User( username, password , email , sex , phone , (java.sql.Date) bday, weight , height );
    }

    static public Entity createUser(){

        return new User();
    }

    /**
     * Comprueba la contraseña y el correo
     * @param username
     * @param password
     * @return
     */
    static public Entity createUser( String username, String password){

        return new User( username, password );
    }

    /**
     * Usado para recuperar la contraseña
     * @param email
     * @return
     */
    static public Entity createUser(  String email){

        return new User(  email );
    }



    //MODULO 3

    //MODULO 3


    // MODULO 6

    /**
     * Fabrica para una actividad
     * @param id
     * @param name
     * @return
     */
    static public Activity createActivity( int id, String name ){

        return new Activity(id,name);

    }

    /**
     * Fabrica de un entrenamiento con todos sus atributos
     * @param id
     * @param trainingName
     * @param trainingPeriod
     * @param listActivities
     * @return un nuevo entrenamiento
     */
    static public Training createTraining(int id, String trainingName, int trainingPeriod,
                                          LinkedList<Activity> listActivities){

        return new Training(id,trainingName, trainingPeriod , listActivities);
    }

    /**
     * Fabrica de un entrenamiento con id y nombre
     * @param id
     * @param trainingName
     * @return
     */
    static public Training createTraining(int id, String trainingName){

        return new Training(id,trainingName);
    }

    /**
     * Fabrica de Entrenamiento con nombre y periodo
     * @param trainingName
     * @param period
     * @return
     */
    static public Training createTraining(String trainingName, int period){

        return new Training(trainingName, period);
    }

    /**
     * Fabrica de un entrenamiento sin id
     * @param trainingName
     * @param trainingPeriod
     * @param listActivities
     * @return retorna el entrenamiento
     */
    static public Training createTraining( String trainingName, int trainingPeriod ,
                                           LinkedList<Activity> listActivities){

        return new Training(trainingName, trainingPeriod, listActivities);
    }
    /***
     *  Metodo que intancia la clase para crear el objeto vacio
     * @return el entrenamiento
     */
    static public Training createTraining(){

        return new Training();
    }

    /**
     * Fabrica para crear un entrenamiento por usuario
     * @param userId
     * @return
     */
    public static Entity createTraining(int userId)
    {
        return new Training( userId );
    }

    /**
     * Fabrica para un entrenamiento con id, nombre y periodo
     * @param id
     * @param name
     * @param period
     * @return
     */
    public static Training createTraining(int id, String name, int period)
    {
        return new Training( id, name, period );
    }

    //si hacen falta unos mas especificos se colocan en el objeto y aqui

    // FIN MODULO 6

    //MODULO 9
    /**
     * Metodo que instancia la clase Challenge vacia
     * @return Objeto Challenge
     * @see Challenge
     */
    static public Entity createChallenge(){
        return new Challenge();
    }

    /**
     * Metodo que instancia la clase Challenge con sus atributos
     * @param id Identificador del reto
     * @param name Nombre del reto
     * @param description Descripcion del reto
     * @param score Record que se lleva del reto.
     * @return Objecto Challenge
     * @see Challenge
     */
    static public Entity createChallenge(int id, String name, String description, int score){
        return new Challenge(id, name, description, score);
    }

    static public List<Entity> getChallenges(){
        return new ArrayList<Entity>();
    }

    static public Entity createActive(Command command){
        return new Active(command);
    }
    //FIN MODULO 9

}