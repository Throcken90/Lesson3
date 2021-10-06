import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.GetElementDisplayed;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

public class TestAtHome {
    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "androidTestDevice");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("app", "D:\\Java\\Lesson3\\Apks\\org.wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchStringHasRightText() {
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
        String search_id = search_element.getText();
        Assert.assertEquals(
                "We do not see expected text",
                "Search…",
                search_id

        );
        assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                "Search…");
        System.out.println("Right text was found");


    }

    @Test
    public void testSearchAndCancel() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' field",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "QA",
                "Cannot find Search input",
                5);
        WebElement element;
        element = waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find page list item",
                15
        );
        List<WebElement> childElements = element.findElements(By.id("org.wikipedia:id/page_list_item_container"));
        String probe = element.getText();

        System.out.println("Element count: " + childElements.size());

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot search text",
                5
        );
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                1
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Text is still present on the page",
                5
        );
        System.out.println("The test is passed");

    }

    @Test
    public void testCheckTitleForAWord() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find Search input",
                5);
//        WebElement title_element = waitForElementPresent(
//                By.id("org.wikipedia:id/page_list_item_title"),
//                "Cannot find article title",
//                15
//          );
        WebElement element;
        element = waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find page list item",
                15
        );
        List<WebElement> childElements = element.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        //String probe = element.getText();
        //System.out.println("size - " + childElements.size());
        childElements.forEach((el)->{
            System.out.println("El - " + el.getText()+"; "+el.getText().contains("Java"));
        boolean search_element = el.getText().contains("Java");
        Assert.assertEquals(true, el.getText().contains("Java"));
            System.out.println("The test is OK.");
        });
        
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private boolean assertElementHasText(By by, String error_message, String expected) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String search_text = element.getText();// .getAttribute("");//.getAttribute("text");

        return expected == search_text;
    }

//    public static Pattern compile(String literal)
//    {
//        Pattern pattern = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
//    return pattern;
//    }
}


