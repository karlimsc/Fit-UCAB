package WebServicesClasses;



import Domain.Sql;
import Domain.Nivel;
import Domain.Size;
import Domain.SubirNivel;
import Domain.Detail;
import Domain.Data;
import Domain.Achievement;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;

import static Domain.Sql.getConInstance;

/**
 * Created by root on 15/05/17.
 */
@Path("/M09_ServicesGamification")
public class M09_ServicesGamification {
    private int _level1 = 1000;
    private int _level2 = 2200;
    private int _level3 = 3600;
    private int _level4 = 5200;
    private int _level5 = 7000;
    private int _level6 = 9000;
    private int _level7 = 11200;
    private int _level8 = 13600;
    private int _level9 = 16200;
    private int _level10 = 19000;
    Gson gson = new Gson();
    private Connection conn = getConInstance();

    /**
     * Informacion de todos los retos logrados
     * @param _id
     * @return
     */
    @GET
    @Path("/obtenerretos")
    @Produces("application/json")
    public String getRetos(@QueryParam("id") int _id){
      String nombre = "";
      int userid =_id;
      String query = "SELECT * FROM CHALLENGE C, DETAIL D WHERE D.FK_CHALLENGEID = C.CHALLENGEID"+
              " AND D.FK_USERID =" +userid+" AND D.DETAILACTIVE = TRUE";
      int tamaño = 0;
      try{
          Statement st = conn.createStatement();
          ResultSet rs = st.executeQuery(query);
          while(rs.next()){
              tamaño++;
          }
      }catch (SQLException E){
          return E.getMessage();
      }
        Achievement logros = new Achievement(tamaño);
        query = "SELECT C.* FROM CHALLENGE C, DETAIL D WHERE D.FK_CHALLENGEID = C.CHALLENGEID"+
                " AND D.FK_USERID = "+userid+" AND D.DETAILACTIVE = TRUE";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Data data = new Data();
                data.setId(rs.getInt("challengeid"));
                data.setName(rs.getString("challengename"));
                data.setDescription(rs.getString("challengedescription"));
                data.setScore(rs.getInt("challengescore"));
                logros.set_challenges(data);
            }

