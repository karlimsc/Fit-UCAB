package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;

import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Comando para agregar agua que extiende de command
 */

public class AddWaterCommand extends Command {

    Entity _water;
    public String returned;

    /**
     * constructor de agregar agua
     * @param water
     */
    public AddWaterCommand (Entity water){ _water = water; };

    public void execute() {
        returned = "AddingWater";
    }
}
