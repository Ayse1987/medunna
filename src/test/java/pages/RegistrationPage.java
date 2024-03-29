package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class RegistrationPage {

    public RegistrationPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "ssn")
    public WebElement ssnTextBox;
    @FindBy(id = "firstName")
    public WebElement firstnameTextBox;
    @FindBy(id = "lastName")
    public WebElement lastnameTextBox;
    @FindBy(id = "username")
    public WebElement usernameTextBox;
    @FindBy(id = "email")
    public WebElement emailTextbox;
    @FindBy(id = "firstPassword")
    public WebElement firstPasswordTextBox;
    @FindBy(id = "secondPassword")
    public WebElement confirmPasswordTextBox;
    @FindBy(id = "register-submit")
    public WebElement registerButton;
    @FindBy(xpath = "//*[contains(text(),'Registration Saved')]")
    public WebElement successMessageToastContainer;

    @FindBy(xpath = "//*[@id='strengthBar']/li[contains(@style,'rgb(255, 0, 0)')]")
    public WebElement passwordStrength1;

    @FindBy(xpath = "//*[@id='strengthBar']/li[contains(@style,'rgb(255, 153, 0)')]")
    public WebElement passwordStrength2;

    @FindBy(xpath = "//*[@id='strengthBar']/li[contains(@style,'rgb(153, 255, 0)')]")
    public WebElement passwordStrength3;


    //---------------------------------------------------------------


    @FindBy(xpath = "//div[contains(text(),'Your SSN is invalid')]")
    public WebElement ssnInvalidMessage;

    @FindBy(xpath = "//div[contains(text(),'Your SSN is required.')]")
    public WebElement ssnRequiredMessage;

    @FindBy(xpath = "//div[contains(text(),'Your FirstName is required.')]")
    public WebElement firstNameRequiredMessage;

    @FindBy(xpath = "//div[contains(text(),'Your LastName is required.')]")
    public WebElement lastNameRequiredMessage;


















}