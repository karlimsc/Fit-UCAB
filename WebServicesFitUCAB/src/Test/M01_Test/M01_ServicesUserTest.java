package M01_Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.*;
import WebServicesClasses.M01_ServicesUser;
import java.net.URL;
import java.sql.*;

import static io.restassured.RestAssured.*;


public class M01_ServicesUserTest {



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

    private Connection conn =bdConnect();


    @Before
    public void setUp() throws Exception {

        java.util.Date fecha = new Date();
        String insertUserQuery =" SELECT M01_REGISTRAR('"+"dan"+"','"+"dan"+"','"+"dan"+"','"+"dan"+"'" +
                ",'"+"dan"+"','"+fecha+"','"+1.0+"','"+1.0+"')";


        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(insertUserQuery);
        }
        catch (Exception e){}

    }

    @After
    public void tearDown() throws Exception {

        String query="SELECT M01_ELIMINARUSER('"+ "dan" +"')";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
        }
        catch (Exception e){}
    }



    @Test
    public void insertUser() throws Exception {
    }

    @Test
    public void userView() throws Exception {
    }

    @Test
    public void userOnly() throws Exception {


        //RestAssured  se inicializa si no se importa static
        // Response response =when().get(prueba);
        //Given acepta la respuesta en json
        //when cuando yo aplico la respuesta GET

        //orueba si responded correctamente
        //then(); para validar thenreturn para capturar
        //body  el cuerpo que va a retornar
        // asString de tipo string
        try {
            URL prueba = new URL("http://localhost:8888/WebServicesFitUCAB_war_exploded/M01_ServicesUser/informationUser?username=dan");
            Response response = given().accept(ContentType.JSON).when().get(prueba);
            System.out.println(response.asString() );
          //  String json = given().accept(ContentType.JSON).when().get(prueba).thenReturn().body().asString();
           // assertNotNull(json);

            //given().accept(ContentType.JSON).when().get().then().assertThat().statusCode(200);
            // se pueden manejar los errore HTTP buscar como agaregar httpstatus.class
            //Response response = given().accept(ContentType.JSON).when().get(prueba);
        } catch (Exception e){

        }
    }

    @Test
    public void getUser() throws Exception {


      /*  URL prueba =new URL("localhost:8888/WebServicesFitUCAB_war_exploded/M01_ServicesUser/getUser?User=daniel&Password=daniel");
        //RestAssured.(lo puse estatico el import)
         when().get(prueba);*/
    }

    @Test
    public void idIncrease() throws Exception {
    }

    @Test
    public void testEmail() throws Exception {
    }

    @Test
    public void prueba() throws Exception {
    }



}