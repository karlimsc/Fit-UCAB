package edu.ucab.desarrollo.fitucab.common.entities;


import java.util.LinkedList;

/**
 * Fabrica de Entidades
 */
public class EntityFactory
{

    //MODULO 1

    //MODULO 1

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

}