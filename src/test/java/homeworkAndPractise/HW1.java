package homeworkAndPractise;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HW1 {

    @BeforeClass
    public void setUpClass() {
        baseURI = ConfigurationReader.get("hr_api_url");
    }

    /**
     * Q1:
     * - Given accept type is Json
     * - Path param value- US
     * - When users sends request to /countries
     * - Then status code is 200
     * - And Content - Type is Json
     * - And country_id is US
     * - And Country_name is United States of America
     * - And Region_id is
     */

    @Test
    public void Q1test() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"country_id\":\"US\"}")
                .when().get("/countries");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        JsonPath jsonPath = response.jsonPath();

        String countryId = jsonPath.getString("items.find {it.country_id==\"US\"}.country_id");
        System.out.println("countryId = " + countryId);

        String countryName = jsonPath.getString("items.find {it.country_name==\"United States of America\"}.country_name");
        System.out.println("countryName = " + countryName);

        int regionId = jsonPath.getInt("items.find {it.region_id==2}.region_id");
        System.out.println("regionId = " + regionId);
    }

    /**
     * Q2:
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     */


    @Test
    public void Q2test() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        JsonPath jsonPath = response.jsonPath();

        List<String> jobIds = jsonPath.getList("items.job_id");
        for (String job_id : jobIds) {
            System.out.println("job_id = " + job_id);
            assertTrue(job_id.contains("SA"));
        }

        List<Integer> departmentId = jsonPath.getList("items.findAll {it.department_id==80}.department_id");
        System.out.println("departmentId = " + departmentId);

        int count = jsonPath.getInt("count");
        System.out.println("count = " + count);
        assertEquals(count, 25);
    }

    /**
     * Q3:
     * - Given accept type is Json
     * -Query param value q= region_id 3
     * - When users sends request to /countries
     * - Then status code is 200
     * - And all regions_id is 3
     * - And count is 6
     * - And hasMore is false
     * - And Country_name are;
     * Australia,China,India,Japan,Malaysia,Singapore
     */

    @Test
    public void Q3test() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        JsonPath jsonPath = response.jsonPath();

        int count = jsonPath.getInt("count");
        System.out.println("count = " + count);
        assertEquals(count, 6);

        String hasMore = (response.path("hasMore").toString());
        System.out.println("hasMore = " + hasMore);
        assertEquals(hasMore, "false");

        ArrayList<String> expectedCountries = new ArrayList<String>();
        expectedCountries.add("Australia");
        expectedCountries.add("China");
        expectedCountries.add("India");
        expectedCountries.add("Japan");
        expectedCountries.add("Malaysia");
        expectedCountries.add("Singapore");
        System.out.println("expectedCountries = " + expectedCountries);
        
        List<String> countryName = jsonPath.getList("items.country_name");
        for (String countryNames : countryName) {
           System.out.println("countryNames = " + countryNames);
           assertTrue(countryName.containsAll(expectedCountries));
        }
    }
}