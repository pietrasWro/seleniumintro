package configuration;

import driver.BrowserType;

import java.net.MalformedURLException;
import java.net.URL;

public class TestRunProperties {

    public static BrowserType getBrowserToRun() {
        return BrowserType.valueOf(ConfigurationProperties.getProperties().getProperty("browser"));
    }

    public static URL getGridHostUrl() {
        try {
            return new URL(ConfigurationProperties.getProperties().getProperty("grid.hub.url"));
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create Remote Driver due to: " + e.getMessage() );
        }
    }

    public static boolean getIsRemoteRun() {
        return Boolean.parseBoolean(ConfigurationProperties.getProperties().getProperty("is.remote.run"));
    }

}
