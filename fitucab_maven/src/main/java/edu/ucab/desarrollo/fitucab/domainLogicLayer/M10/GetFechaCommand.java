package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;


import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * comando para agregar fecha
 */
public class GetFechaCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(DeletLastCommand.class);

    Entity _water;
    public String returned;

    /**
     * constructor de agregar fecha
     * @param water
     */
    public GetFechaCommand (Entity water){ _water = water; };

    public void execute() {

        logger.debug("Debug: Obteniendo Fecha->Comando");

        Gson gson = new Gson();

        Water watergGetWater = (Water) _water;


        SimpleDateFormat _sdf2 = new SimpleDateFormat("MM/dd/yyyy");
        Date fecha = new Date();
        watergGetWater.set_time(_sdf2.format(fecha));
        GetWaterCommand cmd = CommandsFactory.instatiateGetWaterCmd(watergGetWater);

        cmd.execute();

        _water = gson.fromJson(cmd.returned,Water.class);
        ((Water) _water).set_time(_sdf2.format(fecha));
        returned = gson.toJson(_water);

    }
    public Entity Return(){
        return null;
    }
}