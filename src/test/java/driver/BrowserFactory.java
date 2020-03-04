package driver;

import configuration.LocalWebDriverProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static configuration.TestRunProperties.getGridHostUrl;

public class BrowserFactory {

    private static final String MESSAGE_UNKNOWN_BROWSER="Unknown browser type! Please check your configuration!";

    private BrowserType browserType;
    private boolean isRemoteRun;

    public BrowserFactory(BrowserType browserType, boolean isRemoteRun) {
        this.browserType = browserType;
        this.isRemoteRun = isRemoteRun;
    }

    public  WebDriver getBrowser() {
        if (isRemoteRun) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            switch (browserType) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    desiredCapabilities.merge(chromeOptions);
                    break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    desiredCapabilities.merge(firefoxOptions);
                    break;
                case IE:
                    InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                    desiredCapabilities.merge(internetExplorerOptions);
                    break;
                default:
                    throw new IllegalStateException(MESSAGE_UNKNOWN_BROWSER);
            }
            return new RemoteWebDriver(getGridHostUrl(), desiredCapabilities);
        } else {
            switch (browserType) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", LocalWebDriverProperties.getChromeWebDriverLocation());
                    return new ChromeDriver();
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", LocalWebDriverProperties.getFireFoxWebDriverLocation());
                    return new FirefoxDriver();
                case IE:
                    System.setProperty("webdriver.ie.driver", LocalWebDriverProperties.getInternetExplorerWebDriverLocation());
                    return new InternetExplorerDriver();
                default:
                    throw new IllegalStateException("Unknown browser type! Please check your configuration");
            }
        }
    }
}
