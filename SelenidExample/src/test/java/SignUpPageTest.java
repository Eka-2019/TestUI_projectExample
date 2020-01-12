import com.codeborne.selenide.Condition;
import org.junit.*;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;


public class SignUpPageTest {
    SignUpPage page;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver", "/Users/ekaterinabolotova/Downloads/selenium-webdriver/geckodriver");
        baseUrl = "https://www.spotify.com/us/signup";
        //browser = "marionette";
        browser = "firefox";

    }

    @Test
    public void testInvalidYear() {
        page = new SignUpPage();
        page.open()
                .selectBirthMonth("May")
                .typeBirthDay("20")
                .typeBirthYear("23")
                .setSharingCheckBox(true);
        page.getErorr("Please enter a valid year.").shouldBe(Condition.visible);
        page.getErorr("Please enter a valid day of the month.").shouldNotBe(Condition.visible);
        page.getErorr("Please enter your birth month.").shouldNotBe(Condition.visible);
        page.getErorr("When were you born?").shouldNotBe(Condition.visible);
    }

    @Test
    public void testNotMatchEmails() {
        page = new SignUpPage();
        page.open()
                .typeEmail("bolotova-katya@gmail.com")
                .typeConfirmEmail("bolotova.katya@gmail.com")
                .typeRegName("Testname")
                .clickSignUpButton();

        page.getErorr("Email address doesn't match.").shouldBe(Condition.visible);
    }

    @Test
    public void testEmptyPassword() {
        page = new SignUpPage();
        page.open()
                .typeEmail("bolotova-katya@gmail.com")
                .typeConfirmEmail("bolotova.katya@gmail.com")
                .typeRegName("Testname")
                .clickSignUpButton();
        page.getErorr("Enter a password to continue.").shouldBe(Condition.visible);
    }


    @Test
    public void typeInvalidValues() {
        page = new SignUpPage();
        page.open()
                .typeEmail("bolotova-katya@gmail")
                .typePassword("erf")
                .typeRegName("Testname")
                .typeSex("Female")
                .setSharingCheckBox(false)
                .clickSignUpButton();
        page.getListOfErrors().shouldHave(size(7));
        page.getErrorByNumber(1).shouldHave(Condition.text("The email address you supplied is invalid."));
        page.getErrorByNumber(3).shouldHave(Condition.text("Your password is too short."));
        page.getErrorByNumber(7).shouldHave(Condition.text("Please confirm you're not a robot."));
    }


}