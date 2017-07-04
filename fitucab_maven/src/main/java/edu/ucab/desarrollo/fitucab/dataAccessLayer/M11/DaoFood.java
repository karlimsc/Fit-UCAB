package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Food;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by charbel on 24/06/2017.
 */
public class DaoFood extends Dao implements IDaoFood {

    //Atributo que se utiliza para transformar a formado JSON las consultas.
    private Gson gson = new Gson();
    private String response;
    private ArrayList<Food> jsonArray;
    String username;
    String calorie;

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
    public Entity getFoodPer(Entity e) throws SQLException, BdConnectException {

        String query = "SELECT * FROM m11_get_alimentos_person(?)";
        jsonArray = new ArrayList<>();
        Food food = (Food) e;


        Connection conn = Dao.getBdConnect();


        CallableStatement stm = conn.prepareCall("{? = Call m11_get_alimentos_person() }");
       // PreparedStatement stm = conn.prepareStatement(query);
        stm.setString(1, food.get_idname());
        ResultSet rs = stm.executeQuery();

        while(rs.next()){
            jsonArray.add((Food)EntityFactory.CreateFood());
            jsonArray.get(jsonArray.size()).set_foodName(rs.getString("nombre_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
        }

        Food aux = (Food) EntityFactory.CreateFood();
        aux.jsonArray=jsonArray;


        return aux;
    }

    @Override
    public Entity getFoodAll(Entity e) throws SQLException, BdConnectException {

        Food food = (Food) e;
        username= String.valueOf(food.get_id());

        String query = "select * from m11_get_todos_alimentos(?)";
        jsonArray = new ArrayList<>();

        Connection conn = Dao.getBdConnect();


        CallableStatement st = conn.prepareCall("{? = Call  m11_get_todos_alimentos() }");

     //   PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, food.get_idname());
        ResultSet rs = st.executeQuery();

        while(rs.next()) {
            jsonArray.add((Food)EntityFactory.CreateFood());
            jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
            // revisar string
            jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
        }

        Food aux = (Food) EntityFactory.CreateFood();
        aux.jsonArray=jsonArray;


        return aux;
    }

    @Override
    public Entity getSugge(Entity e) throws SQLException, BdConnectException {

        Food food = (Food) e;
        String query = "select * from m11_get_alimentos_sugerencia(?, ?)";
        jsonArray = new ArrayList<>();
        username = String.valueOf(food.get_id());//Revisar aqui
        calorie = food.get_foodCalorie();
        Connection conn = Dao.getBdConnect();

        CallableStatement st = conn.prepareCall("{? = Call m11_get_alimentos_sugerencia(?) }");

       // PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        st.setInt(2, Integer.parseInt(calorie));
        ResultSet rs = st.executeQuery();

        while(rs.next()) {
            jsonArray.add((Food)EntityFactory.CreateFood());
            jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
            // revisar string
            jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
        }

        Food aux = (Food) EntityFactory.CreateFood();
        aux.jsonArray=jsonArray;


        return aux;
    }



    @Override
    public Entity getFoodAuto(Entity e) throws SQLException, BdConnectException {

        Food food = (Food) e;
        String query = "select * from m11_get_todos_alimentos_autocompletar(?)";
        jsonArray = new ArrayList<>();
        username  = String.valueOf(food.get_id());
        Connection conn = Dao.getBdConnect();

        CallableStatement st = conn.prepareCall("{? = Call m11_get_todos_alimentos_autocompletar() }");

        st.setString(1, food.get_idname());
        ResultSet rs = st.executeQuery();

        while(rs.next()) {

            Food aux = (Food) EntityFactory.CreateFood();
            aux.set_id(rs.getInt("id_alimento"));
            aux.set_foodName(rs.getString("nombre_comida"));
            aux.set_foodCalorie(rs.getString("calorias_comida"));
            aux.set_foodWeight(rs.getString("peso_comida"));
            jsonArray.add(aux);

 /*
            jsonArray.add((Food) EntityFactory.CreateFood());

            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
            jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
            // revisar string
            jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
  */

        }
        Food aux = (Food) EntityFactory.CreateFood();
        aux.jsonArray=jsonArray;


        return aux;
    }




    @Override
    public Entity DeletPerFood(Entity e) throws SQLException, BdConnectException {
        Map<String, String> response = new HashMap<String, String>();
        String query = "select * from m11_elimina_alimento_person(?, ?)";
        Food food = (Food) e;


        Connection conn = Dao.getBdConnect();
        CallableStatement st = conn.prepareCall("{call  m11_elimina_alimento_person(?, ?)}");

      //  PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, food.get_foodName());
        st.setInt(2, food.get_id());
        st.executeQuery();
        response.put("data", "Se elimino el alimento de forma exitosa");

        Food aux = (Food) EntityFactory.CreateFood();
        aux.setResponse(response);

        return aux;
    }




    @Override
    public Entity updatePerso(Entity e) throws SQLException, BdConnectException {
        Map<String, String> response = new HashMap<String, String>();
        Food food = (Food) e;
        String query = "select * from m11_act_alimento_person(?, ?, ?, ?)";


        Connection conn = Dao.getBdConnect();
        CallableStatement st = conn.prepareCall("{call  m11_act_alimento_person(?, ?, ?, ?)}");

        //PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, food.get_foodName());
        st.setInt(2, Integer.parseInt(food.get_foodWeight()));
        st.setInt(3, Integer.parseInt(food.get_foodCalorie()));
        st.setInt(4, food.get_id());
        st.executeQuery();
        response.put("data", "Se actualizo el alimento de forma exitosa");

        Food aux = (Food) EntityFactory.CreateFood();
        aux.setResponse(response);

        return aux;
    }



    @Override
    public Entity InsertarAlimen(Entity e) throws SQLException, BdConnectException {
        Map<String, String> response = new HashMap<String, String>();

        Food food = (Food) e;
        String query = "select m11_inserta_alim_person(? , ?, ?, ?, ?)";

        Connection conn = Dao.getBdConnect();



        CallableStatement st = conn.prepareCall("{? = call m11_inserta_alim_person(? , ?, ?, ?)}");



      //  PreparedStatement st = conn.prepareStatement(query);

        st.setString(1, food.get_foodName());
        st.setInt(2, Integer.parseInt(food.get_foodCalorie()));
        st.setInt(3, Integer.parseInt(food.get_foodWeight()));
        st.setBoolean(4 , food.get_foodPersonalized());
        st.setInt(5, food.get_id());




        st.executeQuery();
        response.put("data", "Se insertaron los alimentos de forma exitosa");

        Food aux = (Food) EntityFactory.CreateFood();
        aux.setResponse(response);

        return aux;
    }

    @Override//listo
    public Entity insertarPersoFood(Entity e) throws SQLException, BdConnectException {
        Map<String, String> response = new HashMap<String, String>();
        Food food = (Food) e;

        String query = "select * from m11_inserta_alim_person(? , ?, ?)";
        Type type = new TypeToken<Food[]>(){}.getType();

/*
            Food[] a = new Food[3];

        a[0] = new Food( "cachapa",  "52","20", true,1);
        a[1] = new Food( "cachapa2",  "52","20", true,1);
        a[2] = new Food( "cachapa3",  "52","20", true,1);
        String jsonAlimentos = gson.toJson(a);
          Food[] alimentos = gson.fromJson(jsonAlimentos, type);
*/
        Food[] alimentos = gson.fromJson(food.getJson(), type);

        Connection conn = Dao.getBdConnect();

        CallableStatement st = conn.prepareCall("{call m11_inserta_alim_person(? , ?, ?}");

       // PreparedStatement st = conn.prepareStatement(query);
        for (int i = 0; i < alimentos.length; i++) {
            st.setString(1, alimentos[i].get_foodName());
            st.setInt(2, Integer.parseInt(alimentos[i].get_foodWeight()));
            st.setInt(3, Integer.parseInt(alimentos[i].get_foodCalorie()));
            st.executeQuery();
        }

        response.put("data", "Se insertaron los alimentos de forma exitosa");

        Food aux = (Food) EntityFactory.CreateFood();
        aux.setResponse(response);

        return aux;
    }

    @Override//listo
    public Entity getPersonalizedLis(Entity e) throws BdConnectException, SQLException {

        Food food = (Food) e;

        Connection conn = Dao.getBdConnect();
     //   String query = "select * from m11_get_alimentos_person_lista(?)";

        CallableStatement st = conn.prepareCall("{? = Call m11_get_alimentos_person_lista () }");
        st.setString(1, food.get_idname());
        ResultSet rs = st.executeQuery();
        jsonArray = new ArrayList<>();
        while(rs.next()){
            jsonArray.add((Food)EntityFactory.CreateFood());
            jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));

        }
        Food aux = (Food) EntityFactory.CreateFood();
        aux.jsonArray=jsonArray;


        return aux;
    }


}
