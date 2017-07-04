package edu.ucab.desarrollo.fitucab.common.entities;

/**
 *Patron Entidad
 */
public class Entity {

        private int _id;
        private int _errorCode;
        private String _errorMsg;

        public Entity (){}

        public Entity(int _id){
                this._id=_id;
        }

        public int get_id() {
                return _id;
        }

        public void set_id(int _id) {
                this._id = _id;
        }

        public int get_errorCode()
        {
                return _errorCode;
        }

        public void set_errorCode( int _errorCode )
        {
                this._errorCode = _errorCode;
        }

        public String get_errorMsg()
        {
                return _errorMsg;
        }

        public void set_errorMsg( String _errorMsg )
        {
                this._errorMsg = _errorMsg;
        }
}
