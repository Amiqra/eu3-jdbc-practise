package apitests.Spartan_api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
public class SpartanGetRequest {

    @BeforeClass
    public void beforeclass(){

        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void  test1(){
        Response response = when().get(baseURI+"/api/spartans");

        System.out.println(response.statusCode());

        response.prettyPrint();

    }

    /* TASK
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json;charset=UFT-8
        and json body should contain Fidole
     */

    @Test
    public void  test2(){
        Response response = when().get(baseURI+"/api/spartans/3");

        //verify status code
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println(response.statusCode());

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");

        //verify Fidole
        Assert.assertTrue(response.body().asString().contains("Fidole"));


    }

    /**
    Given no headers provided
    When Users sends GET request to /api/hello
    Then response status code should be 200
    And Content type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And Content-Length should be 17
    And body should be "Hello from Sparta"
    */

    @Test
    public void helloTest(){

        Response response =when().get(baseURI+"/api/hello");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");

        //And header should contain date
        //it accept header as a keyaa
        //date diye headers varmi diye baktik
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
        //to get and header passing as a key
        System.out.println(response.header("Content-Length"));

        //date surekli degistigi icin sadece yadirdik verify etmedik
        //point is existing response
        System.out.println(response.header("Date"));

        //verify Content-Length should be 17
        Assert.assertEquals(response.header("Content-Length"),"17");

        //verify nd body should be "Hello from Sparta"

        //json i string e convert etmek icin asString kullaniyoruz!!
        Assert.assertTrue(response.body().asString().contains("Hello from Sparta"));





    }




    }

