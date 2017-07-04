package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.ucab.desarrollo.fitucab.common.entities.Diet;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;

import java.lang.reflect.Type;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DaoDiet extends Dao implements IDaoDiet {


    private Gson gson = new Gson();
    private String response;
    private ArrayList<Diet> jsonArray;
    String username;
    String date;

    @Override
    public void Create(Entity e) {

    }

    @Override
    public Entity create(Entity e) throws AddException, AddException, AddException {
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


    public Entity GetCaloriesDate(Entity e) throws SQLException, BdConnectException {


        String query = "SELECT * FROM m11_get_calorias_fecha(?, ?)";
        jsonArray = new ArrayList<>();
        Diet diet = (Diet) e;
        date = String.valueOf(diet.get_date());
        username = diet.get_username();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-mm");
        java.util.Date today = new java.util.Date();
        Date date1 = new Date(today.getTime());

       // String a = dateFormat.format(cal.getTime());

        Connection conn = Dao.getBdConnect();
        CallableStatement stm = conn.prepareCall("{? = Call m11_get_calorias_fecha(?) }");


        stm.setDate(1, date1);
        stm.setString(2, username);
        ResultSet rs = stm.executeQuery();
        //La variable donde se almacena el resultado de la consulta.
        while (rs.next()) {

            jsonArray.add((Diet) EntityFactory.createDiet());
            jsonArray.get(jsonArray.size() - 1).set_calorie(rs.getInt("calorias"));

        }

        response = gson.toJson(jsonArray);
        Diet aux = (Diet) EntityFactory.createDiet();
        aux.jsonArray = jsonArray;

        return aux;

    }

    public Entity deleteDiet(Entity e) throws SQLException, BdConnectException {

        Diet diet = (Diet) e;
        Map<String, String> response = new HashMap<String, String>();

        String query = "SELECT m11_elimina_alimento_dieta(?, ?)";
        Connection conn = Dao.getBdConnect();
       PreparedStatement st = conn.prepareStatement(query);


        st.setString(1, diet.get_date());
        st.setString(2, diet.get_username());
        ResultSet rs = st.executeQuery();
        response.put("data", "Se elimino exitosamente");

        Diet aux = (Diet) EntityFactory.createDiet();
        aux.jsonArray = jsonArray;

        return aux;

    }


    public Entity getMomentFood(Entity e) throws SQLException, BdConnectException {

        Diet diet = (Diet) e;
        String query = "select * from m11_get_comida_momento(?, ?, ?)";
        jsonArray = new ArrayList<>();
        Connection conn = Dao.getBdConnect();
       // PreparedStatement st = conn.prepareStatement(query);
        CallableStatement st = conn.prepareCall("{? = Call m11_get_comida_momento(?, ?) }");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-mm");
        java.util.Date today = new java.util.Date();
        Date date1 = new Date(today.getTime());

        st.setString(1, diet.get_moment());
        st.setDate(2, date1);
        st.setString(3, diet.get_username());
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            jsonArray.add((Diet) EntityFactory.createDiet());
            jsonArray.get(jsonArray.size() - 1).set_calorie(rs.getInt("calorias"));
            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_dieta"));
            jsonArray.get(jsonArray.size() - 1).set_food(rs.getString("nombre"));
        }

        response = gson.toJson(jsonArray);
        Diet aux = (Diet) EntityFactory.createDiet();
        aux.jsonArray = jsonArray;

        return aux;

    }


    public Entity getCaloriesConsumedDay(Entity e) throws SQLException, BdConnectException {


        String query = "select * from m11_get_calorias_mes(?, ?, ?)";
        jsonArray = new ArrayList<>();
        ResultSet rs;
        Diet diet = (Diet) e;
        LocalDate fecha = LocalDate.now();
        username = String.valueOf(diet.get_username());
        Date day;
        Connection conn = Dao.getBdConnect();

        CallableStatement st = conn.prepareCall("{? = Call m11_get_calorias_mes(?, ?) }");
        //PreparedStatement st = conn.prepareStatement(query);


        st.setString(1, username);

        for (int i = 0; i <= 6; i++) {
            day = Date.valueOf(fecha);
            st.setDate(2, day);
            st.setDate(3, day);
            rs = st.executeQuery();
            jsonArray.add((Diet) EntityFactory.createDiet());
            jsonArray.get(jsonArray.size() - 1).set_dateTime(fecha);

            if (rs.wasNull()) {
                jsonArray.get(jsonArray.size() - 1).set_calorie(0);
            }
            while (rs.next()) {
                jsonArray.get(jsonArray.size() - 1).set_calorie(rs.getInt("calorias"));
            }

            if (i < 6) {
                fecha = fecha.minusDays(1);
            }
        }

        response = gson.toJson(jsonArray);
        Diet aux = (Diet) EntityFactory.createDiet();
        aux.jsonArray = jsonArray;

        return aux;
    }


    public Entity getCaloriesConsumedWeek(Entity e) throws SQLException, BdConnectException {


        String query = "select * from m11_get_calorias_mes(?, ?, ?)";
        jsonArray = new ArrayList<>();
        ResultSet rs;
        Diet diet = (Diet) e;
        LocalDate fecha = LocalDate.now();
        username = String.valueOf(diet.get_username());
        Date day;
        Connection conn = Dao.getBdConnect();
        CallableStatement st = conn.prepareCall("{? = Call m11_get_calorias_mes(?, ?) }");
       // PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);

        for (int i = 0; i <= 3; i++) {
            day = Date.valueOf(fecha);
            st.setDate(2, day);
            st.setDate(3, day);
            rs = st.executeQuery();
            jsonArray.add((Diet) EntityFactory.createDiet());
            jsonArray.get(jsonArray.size() - 1).set_dateTime(fecha);
            if (rs.wasNull()) {
                jsonArray.get(jsonArray.size() - 1).set_calorie(0);
            }
            while (rs.next()) {
                jsonArray.get(jsonArray.size() - 1).set_calorie(rs.getInt("calorias"));
            }

            if (i < 6) {
                fecha = fecha.minusWeeks(1);
            }
        }
        response = gson.toJson(jsonArray);
        Diet aux = (Diet) EntityFactory.createDiet();
        aux.jsonArray = jsonArray;

        return aux;

    }


    public Entity getCaloriesConsumedMonth(Entity e) throws SQLException, BdConnectException {


        String query = "select * from m11_get_calorias_mes(?, ?, ?)";
        ResultSet rs;
        jsonArray = new ArrayList<>();
        LocalDate fecha = LocalDate.now();
        fecha = fecha.with(TemporalAdjusters.firstDayOfMonth());
        Date fechaInicio = Date.valueOf(fecha);
        Date fechafin = Date.valueOf(fecha.with(TemporalAdjusters.lastDayOfMonth()));
        Connection conn = Dao.getBdConnect();
        //PreparedStatement st = conn.prepareStatement(query);
        CallableStatement st = conn.prepareCall("{? = Call m11_get_calorias_mes(?, ?) }");
        st.setString(1, username);
        st.setDate(2, fechaInicio);
        st.setDate(3, fechafin);
        for (int i = 0; i <= 11; i++) {
            if (i > 0) {
                fecha = fecha.minusMonths(1);
                fechaInicio = Date.valueOf(fecha);
                fechafin = Date.valueOf(fecha.with(TemporalAdjusters.lastDayOfMonth()));
                st.setDate(2, fechaInicio);
                st.setDate(3, fechafin);
            }

            rs = st.executeQuery();
            jsonArray.add((Diet) EntityFactory.createDiet());
            if (rs.wasNull()) {
                jsonArray.get(jsonArray.size() - 1).set_calorie(0);
                jsonArray.get(jsonArray.size() - 1).set_dateTime(fechaInicio.toLocalDate());
            } else {

                while (rs.next()) {
                    jsonArray.get(jsonArray.size() - 1).set_calorie(rs.getInt("calorias"));
                    jsonArray.get(jsonArray.size() - 1).set_dateTime(fechaInicio.toLocalDate());
                }
            }
        }
        response = gson.toJson(jsonArray);
        Diet aux = (Diet) EntityFactory.createDiet();
        aux.jsonArray = jsonArray;

        return aux;

    }

    public Entity insertDiet(Entity e) throws SQLException, BdConnectException {

        Map<String, String> response = new HashMap<String, String>();
        String query = "select * from m11_inserta_dieta(?, ?, ?, ?)";
        Connection conn = Dao.getBdConnect();
        //PreparedStatement st = conn.prepareStatement(query);
        CallableStatement st = conn.prepareCall("{? = Call m11_inserta_dieta(?, ?, ?) }");
        Type type = new TypeToken<Diet[]>(){}.getType();
        Diet diet = (Diet) e;
            /*
            Diet[] a = new Diet[3];
            a[0] = new Diet(20, "cachapa", "desayuno", "Jesus");
            a[2] = new Diet(2, "cachap2", "almuerzo", "Jesus");
            a[1] = new Diet(3, "cachap3", "cena", "Jesus");
            jsonDieta = gson.toJson(a);
            */
        Diet[] dieta = gson.fromJson(gson.toJson(diet.jsonArray), type);

        for (int i = 0; i < dieta.length; i++) {
            st.setInt(1, dieta[i].get_calorie());
            st.setString(2, dieta[i].get_food());
            st.setString(3, dieta[i].get_moment());
            st.setString(4, dieta[i].get_username());
            st.executeQuery();
        }

        response.put("data", "Se inserto la dieta de forma exitosa");
        Diet aux = (Diet) EntityFactory.createDiet();
        aux.setResponse(response);
        return aux;
    }


    //revisa esta mierda
    public Entity insertOneDiet(Entity e) throws SQLException, BdConnectException {

        Map<String, String> response = new HashMap<String, String>();


        String query = "select * from m11_inserta_dieta(?, ?, ?, ?)";
        Connection conn = Dao.getBdConnect();
        CallableStatement st = conn.prepareCall("{? = Call m11_inserta_dieta(?, ?, ?) }");
        //PreparedStatement st = conn.prepareStatement(query);
        Diet diet = (Diet) e;
        Type type = new TypeToken<Diet[]>() {

        }.getType();

        st.setInt(1, diet.get_calorie());
        st.setString(2, diet.get_food());
        st.setString(3, diet.get_moment());
        st.setString(4, diet.get_username());
        st.executeQuery();

        response.put("data", "Se inserto la dieta de forma exitosa");


        Diet aux = (Diet) EntityFactory.createDiet();
        aux.jsonArray = jsonArray;

        return aux;

    }

}


