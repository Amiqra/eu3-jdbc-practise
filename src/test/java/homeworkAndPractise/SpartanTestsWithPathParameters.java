package homeworkAndPractise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static org.testng.Assert.*;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

public class SpartanTestsWithPathParameters {


    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    /**
     * Given accept type is Json
     * And Id parameter value is 18
     * when user send GET request to /api/spartans/{id}
     * Then response status code should be 200
     * and response content type:application/json;charset=UTF-8
     * and "Allen should be in response payload" //payload mean=body
     */

    @Test
    public void PathTest1(){
        //Given accept type is Json,And Id parameter value is 18,when user send GET request to /api/spartans/{id}
        Response response = RestAssured.given().accept(ContentType.JSON).
                and().pathParam("id",18).
                when().get("/api/spartans/{id}");

        //import static org.testng.Assert.*; -> manuel ekledik
        //verify status code
        assertEquals(response.statusCode(),200);
        //verify content type
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        //verify body contains
        assertTrue(response.body().asString().contains("Allen"));
        response.body().prettyPrint();
    }

    /**
     * Given accept type is Json
     * And Id parameter value is 500
     * when user send GET request to /api/spartans/{id}
     * Then response status code should be 404
     * and response content type:application/json;charset=UTF-8
     * and "Spartan Not Found" message should be in response payload
     */

    @Test
    public void NegativePathTest(){

        Response response = RestAssured.given().accept(ContentType.JSON).
                            and().pathParam("id",500).
                when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Spartan Not Found"));
    }

}
