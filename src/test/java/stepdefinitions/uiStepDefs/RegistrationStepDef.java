package stepdefinitions.uiStepDefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.RegistrationPage;
import pojos.Registrant;
import utilities.Driver;
import utilities.WriteToTxt;


public class RegistrationStepDef  {

    RegistrationPage rp=new RegistrationPage();
    Faker faker=new Faker();
    Registrant registrant=new Registrant();

    @Given("user provides ssn id {string}")
    public void user_provides_ssn_id(String ssn) {
        ssn=faker.idNumber().ssnValid();
        registrant.setSsn(ssn);
        Driver.waitAndSendText(rp.ssnTextBox,ssn);

    }
    @Given("user provides firstname and lastname {string} and {string}")
    public void user_provides_firstname_and_lastname_and(String firstName, String lastName) {
        firstName=faker.name().firstName();
        lastName=faker.name().lastName();
        registrant.setFirstname(firstName);
        registrant.setLastname(lastName);
        Driver.waitAndSendText(rp.firstnameTextBox,firstName);
        Driver.waitAndSendText(rp.lastnameTextBox,lastName);

    }
    @Then("user creates username {string}")
    public void user_creates_username(String userName) {
        userName=registrant.getFirstname()+registrant.getLastname();
        Driver.waitAndSendText(rp.usernameTextBox,userName);

    }
    @Then("user provides also email {string}")
    public void user_provides_also_email(String email) {
        email=faker.internet().emailAddress();
        registrant.setEmail(email);
        Driver.waitAndSendText(rp.emailTextbox,email);

    }
    @Then("user generates the password {string}")
    public void user_generates_the_password(String password) {
        password=faker.internet().password(8,20,true,true);
        registrant.setPassword(password);
        Driver.waitAndSendText(rp.firstPasswordTextBox,password);
        Driver.waitAndSendText(rp.secondPasswordTextBox,password);

    }
    @Then("user registers and validates")
    public void user_registers_and_validates() {
        Driver.waitAndClick(rp.registerButton);
        Assert.assertTrue(Driver.waitForVisibility(rp.successMessageToastContainer,3).isDisplayed());

    }
    @Then("user creates the records to a correspondent file")
    public void user_creates_the_records_to_a_correspondent_file() {
        WriteToTxt.saveRegistrantData(registrant);


    }
}
