package tests;

import driver.manager.DriverUtils;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import page.objects.LoginPage;

import static navigation.ApplicationURLs.LOGIN_URL;

public class NegativeLoginTest extends TestBase {

    @Issue("DEFECT-002")
    @TmsLink("ID-001")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Description("The goal of this test is to log in using not proper username and password and check if warning " +
            "message Invalid username or password is displayed")
    public void asUserTryToLogInWithIncorrectLoginAndPassword() {

        DriverUtils.navigateToPage(LOGIN_URL);

        LoginPage loginPage = new LoginPage();
        loginPage
                .typeIntoUserNameField("NotExistingLogin")
                .typeIntoPasswordField("NotExistingPassword")
                .clickOnLoginButton();
        loginPage
                .assertThatWarningIsDisplayed("Invalid username or password. Signon failed.!");
    }

}