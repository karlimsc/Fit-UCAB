package WebServicesClasses;

import Domain.Sql;
import io.restassured.http.ContentType;
import junit.extensions.TestSetup;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

/**
 * Created by JoseA2R on 29/5/17.
 */

public class M10_WaterGlassTest {


    Sql conex = new Sql();
    ResultSet result;


    @Before

    public void setUp() throws Exception {


        String date = "20/06/2016";
        String hour = "04:00.50";
        int typeglass = 300;
        int fkper = 1;

        String addSome =" SELECT M10_AddWater('"+date+hour+"','"+typeglass+"','"+fkper+"')";


        try {

            result =conex.sql(addSome);
        }
        catch (Exception e){}

    }

    @After
    public void tearDown() throws Exception {

        String LessInfo = "SELECT M10_DeletWaterTm('"+ "20/12/2016" +""+ "04:00:50" +"','"+ "1" +"')";

        try {

            result =conex.sql(LessInfo);

        }
        catch (Exception e){}
    }


    @Test
    public void addWater() throws URISyntaxException {

        try {
            URI add = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/M10_WaterGlass/" +
                    "addWater?time=20/12/2016" +
                    "&glasstype=300" +
                    "&fpk=1");

            given().accept(ContentType.JSON).when().get(add).then().assertThat().statusCode(HttpStatus.SC_OK);
            String returnSome = given().accept(ContentType.JSON).when().get(add).thenReturn().body().asString();
            assertNotNull(returnSome);

            URI addPlus = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/M10_WaterGlass/" +
                    "DeletLast?time=20/12/2016" +
                    "&fpk=1");

            given().accept(ContentType.JSON).when().get(addPlus).then().assertThat().statusCode(HttpStatus.SC_OK);
            String json = given().accept(ContentType.JSON).when().get(addPlus).thenReturn().body().asString();
            assertNotNull(json);


        }
        catch(URISyntaxException e) {

                e.printStackTrace();

            }
        catch (Exception e) {

            e.printStackTrace();

        }
    }


    @Test
    public void deletLast() throws Exception {

        try {
            URI add = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/M10_WaterGlass/" +
                    "addWater?time=20/11/2016" +
                    "&glasstype=300" +
                    "&fpk=2");

            given().accept(ContentType.JSON).when().get(add).then().assertThat().statusCode(HttpStatus.SC_OK);
            String returnSome = given().accept(ContentType.JSON).when().get(add).thenReturn().body().asString();
            assertNotNull(returnSome);

            URI addPlus = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/M10_WaterGlass/" +
                    "DeletLast?time=20/11/2016" +
                    "&fpk=2");

            given().accept(ContentType.JSON).when().get(addPlus).then().assertThat().statusCode(HttpStatus.SC_OK);
            String json = given().accept(ContentType.JSON).when().get(addPlus).thenReturn().body().asString();
            assertNotNull(json);


        }
        catch(URISyntaxException e) {

            e.printStackTrace();

        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Test
    public void deletWaterTm() throws Exception {

        try {
            URI add = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/M10_WaterGlass/" +
                    "addWater?time=20/08/2016" +
                    "&glasstype=300" +
                    "&fpk=3");

            given().accept(ContentType.JSON).when().get(add).then().assertThat().statusCode(HttpStatus.SC_OK);
            String returnSome = given().accept(ContentType.JSON).when().get(add).thenReturn().body().asString();
            assertNotNull(returnSome);

            URI addPlus = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/M10_WaterGlass/" +
                    "DeletLast?time=20/08/2016" +
                    "&fpk=3");

            given().accept(ContentType.JSON).when().get(addPlus).then().assertThat().statusCode(HttpStatus.SC_OK);
            String getString = given().accept(ContentType.JSON).when().get(addPlus).thenReturn().body().asString();
            assertNotNull(getString);


        }
        catch(URISyntaxException e) {

            e.printStackTrace();

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }


    @Test
    public void getListDate() throws Exception {

        }


    @Test
    public void getWater() throws Exception {

      try{
        URI add = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/M10_WaterGlass/" +
                "addWater?time=20/08/2016" +
                "&fpk=3");
        given().accept(ContentType.JSON).when().get(add).then()
                .assertThat().statusCode(HttpStatus.SC_OK);
        String json = given().accept(ContentType.JSON).when()
                .get(add).thenReturn().body().asString();
        assertNotNull(json);


    }
        catch (URISyntaxException e){

    }
        catch (Exception e){}
}




    @Test
    public void getFechaInt() throws Exception {

    }


    @Test
    public void getFecha() throws Exception {

        URI getfecha = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/M10_WaterGlass/" +
                "GetFecha?fpk=1");

        String json = given().accept(ContentType.JSON).when()
                .get(getfecha).thenReturn().body().asString();
        int exists = Integer.parseInt(json);
        assertEquals(1,exists);



    }

}