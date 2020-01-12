package testpackage;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import testpackage.steps.serenitySteps.SignUpStepsSerenity;


@RunWith(SerenityRunner.class)
public class SignUpTestSerenityOK {

    @Steps
    SignUpStepsSerenity steps;

    @Managed
    WebDriver driver;

    @Test
    public void testInvalidYear() {
        steps.open_page();
        steps.type_month("December");
        steps.type_day("20");
        steps.type_year("23");
        steps.tick_sharing_checkbox(true);
        steps.should_see_error("Please enter a valid year.");
        steps.should_not_see_error("Please enter a valid day of the month.");
        steps.should_not_see_error("Please enter your birth month.");
        steps.should_not_see_error("When were you born?");
    }

    @Test
    public void testNotMatchEmails() {
        steps.open_page();
        steps.type_email("bolotova-katya@gmail.com");
        steps.type_confirmation_email("bolotova.katya@gmail.com");
        steps.type_name("TestName");
        steps.click_signUp_button();
        steps.should_see_error("Email address doesn't match.");
    }

    @Test
    public void testEmptyPassword() {
        steps.open_page();
        steps.type_email("bolotova-katya@gmail.com");
        steps.type_confirmation_email("bolotova.katya@gmail.com");
        steps.type_name("TestName");
        steps.click_signUp_button();
        steps.should_see_error("Enter a password to continue.");
    }

    @Test
    public void typeInvalidValues() {
        steps.open_page();
        steps.type_email("bolotova-katya@gmail");
        steps.type_password("efr");
        steps.type_name("TestName");
        steps.type_sex("Female");
        steps.tick_sharing_checkbox(false);
        steps.click_signUp_button();
        steps.count_errors_on_page(7);
        steps.sequence_of_error_on_page(1, "The email address you supplied is invalid.");
        steps.sequence_of_error_on_page(3, "Your password is too short.");
        steps.sequence_of_error_on_page(7, "Please confirm you're not a robot.");

    }

}
