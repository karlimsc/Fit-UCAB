package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Moment;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by charbel on 24/06/2017.
 */
public class DaoMoment extends Dao implements iDaoMoment {

    ArrayList<Moment> jsonArray;
    Gson gson = new Gson();


    @Override
    public Entity create(Entity e) throws AddException {
        return null;
    }

    @Override
    public Entity read(Entity e) throws SQLException, BdConnectException {

        String query = "Select * from m11_get_momentos()";
        jsonArray = new ArrayList<>();
        Moment moment = (Moment) e;
        Connection conn = Dao.getBdConnect();
        CallableStatement cs = conn.prepareCall("{call m11_get_momentos}");


            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                jsonArray.add(new Moment());
                jsonArray.get(rs.getRow() - 1).set_description(rs.getString("momento"));
                jsonArray.get(rs.getRow() - 1).set_id(rs.getInt("momento_id"));
            }
            moment.jsonArray = jsonArray;

            return moment;

    }

    @Override
    public Entity update(Entity e) {
        return null;
    }



}
