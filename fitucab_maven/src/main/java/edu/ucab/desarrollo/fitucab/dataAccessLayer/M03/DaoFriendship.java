package edu.ucab.desarrollo.fitucab.dataAccessLayer.M03;
import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.entities.Friendship;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la informacion de las amistades
 * @author Daniel Da Silva, Luis Martinez, Anderson Gomez
 * @version 2.0
 */
public class DaoFriendship extends Dao implements IDaoFriendship {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(DaoFriendship.class);
    private Connection _conn;

    public DaoFriendship()
    {
        try {
            _conn = Dao.getBdConnect();
        } catch (BdConnectException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
    }



    /**
     * Metodo que verifica si la amistad ya existe, y si no la agrega
     * @param friendship es la entidad
     * @param idRequested Id del usuario al que se le solicita amistad
     * @param idRequester Id del usuario que solicito amistad
     * @return retorna la amistad ya agregada
     * @throws SQLException
     */

    public void requestFriendship(int id, List<Entity>  friendship, String idRequester, String idRequested) throws  SQLException{
        //En cada Friendship, el id mas bajo debe ir en fk_personid. Por motivos de orden en la DB.

        //variables de entrada
        int idMayor;
        int idMenor;


        if(Integer.parseInt(idRequested)>=Integer.parseInt(idRequester)) {

            idMayor = Integer.parseInt(idRequested);
            idMenor = Integer.parseInt(idRequester);

        }else{

            idMayor = Integer.parseInt(idRequester);
            idMenor = Integer.parseInt(idRequested);

        }

        //fin variables de entrada
        //primero verifico si la amistad ya existe

        String queryVerificar = "Select * from public.friendship where (fk_persononeid="+idMayor+" and fk_persontwoid= "+idMenor+") or (fk_persononeid="+idMenor+" and fk_persontwoid= "+idMayor+")  ";
        ResultSet verificar = null;
        Sql baseRequestsVerify = new Sql();
        try {
            verificar = baseRequestsVerify.sql(queryVerificar);
            if (verificar.isBeforeFirst())
                logger.error("Error: Ya existe esta amistad");
        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }

        //luego la agrego

        String query = "INSERT INTO public.friendship(fk_persononeid, fk_persontwoid, fk_statusid, friendshipuseractivity) VALUES ("
                +idMenor+", "+idMayor+", 1, "+idRequester+")";

        try {
            Statement st = _conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Entity friend= EntityFactory.createFriendship(id, idMenor, idMayor);
            friendship.add(friend);

        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }

    }


    @Override
    public Entity create(Entity e) throws SQLException {
        return null;
    }

    @Override
    public Entity read(Entity e) throws CreateHomeException, SQLException, BdConnectException {
        return null;
    }

    @Override
    public ArrayList<Friendship> getList(Entity e) throws SQLException {
        return null;
    }


    @Override
    public Entity update(Entity e) {
        return null;
    }


    @Override
    public Entity getFriendship(Entity e) throws SQLException {
        return null;
    }

    @Override
    public Entity deleteFriendship(Entity e) throws SQLException {
        return null;
    }

    @Override
    public void Create(Entity e) {

    }
}
