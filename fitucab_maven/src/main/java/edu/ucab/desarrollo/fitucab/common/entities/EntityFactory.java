package edu.ucab.desarrollo.fitucab.common.entities;


public class EntityFactory
{

    //MODULO 1

    //MODULO 1

    //MODULO 3

    //MODULO 3


    // MODULO 6

    //crear training con Id

    /***
     * Metodo que intancia la clase para crear el objeto
     * @param id
     * @param trainingName
     * @param trainingPeriod
     * @param trainingCalories
     * @return
     */
    static public Entity createTraining(int id, String trainingName, int trainingPeriod, int trainingCalories){

        return new Training(id,trainingName, trainingPeriod, trainingCalories);
    }
    // crear training sin id

    /***
     *  Metodo que intancia la clase para crear el objeto
     * @param trainingName
     * @param trainingPeriod
     * @param trainingCalories
     * @return
     */
    static public Entity createTraining( String trainingName, int trainingPeriod, int trainingCalories){

        return new Training(trainingName, trainingPeriod, trainingCalories);
    }

    /***
     *  Metodo que intancia la clase para crear el objeto
     * @return
     */
    static public Entity createTrainingvacio(){

        return new Training();
    }
               //si hacen falta unos mas especificos se colocan en el objeto y aqui

    // FIN MODULO 6

    //MODULO 9
    /**
     * Metodo que instancia la clase LevelUp
    */

    //FIN MODULO 9

}