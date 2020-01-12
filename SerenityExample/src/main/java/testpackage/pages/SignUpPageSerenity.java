package testpackage.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@DefaultUrl("https://www.spotify.com/us/signup/")
@At("https://www.spotify.com/us/signup/")
public class SignUpPageSerenity extends PageObject {

    private By emailField = By.cssSelector("input#register-email");
    private By confirmEmail = By.cssSelector("input#register-confirm-email");
    private By password = By.cssSelector("input#register-password");
    private By registeredName = By.cssSelector("input#register-displayname");
    private By birthMonthField = By.cssSelector("select#register-dob-month");
    private String monthDropDownOptions = "//select[@id='register-dob-month']/option[text()='%s']";
    private By birthDay = By.cssSelector("input#register-dob-day");
    private By birthYear = By.cssSelector("input#register-dob-year");
    private String sexRadioButton = "//li[@id='li-gender']/label[normalize-space()='%s']/input";
    //private By sexRadioButton = By.cssSelector("li#li-gender");
    private By sharingCheckBox = By.cssSelector("#register-thirdparty");
    private By signUpButton = By.cssSelector("a#register-button-email-submit");
    private By errorLabel = By.xpath("//label[@class='has-error' and string-length(text())>0]");
    private String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]";



    public SignUpPageSerenity typeEmail(String email) {
        find(emailField).sendKeys(email);
        return this;
    }

    public SignUpPageSerenity typeConfirmEmail(String email) {
        find(confirmEmail).sendKeys(email);
        return this;
    }

    public SignUpPageSerenity typePassword(String pass) {
        find(password).sendKeys(pass);
        return this;
    }

    public SignUpPageSerenity typeRegName(String name) {
        find(registeredName).sendKeys(name);
        return this;
    }


    public SignUpPageSerenity selectBirthMonth(String month) {
        find(birthMonthField).click();
        find(By.xpath(String.format(monthDropDownOptions, month))).waitUntilVisible().click();
        return this;
    }

    public SignUpPageSerenity typeBirthDay(String day) {
        find(birthDay).sendKeys(day);
        return this;
    }

    public SignUpPageSerenity typeBirthYear(String year) {
        find(birthYear).sendKeys(year);
        return this;
    }

    public SignUpPageSerenity typeSex(String value) {
        find(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }

    public SignUpPageSerenity setSharingCheckBox(boolean value) {
        WebElement checkbox = find(sharingCheckBox);
        if (!checkbox.isSelected() == value) {
            checkbox.click();
        }
        return this;
    }

    public void clickSignUpButton() {
        find(signUpButton).click();
    }

    public List<WebElementFacade> getListOfErrors() {
        return findAll(errorLabel);
    }

    public String getErrorByNumber(int number) {
        return getListOfErrors().get(number - 1).getText();
    }

    public boolean isErrorVisible(String message) {
        return findAll(By.xpath(String.format(errorByText, message))).size() > 0
                && findAll(By.xpath(String.format(errorByText, message))).get(0).isDisplayed();
    }


}
