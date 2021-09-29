import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class TestAtHome {
    private AppiumDriver driver;
    @Before

    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "androidTestDevice");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Voronkova\\Desktop\\JavaAppiumAutomation\\Apks\\org.wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void assertElementHasText() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        WebElement search_element = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find text 'Search…'",
                15
        );
        String search_text = search_element.getAttribute("text");
        Assert.assertEquals(
                "We do not see expected text",
                "Search…",
                search_text

        );

    }







    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
}
    private  WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
// private  WebElement waitForElementPresent(By by, String error_message) {

       // return waitForElementPresent(by, error_message, 5);


    }