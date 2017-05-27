package Test.M01_Test;
import WebServicesClasses.M01_ServicesUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.*;

import java.net.URI;
import java.net.URL;

import static io.restassured.RestAssured.*;

import static org.junit.Assert.*;




public class M01_ServicesUserTest extends baseClass {




    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
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

        URI prueba =new URI("/M01_ServicesUser/getUser?User=daniel&Password=daniel");
        String  json = given().accept(ContentType.JSON).when().get(prueba).thenReturn().body().asString();
        assertNotNull(json);
        // se pueden manejar los errore HTTP buscar como agaregar httpstatus.class
        //Response response = given().accept(ContentType.JSON).when().get(prueba);

    }

    @Test
    public void getUser() throws Exception {


        URL prueba =new URL("localhost:8888/WebServicesFitUCAB_war_exploded/M01_ServicesUser/getUser?User=daniel&Password=daniel");
        //RestAssured.(lo puse estatico el import)
         when().get(prueba);
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

    @Test
    public void bdConnect() throws Exception {
    }

}