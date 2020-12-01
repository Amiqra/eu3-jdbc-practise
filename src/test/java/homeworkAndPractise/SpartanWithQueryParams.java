package homeworkAndPractise;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class SpartanWithQueryParams {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    /**
     *Given accept type is Json
     * And query param values are:
     * gender|female
     * nameContains|J
     * When user sends GET request to /api/spartans/search
     * Then response status code should be 200
     * And response content-type : application/json;charset=UTF-8
     * And "Female" should be in response payload
     * And "Janette" should be in response payload
     */

    @Test
    public void QueryParam1(){

        //import static io.restassured.RestAssured.*; ekledik
        Response response = given().accept(ContentType.JSON).
                            and().queryParam("gender","Female").
                            and().queryParam("nameContains","J").
                            when().get("/api/spartans/search");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Female"));
        assertFalse(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();
    }

    //ayni ornegi map ile yaptik
    @Test
    public void QueryParamWithMap(){

        Map<String,Object>paramsMap = new HashMap<>();
        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","J");

        //send request
       Response response = given().accept(ContentType.JSON).
        and().queryParams(paramsMap).
        when().get("/api/spartans/search");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
//        assertTrue(response.body().asString().contains("Female"));
//        assertFalse(response.body().asString().contains("Male"));
//        assertTrue(response.body().asString().contains("Janette"));

        //key and value seklinde verify etmek
        assertTrue(paramsMap.containsKey("gender"),("containsName"));
        assertTrue(paramsMap.containsValue("Female"),("Janette"));
        response.prettyPrint();
    }





    }


