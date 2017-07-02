package edu.ucab.desarrollo.fitucab.common.entities;

/**
 * Clase que maneja la informacion de las amistades
 * @author Daniel Da Silva, Luis Martinez, Anderson Gomez
 * @version 2.0
 */

public class Friendship extends Entity {
   private int _userOne;
   private int _userTwo;

   /**
    * Constructor con id y usuarios
    * @param _id
    * @param _userOne
    * @param _userTwo
    */

   public Friendship(int _id, int _userOne, int _userTwo) {
      super(_id);
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

   public int get_userOne() {
      return _userOne;
   }

   /**
    * Set del id del usuario 1
    * @param _userOne
    */

   public void set_userOne(int _userOne) {
      this._userOne = _userOne;
   }

   /**
    * Get del id del usuario 2
    * @return
    */

   public int get_userTwo() {
      return _userTwo;
   }

   /**
    * Set del id del usuario 1
    * @param _userTwo
    */

   public void set_userTwo(int _userTwo) {
      this._userTwo = _userTwo;
   }


}
