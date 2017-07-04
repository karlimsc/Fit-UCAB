package edu.ucab.desarrollo.fitucab.Test.M01_ServicesUserTest;

import io.restassured.http.ContentType;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.http.HttpStatus;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by elberg on 03/07/17.
 */
class M01_ServicesUsersTest {

    @Test
    @Ignore
    void getUser() {
       /* try {

            URI prueba = new URI("http://localhost:8080/localhost:8080/fitucab_war_exploded/" +
                    "M01_ServicesUser/login_user?username=karo&password=1234");

            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);
            URI prueba2 = new URI("http://localhost:8080/fitucab_maven_war_exploded/" +
                    "M01_ServicesUser/login_user?username=danri&password=dannnnn");

            String json = given().accept(ContentType.JSON).when()
                    .get(prueba2).thenReturn().body().asString();
            int noEncontrado = Integer.parseInt(json);
            assertEquals(500,noEncontrado);


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (Exception e){

        }*/
    }

    @Test
    @Ignore
    void insertUser() {
       /* try {
            URI prueba = new URI("http://localhost:8080/" +
                    "fitucab_war_exploded/M01_ServicesUser/" +
                    "insertRegistry?" +
                    "username=dan1rive" +
                    "&password=dan1234" +
                    "&email=dan1" +
                    "&sex=F" +
                    "&phone=12a3456" +
                    "&birthdate=12/06/1993" +
                    "&weight=1" +
                    "&height=1");
            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);


        }
        catch (URISyntaxException e) {
            e.printStackTrace();

        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (Exception e){

        }*/
    }

    @Test
    @Ignore
    void userOnly() {
    }

    @Test
    @Ignore
    void testEmail() {
    }

}