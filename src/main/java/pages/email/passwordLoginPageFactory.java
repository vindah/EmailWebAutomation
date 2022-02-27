package pages.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class passwordLoginPageFactory {
    WebDriver driver;

    //Getting all the paths/names/selectors on the page
    @FindBy(id="logo")
    WebElement logo;

    @FindBy(id ="headingText")
    WebElement pHeaderText;

    @FindBy(id = "profileIdentifier")
    WebElement userEmail;

    @FindBy(id = "selectionc0")
    WebElement showPasswordText;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(id = "passwordNext")
    WebElement nextBtn;




    public passwordLoginPageFactory(WebDriver driver) {
        this.driver = driver;

        //Initialize web elements
        PageFactory.initElements(driver, this);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public emailSendPageFactory clickOnNextBtn() {
        nextBtn.click();
        return new emailSendPageFactory(driver);
    }

//    public void passwordNext(String password) {
//        setPassword(password);
//    }

    //Check that elements on the page are displayed.
    public boolean isHeaderTextDisplayed () {
        try {
            return pHeaderText.isDisplayed();
        } catch(Exception e) {
            System.out.print("Sign in text in header cannot be located \n" + e.getMessage());
            return false;
        }
    }

    public boolean isUserEmailDisplayed () {
        try {
            return userEmail.isDisplayed();
        } catch(Exception e) {
            System.out.print("User email cannot be located \n" + e.getMessage());
            return false;
        }
    }

    public boolean isShowPasswordDisplayed () {
        try {
            return showPasswordText.isDisplayed();
        } catch(Exception e) {
            System.out.print("Show password text cannot be located \n" + e.getMessage());
            return false;
        }
    }

    public boolean isPasswordFieldDisplayed () {
        try {
            return passwordField.isDisplayed();
        } catch(Exception e) {
            System.out.print("Password field cannot be loacated \n" + e.getMessage());
            return false;
        }
    }

    public String getPasswordFieldTypeAttribute () {
        return passwordField.getAttribute("type");
    }

    public boolean isNextBtnDisplayed () {
        try {
            return nextBtn.isDisplayed();
        } catch(Exception e) {
            System.out.print("Next button cannot be located \n" + e.getMessage());
            return false;
        }
    }

    //Check that password field is editable
    private boolean isEditable(WebElement element) {
        element.sendKeys("editable");
        if (element.getAttribute("value").contentEquals("editable")) return true;
        return false;
    }

    public boolean isEmailEditable() {
        return isEditable(passwordField);
    }







}
