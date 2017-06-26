package edu.ucab.desarrollo.fitucab.common.entities;

/**
 *Patron Entidad
 */
public class Entity {

        public int _id;

        public int get_id() {
                return _id;
        }

        public void set_id(int _id) {
                this._id = _id;
        }

        public Entity (){}

        public Entity(int _id){
                this._id=_id;
        }

}
