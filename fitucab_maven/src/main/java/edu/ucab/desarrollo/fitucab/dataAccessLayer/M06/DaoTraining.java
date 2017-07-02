package edu.ucab.desarrollo.fitucab.dataAccessLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class DaoTraining extends Dao implements IDaoTraining
{

    Entity _entidad;
    private static Logger logger = LoggerFactory.getLogger( DaoTraining.class );

    public DaoTraining( Entity entidad )

    {
            this._entidad = entidad;
    }

    public Entity create(Entity e) throws AddException {


        Training t = (Training) e;
        String query ="SELECT M06_CREATETRAINING('"+t.get_userId()+"','"+t.getTrainingName()+"')";

        try {
            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //User user = null;


            while (rs.next()) {
                int Id = rs.getInt("id");
                e.set_id(Id);
            }

        }
        catch (SQLException ex) {

        }
        catch (BdConnectException ex) {

        }
        catch (Exception ex) {

        }

        for (Entity activity : t.get_activitylist()) {
            Activity a = (Activity) activity;
            String _query =
                    "SELECT M06_ADDTRAINING_ACTIVITY('" + a.get_duration() + "', '" + e.get_id() + "', '" + a.get_id() + ")";
            try {

                Connection conn = Dao.getBdConnect();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(_query);
                //User user = null;

            }
            catch (SQLException ex) {
                //throw new ExceptionPropia esto se hace en cada Exception
            }
            catch (BdConnectException ex) {

            }
            catch (Exception ex) {

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


    public Boolean delete(Entity e)
    {
        Training t = (Training) e;
        String query ="SELECT M06_DELETETRAINING('"+t.get_id()+"')";

        try {
            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
        }
        catch (SQLException ex) {

        }
        catch (BdConnectException ex) {

        }
        catch (Exception ex) {

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
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        Connection conn;

        try
        {
            resultList = new LinkedList<Entity>();
            conn = getBdConnect();

            //Aqui se invoca el SP
            preStatement = conn.prepareStatement( "{call M06_GET_TRAININGS(?)}" );
            //Aqui meto los parametros
            preStatement.setInt( 1, entidad.get_id() );
            //Aqui ejecuto el SP
            resultSet = preStatement.executeQuery();

            while ( resultSet.next() )
            {
                int id = resultSet.getInt( "training_id" );
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
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        EntityMapTraining etMap;

        try
        {
            etMap = EntityMapTraining.getInstance();
            entity = etMap.get( entidad.get_id() );

            if( entity == null )
            {
                // Aqui se invoca el SP
                CallableStatement test = getBdConnect().prepareCall( "{ call M06_GET_TRAINING_DETAILS(?) }" );
                // Aqui meto los parametros
                entity = (Training) entidad;
                //preStatement.setInt( 1, entidad.get_id() );
                test.setInt(1, entidad.get_id());
                //Aqui ejecuto el SP
                resultSet = test.executeQuery();

                int id = resultSet.getInt( "training_id" );
                String name = resultSet.getString( "training_name" );

                entity = EntityFactory.createTraining( id, name);
                entity.set_activitiesList( listActivities() );
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

    public Boolean shareTraining( Entity e )
    {
        Training t = (Training) e;
        String query ="SELECT M06_CREATETRAINING('"+t.get_userId()+"','"+t.getTrainingName()+"')";

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
                rs = st.executeQuery(query);
                //User user = null;
            }

        }
        catch (SQLException ex) {

        }
        catch (BdConnectException ex) {

        }
        catch (Exception ex) {

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

    public Boolean modifyName(Entity e) {
        Training t = (Training) e;
        String query ="SELECT M06_MODIFYNAMETRAINING('"+t.get_id()+"','"+t.getTrainingName()+"')";

        try {

            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //User user = null;

        }
        catch (SQLException ex) {

        }
        catch (BdConnectException ex) {

        }
        catch (Exception ex) {

        }

        return true;
    }

    public Boolean addActivities(Entity e) {

        try {
            Training t = (Training) e;
            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            //User user = null;

            for (Entity activity : t.get_activitylist()) {
                Activity a = (Activity) activity;
                String _query =
                        "SELECT M06_ADDTRAINING_ACTIVITY('" + a.get_duration() + "', '" + e.get_id() + "', '" + a.get_id() + ")";
                conn = Dao.getBdConnect();
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(_query);
                //User user = null;
            }

        }
        catch (SQLException ex) {

        }
        catch (BdConnectException ex) {

        }
        catch (Exception ex) {

        }

        return true;
    }

    public Boolean removeActivities(Entity e) {
        try {
            Training t = (Training) e;
            Connection conn = Dao.getBdConnect();
            Statement st = conn.createStatement();
            //User user = null;

            for (Entity activity : t.get_activitylist()) {
                Activity a = (Activity) activity;
                String _query =
                        "SELECT M06_DELETE_ACTIVITY(" + e.get_id() + "', '" + a.get_id() + ")";
                conn = Dao.getBdConnect();
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(_query);
                //User user = null;
            }

        }
        catch (SQLException ex) {

        }
        catch (BdConnectException ex) {

        }
        catch (Exception ex) {

        }

        return true;
    }
}
