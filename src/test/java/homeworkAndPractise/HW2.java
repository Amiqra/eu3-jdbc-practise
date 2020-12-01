package homeworkAndPractise;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HW2 {

    @BeforeClass
    public void setUpClass(){
        //the beauty of baseURI we do not use get method or etc
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    /**
     * Given accept type is json
     * And path param id is 20
     * When user sends a get request to "/api/spartans/{id}"
     * Then status code is 200
     * And content-type is "application/json;charset=UTF-8"
     * And response header contains Date
     * And Transfer-Encoding is chunked
     * And response payload values match the following:
     * id is 20,
     * name is "Lothario",
     * gender is "Male",
     * phone is 7551551687
     */

    @Test
    public void Q1test(){

        Response response = given().accept(ContentType.JSON).
        and().pathParam("id",20).
                when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

      assertTrue(response.headers().hasHeaderWithName("Date"));
      assertEquals(response.header("Transfer-Encoding"),"chunked");

      int id = response.path("id");
      String name= response.path("name");
      String gender = response.path("gender");
      long phone = response.path("phone");

      assertEquals(id,20);
      assertEquals(name,"Lothario");
      assertEquals(gender,"Male");
      assertEquals(phone,7551551687l);
    }

    /**
     * Q2:
     * Given accept type is json
     * And query param gender = Female
     * And query param nameContains = r
     * When user sends a get request to "/api/spartans/search"
     * Then status code is 200
     * And content-type is "application/json;charset=UTF-8"
     * And all genders are Female
     * And all names contains r
     * And size is 20
     * And totalPages is 1
     * And sorted is false
     */

    @Test
    public void Q2test() {
        Response response = given().accept(ContentType.JSON).
                            and().queryParam("gender","Female").
                            and().queryParam("nameContains","r").
                            when().get("/api/spartans/search");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        JsonPath jsonPath = response.jsonPath();

        List<String> genders = response.path("content.gender");
        for (String gender : genders) {
            System.out.println("gender = " + gender);
            assertEquals(gender,"Female");
        }
        List<String> names=response.path("content.name");
        for (String name : names) {
            System.out.println("name = " + name);
            assertTrue(name.contains("r") || name.contains("R"));
        }
        int size = response.path("size");
        assertEquals(size,20);

        int totalPage = response.path("totalPages");
        assertEquals(totalPage,1);

       // assertEquals(response.path("sort.sorted").toString(),"false");
        String sortd =(response.path("sort.sorted").toString());
        assertEquals(sortd,"false");

    }

}
