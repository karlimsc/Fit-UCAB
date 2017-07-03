package edu.ucab.desarrollo.fitucab.common;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Clase para el mapeo de la Entidad Usuario
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 2.0
 */
public class M02Cache{

    private static org.slf4j.Logger _logger = LoggerFactory.getLogger(DaoUser.class);

    private Entity _user;
    public static HashMap _mapUser = new HashMap<Integer, Entity>();
    public M02Cache(){
    }

    public void llenar(int id, Entity entity){
        _mapUser.put(id, entity);
         System.out.print(_mapUser.toString());
    }


    public void update(int _id, String _username, String _phone, String _email, Entity _userC){

        try {

            if (_mapUser.containsKey(_id)) {
                User _userCache = (User) _mapUser.get(_id);
                //Para el modificar solo se alteran ciertos Valores.
                //por lo que al _user en cache m02 le asigno los valores nuevos y actualizo el map
                _userCache.setUser(_username);
                _userCache.setEmail(_email);
                _userCache.setPhone(_phone);
                _userCache.setBirthdate(((User)_userC).getBirthdate());
                _userCache.setHeight(((User)_userC).getHeight());
                _userCache.setWeight(((User)_userC).getWeight());
                _userCache.setSex(((User)_userC).getSex());
                //Eliminam02os el usuario desactualizado que esta en el cache
                _mapUser.remove(_id);
                //Agrego el usuario actualizado al mapa
                _mapUser.put(_id, _userCache);
            }
        } catch (Exception _error){
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
        }
    }

    public Entity getUser(int _id){

        try{
           Entity user =(Entity) _mapUser.get(_id);
           return user;
        }catch (NullPointerException _error){
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            return null;
        }catch (Exception _error){
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            return null;
        }
    }

    public boolean searchUser(int _id){

        if (_mapUser.containsKey(_id))
            return true;
        else
            return false;
    }
}
