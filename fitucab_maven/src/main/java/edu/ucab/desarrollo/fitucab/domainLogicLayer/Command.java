package edu.ucab.desarrollo.fitucab.domainLogicLayer;


import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;

import java.util.List;

public abstract class Command
{
    public abstract void execute();

    public List<Entity> getChallenges(){
        return null;
    }

}