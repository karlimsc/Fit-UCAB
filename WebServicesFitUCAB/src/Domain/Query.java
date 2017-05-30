package Domain;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Clase perteneciente a PERFIL que manejara los querys
 */
public class Query {

    private Sql _sql;
    private User _user;
    private Home _home;

    /**
     * Constructor vacio
     */
    public Query() {
    }

    /**
     * Metodo que retornara el usuario con un id dado
     * @param id Id que identificara el usuario a extraer
     * @return  Clase User o null si no encuentra nada
     * @throws NullPointerException Si error de null
     * @throws SQLException Si hay un error en sql
     * @throws Exception
     * @See User
     */
    public User getUser( int id ){
        try {
            _sql = new Sql();
            _user = new User();
            ResultSet result = _sql.sql("select * from m02_consultarperfilid("+ id +")");
            while (result.next()){
                _user.setId( result.getInt( "id" ) );
                _user.setUser( result.getString( "usuario" ) );
                _user.setEmail( result.getString( "email" ) );
                _user.setSex( result.getString( "sex" ) );
                _user.setPhone( result.getString( "phone" ) );
                _user.setBirthdate( result.getDate( "birthdate" ) );
                _user.setHeight( result.getInt( "height" ) );
                _user.setWeight( result.getInt( "weight" ) );
            }
            return _user;
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("NullPointerGetUserException : " + e.getMessage() + ", id: " + id);
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLGetUserException :" + e.getMessage() + ", id: " + id);
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("GetUserException : " + e.getMessage() + ", id: " + id);
            return null;
        }
    }

    //TODO: Revisar el error y ver los campos nulos
    /**
     * Metodo que actulizara al usuario
     * @param user Usuario con los parametros a modificar
     * @return Boleano que verfica si el usuario fue actualizado o no
     * @throws SQLException Si hay un error en sql
     * @throws Exception
     * @see User
     */
    public boolean updateUser( User user ){
        try {
            _sql = new Sql();
            ResultSet rs = _sql.sqlConn("select m02_personexiste("+ user.getId() +")");
            rs.next();
            if (rs.getInt("m02_personexiste") == 1){
                if (!user.getPassword().isEmpty()){
                    _sql.sqlConn("select m02_modperfilpass("+ user.getId() +", '"+ user.getPassword() +"')");
                }
                if (!user.getEmail().isEmpty()){
                    _sql.sqlConn("select m02_modperfilmail("+ user.getId() +", '"+ user.getEmail() +"')");
                }
                if (!user.getSex().isEmpty()){
                    _sql.sqlConn("select m02_modperfilsex("+ user.getId() +", '"+ user.getSex() +"')");
                }
                if (!user.getPhone().isEmpty()){
                    _sql.sqlConn("select m02_modperfilphone("+ user.getId() +", '"+ user.getPhone() +"')");
                }
                _sql.closeConnection(_sql.getConn());
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLExceptionUpdate: " + e.getMessage() + " , User: " + user.getId());
            return false;
        } catch (NullPointerException e){
            e.printStackTrace();
            System.err.println("NullPointerExceptionUpdate: " + e.getMessage() + " , User: " + user.getPassword());
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println("ExceptionUpdate: " + e.getMessage() + " , User: " + user.getId());
            return false;
        }
    }

    /**
     * Metodo que actualiza el Home
     * @param id Usuario que brinda sus atributos
     * @return Clase Home con el total de las calorias y el total de los vasos tomados
     * @throws SQLException Si hay un error en sql
     * @throws Exception
     * @see Home
     */
    public Home getHome(int id) {
        try {
            _sql = new Sql();
            User user = new User();
            ResultSet rsW = _sql.sqlConn("SELECT countg FROM m10_getwaterglass("+user.getId()+"," +
                    "'"+user.getBirthdate()+"')");
            ResultSet rsC = _sql.sqlConn("SELECT calorias FROM m11_get_calorias_dia('"+user.getUser()+"')");
            while (rsW.next()){
                _home.setTotalAgua( rsW.getInt("countg") );
                System.out.println("Agua: "+ _home.getTotalAgua());
            }
            while (rsC.next()){
                _home.setTotalCaloria( rsC.getInt("calorias") );
                System.out.println("Calorias: "+ _home.getTotalCaloria());
            }
            _sql.closeConnection(_sql.getConn());
            System.out.println("Calorias: "+ _home.getTotalCaloria());
            System.out.println("Agua: "+ _home.getTotalAgua());
            return new Home( _home.getTotalCaloria(), _home.getTotalAgua() );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
