package apitests.Spartan_api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import  static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

    //there will be no testng assertions.
    //HamcrestMatchers is assertion library
    //we will be using HamcrestMatchers for chaining method of rest assured

    /**
     given accept type is Json
     And path param id is 15
     When user sends a get request to spartans/{id}
     Then status code is 200
     And content type is Json
     And json data has following
         "id": 15,
         "name": "Meta",
         "gender": "Female",
         "phone": 1938695106
      */

//assertThat is not affecting, only it increase the readibility
        //HamcrestMatchers is a library for assertion
        @Test
        public void OneSpartanWithHamcrest(){
            given().accept(ContentType.JSON)
                    .and().pathParam("id",15).
                    when().get("http://3.86.59.184:8000/api/spartans/{id}")
                    .then().statusCode(200)
                    .and().assertThat().contentType(equalTo("application/json;charset=UTF-8"))
                    .and().assertThat().body("id",equalTo(15),//-> body->1.id -> for id info.+equalTo 2.what you want to verify
                    "name",equalTo("Meta"), // equalTo ->chains method
                    "gender",equalTo("Female"),
                    "phone",equalTo(1938695106));

//bu test SpartanTestWithPath classinda yaptiklarimiz ile ayni!

        }

        @Test
        public void teacherData(){
            given().accept(ContentType.JSON)
                    .and().pathParam("id",8261)
                    .when().log().all().get("http://api.cybertektraining.com/teacher/{id}")
                    .then().assertThat().statusCode(200)
                    .and().contentType(equalTo("application/json;charset=UTF-8"))
                    .and().header("Content-Length",equalTo("240"))
                    .and().header("Connection",equalTo("Keep-Alive"))
                    .and().header("Date",notNullValue())
                    .and().assertThat().body("teachers.firstName[0]",equalTo("James"),
                    "teachers.lastName[0]",equalTo("Bond"),
                    "teachers.gender[0]",equalTo("Male"))
                    .log().all()
            ;

        }

    }
