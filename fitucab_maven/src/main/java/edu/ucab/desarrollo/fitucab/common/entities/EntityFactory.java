package edu.ucab.desarrollo.fitucab.common.entities;


public class EntityFactory
{

    /**
     * Metodo que instancia la clase paara crear el objeto user
     *   @param  int id
     *   @param  username
     *   @param  password
     *   @param  email
     *   @param  sex
     *   @param  phone
     *   @param  birthdate
     *   @param  weight
     *   @param  height
     */

    static public Entity createUser(int id, String username, String password, String email, String sex, String phone
                                    String birthdate, int weight, int height){

        return new User(id, username, password , email , sex , phone , birthdate , weight , height);
    }

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

}