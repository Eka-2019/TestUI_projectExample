import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SignUpPage {

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


    public SignUpPage open() {
        Selenide.open("/");       //rest of open link, it is starts from https://www.spotify.com/
        return this;
    }

    public SignUpPage typeEmail(String email) {
        $(emailField).setValue(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email) {
        $(confirmEmail).setValue(email);
        return this;
    }

    public SignUpPage typePassword(String pass) {
        $(password).setValue(pass);
        return this;
    }

    public SignUpPage typeRegName(String name) {
        $(registeredName).setValue(name);
        return this;
    }

    public SignUpPage selectBirthMonth(String month) {
        $(birthMonthField).selectOption(month);
        return this;
    }

    public SignUpPage typeBirthDay(String day) {
        $(birthDay).setValue(day);
        return this;
    }

    public SignUpPage typeBirthYear(String year) {
        $(birthYear).setValue(year);
        return this;
    }

    public SignUpPage typeSex(String value) {
        $(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }

    public SignUpPage setSharingCheckBox(boolean value) {
        $(sharingCheckBox).setSelected(value);
        return this;
    }

    public void clickSignUpButton() {
        //$(signUpButton).waitWhile(Condition.disabled, 5000);              //explicite wait untill the button became enabled
        $(signUpButton).waitUntil(Condition.enabled, 5000);
        $(signUpButton).click();
    }

    public ElementsCollection getListOfErrors() {
        return $$(errorLabel);
    }

    public SelenideElement getErrorByNumber(int number) {
        return getListOfErrors().get(number - 1);

    }

    public SelenideElement getErorr(String message) {
        return $(By.xpath(String.format(errorByText, message)));
    }

}