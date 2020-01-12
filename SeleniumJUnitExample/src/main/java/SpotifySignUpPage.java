import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class SpotifySignUpPage {

    private WebDriver driver;

    public SpotifySignUpPage(WebDriver driver) {
        this.driver = driver;
    }


    //private By emailField = By.cssSelector("input#register-email");
    private By emailField = By.xpath("//*[@id=\"register-email\"]");
    private By confirmEmail = By.cssSelector("input#register-confirm-email");
    private By password = By.cssSelector("input#register-password");
    private By registeredName = By.cssSelector("input#register-displayname");
    private By birthMonthField = By.cssSelector("select#register-dob-month");
    private String monthDropDownOptions = "//select[@id='register-dob-month']/option[text()='%s']";
    private By birthDay = By.cssSelector("input#register-dob-day");
    private By birthYear = By.cssSelector("input#register-dob-year");
    private String sexRadioButton = "//li[@id='li-gender']/label[normalize-space()='%s']/input";
    private By sharingCheckBox = By.cssSelector("input#register-thirdparty");
    private By signUpButton = By.cssSelector("a#register-button-email-submit");
    private By errorLabel = By.xpath("//label[@class='has-error' and string-length(text())>0]");
    private String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]";




    public SpotifySignUpPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }


    public SpotifySignUpPage typeConfirmEmail(String email) {
        driver.findElement(confirmEmail).sendKeys(email);
        return this;
    }


    public SpotifySignUpPage typePassword(String pass) {
        driver.findElement(password).sendKeys(pass);
        return this;
    }


    public SpotifySignUpPage typeRegName(String name) {
        driver.findElement(registeredName).sendKeys(name);
        return this;
    }


    public SpotifySignUpPage selectBirthMonth(String month) {
        driver.findElement(birthMonthField).click();
        new WebDriverWait(driver, 5).until(visibilityOfElementLocated(By.xpath(String.format(monthDropDownOptions, month)))).click();
        return this;
    }


    public SpotifySignUpPage typeBirthDay(String day) {
        driver.findElement(birthDay).sendKeys(day);
        return this;
    }


    public SpotifySignUpPage typeBirthYear(String year) {
        driver.findElement(birthYear).sendKeys(year);
        return this;
    }


    public SpotifySignUpPage typeSex(String value) {
        driver.findElement(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }


    public SpotifySignUpPage setSharingCheckBox(boolean value) {
        WebElement checkbox = driver.findElement(sharingCheckBox);
        if (!checkbox.isSelected() == value) {
            checkbox.click();
        }
        return this;
    }


    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }


    public List<WebElement> getListOfErrors() {
        return driver.findElements(errorLabel);
    }


    public String getErrorByNumber(int number) {
        return getListOfErrors().get(number - 1).getText();
    }


    public boolean isErrorVisible(String message) {
        return driver.findElements(By.xpath(String.format(errorByText, message))).size() > 0
                && driver.findElements(By.xpath(String.format(errorByText, message))).get(0).isDisplayed();
    }


}
