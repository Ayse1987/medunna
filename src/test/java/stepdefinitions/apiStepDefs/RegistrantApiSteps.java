package stepdefinitions.apiStepDefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.tr.Fakat;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojos.Registrant;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.given;
import static utilities.WriteToTxt.saveRegistrantData;

public class RegistrantApiSteps {
    Registrant registrant = new Registrant();
    Faker faker = new Faker();
    Response response;
    @Given("user sets the necessary path params")
    public void user_sets_the_necessary_path_params() {
       Hooks.spec.pathParams("first", "api", "second", "register");

    }
    @And("user sets the expected data {string} , {string} {string} {string} , {string} ,{string} , {string}")
    public void userSetsTheExpectedData(String firstname, String lastname, String ssn, String email, String username, String password, String lan) {
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
    }

    @And("user sends the request and gets the response")
    public void userSendsTheRequestAndGetsTheResponse() {
       //RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://medunna.com").build();
       //Hooks.spec.pathParams("first", "api", "second", "register");
        response = given().spec(Hooks.spec).contentType(ContentType.JSON).body(registrant).when().post("/{first}/{second}");
    }

    @When("user saves the api records to correspondent files")
    public void user_saves_the_api_records_to_correspondent_files() {
        saveRegistrantData(registrant);
    }
    @Then("user validates api records")
    public void user_validates_api_records() throws Exception {
        response.then().statusCode(201);
        response.prettyPrint();

        ObjectMapper obj=new ObjectMapper();
        //when we use objectMapper we need to use

        Registrant actualRegistrant=obj.readValue(response.asString(),Registrant.class);
        System.out.println(actualRegistrant);

        Assert.assertEquals(registrant.getFirstName(),actualRegistrant.getFirstName());
        Assert.assertEquals(registrant.getSsn(),actualRegistrant.getSsn());
    }



}