package stepdefinitions.apiStepDefs;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.tr.Fakat;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojos.Registrant;
import utilities.ConfigurationReader;
import utilities.ReadTxt;
import utilities.WriteToTxt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static utilities.ApiUtils.getRequest;
import static utilities.ApiUtils.putRequest;
import static utilities.Authentication.generateToken;
import static utilities.ReadTxt.*;
import static utilities.WriteToTxt.saveRegistrantData;
import static hooks.Hooks.spec;
import static utilities.WriteToTxt.saveRegistrantsData;

public class RegistrantApiSteps  {
    Registrant registrant = new Registrant();
    Faker faker = new Faker();
    Response response;
    Registrant []registrants;
    @Given("user sets the necessary path params")
    public void user_sets_the_necessary_path_params() {
        spec.pathParams("first", "api", "second", "register");
    }
    @Given("user sets the expected data {string}, {string} {string} {string} {string} {string} and {string}")
    public void user_sets_the_expected_data_and(String firstname, String lastname, String ssn, String email, String username, String password, String lan) {
        firstname = faker.name().firstName();
        lastname = faker.name().lastName();
        ssn = faker.idNumber().ssnValid();
        email = faker.internet().emailAddress();
        username = faker.name().username();
        password = faker.internet().password(8, 15, true, true);
        registrant.setFirstName(firstname);
        registrant.setLastName(lastname);
        registrant.setSsn(ssn);
        registrant.setEmail(email);
        registrant.setLogin(username);
        registrant.setPassword(password);
        registrant.setLangKey(lan);
//        Map<String ,Object> expectedData = new HashMap<>();
//        expectedData.put("firstName", firstname);
    }
    @Given("user sends the POST request and gets the response")
    public void user_sends_the_post_request_and_gets_the_response() {
        response = given().spec(spec).contentType(ContentType.JSON).body(registrant).when().post("/{first}/{second}");
    }
    @When("user saves the api records to correspondent files")
    public void user_saves_the_api_records_to_correspondent_files() {
        saveRegistrantData(registrant);
    }
    @Then("user validates api records")
    public void user_validates_api_records() throws  Exception{
        response.then().statusCode(201);
        response.prettyPrint();
        ObjectMapper obj = new ObjectMapper();
        Registrant actualRegistrant = obj.readValue(response.asString(), Registrant.class);
        System.out.println(actualRegistrant);
        assertEquals(registrant.getFirstName(), actualRegistrant.getFirstName());
        assertEquals(registrant.getLastName(), actualRegistrant.getLastName());
        assertEquals(registrant.getSsn(), actualRegistrant.getSsn());
    }
    @Given("user sends the get request for users data")
    public void user_sends_the_get_request_for_users_data() {
        response = getRequest(generateToken(), ConfigurationReader.getProperty("registrant_endpoint"));
        //This can be also used
        /*
        response = given().headers(
                "Authorization",
                "Bearer " + token,
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON).when().get(endpoint);
         */
    }
    @Given("user deserializes data to Java")
    public void user_deserializes_data_to_java()throws Exception {
//        response.prettyPrint();
        ObjectMapper obj = new ObjectMapper();
//
        registrants = obj.readValue(response.asString(), Registrant[].class);
//        System.out.println(registrants.length);
//        for (int i=0; i< registrants.length; i++){
//            System.out.println("name"+registrants[i].getFirstName());
//        }
    }
    @Given("user saves the users data to correspondent files")
    public void user_saves_the_users_data_to_correspondent_files() {
        List<String> expectedData = getSSNIDs();
        saveRegistrantsData(registrants);
        List<String> actualSSNIDs = getAPISSNIDs();
        List<String > checkList = new ArrayList<>();
        for(int i=actualSSNIDs.size()-1; i>= actualSSNIDs.size()-10;i--){
            checkList.add(actualSSNIDs.get(i));//10 records
        }
        System.out.println(actualSSNIDs);
        Assert.assertTrue(expectedData.containsAll(checkList));
        System.out.println(checkList);
    }

    //=====================================================

    @Given("set all registrant info to response")
    public void setAllRegistrantInfoToResponse() {
        response = getRequest(generateToken(), ConfigurationReader.getProperty("registrant_endpoint"));
        //response.prettyPrint();
    }

