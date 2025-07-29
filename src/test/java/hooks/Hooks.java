package hooks;

import java.net.URI;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    public static AppiumDriver driver;
    public static Scenario scenario;

    @Before
    public static void setUp(Scenario sc) throws Exception {
        scenario = sc;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("appium:deviceName", "Medium Phone");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:chromedriverAutodownload", true);

        driver = new AppiumDriver(new URI("http://127.0.0.1:4723/").toURL(), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
