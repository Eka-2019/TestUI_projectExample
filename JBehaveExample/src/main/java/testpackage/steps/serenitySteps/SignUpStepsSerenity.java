package testpackage.steps.serenitySteps;

import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import testpackage.pages.SignUpPageJBehave;

public class SignUpStepsSerenity {
    SignUpPageJBehave page;


    @Step
    public void open_page() {
        page.open();
    }

    @Step("User added email (0)")
    public void type_email(String email) {
        page.typeEmail(email);
    }

    @Step
    public void type_confirmation_email(String email) {
        page.typeConfirmEmail(email);
    }

    @Step
    public void type_password(String pass) {
        page.typePassword(pass);
    }

    @Step
    public void type_name(String name) {
        page.typeRegName(name);
    }


    @Step
    public void type_month(String month) {
        page.selectBirthMonth(month);
    }

    @Step
    public void type_day(String day) {
        page.typeBirthDay(day);
    }

    @Step
    public void type_year(String year) {
        page.typeBirthYear(year);
    }

    @Step
    public void type_sex(String sex) {
        page.typeSex(sex);
    }

    @Step
    public void tick_sharing_checkbox(boolean value) {
        page.setSharingCheckBox(value);
    }

    @Step
    public void click_signUp_button() {
        page.clickSignUpButton();
    }

    @Step
    public void should_see_error(String message) {
        Assertions.assertThat(page.isErrorVisible(message))
                .as("User should see message, but he does not")
                .isTrue();
    }

    @Step
    public void should_not_see_error(String message) {
        Assertions.assertThat(page.isErrorVisible(message))
                .as("User should not see message, but he does")
                .isFalse();
    }

    @Step
    public void count_errors_on_page(int count){
        Assertions.assertThat(page.getListOfErrors()).hasSize(count);
    }

    @Step
    public void sequence_of_error_on_page(int number, String message){
        Assertions.assertThat(page.getErrorByNumber(number)).isEqualTo(message);
    }

    @Step
    public void print_status_of_test(String message){
        System.out.println(message);
    }


}
