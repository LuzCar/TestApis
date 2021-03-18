import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import com.jayway.jsonpath.JsonPath;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class gorest_Api {

    private static int id_user;
    private static String name_user_creado;
    @Test
    public void get_users(){
        RestAssured.baseURI = String.format("https://gorest.co.in/public-api/users");
        Response response = given()
                .log().all().header("Accept","*/*").get();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Headers: " + response.getStatusCode());

        System.out.println("Test ---------------------------------------------");

        assertEquals(200,response.getStatusCode());
        assertNotNull(body_response);
        assertTrue(body_response.contains("data"));
    }

    @Test
    public void a_crear_usuarios(){
        RestAssured.baseURI = String.format("https://gorest.co.in/public-api/users");
        String username = "agente" + (Math.floor(Math.random()*7539 + 2));
        String email = username + "@mailinator.com";

        String bodyRequest = "{\"name\":\"Tenali Ramakrishna\", \n" +
                "\"gender\":\"Male\", \n" +
                "\"email\":\""+email+"\", \n" +
                "\"status\":\"Active\"\n" +
                "}";
        Response response = given()
                .log().all().header("Accept","*/*")
                .header("Authorization","Bearer 76b39a0552b1bb771ad4b90fa5c4de3611eb23bce32e29e5e728624469dd6fb0")
                .header("Content-Type","application/json")
                .body(bodyRequest)
                .post();

        String body_response = response.getBody().asString();
        //obtener usuario
         id_user = JsonPath.read(body_response,"$.data.id");
         name_user_creado =JsonPath.read(body_response,"$.data.name");
         System.out.println("id usuario generado = "+id_user);

        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Headers: " + response.getStatusCode());


        System.out.println("Test ---------------------------------------------");

        assertEquals(200,response.getStatusCode());
        assertNotNull(body_response);
        assertTrue(body_response.contains("id"));
    }

    @Test
    public void b_buscar_usuario() {
        RestAssured.baseURI = String.format("https://gorest.co.in/public-api/users/%s",id_user);
        Response response = given()
                .log().all().header("Accept","*/*")
                .header("Authorization","Bearer 76b39a0552b1bb771ad4b90fa5c4de3611eb23bce32e29e5e728624469dd6fb0")
                .get();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);

        System.out.println("Test --------------------------------------- ");
        assertEquals(200,response.getStatusCode());
        assertEquals("Tenali Ramakrishna",JsonPath.read(body_response,"$.data.name"));
        assertNotNull(body_response);
         }

    @Test
    public void c_actualizar_usuario() {
        RestAssured.baseURI = String.format("https://gorest.co.in/public-api/users/%s",id_user);

        String bodyRequest = "{\"name\":\"Charlie\", \n" +
                "\"email\": \"charlie@gmail.com\",\n" +
                "\"status\":\"Active\"\n" +
                "}";
        Response response = given()
                .log().all().header("Accept","*/*")
                .header("Authorization","Bearer 76b39a0552b1bb771ad4b90fa5c4de3611eb23bce32e29e5e728624469dd6fb0")
                .header("Content-Type","application/json")
                .body(bodyRequest)
                .patch();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);

        System.out.println("Test --------------------------------------- ");
        assertEquals(200,response.getStatusCode());
        //assertEquals("Tenali Ramakrishna",JsonPath.read(body_response,"$.data.name"));
        assertNotNull(body_response);
    }

    @Test
    public void d_borrar_usuario() {
        RestAssured.baseURI = String.format("https://gorest.co.in/public-api/users/%s",id_user);

        Response response = given()
                .log().all().header("Accept","*/*")
                .header("Authorization","Bearer 76b39a0552b1bb771ad4b90fa5c4de3611eb23bce32e29e5e728624469dd6fb0")
                .patch();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);

        System.out.println("Test --------------------------------------- ");
        assertEquals(200,response.getStatusCode());
        assertNotNull(body_response);
    }
}
