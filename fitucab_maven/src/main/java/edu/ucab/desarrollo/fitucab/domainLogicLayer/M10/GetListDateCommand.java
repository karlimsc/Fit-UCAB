package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 *
 */
public class GetListDateCommand extends Command {

    Entity _water;
    public String returned;

    /**
     * constructor de getlistdate
     * @param water
     */
    public GetListDateCommand (Entity water){ _water = water; };

    public void execute() {
        returned = "GETLISTDATE";
    }
}

