package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;


import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;


/**
 * comando para agregar fecha
 */
public class GetFechaCommand extends Command {

    Entity _water;
    public String returned;

    /**
     * constructor de agregar fecha
     * @param water
     */
    public GetFechaCommand (Entity water){ _water = water; };

    public void execute() {
        returned = "GETFECHA";
    }
}