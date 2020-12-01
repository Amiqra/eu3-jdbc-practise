package homeworkAndPractise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;

public class SpartanTest {

    /**
     * how to send any simple request code without any header
     */

    //ilk adim DB nin url sini yazmak. http ye dikkat!!!!
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void viewSpartanTest() {

        Response response = when().get(baseURI + "/api/spartans");

        //response kodu yazdirdik
        System.out.println(response.statusCode());

        //print body
        System.out.println(response.body().asString());
    }

    /**
     * when user send get request to /api/spartans en point
     * then status code must be 200
     * and boddy contains Allen
     */

    @Test
    public void viewSpartanTests2() {

        Response response = when().get(baseURI + "/api/spartans");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.body().asString().contains("Allen"));
    }

    /**
     * given accept type is Json
     * when user send get request to spartanAllUrl
     * then status code must be 200
     * and response body should be Json format
     */

    @Test
    public void viewSpartanTests3() {

        Response response = given().accept(ContentType.JSON).
                when().get(baseURI + "/api/spartans");

        Assert.assertEquals(response.statusCode(),200);

        //json formatinda mi degilmi anlamak icin "application/json;charset=UTF-8" bunu kopyalamaliyiz
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");

    }


}