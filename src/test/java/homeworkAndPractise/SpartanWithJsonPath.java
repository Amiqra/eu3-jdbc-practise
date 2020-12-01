package homeworkAndPractise;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanWithJsonPath {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    /**
     Given accept type is json
     And path param id is 11
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json;charset=UTF-8"
     And response payload values match the following:
     id is 11,
     name is "Nona",
     gender is "Female",
     phone is 7959094216
     */
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).
                and().pathParam("id",11).
                when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        JsonPath jsonData = response.jsonPath();

        int id1 = jsonData.getInt("id");
        String name = jsonData.getString("name");
        String gender = jsonData.getString("gender");
        long phone = jsonData.getLong("phone");

        System.out.println(id1);
        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);

        assertEquals(id1,11);
        assertEquals(name,"Nona");
        assertEquals(gender,"Female");
        assertEquals(phone,7959094216l);



    }

}
