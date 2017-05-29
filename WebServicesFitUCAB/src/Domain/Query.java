package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que manejara los querys
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
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
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
            ResultSet rs = _sql.sql("SELECT m02_personexiste("+user.getId()+")");
            rs.getRow();
            while (rs.next()) {
                if (rs.getInt("result") == 1) {
                    if (!user.getPassword().isEmpty()){
                        _sql.sql("select m02_modperfilpass("+ user.getId() +","+ user.getPassword() +")");
                    }
                    if (!user.getEmail().isEmpty()){
                        _sql.sql("select m02_modperfilpass("+ user.getId() +","+ user.getEmail() +")");
                    }
                    if (!user.getSex().isEmpty()){
                        _sql.sql("select m02_modperfilpass("+ user.getId() +","+ user.getSex() +")");
                    }
                    if (!user.getPhone().isEmpty()){
                        _sql.sql("select m02_modperfilpass("+ user.getId() +","+ user.getPhone() +")");
                    }
                }
                else {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //TODO: Realizar consulta
    /**
     * Metodo que actualiza el Home
     * @return Clase Home con el total de las calorias y el total de los vasos tomados
     * @throws SQLException Si hay un error en sql
     * @throws Exception
     * @see Home
     */
    public Home getHome() {
        try {
            _sql = new Sql();
            ResultSet result = _sql.sql("select 2 totalAgua, 3 totalCalorias;");
            while (result.next()){
                _home.setTotalAgua( result.getInt("totalAgua") );
                _home.setTotalCaloria( result.getFloat("totalCalorias") );
            }
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
