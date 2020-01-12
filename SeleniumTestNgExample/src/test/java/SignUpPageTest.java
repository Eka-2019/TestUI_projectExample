

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class SignUpPageTest {
    WebDriver driver;
    SpotifySignUpPage page;

    @BeforeMethod
    public void setUp() {
       // FirefoxOptions options = new FirefoxOptions();
        //WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), options);
        System.setProperty("webdriver.gecko.driver", "/Users/ekaterinabolotova/Downloads/selenium-webdriver/geckodriver");
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.spotify.com/us/signup/");

    }

    @Test(priority = 4)
    public void testInvalidYear() {
        page = new SpotifySignUpPage(driver);
        page.selectBirthMonth("May")
                .typeBirthDay("20")
                .typeBirthYear("23")
                .setSharingCheckBox(true);
        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        Assert.assertFalse(page.isErrorVisible("Please enter a valid day of the month."));
        Assert.assertFalse(page.isErrorVisible("Please enter your birth month."));
        Assert.assertFalse(page.isErrorVisible("When were you born?"));

    }

    @Test(priority = 3)
    public void testNotMatchEmails() {
        page = new SpotifySignUpPage(driver);
        page.typeEmail("bolotova.katya@gmail.com")
                .typeConfirmEmail("bolotova-katya@gmail.com")
                .typeRegName("Testname")
                .clickSignUpButton();

        Assert.assertTrue(page.isErrorVisible("Email address doesn't match."));
    }

    @Test(priority = 2)
    public void testEmptyPassword() {
        page = new SpotifySignUpPage(driver);
        page.typeEmail("bolotova.katya@gmail.com")
                .typeConfirmEmail("bolotova-katya@gmail.com")
                .typeRegName("Testname")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Enter a password to continue."));
    }

    @Test(priority = 1)
    public void typeInvalidValues() {
        page = new SpotifySignUpPage(driver);
        page.typeEmail("bolotova-katya@gmail")
                .typePassword("erf")
                .typeRegName("Testname")
                .typeSex("Female")
                .setSharingCheckBox(false)
                .clickSignUpButton();
        Assert.assertEquals(7, page.getListOfErrors().size());
        Assert.assertEquals("The email address you supplied is invalid.", page.getErrorByNumber(1));
        Assert.assertEquals("Your password is too short.", page.getErrorByNumber(3));
        Assert.assertEquals("Please confirm you're not a robot.", page.getErrorByNumber(7));
    }


    @AfterMethod
    public void exit() {
        driver.close();
    }
}