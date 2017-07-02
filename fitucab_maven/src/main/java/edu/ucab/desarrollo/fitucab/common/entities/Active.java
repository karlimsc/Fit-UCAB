package edu.ucab.desarrollo.fitucab.common.entities;

import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Actividad que juega el papel de invocador de los comandos.
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class Active extends Entity {
    private Command _command;

    /**
     * Constructor que inicializa el comando
     * @param command
     */
    public Active(Command command) {
        _command = command;
    }

    /**
     * Metodo que ejecutara todos los comandos.
     * @throws ListByIdException
     * @throws ListAllException
     */
    public void exec() throws Exception {
        _command.execute();
    }
}
