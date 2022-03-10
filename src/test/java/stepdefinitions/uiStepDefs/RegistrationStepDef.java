package stepdefinitions.uiStepDefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
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
        registrant.setFirstName(firstName);
        registrant.setLastName(lastName);
        Driver.waitAndSendText(rp.firstnameTextBox,firstName);
        Driver.waitAndSendText(rp.lastnameTextBox,lastName);

    }
    @Then("user creates username {string}")
    public void user_creates_username(String userName) {
        userName=registrant.getFirstName()+registrant.getLastName();
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
        Driver.waitAndSendText(rp.confirmPasswordTextBox,password);

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
    @Given("user provides the password {string}")
    public void userProvidesThePassword(String password) {
       Driver.waitAndSendText(rp.firstPasswordTextBox,password);
    }
    @Then("user validates the password strength {string}")
    public void user_validates_the_password_strength(String level) {
        if(1==Integer.parseInt(level)){
            Assert.assertTrue(rp.passwordStrength1.isDisplayed());

        }else if(2==Integer.parseInt(level)){
            Assert.assertTrue(rp.passwordStrength2.isDisplayed());
        }else if (3==Integer.parseInt(level)){
            Assert.assertTrue(rp.passwordStrength3.isDisplayed());

        }
    }


    //-------------------------------------------------------------------

    @Given("go to medunna registration url {string}")
    public void goToMedunnaRegistrationUrl(String url) {
        Driver.getDriver().get(url);


    }

    @Then("verify the SSN number is valid")
    public void userVerifiesTheSSNNumberIsValid() {
        rp.firstnameTextBox.click();

        try {
            Assert.assertFalse(rp.ssnInvalidMessage.isDisplayed());
        }catch(Exception e){
            Assert.assertTrue(true);
        }

    }

    @And("leave the SSN box blank")
    public void leaveTheSSNBoxBlank() {
        rp.ssnTextBox.sendKeys("");

    }

    @Then("verify SSN required message is displayed")
    public void verifySSNRequiredMessageIsDisplayed() {
        rp.firstnameTextBox.click();
        try {
            Assert.assertTrue(rp.ssnRequiredMessage.isDisplayed());
        }catch(Exception e){
            Assert.assertTrue(false);
        }
    }

    @And("enter nine digit SSN without - ssn {string}")
    public void enterNineDigitSSNWithoutSsn(String ssn) {
        rp.ssnTextBox.sendKeys(ssn);

    }

    @Then("verify the SSN invalid message is displayed")
    public void verifyTheSSNInvalidMessageIsDisplayed() {
        rp.firstnameTextBox.click();
        try{
            Assert.assertTrue(rp.ssnInvalidMessage.isDisplayed());
        }catch(Exception e){
            Assert.assertTrue(false);

        }

    }


    @And("enter eight digit SSN {string}")
    public void userShouldEnterEightDigitSSN(String ssn) {
        rp.ssnTextBox.sendKeys(ssn);
    }


    @And("enter ten digit SSN {string}")
    public void userShouldEnterTenDigitSSN(String ssn) {
        rp.ssnTextBox.sendKeys(ssn);
    }

    @And("enter valid firstname {string}")
    public void enterValidFirstname(String firstName) {
        rp.firstnameTextBox.sendKeys(firstName);

    }

    @Then("verify first name is valid")
    public void verifyFirstNameIsValid() {
        rp.lastnameTextBox.click();
        try {
            Assert.assertFalse(rp.firstNameRequiredMessage.isDisplayed());
        }catch(Exception e){
            Assert.assertTrue(true);
        }
    }

    @And("leave first name box blank")
    public void leaveFirstNameBoxBlank() {
        rp.firstnameTextBox.sendKeys("");
    }

    @Then("verify first name required message is displayed")
    public void verifyFirstNameRequiredMessageIsDisplayed() {
        rp.lastnameTextBox.click();
        try {
            Assert.assertTrue(rp.firstNameRequiredMessage.isDisplayed());
        }catch(Exception e){
            Assert.assertTrue(false);
        }
    }


    @And("enter valid lastname {string}")
    public void enterValidLastname(String lastName) {
        rp.lastnameTextBox.sendKeys(lastName);

    }

    @Then("verify lastname is valid")
    public void verifyLastnameIsValid() {
        rp.usernameTextBox.click();
        try{
            Assert.assertFalse(rp.lastNameRequiredMessage.isDisplayed());
        }catch(Exception e){
            Assert.assertTrue(true);
        }
    }

    @And("leave last name blank")
    public void leaveLastNameBlank() {
        rp.lastnameTextBox.sendKeys("");

    }

    @Then("verify last name required message is displayed")
    public void verifyLastNameRequiredMessageIsDisplayed() {
        rp.usernameTextBox.click();
        try {
            Assert.assertTrue(rp.lastNameRequiredMessage.isDisplayed());
        }catch(Exception e){
            Assert.assertTrue(false);
        }
    }
}
