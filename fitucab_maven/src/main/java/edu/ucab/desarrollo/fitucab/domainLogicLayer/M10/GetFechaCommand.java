package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;


import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


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
        Gson gson = new Gson();

        GetWaterCommand cmd = CommandsFactory.instatiateGetWaterCmd(_water);

        Water _water = (Water) EntityFactory.createWater();



        cmd.execute();

        _water = gson.fromJson(cmd.returned,Water.class);

        SimpleDateFormat _sdf2 = new SimpleDateFormat("MM/dd/yyyy");
        Date fecha = new Date();
        _water.set_time(_sdf2.format(fecha));

        returned = gson.toJson(_water);

    }
    public Entity Return(){
        return null;
    }
}