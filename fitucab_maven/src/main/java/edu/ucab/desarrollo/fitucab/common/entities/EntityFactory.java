package edu.ucab.desarrollo.fitucab.common.entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;



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



    static public Entity createUser(int id, String username, String password, String email, String sex, String phone,

                                    Date bday, int weight, int height){



        return new User( id,username, password , email , sex , phone , (java.sql.Date) bday, weight , height );

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





    //MODULO2



    static public Home createHome(float totalCaloria, int totalAgua){



        return new Home( totalCaloria, totalAgua );

    }



    /**

     * Metodo que instancia la clase paara crear el objeto user

     *   @param  username

     *   @param  email

     *   @param  sex

     *   @param  phone

     *   @param  bday

     *   @param  weight

     *   @param  height

     */

    static public User createUser(int id, String username, String email, String sex, String phone,

                                  Date bday, int weight, int height){



        return new User(id, username, email , sex , phone , (java.sql.Date) bday, weight , height );

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

     * Metodo que instancia la clase Challenge con su id, nombre, descripcion y record

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



    /**

     * Metodo que instancia la clase Challenge con sus retos logrados y no logrados

     * @param achieve cantidad de retos logrados

     * @param unachieve cantidad de retos no logrados

     * @return Objecto Challenge

     * @see Challenge

     */

   /* static public Entity createChallenge(long achieve, long unachieve){

        return new Challenge(achieve,unachieve);

    }

*/

    /**

     * Metodo que instancia la clase Challenge con la suma de sus records

     * @param score Record acumulado que se lleva del reto.

     * @return Objeto Challenge

     * @see Challenge

     */

    /*static public Entity createChallenge(int score){

        return new Challenge(score);

    }

*/



    static  public List<Entity> getChallenges(){return new ArrayList<Entity>();}

    //FIN MODULO 9



    //Modulo 10

    /**

     * Metodo que instancia la clase Water con sus atributos

     * @param GlassType Indentificador de vaso

     * @param fkp   fk de usuario

     * @param dia  fecha

     * @return Water

     * @see Water

     */

    static public Water createWater(int GlassType, int fkp,String dia){

        return new Water(GlassType,fkp,dia);

    }

    /**

     * Metodo que instancia la clase Water con sus atributos

     * @param fkp   fk de usuario

     * @param dia  fecha

     * @return Water

     * @see Water

     */



    static public Water createWater(int fkp,String dia){

        return new Water(fkp,dia);

    }



    /**

     * Metodo que instancia la clase Water con sus atributos

     * @param fkp   fk de usuario

     * @return Water

     * @see Water

     */



    static public Water createWater(int fkp){

        return new Water(fkp);

    }





    /**

     * Metodo que instancia vacia

     * @see Water

     */

    static public Water createWater(){

        return new Water();

    }

    //Fin Modulo 10

    //Modulo 3

    /**

     * Metodo que instancia la clase Water con sus atributos

     * @param id Identificador

     * @param userOne   id del usuario 1

     * @param userTwo id del usuario 2

     * @return Friendship

     * @see Friendship

     */
    static public Friendship createFriendship(int id, int userOne, int userTwo){

        return new Friendship(id, userOne, userTwo);

    }

    /**

     * Metodo que instancia vacia

     * @see Friendship

     */

    static public Friendship createFriendship(){
        return new Friendship();
    }

    static  public List<Entity> getFriendship(){return new ArrayList<Entity>();}

    //Fin Modulo 3



    static  public Entity getUsername(String username){
        return new Food (username);
    }

    static public Entity getUserCal (String username, String calor){
        return new Food (username,calor);
    }

    static public Entity CreateFood (){
        return new Food ();
    }

    static public Entity getFoodIDuser (String name , int id){
        return  new Food(name,id);
    }

    static public Entity getFoodWeigthCaloriId (String foodname, String foodWeight , String foodCalorie, int id)
    {
        return new Food(foodname,foodWeight,foodCalorie,id);
    }

    static  public  Entity getFoodall ( String foodName,  String foodCalorie,String foodWeight,
                                        Boolean _foodPersonalized,int id)
    {
        return  new Food(foodName,foodCalorie,foodWeight,_foodPersonalized ,id);
    }

    static  public Entity putJson (String json){
        Food food  =(Food) CreateFood();
        food.setjson(json);

        return food;
    }


    static public Entity getCaloriesDate(String Date, String username){
        return new Diet(Date,username);
    }

    static public Entity deleteDiet(String moment, String username){
        return new Diet(moment,username);
    }

    static public Entity getMoment(String moment, LocalDate Date, String username){
        return new Diet(moment,Date,username);
    }
    static public Entity getMoments(){return new Moment(); }

    static public Entity getCaloriesdate(String username){
        return new Diet(username);
    }

    static public Entity insertDiet(int id, String cal, String foodName, String moment){
        return new Diet(id, cal, foodName, moment);
    }
}