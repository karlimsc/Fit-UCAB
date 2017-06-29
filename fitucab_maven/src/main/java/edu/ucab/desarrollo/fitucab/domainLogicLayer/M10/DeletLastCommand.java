package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Comando para eliminar al ultimo que extiende de command
 */
public class DeletLastCommand extends Command {

    Entity _water;
    public String returned;

    /**
     * constructor de deletcommand
     * @param water
     */
    public DeletLastCommand (Entity water){ _water = water; };

    public void execute() {
        returned = "DELETLAST";
    }
}

