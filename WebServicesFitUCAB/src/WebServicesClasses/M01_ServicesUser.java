package WebServicesClasses;

import Domain.User;
import com.google.gson.Gson;
//imports para poder hacer el recuperar password
import javax.mail.MessagingException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
//FIN de imports para poder hacer el recuperar password
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;
import java.util.Properties;

/**
 * Clase de Servicios Web del Modulo 01
 */
@Path("/M01_ServicesUser")
public class M01_ServicesUser {

    private Connection conn =bdConnect();
    Gson gson = new Gson();

    /**
     * Metodo que es llamado a traves del web service para agregar a la base de datos los parametros recibidos
     * @param username
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @param weight
     * @param height
     * @return
     */

    @GET
    @Path("/insertRegistry")
    @Produces("application/json")

    public String insertUser(@QueryParam("username") String username,
                             @QueryParam("password") String password,
                             @QueryParam("email") String email,
                             @QueryParam("sex") String sex,
                             @QueryParam("phone") String phone,
                             @QueryParam("birthdate") String birthdate,
                             @QueryParam("weight") String weight,
                             @QueryParam("height")String height
                            )
    {
        String insertUserQuery =" SELECT M01_REGISTRAR('"+username+"','"+password+"','"+email+"','"+sex+"'" +
                ",'"+phone+"','"+birthdate+"','"+weight+"','"+height+"')";


        try{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(insertUserQuery);
            User user= null;

            int iniciosesion=0;
            while(rs.next()){
                iniciosesion = rs.getRow();

            }

            return gson.toJson(iniciosesion);

        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    /***
     * Metodo que devuelve la informacion completa de la persona
     * @param username
     * @return
     */
    @GET
    @Path("/userView")
    @Produces("application/json")
    public String userView(@QueryParam("username") String username)
    {
        String insertUserQuery =" SELECT * FROM M01_INFORMACIONUSER('"+username+"')";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(insertUserQuery);
            User user = null;
            while(rs.next()){

                String username1 = rs.getString("usuario");
                String password =rs.getString("pwd");
                int id =rs.getInt("id");
                String mail=rs.getString("mail");
                String sex=rs.getString("sex");
                String phone=rs.getString("phone");
                Date birthdate =rs.getDate("birthdate");
                user= new User(id,username1,password,mail,sex,phone,birthdate);

            }
            return gson.toJson(user);
        }
        catch(Exception e) {
        return e.getMessage();
    }

    }
    /**
     * Metodo que es llamado a traves del web service para consultar un usuario existente en la base de datos
     * @param userparam
     * @param passwordparam
     * @return el usuario con los datos que trae la consulta
     */
    @GET
    @Path("/login_user")
    @Produces("application/json")

    public String getUser(@QueryParam("username") String userparam,@QueryParam("password") String passwordparam)
    {

        String query="SELECT M01_INICIARSESION('"+userparam+"','"+passwordparam+"')";


        try{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

          //  User user= null;
            int iniciosesion =0;

            while(rs.next()){

                 iniciosesion = rs.getRow();


            }
            return gson.toJson(iniciosesion);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Sevicio Web para poder enviar el correo al usuario con su password
     * @return por ahora retorna un String
     */
    @GET
    @Path("/restorePassword")
    @Produces("application/json")
    public String testEmail()
    {

        try {
            //Establecemos el usuario que es el correo que cree para hacer el recuperar
            final String username = "ds1617b@gmail.com";
            //la clave
            final String password = "fitucab2017";
            //Estas son las propiedades de seguridad de gmail
            Properties props = new Properties();
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            /*
             * EN ALGUNA PARTE DE AQUI ES DONDE DEBERIA HACER LA BUSQUEDA EN BD DEL USUARIO
             * DESPUES EL CAMBIO DE CLAVE POR ALGUN STRING ALEATORIO Y ENCRIPTADO
             * Y OBTENER EL CORREO ASOCIADO A ESE USUARIO
             * Y LUEGO ENVIARLE EL STRING SIN ENCRIPTAR AL USUARIO
             */


            /*
            Se crea la sesion para autenticar
             */
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                //creamos un objeto MIME
                Message message = new MimeMessage(session);
                //ponemos el remitente
                message.setFrom(new InternetAddress("ds1617b@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                //aqui va el destinatario
                InternetAddress.parse("karlianamsuarez@gmail.com"));
                //El tema del correo
                message.setSubject("Password Recovery FitUCAB");
                //El contenido del correo
                message.setText("password");
                //Enviamos
                Transport.send(message);
                //Aqui en adelante cualquier tipo de validacion
                System.out.println("Done");
                return ("done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        catch(Exception e) {
            return e.getMessage();
        }

    }

    @GET
    @Path("/helloWorld")
    @Produces("application/json")
    public String prueba()
    {
        String query="SELECT * FROM PERSON";

        try{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            User user= null;
            while(rs.next()){
                String username = rs.getString("PERSONUSERNAME");
                int id = rs.getInt("PERSONID");
                String password = rs.getString("PERSONPASSWORD");
                String sexo= rs.getString("PERSONSEX");
                String phone= rs.getString("PERSONPHONE");
                String email= rs.getString("PERSONEMAIL");
                Date birtdate= rs.getDate("PERSONBIRTHDATE");

                user= new User(id,username,password,email,sexo,phone,birtdate);
            }
            return gson.toJson(user);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    //esto no va a aqui , se puso momentaneamente.
    public Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/fitucabdb";
            conn = DriverManager.getConnection(url,"fitucab", "fitucab");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(2);
        }
        return conn;
    }

}
