package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class UIAppointmentPage {

    public UIAppointmentPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }



    @FindBy(xpath = "//*[text()='Make an Appointment']")
// @FindBy(className = "d-none d-md-inline")
    public WebElement MakeAnAppointmentButton;

    @FindBy(id = "firstName")
    public WebElement firstnameTextbox;

    @FindBy(id = "lastName")
    public WebElement lastnameTextbox;

    @FindBy(id = "ssn")
    public WebElement ssnTextbox;

    @FindBy(id = "email")
    public WebElement emailTextbox;

    @FindBy(id = "phone")
    public WebElement phoneTextbox;

    @FindBy(xpath = "//*[text()='Send an Appointment Request']")
    public WebElement requestButton;

    @FindBy (name = "appoDate")
    public WebElement datTextBox;

    @FindBy(xpath = "//*[text()='Appointment registration saved!']")
    public WebElement successMessageToastContainer;


}


