package M06;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.http.impl.conn.BasicClientConnectionManager;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
class M06_ServicesTrainingTest {


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createTraining() {


        try
        {
            URI prueba = new URI("http://localhost:8888/fitucab/M06_ServicesTraining/" +
                    "createTraining?" +
                    "trainingName=daniel" +
                    "&trainingActivities=trotar" +
                    "&userId=1");
            given().accept(ContentType.JSON).when().get(prueba).then().assertThat().statusCode(HttpStatus.SC_OK);

            String json = given().accept(ContentType.JSON).when()
                    .get(prueba).thenReturn().body().asString();
            assertNotNull(json);

        }
        catch (Exception e){

        }
    }

    @Test
    void renameTraining() {


        try
        {
            URI prueba = new URI("http://localhost:8888/fitucab/M06_ServicesTraining/" +
                    "changeTrainingName?" +
                    "idTraining=1" +
                    "&trainingName=trotar");
            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);
            String json = given().accept(ContentType.JSON).when()
                    .get(prueba).thenReturn().body().asString();
            assertNotNull(json);

        }
        catch (Exception e){

        }
    }

    @Test
    void addActivitiesToTraining() {

        try
        {
            URI prueba = new URI("http://localhost:8888/fitucab/M06_ServicesTraining/" +
                    "addActivitiesToTraining?" +
                    "idTraining=1" +
                    "&trainingName=camniar" +
                    "&trainingActivities=prueba");
            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);
            String json = given().accept(ContentType.JSON).when()
                    .get(prueba).thenReturn().body().asString();
            assertNotNull(json);

        }
        catch (Exception e){

        }
    }

    @Test
    void removeActivitiesToTraining() {

        try
        {
            URI prueba = new URI("http://localhost:8888/fitucab/M06_ServicesTraining/" +
                    "removeActivitiesToTraining?" +
                    "idTraining=1" +
                    "&trainingName=camniar" +
                    "&trainingActivities=prueba");
            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);
            String json = given().accept(ContentType.JSON).when()
                    .get(prueba).thenReturn().body().asString();
            assertNotNull(json);

        }
        catch (Exception e){

        }
    }

    @Test
    void getTraining() {
    }

    @Test
    void deleteTraining() {


        try
        {
            URI prueba = new URI("http://localhost:8888/fitucab/M06_ServicesTraining/" +
                    "deleteTraining?" +
                    "idTraining=1" +
                    "&trainingName=camniar");
            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);
            String json = given().accept(ContentType.JSON).when()
                    .get(prueba).thenReturn().body().asString();
            assertNotNull(json);

        }
        catch (Exception e){

        }
    }

    @Test
    void shareTraining() {


        try
        {
            URI prueba = new URI("http://localhost:8888/fitucab/M06_ServicesTraining/" +
                    "shareTraining?" +
                    "trainingName=caminar" +
                    "&trainingActivities=trotar" +
                    "&userId=3");
            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);
            String json = given().accept(ContentType.JSON).when()
                    .get(prueba).thenReturn().body().asString();
            assertNotNull(json);

        }
        catch (Exception e){

        }
    }

    @Test
    void getTrainingDetail() {
    }

    @Test
    void getAllTraining() {
    }

}