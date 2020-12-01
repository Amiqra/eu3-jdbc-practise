package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class simpleGetRequest {

    //db baglantisi, once url
    String hrurl = "http://34.227.143.172:1000/ords/hr/regions";

    @Test
    public void test1() {

        //we downloaded rest-assured library

        Response response = RestAssured.get(hrurl);

        // print the status code
        System.out.println(response.statusCode());

        //print the json body
        response.prettyPrint();

        /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        and body is json format
     */
    }

    @Test
    public void test2() {

        //cunku accept, headers a direkt baglaniyor

        //bizim requestimiz
        Response response = RestAssured.
                given().accept(ContentType.JSON)
                .when()
                .get(hrurl);
        //verify response status code is 200
        //we sent to veriify


        //verify content type is application json

        System.out.println(response.contentType());

        Assert.assertEquals(response.contentType(), "application/json");

    }

    @Test
    public void test3() {

        //given,when,then
        RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl).then()
                .assertThat().statusCode(200)
                .and().contentType("application/json");


    }

        /**
        Given accept type is json
        When user sends get request to regions/2
        Then response status code must be 200
        and body is json format
        and response body contains Americas
     */

    @Test
    public void test4(){
        //given hata veriyordu line 80 i ekledik
        //import static io.restassured.RestAssured.*;
        Response response = given().accept(ContentType.JSON)
              .when().get(hrurl + "/2");

        //verify status code
        Assert.assertEquals(response.getStatusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json");

        //verify body contains Americas
        //this is not good way
        Assert.assertTrue(response.body().asString().contains("Americas"));
    }



}

