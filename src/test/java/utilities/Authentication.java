package utilities;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static hooks.Hooks.spec;
import static io.restassured.RestAssured.given;
public class Authentication {

    public static void main(String[] args) {
        System.out.println(generateToken());
    }

    public static String generateToken(){
        //Set the base url
        //api/authenticate
        //spec = new RequestSpecBuilder().setBaseUri(ConfigurationReader.getProperty("base_url")).build();

        spec.pathParams("first", "api", "second", "authenticate");

        //Set the expected data

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("username", "admin79");
        expectedData.put("password", "admin");
        expectedData.put("rememberMe","true" );

        //Send the Post request and get the data

        Response response = given().spec(spec).contentType(ContentType.JSON).
                body(expectedData).when().post("/{first}/{second}");

        //response.prettyPrint();
        JsonPath json = response.jsonPath();

        //for validation
        //I can use hemchrestmatchers, ObjectMapper, JsonPath, Map

        return json.getString("id_token");
    }
}