    @And("deserialize all registrant info to Java")
    public void deserializeAllRegistrantInfoToJava() throws Exception {
        ObjectMapper obj=new ObjectMapper();

        registrants = obj.readValue(response.asString(), Registrant[].class);
        System.out.println(registrants.length);

        for(int i=0; i<registrants.length; i++){
           // System.out.println("ssn "+ registrants[i].getSsn());
        }


    }

    @And("save all registrant info to correspondent files")
    public void saveAllRegistrantInfoToCorrespondentFiles() {
        WriteToTxt.saveRegistrantsData(registrants);
    }


    @Then("validate registrants info contains {string}")
    public void validateRegistrantsInfoContains(String expextedSSN) {

        List<String>actualSSNIDs= ReadTxt.getAPISSNIDs();//from api
        List<String>expectedSSNIDs=ReadTxt.getSSNIDs(); //from database
       // System.out.println(currentData);

        List<String >checkList=new ArrayList<>();

        for(int i=200; i<210; i++){
            checkList.add(expectedSSNIDs.get(i));
        }

        System.out.println(checkList);
        System.out.println(actualSSNIDs);


        Assert.assertTrue(actualSSNIDs.containsAll(checkList));

        /*
        boolean flag=false;
        for(int i=0; i<registrants.length; i++){
           if(registrants[i].getSsn().equals(expextedSSN)){
               //System.out.println("ok pass");
               flag=true;
               break;
           }

        }
        Assert.assertTrue(flag);
         */
    }

    @Then("user validates status code and api records")
    public void userValidatesStatusCodeAndApiRecords() throws IOException {
        response.then().statusCode(201);
        response.prettyPrint();

        ObjectMapper obj=new ObjectMapper();
        //when we use objectMapper we need to use

        Registrant actualRegistrant=obj.readValue(response.asString(),Registrant.class);
        System.out.println(actualRegistrant);

        Assert.assertEquals(registrant.getFirstName(),actualRegistrant.getFirstName());
        Assert.assertEquals(registrant.getSsn(),actualRegistrant.getSsn());
    }


    /*
    {
        "id": 3315,
        "login": "btrk",
        "firstName": "Trk",
        "lastName": "Bhr",
        "ssn": "355-99-1256",
        "email": "btrk@qa.team",
        "imageUrl": null,
        "activated": true,
        "langKey": "en",
        "createdBy": "anonymousUser",
        "createdDate": "2021-12-25T18:36:55.855770Z",
        "lastModifiedBy": "olleydone",
        "lastModifiedDate": "2022-03-15T17:00:40.369108Z",
        "authorities": [
            "ROLE_ADMIN"
        ]
    }
     */

    @Given("user sets the necessary path params for put request")
    public void userSetsTheNecessaryPathParamsForPutRequest() {
        //spec.pathParams("first","api","second","users");
    }

    @And("user sets the expected user data")
    public void userSetsTheExpectedUserData() {
        String [] authority = {"USER_ROLE"};
        registrant.setFirstName("usernew");
        registrant.setLastName("Sensoy23");
        registrant.setEmail("reySen263@gmail.com");
        registrant.setLogin("reyhannhanim23");
        registrant.setSsn("746-39-7563");
        registrant.setLangKey("en");
        registrant.setActivated(true);
        registrant.setAuthorities(authority);



        List<Registrant> registrants = getAllRegistrants();
        registrant.setId(registrants.get(registrants.size()-3).getId());

        System.out.println("User id: "+registrants.get(registrants.size()-3).getId());

    }

    @And("user makes a put request for users")
    public void userMakesAPutRequestForUsers() {

        putRequest(generateToken(), ConfigurationReader.getProperty("registrant_put_endpoint"), registrant);
        /*response=given().headers("Authorization","Bearer ",generateToken(),
                "Content-Type",ContentType.JSON,"Accept",ContentType.JSON).contentType(ContentType.JSON).
                body(registrant).when().put("/{first}/{second}");*/
       // response.prettyPrint();
    }

    @And("user validates the changes")
    public void userValidatesTheChanges() {
        JsonPath json=response.jsonPath();
        System.out.println(json.getString("firstName"));
        System.out.println(registrant.getFirstName());


        Assert.assertEquals(registrant.getFirstName(),json.getString("firstName"));
    }
}
