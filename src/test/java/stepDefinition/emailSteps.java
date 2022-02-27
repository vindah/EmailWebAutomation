package stepDefinition;

import java.util.concurrent.TimeUnit;

import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import dataProvider.configFileReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.email.emailLoginPageFactory;
import pages.email.passwordLoginPageFactory;
import pages.email.emailSendPageFactory;


public class emailSteps {


    WebDriver driver;
    emailLoginPageFactory emailLoginPage;
    passwordLoginPageFactory passwordLoginPage;
    emailSendPageFactory emailSendPage;
    configFileReader ConfigFileReader;


    @Before()
    public void setup() {
        driver = utilities.driverFactory.open("chrome");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        ConfigFileReader = new configFileReader();
    }

    //For the smoke tests(Verify that the login page loads properly)
    @Given ("The merchant is on the email login page")
    public void merchant_on_login_page() {
        System.out.println("Opening URL: " + ConfigFileReader.getApplicationUrl());    //+ "signin"
        driver.get(ConfigFileReader.getApplicationUrl()) ;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        emailLoginPage = new emailLoginPageFactory(driver);
    }

    @Then ("The Logo should be displayed")
    public void verify_login_logo () {
        Assert.assertTrue(emailLoginPage.isLogoDisplayed());
    }

    @Then ("The sign in text should be displayed")
    public void verify_signIn_text () {
        Assert.assertTrue(emailLoginPage.isHeaderTextDisplayed());
        Assert.assertTrue(emailLoginPage.isHeaderSubtextDisplayed());
    }

    @Then ("The Email field should be present and editable")
    public void verify_email () {
        Assert.assertTrue(emailLoginPage.isEmailFieldDisplayed());
        Assert.assertTrue(emailLoginPage.isEmailEditable());
        Assert.assertEquals(emailLoginPage.getEmailFieldTypeAttribute(), "email");
    }

    @Then ("The Next button should be present and clickable")
    public void verify_next_btn () {
        Assert.assertTrue(emailLoginPage.isNextBtnDisplayed());
        Assert.assertTrue(emailLoginPage.isNextBtnEnabled());
    }

    @Then ("The Forgot email text should be displayed")
    public void verify_forgot_pwd_text () {
        Assert.assertTrue(emailLoginPage.isForgotPasswordDisplayed());
    }

    //Functional test - For the entries on the email page
    @When("The user enters their email")
    public void the_user_enters_a_correct_email() {
        emailLoginPage.setEmail(ConfigFileReader.getValidEmail());
        //emailLoginPage.emailNext(email);
    }

    @And("The user clicks on the next button")
    public void click_the_next_button() {
        passwordLoginPage = emailLoginPage.clickNextBtn();
    }

    @Then("The password page should be displayed")
    public void email_next_success() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.print("Password Page did not load on time /n" + e.getMessage());
        }

        passwordLoginPage = new passwordLoginPageFactory(driver);
        Assert.assertTrue(passwordLoginPage.isShowPasswordDisplayed());
        Assert.assertTrue(passwordLoginPage.isHeaderTextDisplayed());
        Assert.assertTrue(passwordLoginPage.isUserEmailDisplayed());
        Assert.assertTrue(passwordLoginPage.isPasswordFieldDisplayed());
        Assert.assertTrue(passwordLoginPage.getPasswordFieldTypeAttribute().equals("password")
                || passwordLoginPage.getPasswordFieldTypeAttribute().equals(null));

    }

    //Functional test - For the entries on the password page
    @When("The user enters their password")
    public void the_user_enters_a_correct_password() {
        //passwordLoginPage.passwordNext(password);
        passwordLoginPage.setPassword(ConfigFileReader.getValidPassword());
        Assert.assertTrue(passwordLoginPage.isNextBtnDisplayed());
    }

    @And("The user clicks on the password next button")
    public void click_on_next_btn() {
        emailSendPage = passwordLoginPage.clickOnNextBtn();
    }

    @Then("The users email page should be displayed successfully.")
    public void password_next_success() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.print("Password Page did not load on time /n" + e.getMessage());
        }

        emailSendPage = new emailSendPageFactory(driver);
        Assert.assertTrue(emailSendPage.isSideLogoTextDisplayed());
        Assert.assertTrue(emailSendPage.isSearchFieldDisplayed());
        Assert.assertTrue(emailSendPage.isComposeEmailBtnDisplayed());

    }

    @When("The user clicks on Compose")
    public void click_on_compose(){
        emailSendPage.clickOnComposeBtn();
    }

    @Then("The New message modal should be displayed")
    public void verify_new_message_view(){
        Assert.assertTrue(emailSendPage.isNewMessageHeaderDisplayed());
        Assert.assertTrue(emailSendPage.isToFieldDisplayed());
    }

    @When("The user enters the recipient email")
    public void enters_recipient_email(){
        Assert.assertTrue(emailSendPage.isToFieldEditable());
        emailSendPage.setRecipientEmail(ConfigFileReader.getValidEmail());
    }

    @And("The user enters the subject and body")
    public void enters_subject_and_body(){
        emailSendPage.setSubjectBox("hi");
        emailSendPage.setBody("Automation test");
    }

    @And("The user clicks on send")
    public void clicks_on_sendbtn(){
        emailSendPage.clickOnSendBtn();
    }

    @Then("The email should be sent successfully")
    public void email_sent_success() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.print("Email did not send but saved as draft /n" + e.getMessage());
        }

        emailSendPage = new emailSendPageFactory(driver);
        Assert.assertTrue(emailSendPage.isSuccessMessageDisplayed());

    }

    @When("The user clicks on received email")
    public void click_on_received_email(){
        emailSendPage.clickOnReceivedEmail();
    }

    @Then("The body of the email should be visible and correct")
    public void body_or_mail_visible(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.print("Cannot see body of email /n" + e.getMessage());
        }

        emailSendPage = new emailSendPageFactory(driver);
        Assert.assertTrue(emailSendPage.isReceivedEmailBodyDisplayed());
        Assert.assertTrue(emailSendPage.isReceivedEmailBodyVisible());

    }







    @After()
    public void takeScreenshots(Scenario scenario) {
        if (scenario.isFailed()) {
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
        driver.quit();
    }


}
