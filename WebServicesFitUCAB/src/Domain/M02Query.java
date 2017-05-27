package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que manejara los querys del modulo 02
 */
public class M02Query {

    private Sql _sql;
    private User _user;

    /**
     * Constructor que inicializa la clase con la conexion a la base de datos
     */
    public M02Query() {
        _sql = new Sql();
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
            _user = new User();
//            ResultSet result = _sql.sql("SELECT M02_CONSULTARPERFILID("+ id +")");
            ResultSet result = _sql.sql("SELECT * FROM PERSON WHERE personid = "+ id +")");
            while (result.next()){
                _user.setId( result.getInt( "id" ) );
                _user.setUser( result.getString( "usuario" ) );
                _user.setEmail( result.getString( "email" ) );
                _user.setSex( result.getString( "sex" ) );
                _user.setPhone( result.getString( "phone" ) );
                _user.setBirthdate( result.getDate( "birthdate" ) );
                /*_user.setAltura( result.getInt( "height" ) );
                _user.setPeso( result.getInt( "weight" ) );*///TODO QUITAR
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
