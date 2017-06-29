package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;



public class GetFechaIntCommand extends Command {

    Entity _water;
    public String returned;

    public GetFechaIntCommand (Entity water){ _water = water; };

    public void execute() {
        returned = "GETFECHAINT";
    }
}