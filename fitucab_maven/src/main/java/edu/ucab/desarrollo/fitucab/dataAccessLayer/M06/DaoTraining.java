package edu.ucab.desarrollo.fitucab.dataAccessLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;
import java.sql.*;

import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;

import java.util.ArrayList;
import java.util.LinkedList;


public class DaoTraining extends Dao implements IDaoTraining
{
    private static Logger logger = LoggerFactory.getLogger( DaoTraining.class );
    //final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);

    Entity _entidad;


    public DaoTraining( Entity entidad )

    {
            this._entidad = entidad;
    }

    public Entity create(Entity e) throws AddException {


        Training t = (Training) e;
        String query ="SELECT * FROM M06_CREATETRAINING('"+t.getTrainingName()+"','"+t.get_userId()+"')";

        try {
            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //User user = null;


            while (rs.next()) {
                int Id = rs.getInt("M06_CREATETRAINING");
                e.set_id(Id);
            }

        }
        catch (SQLException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new AddException(ex);
        }
        catch (BdConnectException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new AddException(ex);
        }
        catch (Exception ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new AddException(ex);
        }

        for (Entity activity : t.get_activitylist()) {
            Activity a = (Activity) activity;
            String _query =
                    "SELECT M06_ADDTRAINING_ACTIVITY('" + a.get_duration() + "', '" + e.get_id() + "', '" + a.get_id() + "')";
            try {

                Connection conn = Dao.getBdConnect();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(_query);
                //User user = null;

            }
            catch (SQLException ex) {
                MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                        Thread.currentThread().getStackTrace()[1].getMethodName());
                logger.debug(error.toString());
                logger.error(error.toString());
                throw new AddException(ex);
            }
            catch (BdConnectException ex) {
                MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                        Thread.currentThread().getStackTrace()[1].getMethodName());
                logger.debug(error.toString());
                logger.error(error.toString());
                throw new AddException(ex);
            }
            catch (Exception ex) {
                MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                        Thread.currentThread().getStackTrace()[1].getMethodName());
                logger.debug(error.toString());
                logger.error(error.toString());
                throw new AddException(ex);
            }
        }

        return e;
    }

    public Entity read(Entity e )
    {
        return null;
    }

    public Entity update( Entity e )
    {
        return null;
    }

    public Boolean delete(Entity e) throws DeleteException
    {
        Training t = (Training) e;
        String query ="SELECT M06_DELETE_TRAINING('"+t.get_id()+"')";

        try {
            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
        }
        catch (SQLException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.debug(error.toString());
            throw new DeleteException(ex);
        }
        catch (BdConnectException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.debug(error.toString());
            throw new DeleteException(ex);
        }
        catch (Exception ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.debug(error.toString());
            throw new DeleteException(ex);
        }
        return true;
    }

    /**
     * Lista todos los entrenamientos
     * @param entidad
     * @return lista de entrenamientos
     */
    public LinkedList<Entity> listAll( Entity entidad ) throws ListAllException
    {
        LinkedList<Entity> resultList = null;
        Training entity;
        CallableStatement preStatement = null;
        ResultSet resultSet = null;
        Connection conn;

        try
        {
            resultList = new LinkedList<Entity>();
            conn = getBdConnect();

            //Aqui se invoca el SP
            preStatement = conn.prepareCall( "{call M06_GET_TRAININGS(?)}" );
            //Aqui meto los parametros
            preStatement.setInt( 1, entidad.get_id() );
            //Aqui ejecuto el SP
            resultSet = preStatement.executeQuery();

            while ( resultSet.next() )
            {
                int id = resultSet.getInt( "id" );
                String name = resultSet.getString( "training_name" );

                entity = EntityFactory.createTraining( id, name );
                entity.set_activitiesList( listActivities() );
                resultList.add( entity );
            }

            resultSet.close();

        }
        catch ( SQLException e )
        {

            logger.error( "Metodo: {} {}", "listAll", e.toString() );
            throw new ListAllException( e );
        }
        catch( BdConnectException e )
        {
            logger.error( "Metodo: {} {}", "listAll", e.toString() );
            throw new ListAllException( e );
        }
        catch ( Exception e )
        {
            logger.error( "Metodo: {} {}", "listAll", e.toString() );
            throw new ListAllException( e );
        }
        finally
        {
            closeConnection();
        }

        return resultList;
    }

    /**
     * Metodo para mostrar el entrenamiento a detalle     *
     * @param entidad     *
     * @return la entidad entrenamiento     *
     * @throws ListByIdException
     */
    public Entity trainingDetail( Entity entidad) throws ListByIdException
    {

        Training entity = null;
        User userId = null;
        LinkedList<Entity> resultList = null;
        CallableStatement preStatement = null;
        ResultSet resultSet = null;
        EntityMapTraining etMap;

        try
        {
            etMap = EntityMapTraining.getInstance();
            entity = etMap.get( entidad.get_id() );

            if( entity == null )
            {
                // Aqui se invoca el SP
                preStatement = getBdConnect().prepareCall( "{ call M06_GET_TRAINING_DETAILS(?) }" );
                // Aqui meto los parametros
                entity = (Training) entidad;
                //preStatement.setInt( 1, entidad.get_id() );
                preStatement.setInt(1, entidad.get_id());
                //Aqui ejecuto el SP
                resultSet = preStatement.executeQuery();

                while ( resultSet.next() )
                {
                    int id = resultSet.getInt( "id" );
                    String name = resultSet.getString( "name" );

                    entity = EntityFactory.createTraining( id, name );
                    entity.set_activitiesList( listActivities() );

                }

                resultSet.close();

            }

        }
        catch ( SQLException e )
        {
            logger.error( "Metodo: {} {}", "trainingDetail", e.toString() );
            throw new ListByIdException( e );
        }
        catch( BdConnectException e )
        {
            logger.error( "Metodo: {} {}", "trainingDetail", e.toString() );
            throw new ListByIdException( e );
        }
        catch ( Exception e )
        {
            logger.error( "Metodo: {} {}", "trainingDetail", e.toString() );
            throw new ListByIdException( e );
        }
        finally
        {
            closeConnection();
        }

        return entity;
    }

    public Boolean activateTraining( Entity e )
    {
        return null;
    }

    public Boolean shareTraining( Entity e ) throws ShareException
    {
        Training t = (Training) e;
        String query ="SELECT M06_CREATETRAINING('"+t.getTrainingName()+"','"+t.get_userId()+"')";

        try {
            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //User user = null;

            for (Entity activity : t.get_activitylist()) {
                Activity a = (Activity) activity;
                String _query =
                        "SELECT M06_ADDTRAINING_ACTIVITY('" + a.get_duration() + "', '" + e.get_id() + "', '" + a.get_id() + ")";
                conn = Dao.getBdConnect();
                st = conn.createStatement();
                rs = st.executeQuery(_query);
                //User user = null;
            }

        }
        catch (SQLException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new ShareException(ex);
        }
        catch (BdConnectException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new ShareException(ex);
        }
        catch (Exception ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new ShareException(ex);
        }

        return true;
    }

    /**
     * Metodo de Karli para listar las actividades     *
     * @return lista de actividades
     */
    private ArrayList<Entity> listActivities()
    {
        ArrayList<Entity> resultList = null;
        Activity entity;

        resultList = new ArrayList<Entity>( 5 );

        entity = EntityFactory.createActivity( 1, "Caminar",2 );
        resultList.add( entity );
        entity = EntityFactory.createActivity( 2, "Trotar",3 );
        resultList.add( entity );
        entity = EntityFactory.createActivity( 3, "Correr",2 );
        resultList.add( entity );
        entity = EntityFactory.createActivity( 4, "Lagartijas",1 );
        resultList.add( entity );
        entity = EntityFactory.createActivity( 5,"Nadar",2 );
        resultList.add( entity );


        return resultList;
    }

    public ArrayList<Entity> listTrainingsShared( Entity e )
    {
        return null;
    }


    public Boolean modifyName(Entity e)  throws UpdateException{
        Training t = (Training) e;
        String query ="SELECT M06_MODIFYNAMETRAINING('"+t.get_id()+"','"+t.getTrainingName()+"')";

        try {

            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //User user = null;

        }
        catch (SQLException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new UpdateException(ex);
        }
        catch (BdConnectException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new UpdateException(ex);
        }
        catch (Exception ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new UpdateException(ex);
        }

        return true;
    }

    public Boolean addActivities(Entity e) throws AddException {

        try {
            removeActivities(e);
            Training t = (Training) e;
            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            //User user = null;

            for (Entity activity : t.get_activitylist()) {
                Activity a = (Activity) activity;
                String _query =
                        "SELECT M06_ADDTRAINING_ACTIVITY('" + a.get_duration() + "', '" + e.get_id() + "', '" + a.get_id() + "' )";
                conn = Dao.getBdConnect();
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(_query);
                //User user = null;
            }

        }
        catch (SQLException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new AddException(ex);
        }
        catch (BdConnectException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new AddException(ex);
        }
        catch (Exception ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new AddException(ex);
        }

        return true;
    }

    public Boolean removeActivities(Entity e) throws DeleteException{
        try {
            Training t = (Training) e;
            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            //User user = null;

                String _query =
                        "SELECT M06_DELETE_ACTIVITY('" + e.get_id() + "', '" + 0 + "')";
                conn = Dao.getBdConnect();
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(_query);
                //User user = null;

        }
        catch (SQLException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new DeleteException(ex);
        }
        catch (BdConnectException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new DeleteException(ex);
        }
        catch (Exception ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw new DeleteException(ex);
        }

        return true;
    }
}
