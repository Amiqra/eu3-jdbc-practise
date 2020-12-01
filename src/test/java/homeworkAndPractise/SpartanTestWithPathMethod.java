package homeworkAndPractise;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class SpartanTestWithPathMethod {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    /**
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json;charset=UTF-8"
     And response payload values match the following:
     id is 10,
     name is "Lorenza",
     gender is "Female",
     phone is 3312820936
     */

    @Test
    public void test1() {
      Response response=  given().accept(ContentType.JSON).
        and().pathParam("id","10").
        when().get("api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,3312820936l);

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

    }
    
    @Test
    public void test2(){
        
        Response response = get("api/spartans");
        
        //extract first id
        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);
        //extract first name
        String first1stName = response.path("name[0]");
        System.out.println("first1stName = " + first1stName);
        //get the last firstname
        String last1stName = response.path("name[-1]");
        System.out.println("last1stName = " + last1stName);
        
        //extract all first name and print them
       List<String> names = response.path("name");
        for (String name : names) {
            System.out.println(name);
        }

        System.out.println("=========================================");

        //all phone numbers
        List<Object> phoneNumbers = response.path("phone");

        for (Object phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }



    }


}
