import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class token_Ejemplo {
    @Test
    public void  generarToken(){
        String url = String.format("https://webapi.segundamano.mx/nga/api/v1.1/private/accounts?lang=es");
        Response resp = given().header("Authorization","Basic cGFwaXRhc2xleXM5MUBnbWFpbC5jb206Y29udHJhMTIz").post(url);
        String body_response = resp.getBody().asString();
        System.out.println("Body Response"+ body_response);
    }
}
