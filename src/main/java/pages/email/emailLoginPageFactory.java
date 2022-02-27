package pages.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.File;
import java.io.FileWriter;
import pages.email.passwordLoginPageFactory;



public class emailLoginPageFactory {
    WebDriver driver;

    //Getting all the paths/names/selectors on the page
    @FindBy(id="logo")
    WebElement logo;

    @FindBy(id ="headingText")
    WebElement headerText;

    @FindBy(id = "headingSubtext")
    WebElement headerSubtext;

    @FindBy(xpath = "//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[3]/button")
    WebElement forgotPasswordBtn;

    @FindBy(id = "identifierId")
    WebElement emailField;

    @FindBy(id = "identifierNext")
    WebElement nextBtn;




    public emailLoginPageFactory(WebDriver driver) {
        this.driver = driver;

        //Initialize web elements
        PageFactory.initElements(driver, this);
    }

    public void setEmail(String email) {
        emailField.sendKeys(email);
    }

    public passwordLoginPageFactory clickNextBtn() {
        nextBtn.click();
        return new passwordLoginPageFactory(driver);
    }

//    public void emailNext(String email) {
//        setEmail(email);
//        //clickNextBtn();
//    }

    //Check that elements on the page are displayed.
    public boolean isLogoDisplayed () {
        try {
            return logo.isDisplayed();
        } catch(Exception e) {
            System.out.print("Logo cannot be located \n" + e.getMessage());
            return false;
        }
    }

    public boolean isEmailFieldDisplayed () {
        try {
            return emailField.isDisplayed();
        } catch(Exception e) {
            System.out.print("Email field cannot be located \n" + e.getMessage());
            return false;
        }
    }
    public String getEmailFieldTypeAttribute () {
        return emailField.getAttribute("type");
    }

    public boolean isHeaderTextDisplayed () {
        try {
            return headerText.isDisplayed();
        } catch(Exception e) {
            System.out.print("Sign in text in header cannot be located \n" + e.getMessage());
            return false;
        }
    }

    public boolean isHeaderSubtextDisplayed () {
        try {
            return headerSubtext.isDisplayed();
        } catch(Exception e) {
            System.out.print("Sign in sub text cannot be located \n" + e.getMessage());
            return false;
        }
    }

    public boolean isForgotPasswordDisplayed () {
        try {
            return forgotPasswordBtn.isDisplayed();
        } catch(Exception e) {
            System.out.print("Forgot password text cannot be located \n" + e.getMessage());
            return false;
        }
    }
//    public String getFP_href() {
//        return forgotPasswordBtn.getAttribute("href");
//    }

    public boolean isNextBtnDisplayed () {
        try {
            return nextBtn.isDisplayed();
        } catch(Exception e) {
            System.out.print("Next button cannot be located \n" + e.getMessage());
            return false;
        }
    }
    public boolean isNextBtnEnabled () {
        if (nextBtn.isEnabled()) return true;
        return false;
    }

    //Check that email field is editable
    private boolean isEditable(WebElement element) {
        element.sendKeys("editable");
        if (element.getAttribute("value").contentEquals("editable")) return true;
        return false;
    }

    public boolean isEmailEditable() {
        return isEditable(emailField);
    }





}


