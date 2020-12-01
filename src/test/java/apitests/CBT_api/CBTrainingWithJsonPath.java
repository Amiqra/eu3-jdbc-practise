package apitests.CBT_api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeclass(){

        baseURI= ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1(){

       Response response= given().accept(ContentType.JSON).
                and().pathParam("id",17982).
                when().get("/student/{id}");

        //verify status code
        assertEquals(response.statusCode(),200);

        JsonPath jsonPath = response.jsonPath();

        //get values from jsonpath
        String firstName = jsonPath.getString("students.firstName[0]");
        System.out.println("firstName = " + firstName);

        String lastName = jsonPath.get("students.lastName[0]");
        System.out.println("lastName = " + lastName);

        //contact ayri bir jsonpath o yuzden contact[0].phone yazdik
        //phone one index so do not need to phone[0]
        String phone = jsonPath.getString("students.contact[0].phone");
        System.out.println("phone = " + phone);

        //get me city and zipcode, do assertion
        String city = jsonPath.getString("students.company[0].address.city");
        System.out.println("city = " + city);
        assertEquals(city,"Chicago");

        String zipCode = jsonPath.getString("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);
        assertEquals(zipCode,"60606");

        String firstname2 = jsonPath.getString("students.firstName");
        System.out.println("firstname2 = " + firstname2);

        //stringi array icinden aldigimiz icin getString ile almaliyiz!
//        String firstname3 =response.path("students.firstName");
//        System.out.println("firstname3 = " + firstname3);

//        String zipCode2= response.path("students.company[0].address.zipCode");
//        System.out.println("zipCode2 = " + zipCode2);

    }
}


