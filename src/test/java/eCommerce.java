import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import com.jayway.jsonpath.JsonPath;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class eCommerce {
    private static String baseURL;
    private static String token;
    private static String uuid;
    private static String account_id;



    @Before
    public void obtener_variables(){
        baseURL= System.getenv("base_url");
    }

    //Token
    //Variables ambiente
    //Calculos
    //Datos

    @Test
    public void  aagenerarToken(){

        String email = "agente1433@mailinator.com";
        int password = 41809;

        String datos = email +":"+password;
         String encode_Auth = Base64.getEncoder().encodeToString(datos.getBytes(StandardCharsets.UTF_8));
        //String url = String.format("https://%s/nga/api/v1.1/private/accounts?lang=es",baseURL);

        RestAssured.baseURI = String.format("https://%s/nga/api/v1.1/private/accounts",baseURL);
      /* Response resp = given()
                .header("Authorization","Basic "+ encode_Auth)
                .post(url);*/

        Response resp = given()
                .log().all()
                .queryParam("lang","es")
                .header("Authorization","Basic "+ encode_Auth)
                .post();

        String body_response = resp.getBody().asString();
        System.out.println("Body Response"+ body_response);
        token = JsonPath.read(body_response,"$.access_token");
        uuid = JsonPath.read(body_response,"$.account.uuid");
        account_id = JsonPath.read(body_response,"$.account.account_id");
        System.out.println("Token"+ token);
        System.out.println("uuid"+ uuid);
        System.out.println("account_id"+ account_id);

        assertEquals(200,resp.statusCode());
          }

    @Test
    public void obtener_categorias(){
        //String base_URL = "webapi.segundamano.mx";
        //String baseURL = System.getenv("base_url");
        RestAssured.baseURI = String.format("https://%s/nga/api/v1/public/categories/insert",baseURL);
        Response response = given()
                .log().all()
                .queryParam("lang","es").get();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Headers: "+ response.getHeader("Content-Type")); //- imprime un header
        System.out.println("Time " +response.getTime());

        assertEquals(200,response.statusCode());
        assertEquals(200,response.getStatusCode());
        assertTrue(body_response.contains("categories"));
        assertTrue(response.getTime()<=5000);

    }

    @Test()
    public void crear_urls(){
        RestAssured.baseURI = String.format("https://%s/urls/v1/public/ad-listing",baseURL);
        //https://webapi.segundamano.mx/urls/v1/public/ad-listing?lang=es
        String body_request = "{\n" +
                "    \"filters\": [\n" +
                "        {\n" +
                "            \"category\": \"1000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"1020\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"1040\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"1060\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"1080\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"2000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"2020\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"2040\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"2120\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"2080\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"2060\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"5000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"5040\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"5080\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"5020\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"5060\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"3000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"3040\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"3020\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"3060\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"3100\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"3080\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"3120\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"6000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"6020\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"6040\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"6060\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"4000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"4020\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"4040\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"4060\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"4100\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"4080\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"4120\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"4140\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8020\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8040\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8060\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8080\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8100\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8120\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8140\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8160\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8180\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8200\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8220\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"8240\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"9000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"9020\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"category\": \"9040\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Response response = given()
                .log().all()
                .queryParam("lang","es")
                .body(body_request)
                .post();

        String body_response = response.getBody().asString();
        System.out.println("Response  --------------------------------------- ");
        System.out.println("Body response: " + body_response);
        assertTrue(body_response.contains("urls"));

        assertEquals(200,response.getStatusCode());
        assertNotNull(body_response);
    }

    @Test
    public void crear_usuario(){
        String username = "agente" + (Math.floor(Math.random()*7539 + 2));
        String email = username + "@mailinator.com";
        double password = (Math.floor(Math.random()*75639 + 2));

        String datos = email +":"+password;
        String encode_Auth = Base64.getEncoder().encodeToString(datos.getBytes(StandardCharsets.UTF_8));


         String body_request = "{\"account\":{\"email\":\""+email+"\",\"name\":\""+username+"\",\"phone\":4949451721}}";
         System.out.println ("Body request" + body_request);
        RestAssured.baseURI = String.format("https://%s/nga/api/v1.1/private/accounts",baseURL);

        Response response = given()
                .log().all()
                .contentType("application/json")
                .header("Authorization","Basic " +encode_Auth)
                .body(body_request)
                .post();

        String body_response = response.getBody().asString();
        System.out.println("Body Response: " + body_response);

        System.out.println("Datos : " + username +" "+email+" "+password );
        System.out.println("Encoded: " + encode_Auth );

        assertEquals(200,response.getStatusCode());
        assertNotNull(body_response);
    }

    @Test

    public void  crear_una_direccion(){
        RestAssured.baseURI = String.format("https://%s/addresses/v1/create",baseURL);
        String  datos = uuid+":"+token;
        System.out.println("Datos "+datos);
        String encode_Auth = Base64.getEncoder().encodeToString(datos.getBytes(StandardCharsets.UTF_8));

        Response response = given()
                .log().all()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization","Basic " + encode_Auth)
                .formParam("contact","Martha Martinez A")
                .formParam("phone","3234445555")
                .formParam("rfc", "CASA681225XXX")
                .formParam("zipCode", "45050")
                .formParam("exteriorInfo", "Hidalgo 204213")
                .formParam("region", "10")
                .formParam("municipality", "233")
                .formParam("area", "5696")
                .formParam("alias", "big house")
                .post();


        String body_response = response.getBody().asString();
        System.out.println("Body Response"+ body_response);
        assertEquals(201,response.getStatusCode());
        assertNotNull(body_response);
    }

    @Test
    public void  crear_una_anuncio(){
        System.out.println("uuid "+ uuid);
        RestAssured.baseURI = String.format("https://%s/accounts/%s/up", baseURL, uuid);
        String datos = uuid + ":" + token;
        String encodeAuth = Base64.getEncoder().encodeToString(datos.getBytes(StandardCharsets.UTF_8));
        String bodyRequest = "{\n" +
                "    \"images\": \"8591150986.jpg\",\n" +
                "    \"category\": \"4100\",\n" +
                "    \"subject\": \"Figuras Amiibo, Varias. Zelda, Mario, Pokémon.\",\n" +
                "    \"body\": \"Figuras interactivas y coleccionables de Nintendo. Varios personajes. Pregunta por disponibilidad.\",\n" +
                "    \"is_new\": \"0\",\n" +
                "    \"price\": \"350\",\n" +
                "    \"region\": \"11\",\n" +
                "    \"municipality\": \"294\",\n" +
                "    \"area\": \"119537\",\n" +
                "    \"phone_hidden\": \"true\",\n" +
                "    \"show_phone\": \"false\"\n" +
                "}";
        Response response = given()
                .log().all()
                .header("Authorization", "Basic " + encodeAuth)
                .header("x-source", "PHOENIX_DESKTOP")
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .post();
        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);
        assertEquals(200,response.getStatusCode());
        assertNotNull(bodyResponse);
    }

    @Test
    public void cambiar_de_telefono(){

        RestAssured.baseURI= String.format("https://%s/nga/api/v1/private/accounts/11116213?lang=es", baseURL);
        String bodyRequest = "{\n" +
                "    \"account\": {\n" +
                "        \"name\": \"agente Carrillo\",\n" +
                "        \"phone\": \"8632712665\",\n" +
                "        \"locations\": [\n" +
                "            {\n" +
                "                \"code\": \"21\",\n" +
                "                \"key\": \"region\",\n" +
                "                \"label\": \"Nuevo León\",\n" +
                "                \"locations\": [\n" +
                "                    {\n" +
                "                        \"code\": \"1003\",\n" +
                "                        \"key\": \"municipality\",\n" +
                "                        \"label\": \"Monterrey\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"professional\": false,\n" +
                "        \"phone_hidden\": false\n" +
                "    }\n" +
                "}";

        Response response = given()
                .log().all()
                .header("Authorization", "tag:scmcoord.com,2013:api " + token)
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .patch();
        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);
        assertEquals(200,response.getStatusCode());
        assertNotNull(bodyResponse);
    }

    @Test
    public void alertas(){
        RestAssured.baseURI= String.format("https://%s/alerts/v1/private/account/%s/alert",baseURL,uuid);
        String datos = uuid + ":" + token;
        String encode_Autho = Base64.getEncoder().encodeToString(datos.getBytes(StandardCharsets.UTF_8));

        Response response  = given()
                .log().all()
                .header("Authorization", "Basic " + encode_Autho)
                .get();
                String bodyResponse = response.getBody().asString();
                System.out.println("Body Response: " + bodyResponse);
        assertEquals(200,response.getStatusCode());
        assertNotNull(bodyResponse);
    }

    @Test
    public void cambiar_de_telefono_incorrecto(){

        RestAssured.baseURI= String.format("https://%s/nga/api/v1/private/accounts/11116213?lang=es", baseURL);
        String bodyRequest = "{\n" +
                "    \"account\": {\n" +
                "        \"name\": \"agente Carrillo\",\n" +
                "        \"phone\": \"8632712\",\n" +
                "        \"locations\": [\n" +
                "            {\n" +
                "                \"code\": \"21\",\n" +
                "                \"key\": \"region\",\n" +
                "                \"label\": \"Nuevo León\",\n" +
                "                \"locations\": [\n" +
                "                    {\n" +
                "                        \"code\": \"1003\",\n" +
                "                        \"key\": \"municipality\",\n" +
                "                        \"label\": \"Monterrey\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"professional\": false,\n" +
                "        \"phone_hidden\": false\n" +
                "    }\n" +
                "}";

        Response response = given()
                .log().all()
                .header("Authorization", "tag:scmcoord.com,2013:api " + token)
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .patch();
        String bodyResponse = response.getBody().asString();
        System.out.println("Body Response: " + bodyResponse);

        assertEquals(400,response.getStatusCode());
        assertNotNull(bodyResponse);
    }

}

