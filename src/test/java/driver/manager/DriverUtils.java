package driver.manager;

import io.qameta.allure.Step;

import java.util.concurrent.TimeUnit;

public class DriverUtils {

    @Step("Maximizing browser window")
    public static void setInitialConfiguration() {
        DriverManager.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverManager.getWebDriver().manage().window().maximize();
    }

    @Step("Navigating to URL: {pageUrl}")
    public static void navigateToPage(String pageUrl) {
        DriverManager.getWebDriver().navigate().to(pageUrl);
    }

}
