package edu.ucab.desarrollo.fitucab.dataAccessLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class DaoTraining extends Dao implements IDaoTraining
{
    private static Logger logger = LoggerFactory.getLogger( DaoTraining.class );

    Entity _entidad;

    public DaoTraining( Entity entidad )

    {
            this._entidad = entidad;
    }

    /**
     * Metodo para crear un Training
     * @param e entidad
     * @return entidad
     * @throws AddException
     */
    public Entity create(Entity e) throws AddException {

        Training entity = null;
        CallableStatement preStatement = null;
        ResultSet resultSet = null;
        EntityMapTraining etMap= null;

        try {
            entity = (Training) e;

            preStatement= getBdConnect().prepareCall("{ call M06_CREATETRAINING(?) }" );
            // Aqui meto los parametros
            preStatement.setString(1, ((Training) e).getTrainingName());
            preStatement.setInt(1,(((Training) e).get_userId()));
            //Aqui ejecuto el SP
            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                String trainingName = resultSet.getString("name");

                entity = EntityFactory.createTraining( Id, trainingName );
                entity.set_activitiesList( listActivities() );

            }

            etMap.put(e.get_id(),entity);
        }
        catch ( SQLException _e )
        {
            logger.error( "Metodo: {} {}", "createTraining", e.toString() );
            throw new AddException( _e );
        }
        catch( BdConnectException _e )
        {
            logger.error( "Metodo: {} {}", "createTraining", e.toString() );
            throw new AddException( _e );
        }
        catch ( Exception _e )
        {
            logger.error( "Metodo: {} {}", "createTraining", e.toString() );
            throw new AddException( _e );
        }
        finally
        {
            closeConnection();
        }

        return entity;

    }

    public Entity read(Entity e )
    {
        return null;
    }

    /**
     * Metodo para modificar un Training
     * @param e entidad
     * @return entidad
     * @throws UpdateException
     */
    public Entity update( Entity e ) throws UpdateException
    {
        Training entity = null;
        User userId = null;
        CallableStatement preStatement = null;
        ResultSet resultSet = null;
        EntityMapTraining etMap = null;

        try {

           // Aqui se invoca el SP
                preStatement = getBdConnect().prepareCall( "{ call M06_MODIFYNAMETRAINING(?) }" );
                // Aqui meto los parametros
                entity = (Training) e;

                preStatement.setInt(1, ((Training) e).get_id());
                preStatement.setString(1, ((Training) e).getTrainingName());
                //Aqui ejecuto el SP
                resultSet = preStatement.executeQuery();
                //cierro la conexion
                resultSet.close();

                entity= EntityFactory.createTraining(e.get_id(),((Training) e).getTrainingName());
                entity.set_activitiesList(listActivities());

                //Guardo en el entityMap
                etMap.replace(e.get_id(),entity);

        }
        catch ( SQLException _e )
        {
            logger.error( "Metodo: {} {}", "updateTraining", e.toString() );
            throw new UpdateException( _e );
        }
        catch( BdConnectException _e )
        {
            logger.error( "Metodo: {} {}", "updateTraining", e.toString() );
            throw new UpdateException( _e );
        }
        catch ( Exception _e )
        {
            logger.error( "Metodo: {} {}", "updateTraining", e.toString() );
            throw new UpdateException( _e );
        }
        finally
        {
            closeConnection();
        }


        return entity;
    }

    public Boolean delete(Entity e) throws DeleteException
    {
        Training entity = null;
        User userId = null;
        CallableStatement preStatement = null;
        ResultSet resultSet = null;
        EntityMapTraining etMap = null;

        try {

            preStatement = getBdConnect().prepareCall( "{ call M06_DELETE_TRAINING(?) }" );

            preStatement.setInt(1, ((Training) e).get_id());

            resultSet = preStatement.executeQuery();
        }
        catch ( SQLException _e )
        {
            logger.error( "Metodo: {} {}", "deleteTraining", e.toString() );
            throw new DeleteException( _e );
        }
        catch( BdConnectException _e )
        {
            logger.error( "Metodo: {} {}", "deleteTraining", e.toString() );
            throw new DeleteException( _e );
        }
        catch ( Exception _e )
        {
            logger.error( "Metodo: {} {}", "deleteTraining", e.toString() );
            throw new DeleteException( _e );
        }
        finally
        {
            closeConnection();
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
     * @param e   entidad
     * @return la entidad entrenamiento     *
     * @throws ListByIdException
     */
    public Entity trainingDetail( Entity e) throws ListByIdException
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
            entity = etMap.get( e.get_id() );

            if( entity == null )
            {
                // Aqui se invoca el SP
                preStatement = getBdConnect().prepareCall( "{ call M06_GET_TRAINING_DETAILS(?) }" );
                // Aqui meto los parametros
                entity = (Training) e;
                //preStatement.setInt( 1, entidad.get_id() );
                preStatement.setInt(1, e.get_id());
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
        catch ( SQLException _e )
        {
            logger.error( "Metodo: {} {}", "trainingDetail", e.toString() );
            throw new ListByIdException( _e );
        }
        catch( BdConnectException _e )
        {
            logger.error( "Metodo: {} {}", "trainingDetail", e.toString() );
            throw new ListByIdException( _e );
        }
        catch ( Exception _e )
        {
            logger.error( "Metodo: {} {}", "trainingDetail", e.toString() );
            throw new ListByIdException( _e );
        }
        finally
        {
            closeConnection();
        }

        return entity;
    }

    public Boolean activateTraining( Entity e, Entity a ) throws ActiveTrainingException
    {
        Training entity = null;
        User user = null;
        CallableStatement preStatement = null;
        ResultSet resultSet = null;

        try
        {
            entity = (Training) e;
            user = (User) a;

            preStatement = getBdConnect().prepareCall( "{ call M06_ACTIVE_TRAINING(?) }" );
            preStatement.setInt(1, a.get_id());
            preStatement.setInt(2,e.get_id());

            resultSet = preStatement.executeQuery();
            resultSet.close();

        }
        catch ( SQLException _e )
        {
            logger.error( "Metodo: {} {}", "ActiveTraining", e.toString() );
            throw new ActiveTrainingException( _e );
        }
        catch( BdConnectException _e )
        {
            logger.error( "Metodo: {} {}", "ActiveTraining", e.toString() );
            throw new ActiveTrainingException( _e );
        }
        catch ( Exception _e )
        {
            logger.error( "Metodo: {} {}", "ActiveTraining", e.toString() );
            throw new ActiveTrainingException( _e );
        }
        finally
        {
            closeConnection();
        }

        return true;
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

}
