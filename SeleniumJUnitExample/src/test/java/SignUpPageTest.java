
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING) //for Junit4

public class SignUpPageTest {
    WebDriver driver;
    SpotifySignUpPage page;

    @BeforeEach
    public void setUp() {
       // FirefoxOptions options = new FirefoxOptions();
        //WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), options);
        System.setProperty("webdriver.gecko.driver", "/Users/ekaterinabolotova/Downloads/selenium-webdriver/geckodriver");
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.spotify.com/us/signup/");
    }

    @Test
    @Order(4)
    public void testInvalidYear() {
        page = new SpotifySignUpPage(driver);
        page.selectBirthMonth("May")
                .typeBirthDay("20")
                .typeBirthYear("23")
                .setSharingCheckBox(true);
        Assertions.assertTrue(page.isErrorVisible("Please enter a valid year."));
        Assertions.assertFalse(page.isErrorVisible("Please enter a valid day of the month."));
        Assertions.assertFalse(page.isErrorVisible("Please enter your birth month."));
        Assertions.assertFalse(page.isErrorVisible("When were you born?"));

    }

    @Test
    @Order(3)
    public void testNotMatchEmails() {
        page = new SpotifySignUpPage(driver);
        page.typeEmail("bolotova.katya@gmail.com")
                .typeConfirmEmail("bolotova-katya@gmail.com")
                .typeRegName("Testname")
                .clickSignUpButton();

        Assertions.assertTrue(page.isErrorVisible("Email address doesn't match."));
    }

    @Test
    @Order(2)
    public void testEmptyPassword() {
        page = new SpotifySignUpPage(driver);
        page.typeEmail("bolotova.katya@gmail.com")
                .typeConfirmEmail("bolotova-katya@gmail.com")
                .typeRegName("Testname")
                .clickSignUpButton();
        Assertions.assertTrue(page.isErrorVisible("Enter a password to continue."));
    }

    @Test
    @Order(1)
    public void typeInvalidValues() {
        page = new SpotifySignUpPage(driver);
        page.typeEmail("bolotova-katya@gmail")
                .typePassword("erf")
                .typeRegName("Testname")
                .typeSex("Female")
                .setSharingCheckBox(false)
                .clickSignUpButton();
        Assertions.assertEquals(7, page.getListOfErrors().size());
        Assertions.assertEquals("The email address you supplied is invalid.", page.getErrorByNumber(1));
        Assertions.assertEquals("Your password is too short.", page.getErrorByNumber(3));
        Assertions.assertEquals("Please confirm you're not a robot.", page.getErrorByNumber(7));
    }


    @AfterEach
    public void exit() {
        driver.close();
    }
}