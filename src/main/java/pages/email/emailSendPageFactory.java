package pages.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class emailSendPageFactory {
    WebDriver driver;

    //Getting all the elements/selectors on the page
    @FindBy(xpath = "//*[@id=\"gb\"]/div[2]/div[1]/div[4]/div/a")
    WebElement sideLogoText;

    @FindBy(id ="aso_search_form_anchor")
    WebElement searchField;

    @FindBy(css = "div.T-I.T-I-KE.L3")
    WebElement composeEmailBtn;

    @FindBy(className = "aYF")
    WebElement newMessageHeader;

    @FindBy(css = ".oj div textarea")
    WebElement toField;

    @FindBy(name = "subjectbox")
    WebElement subjectField;

    @FindBy(css = ".Ar.Au div")
    WebElement emailBody;

    @FindBy(css = "tr.btC td:nth-of-type(1) div div:nth-of-type(2) div")
    WebElement sendBtn;

    @FindBy(xpath = "/html/body/div[7]/div[3]/div/div[1]/div[4]/div[1]/div/div[3]/div/div/div[2]/span/span[1]")
    WebElement successAlert;

    @FindBy(xpath = "//*[@id=\":1t\"]/tbody") //zA.zE
    List<WebElement> receivedEmail;

    @FindBy(css = ".ii.gt div div:nth-of-type(1)")
    WebElement receivedEmailBody;




    public emailSendPageFactory(WebDriver driver) {
        this.driver = driver;

        //Initialize web elements
        PageFactory.initElements(driver, this);
    }

    //Setting all methods to type values and click
    public void setRecipientEmail(String recipientEmail) {
        toField.sendKeys(recipientEmail);
    }

    public void setSubjectBox(String subject){
        subjectField.sendKeys(subject);
    }

    public void setBody(String body){
        emailBody.sendKeys(body);
    }

    public emailSendPageFactory clickOnComposeBtn() {
        composeEmailBtn.click();
        return new emailSendPageFactory(driver);
    }

    public emailSendPageFactory clickOnSendBtn(){
        sendBtn.click();
        return new emailSendPageFactory(driver);
    }

    public emailSendPageFactory clickOnReceivedEmail(){
        for(int i=0;i<receivedEmail.size();i++){

            System.out.println(receivedEmail.get(i).getText());

        }
        receivedEmail.get(0).click();
        return new emailSendPageFactory(driver);
    }

    //Check that elements on the page are displayed.
    public boolean isSideLogoTextDisplayed () {
        try {
            return sideLogoText.isDisplayed();
        } catch(Exception e) {
            System.out.print("Side logo text cannot be located \n" + e.getMessage());
            return false;
        }
    }

    public boolean isSearchFieldDisplayed () {
        try {
            return searchField.isDisplayed();
        } catch(Exception e) {
            System.out.print("Search field cannot be located \n" + e.getMessage());
            return false;
        }
    }

    public boolean isComposeEmailBtnDisplayed () {
        try {
            return composeEmailBtn.isDisplayed();
        } catch(Exception e) {
            System.out.print("Compose email button cannot be located \n" + e.getMessage());
            return false;
        }
    }

    public boolean isNewMessageHeaderDisplayed (){
        try {
            return newMessageHeader.isDisplayed();
        } catch(Exception e) {
            System.out.print("New email text cannot be loacated \n" + e.getMessage());
            return false;
        }
    }

    public boolean isToFieldDisplayed () {
        try {
            return toField.isDisplayed();
        } catch(Exception e) {
            System.out.print("Email to field cannot be loacated \n" + e.getMessage());
            return false;
        }
    }
        public boolean isSuccessMessageDisplayed () {
        try {
            return successAlert.isDisplayed();
        } catch(Exception e) {
            System.out.print("Success alert cannot be located \n" + e.getMessage());
            return false;
        }
    }

    public boolean isReceivedEmailBodyDisplayed () {
        try {
            return receivedEmailBody.isDisplayed();
        } catch(Exception e) {
            System.out.print("Received email body cannot be located \n" + e.getMessage());
            return false;
        }
    }

    //Check that email to field is editable
    private boolean isEditable(WebElement element) {
        element.sendKeys("editable");
        if (element.getAttribute("value").contentEquals("editable")){
            element.clear();
            return true;
        }
        return false;
    }

    public boolean isToFieldEditable() {
        return isEditable(toField);
    }

    //Check that the email body of the email received is "Application test"
    private boolean isVisible(WebElement recElement){
        if(recElement.getText().contains("Automation test")) return true;
        return false;
    }

    public boolean isReceivedEmailBodyVisible(){
        return isVisible(receivedEmailBody);
    }


}
