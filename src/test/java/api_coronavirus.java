import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import com.jayway.jsonpath.JsonPath;

public class api_coronavirus {

    @Test
    public void test_summary_api() {
        RestAssured.baseURI = String.format("https://api.quarantine.country/api/v1/summary/latest");
        Response response = given()
                .log().all().header("Accept","*/*").get();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Headers: " + response.getStatusCode());
        System.out.println("Headers: "+ response.getHeaders()); //-- Imprime todo los headers
        System.out.println("Headers: "+ response.getHeader("Content-Type()")); //- imprime un header


        System.out.println("Test ---------------------------------------------");

        assertEquals(200,response.getStatusCode());
        assertEquals("stack",JsonPath.read(body_response,"$.type"));
        assertNotNull(body_response);
        assertTrue(body_response.contains("recovered"));
    }
    @Test
    public void Api_por_region (){
        RestAssured.baseURI = String.format("https://api.quarantine.country/api/v1/summary/region");
        Response response = given()
                .log().all()
                .header("Accept","*/*")
                .param("region","mexico")
                .param("sub_area","1")
                .get();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);

        assertEquals(200,response.getStatusCode());
        assertEquals("stack",JsonPath.read(body_response,"$.type"));
        assertNotNull(body_response);
        assertTrue(body_response.contains("recovered"));
    }

    @Test
    public void api_region_por_semana(){
        RestAssured.baseURI = String.format("https://api.quarantine.country/api/v1/spots/week");
        Response response = given()
                .log().all()
                .header("Accept","*/*")
                .param("region","russia")
                .get();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);

        assertEquals(200,response.getStatusCode());
        assertEquals("stack",JsonPath.read(body_response,"$.type"));
        assertNotNull(body_response);
        assertTrue(body_response.contains("recovered"));
    }

    @Test
    public void get_spots_for_last_365days_since(){
        RestAssured.baseURI = String.format("https://api.quarantine.country/api/v1/spots/year");
        Response response = given()
                .log().all()
                .header("Accept","*/*")
                .param("region","china")
                .get();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);

        assertEquals(200,response.getStatusCode());
        assertEquals("stack",JsonPath.read(body_response,"$.type"));
        assertNotNull(body_response);
        assertTrue(body_response.contains("recovered"));
    }
    @Test
    public void primeros_casos_por_region(){
        RestAssured.baseURI = String.format("https://api.quarantine.country/api/v1/spots/region");
        Response response = given()
                .log().all()
                .header("Accept","*/*")
                .param("region","mexico")
                .get();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);

        assertEquals(200,response.getStatusCode());
        assertEquals("stack",JsonPath.read(body_response,"$.type"));
        assertNotNull(body_response);
        assertTrue(body_response.contains("recovered"));
    }





}