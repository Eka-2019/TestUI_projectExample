package testpackage.steps.jbehaveSteps;

import io.appium.java_client.pagefactory.AndroidBy;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import testpackage.steps.serenitySteps.SignUpStepsSerenity;

import java.util.List;
import java.util.Map;

public class SignUpStepsJBehave {

    @Steps
    SignUpStepsSerenity steps;

    @Given("I open singup page")
    public void open_page() {
        steps.open_page();
    }


    @When("I set date: $table")
    public void set_date(ExamplesTable table) {
        //Map<String, String> date = table.getRow(0);
        List<Map<String, String>> rows = table.getRows();
        for (Map<String, String> row : rows) {
            if (!table.getHeaders().contains("month")) {
                System.out.println("The table does not contain Header = month");
            } else {
                steps.type_month(row.get("month"));
                steps.type_day(row.get("day"));
                steps.type_year(row.get("year"));
            }
        }
    }

    @When("I set date0: $table")
    public void set_date0(ExamplesTable table) {
        Map<String, String> date = table.getRow(0);
        if (!table.getHeaders().contains("month")) {
            System.out.println("The table does not contain Header = month");
        } else {
            steps.type_month(date.get("month"));
            steps.type_day(date.get("day"));
            steps.type_year(date.get("year"));
        }
    }


    @When("I set date1: $table")
    public void set_date1(ExamplesTable table) {
        int rowsCount = table.getRowCount();

        for (int i = 0; i < rowsCount; i++) {
            Map<String, String> date = table.getRow(i);
            if (!table.getHeaders().contains("month")) {
                System.out.println("The table does not contain Header = month");
            } else {
                steps.type_month(date.get("month"));
                steps.type_day(date.get("day"));
                steps.type_year(date.get("year"));
            }
        }
    }

    @When("I set date2: $table")
    public void set_date2(ExamplesTable table) {
        List<Map<String, String>> rows = table.getRows();
        for (Map<String, String> row : rows) {
            if (!table.getHeaders().contains("month")) {
                System.out.println("The table does not contain Header = month");
            } else {
                steps.type_month(row.get("month"));
                steps.type_day(row.get("day"));
                steps.type_year(row.get("year"));
            }
        }
        //Map<String, String> date = table.getRow(0);

    }

    @When("I set month \"$month\"")
    public void set_month(String month) {
        steps.type_month(month);
    }

    @When("I set day \"$day\"")
    public void set_day(String day) {
        steps.type_day(day);
    }

    @When("I set year \"$year\"")
    public void set_year(String year) {
        steps.type_year(year);
    }

    @When("I set check sharing_checkbox")
    public void check_share() {
        steps.tick_sharing_checkbox(true);
    }

    @When("I set uncheck sharing_checkbox")
    public void uncheck_share() {
        steps.tick_sharing_checkbox(false);
    }

    @When("I set email \"$mail\"")
    public void set_email(String mail) {
        steps.type_email(mail);
    }

    @When("I set confirmation_email \"$mail\"")
    public void confirm_email(String mail) {
        steps.type_confirmation_email(mail);
    }

    @When("I set name \"$name\"")
    public void set_registration_name(String name) {
        steps.type_name(name);
    }

    @When("I set password \"$password\"")
    public void set_password(String password) {
        steps.type_name(password);
    }

    @When("I set sex \"$sex\"")
    public void set_sex(String sex) {
        steps.type_name(sex);
    }

    @When("Click signUp button")
    public void click_signUp_button() {
        steps.click_signUp_button();
    }

    @Then("I should see error: \"$message\"")
    public void see_error(String message) {
        steps.should_see_error(message);
    }

    @Then("I should not see error: \"$message\"")
    public void do_not_see_error(String message) {
        steps.should_not_see_error(message);
    }

    @Then("I see \"$quantity\" errors messages")
    public void get_quantity_errors(int amount) {
        steps.count_errors_on_page(amount);
    }

    @Then("Number \"$num\" error has text: \"$text\"")
    public void get_text_and_number_error(int amount, String text) {
        steps.sequence_of_error_on_page(amount, text);
    }

    @Then("Print message: done")
    public void print_message_succes(String text) {
        steps.print_status_of_test(text);
    }

    @Then("Print message: failed")
    public void print_message_failed(String text) {
        steps.print_status_of_test(text);
    }
}
