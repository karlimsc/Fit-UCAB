package edu.ucab.desarrollo.fitucab.common.entities;

/**
 * Clase que maneja la informacion de las amistades
 * @author Daniel Da Silva, Luis Martinez, Anderson Gomez
 * @version 2.0
 */

public class Friendship extends Entity {
   private String _userOne;
   private String _userTwo;

   /**
    * Constructor usuarios
    * @param _userOne
    * @param _userTwo
    */

   public Friendship(String _userOne, String _userTwo) {
      this._userOne = _userOne;
      this._userTwo = _userTwo;
   }

    /**
     * Constructor vacio
     */

   public Friendship(){

   }

   /**
    * Get del id del usuario 1
    * @return
    */

    public String get_userOne() {
        return _userOne;
    }

    /**
    * Set del id del usuario 1
    * @param _userOne
    */

   public void set_userOne(String _userOne) {
      this._userOne = _userOne;
   }

   /**
    * Get del id del usuario 2
    * @return
    */

   public String get_userTwo() {
      return _userTwo;
   }

   /**
    * Set del id del usuario 1
    * @param _userTwo
    */

   public void set_userTwo(String _userTwo) {
      this._userTwo = _userTwo;
   }


}
