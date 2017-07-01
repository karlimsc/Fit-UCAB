package edu.ucab.desarrollo.fitucab.dataAccessLayer.M01;

import com.google.gson.Gson;

import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Security;
import org.slf4j.LoggerFactory;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.Properties;


/**
 * Created by karo on 24/06/17.
 */
public class DaoUser  extends Dao implements IDaoUser {
    //Conexion con la base de datos

    private int RESULT_CODE_OK=200;
    private int RESULT_CODE_FAIL=300;
    private int RESULT_USER_FAIL=400;
    private int RESULT_EMAIL_OK=500;

    private Connection _bdCon;
    //Encargado de encriptar la contraseña
    private Security   _sc;

    //String de conexion funciones
    String _sqlInicioSesion="{?=call M01_INICIARSESION(?,?)}";
    String _sqlLastUser="{?=call M01_LASTUSER()}";
    String _sqlRegistrarUsuario="{?=call M01_REGISTRAR(?,?,?,?,?,?,?,?)}";

    //String de conexion procedimientos
    String _sqlRegistrarUsuario1="{ call M01_REGISTRAR(?,?,?,?,?,?,?,?)}";

    Entity _user;

    Gson gson = new Gson();

    private static org.slf4j.Logger logger = LoggerFactory
                                             .getLogger(DaoUser.class);


    public DaoUser(Entity _user)  {
         this._user= _user;
        try {
            _bdCon = Dao.getBdConnect();
        } catch (BdConnectException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
    }

    public DaoUser()  {
        try {
            _bdCon = Dao.getBdConnect();
        } catch (BdConnectException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
    }


    /**
     * Devuelve el usuario que esté registrado
     * @param e
     * @return
     */

    public Entity read(Entity e) {
        _sc = new Security();

        CallableStatement cstmt;

        User _user = (User) e;

        String password =_sc.encryptPassword(_user.getPassword());

        try{
            cstmt = _bdCon.prepareCall(_sqlInicioSesion.toString());

            //1er signo de interrogacion el parametro de salida
            cstmt.registerOutParameter(1, Types.INTEGER);

            //2do y 3er signo de interrogacion parametros de entrada
            cstmt.setString(2, _user.getUser());
            cstmt.setString(3, password);

            cstmt.execute();

            int id = cstmt.getInt(1);
            System.out.printf(String.valueOf(id));

            _user.setId(id);
            return _user;

        }
        catch (SQLException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());

            //Retorna null por el error
            return null;
        }
        catch (Exception ex){
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
            return null;
        }
    }


    /**
     * Metodo que es llamado a traves del web service para agregar a la base de datos
     * los parametros recibidos
     * @return
     */

    @Override
    public Entity create(Entity e) throws Exception {

        _sc = new Security();

        User _user = (User) e;

        String password = _sc.encryptPassword(_user.getPassword());

        CallableStatement cstmt, cs;


        try {
            cstmt = _bdCon.prepareCall(_sqlRegistrarUsuario.toString());
            //Parametro de salida
            cstmt.registerOutParameter(1, Types.INTEGER);

            //Parametros de entrada
            cstmt.setString(2, _user.getUser());
            cstmt.setString(3, password);
            cstmt.setString(4, _user.getEmail());
            cstmt.setString(5, _user.getSex());
            cstmt.setString(6, _user.getPhone());
            cstmt.setDate(7, _user.getBirthdate());
            cstmt.setInt(8, _user.getWeight());
            cstmt.setInt(9, _user.getHeight());
            cstmt.execute();

            int id = cstmt.getInt(1);
            _user.setId(id);

            return _user;

        } catch (SQLException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());

            //Retorna null por el error
            return null;
        } catch (Exception ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
            return null;
        } finally {
            _bdCon.close();
        }
    }

        /**
         * Sevicio Web para poder enviar el correo al usuario con su password
         * @return por ahora retorna un String
         */
        @Override
        public String testEmail (String email) throws SQLException {
            User user = null;
            Boolean validaEmail = false;
            String usernameResult = "";
            String passwordResult = "";


           // String query = "SELECT * FROM M01_RECUPERARPWD('" + email + "')";

            try { //TODO: RECUERDA COLOCAR EN REGISTRY
                //Establecemos el usuario que es el correo que cree para hacer el recuperar
                final String username = "fitucabprueba2@gmail.com";
                //la clave
                final String password = "ucab2017";
                //Estas son las propiedades de seguridad de gmail
                Properties props = new Properties();
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                System.out.print("Debug: email " + email);

            /*
             * EN ALGUNA PARTE DE AQUI ES DONDE DEBERIA HACER EL CAMBIO DE CLAVE POR
             * ALGUN STRING ALEATORIO Y ENCRIPTADO
             * Y LUEGO ENVIARLE EL STRING SIN ENCRIPTAR AL USUARIO
             *
             */
                CallableStatement cstmt;
                cstmt = _bdCon.prepareCall("{ call M01_RECUPERARPWD(?,?,?)}");
                cstmt.registerOutParameter(1, Types.VARCHAR);
                System.out.print("Debug: 1 ");
                cstmt.registerOutParameter(2, Types.VARCHAR);
                System.out.print("Debug: 2 ");
                cstmt.setString(3, email);
                System.out.print("Debug: 3 ");

                cstmt.execute();

                validaEmail = true;
                if (validaEmail == true) {
                    usernameResult = cstmt.getString(1);
                    passwordResult = cstmt.getString(2);
                    passwordResult= _sc.decryptPassword(passwordResult);
                    System.out.print("Debug: user " + usernameResult);
                    //Se crea la sesion para autenticar
                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                                }
                            });

                    //creamos un objeto MIME
                    javax.mail.Message message = new MimeMessage(session);
                    //ponemos el remitente
                    message.setFrom(new InternetAddress("fitucabprueba2@gmail.com"));
                    message.setRecipients( javax.mail.Message.RecipientType.TO,
                            //aqui va el destinatario
                            InternetAddress.parse(email));
                    //El tema del correo
                    message.setSubject("Recuperar contraseña FitUCAB");
                    //El contenido del correo
                    message.setText(" Hola FitUcabista! " +
                            " tu usuario es: " + usernameResult +
                            " y tu clave:" + passwordResult + " " +
                            " Ahora puedes seguir entrenando");
                    //Enviamos
                    Transport.send(message);
                    //Aqui esta la validacion
                    User userOk = new User();
                    userOk.set_status(Integer.toString(RESULT_EMAIL_OK));
                    return gson.toJson(userOk);
                } else {
                    System.out.print("Debug: user " + usernameResult);
                    User userFail = new User();
                    userFail.set_status(Integer.toString(RESULT_USER_FAIL));
                    return gson.toJson(userFail);
                }

            } catch (SQLException e) {
                MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                        Thread.currentThread().getStackTrace()[1].getMethodName());
                logger.error("Error: ", error.toString());
                System.out.print("Error: " + error.toString());
                return e.getSQLState();
            } catch (Exception e) {
                MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                        Thread.currentThread().getStackTrace()[1].getMethodName());
                logger.error("Error: ", error.toString());
                System.out.print("Error: " + error.toString());
                return e.getMessage();
            }finally {
                _bdCon.close();
            }
        }

    public Entity update(Entity e) {
        return null;
    }



}