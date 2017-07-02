package edu.ucab.desarrollo.fitucab.dataAccessLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Activity;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;


import java.util.ArrayList;
import java.util.LinkedList;


public class DaoTraining extends Dao implements IDaoTraining
{


    public DaoTraining( Entity entidad )
    {

    }

    public Entity create( Entity entidad ) throws AddException
    {
        return null;
    }

    public Entity read( Entity e )
    {
        return null;
    }

    public Entity update( Entity e )
    {
        return null;
    }


    public Boolean delete(Entity e)
    {
        return true;
    }

    /**
     * Lista todos los entrenamientos
     *
     * @param entidad
     *
     * @return lista de entrenamientos
     */
    public LinkedList<Entity> listAll( Entity entidad ) throws ListAllException
    {
        return null;
    }


    /**
     * Metodo para mostrar el entrenamiento a detalle
     *
     * @param entidad
     *
     * @return la entidad entrenamiento
     *
     * @throws ListByIdException
     */
    public Entity trainingDetail( Entity entidad ) throws ListByIdException
    {
        return null;
    }


    public Boolean activateTraining( Entity e )
    {
        return null;
    }

    public Boolean shareTraining( Entity e )
    {
        return null;
    }

    /**
     * Metodo para listar las actividades
     *
     * @return lista de actividades
     */
    private ArrayList<Entity> listActivities()
    {
        ArrayList<Entity> resultList = null;
        Activity entity;

        resultList = new ArrayList<Entity>( 5 );

        int id1 = 1;
        String name1 = "Caminar";
        int id2 = 2;
        String name2 = "Trotar";
        int id3 = 3;
        String name3 = "Correr";
        int id4 = 4;
        String name4 = "Lagartijas";
        int id5 = 5;
        String name5 = "Nadar";
        entity = EntityFactory.createActivity( id1, name1 );
        resultList.add( entity );
        entity = EntityFactory.createActivity( id2, name2 );
        resultList.add( entity );
        entity = EntityFactory.createActivity( id3, name3 );
        resultList.add( entity );
        entity = EntityFactory.createActivity( id4, name4 );
        resultList.add( entity );
        entity = EntityFactory.createActivity( id5, name5 );
        resultList.add( entity );


        return resultList;
    }

    public ArrayList<Entity> listTrainingsShared( Entity e )
    {
        return null;
    }

    @Override
    public void Create(Entity e) {

    }
}
