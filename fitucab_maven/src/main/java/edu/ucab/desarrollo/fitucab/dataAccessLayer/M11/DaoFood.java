package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.Exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Food;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by charbel on 24/06/2017.
 */
public class DaoFood implements IDaoFood {
    private Connection conn = Sql.getConInstance();
    //Atributo que se utiliza para transformar a formado JSON las consultas.
    private Gson gson = new Gson();
    private String response;
    private ArrayList<Food> jsonArray;

    public DaoFood() {  }

    @Override
    public void Create(Entity e) {

    }

    @Override
    public Entity create(Entity e) throws AddException {
        return null;
    }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }



    @Override
    public String getFoodPer(Entity e) throws SQLException {

        String query = "SELECT * FROM m11_get_alimentos_person(?)";
        jsonArray = new ArrayList<>();
        Food food = (Food) e;
        String username= String.valueOf(food.get_id());

        PreparedStatement stm = conn.prepareStatement(query);
        stm.setString(1, username);
        ResultSet rs = stm.executeQuery();

        while(rs.next()){
            jsonArray.add(new Food());
            jsonArray.get(jsonArray.size()).set_foodName(rs.getString("nombre_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
        }




        return  gson.toJson(jsonArray);
    }
}
