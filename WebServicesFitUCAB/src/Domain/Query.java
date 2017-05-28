package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que manejara los querys
 */
public class Query {

    private Sql _sql;
    private User _user;

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
}