            return gson.toJson(logros);
        }catch (SQLException E){
            return E.getMessage();
        }

    }

    /**
     * Cantidad de retos logrados y no logrados (Grafica)
     * @param _id
     * @return
     */
    @GET
    @Path("/obtenerlogrados")
    @Produces("application/json")
    public String getCantidad(@QueryParam("id") int _id) {
        String nombre = "";

        String query = "SELECT * FROM  DETAIL D WHERE D.FK_USERID = "+_id;
        int tamaño = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                tamaño ++;
            }

        } catch (SQLException E) {
            return E.getMessage();
        }
        //Todos los restos que ha empezado
        Size grafica = new Size();
        query = "SELECT C.* FROM CHALLENGE C, DETAIL D WHERE D.FK_CHALLENGEID = C.CHALLENGEID" +
                " AND D.FK_USERID = "+ _id +" AND D.DETAILACTIVE = TRUE";
        int tamañoLogrado = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                tamañoLogrado++;

            }
            int tamañoNoLogrado;
            tamañoNoLogrado=tamaño-tamañoLogrado;
            grafica.setLogrado(tamañoLogrado);
            grafica.setNoLogrado(tamañoNoLogrado);
            return gson.toJson(grafica);
        } catch (SQLException E) {
            return E.getMessage();
        }

    }

    /**
     * Puntuacion de los retos logrados
     * @param _id
     * @return
     */
    @GET
    @Path("/obtenernivel")
    @Produces("application/json")
    public String getQuantity(@QueryParam("id") int _id) {
        String _name = "";

        String query = "SELECT C.CHALLENGESCORE FROM CHALLENGE C, DETAIL D WHERE D.FK_CHALLENGEID = C.CHALLENGEID" +
                " AND D.FK_USERID ="+ _id +" AND D.DETAILACTIVE = TRUE";
        int _size = 0;
        int _score= 0;
        int _level = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Data _dataScore = new Data();
            while (rs.next()){
                _dataScore.setScore(rs.getInt("challengescore"));
                _score = _dataScore.getScore();
                _size=_size+_score;
            }

        } catch (SQLException E) {
            return E.getMessage();
        }
        if (_size <_level1)
            _level = 1;
        else if ((_size >= _level1) && (_size < _level2))
            _level = 2;
        else if ((_size >= _level2) && (_size < _level3))
            _level = 3;
        else if ((_size >= _level3) && (_size < _level4))
            _level = 4;
        else if ((_size >= _level4) && (_size < _level5))
            _level = 5;
        else if ((_size >= _level5) && (_size < _level6))
            _level = 6;
        else if ((_size >= _level6) && (_size < _level7))
            _level = 7;
        else if ((_size >= _level7) && (_size < _level8))
            _level = 8;
        else if ((_size >= _level8) && (_size < _level9))
            _level = 9;
        else if (_size >= _level9)
            _level = 10;

        Nivel _Level = new Nivel();
        _Level.setNivel(_level);
        return gson.toJson(_Level);
    }


    /**
     * No se llego a implementar en android (grupo retiro)
     * Muestra cuando aumentas de nivel
     * @param _plus
     * @param _id
     * @return
     */
    @GET
    @Path("/obtenerverificarnivel")
    @Produces("application/json")
    public String getSubirNivel(@QueryParam("_plus") int _plus, @QueryParam("id") int _id) {
        String nombre = "";

        String query = "SELECT C.CHALLENGESCORE FROM CHALLENGE C, DETAIL D WHERE D.FK_CHALLENGEID = C.CHALLENGEID" +
                " AND D.FK_USERID ="+ _id +" AND D.DETAILACTIVE = TRUE";
        int _size = 0;
        int _score = 0;
        Boolean _subirLevel = Boolean.FALSE;
        String _menssage = "";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Data _dataScore = new Data();
            while (rs.next()) {
                _dataScore.setScore(rs.getInt("challengescore"));
                _score = _dataScore.getScore();
                _size = _size + _score;
            }

        } catch (SQLException E) {
            return E.getMessage();
        }
        _plus = _size + _plus;

        if ((_size < _level1) && (_plus >= _level1)) {

            _menssage = "¡Felicitaciones por haber alcanzado el nivel 2! ¡Sigue asi!";
            _subirLevel = Boolean.TRUE;

        } else if ((_size < _level2) && (_plus >= _level2)) {

            _menssage = "¡Felicitaciones por haber alcanzado el nivel 3! ¡Si se puede!";
            _subirLevel = Boolean.TRUE;

        } else if ((_size < _level3) && (_plus >= _level3)) {

            _menssage = "¡Felicitaciones por haber alcanzado el nivel 4! ¡Excelente trabajo!";
            _subirLevel = Boolean.TRUE;

        } else if ((_size < _level4) && (_plus >= _level4)) {

            _menssage = "¡Felicitaciones por haber alcanzado el nivel 5! ¡Estas a a mitad del camino!";
            _subirLevel = Boolean.TRUE;

        } else if ((_size < _level5) && (_plus >= _level5)) {

            _menssage = "¡Felicitaciones por haber alcanzado el nivel 6! ¡Estas entre los mejores!";
            _subirLevel = Boolean.TRUE;

        } else if ((_size < _level6) && (_plus >= _level6)) {

            _menssage = "¡Felicitaciones por haber alcanzado el nivel 7! ¡El estadio te aclama!";
            _subirLevel = Boolean.TRUE;

        } else if ((_size < _level7) && (_plus >= _level7)) {

            _menssage = "¡Felicitaciones por haber alcanzado el nivel 8! ¡Dominando el mundo!";
            _subirLevel = Boolean.TRUE;

        } else if ((_size < _level8) && (_plus >= _level8)) {

            _menssage = "¡Felicitaciones por haber alcanzado el nivel 9! ¡Imparable!";
            _subirLevel = Boolean.TRUE;

        } else if ((_size < _level9) && (_plus >= _level9)) {

            _menssage = "¡Felicitaciones por haber alcanzado el nivel 4! ¡Eres el mejor de todos!";
            _subirLevel = Boolean.TRUE;

        }else if ((_size < _level10) && (_plus >= _level10)) {

            _menssage = "¡Felicitaciones por haber alcanzado el nivel 4! ¡Eres el mejor de todos!";
            _subirLevel = Boolean.TRUE;

        }

        SubirNivel sb = new SubirNivel();
        sb.setSubirNivel(_menssage);
        sb.set_subir(_subirLevel);
        return gson.toJson(sb);
    }
}
