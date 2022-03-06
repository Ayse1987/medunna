package stepdefinitions.uiStepDefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.UIAppointmentPage;
import pojos.Appointment;
import utilities.DateUtils;
import utilities.Driver;
import utilities.WriteToTxt;

public class UIAppointmentStepDefs {

    UIAppointmentPage ap=new UIAppointmentPage();
    Faker faker=new Faker();
    Appointment appointment=new Appointment();

    @Given("user cliks on make an appointment")
    public void user_cliks_on_make_an_appointment() {

        //Driver.waitAndClick(ap.MakeAnAppointmentButton);

    }

    @Given("user provides the appointment name {string}")
    public void user_provides_the_appointment_name(String firstname) {
        firstname=faker.name().firstName();
        appointment.setFirstname(firstname);

        Driver.waitAndSendText(ap.firstnameTextbox, firstname);

    }

    @And("user provides the appointment lastname {string}")
    public void userProvidesTheAppointmentLastname(String lastname) {
        lastname=faker.name().lastName();
        appointment.setFirstname(lastname);

        Driver.waitAndSendText(ap.lastnameTextbox, lastname);

    }


    @And("user provides ssn and email {string} and {string}")
    public void userProvidesSsnAndEmailAnd(String ssn, String email) {
        ssn=faker.idNumber().ssnValid();
        email=faker.internet().emailAddress();
        appointment.setSsn(ssn);
        appointment.setEmail(email);

        Driver.waitAndSendText(ap.ssnTextbox, ssn);
        Driver.waitAndSendText(ap.emailTextbox, email);

    }

    @Given("user provides the phone number {string}")
    public void user_provides_the_phone_number(String phoneNumber) {
        phoneNumber=faker.phoneNumber().phoneNumber();
        appointment.setPhoneNumber(phoneNumber);

        Driver.waitAndSendText(ap.phoneTextbox, phoneNumber);

    }

    @Given("user provides the date {string}")
    public void user_provides_the_date(String date) {
        //05-04-2022
        //date= DateUtils.getDate();

        appointment.setDate(date);

        Driver.waitAndSendText(ap.datTextBox,date);



    }

    @Then("user requests appointment and verifies the success message")
    public void user_requests_appointment_and_verifies_the_success_message() {

        Driver.waitAndClick(ap.requestButton);
        WriteToTxt.saveAppointmentData(appointment);

        Assert.assertTrue(Driver.waitForVisibility(ap.successMessageToastContainer,10).isDisplayed());


    }


}
