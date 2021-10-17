import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
        childElements.forEach((el) -> {
            System.out.println("El - " + el.getText() + "; " + el.getText().contains("Java"));
            boolean search_element = el.getText().contains("Java");
            Assert.assertEquals(true, el.getText().contains("Java"));
            System.out.println("The test is OK.");
        });


    }
        @Test
        public void saveArticlesToTheFolder() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "QA",
                "Cannot find Search input",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text='Wikimedia disambiguation page']"),
                "Cannot find Search Wikipedia input",
                5);
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find got it tip overlay",
                5
        );
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article folder",
                5
        );
       String name_of_folder = "Software Testing";
       waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
               name_of_folder,
                "Cannot put text into article folder input",
                5
       );
        waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot press Ok button ",
                5
        );
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                5
        );



            waitForElementAndClick(
                    By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                    "Cannot find Search Wikipedia input",
                    5
            );

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text, 'Search…')]"),
                    "Quality",
                    "Cannot find Search input",
                    5);
            waitForElementAndClick(
                    By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text='Way of preventing mistakes or defects in manufactured products and avoiding problems when delivering solutions or services to customers; (ISO 9000) part of quality management focused on providing confidence that quality requirements will be fulfilled']"),
                    "Cannot find Search Wikipedia input",
                    5);
            waitForElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "Cannot find article title",
                    15
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Cannot find button to open article options",
                    5
            );
            waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                    "Cannot find option to add article to reading list",
                    5
            );
            waitForElementAndClick(By.id("org.wikipedia:id/item_title"),
                    "Cannot find got it tip overlay",
                    5
            );

            waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot close article, cannot find X button",
                    5
            );
            waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                    "Cannot find My lists,cannot click it",
                    5
            );
            waitForElementAndClick(By.id("org.wikipedia:id/item_title"),
                    "Cannot find got it tip overlay",
                    5
            );
            swipeElementToLeft(
                    By.xpath("//*[@text='Quality assurance']"),
                    "Cannot find saved article"

            );
            waitForElementNotPresent(
                    By.xpath("//*[@text='Quality assurance']"),
                    "Cannot delete saved article",
                    5
            );
            waitForElementAndClick(By.id("org.wikipedia:id/item_title"),
                    "Cannot find got it tip overlay",
                    5
            );
//            waitForElementPresent(
//                    By.id("org.wikipedia:id/page_list_item_title"),
//                    "Cannot find article title",
//                    15
//            );
            WebElement title_element = waitForElementPresent(
                    By.id("org.wikipedia:id/page_list_item_title"),
                    "Cannot find article title",
                    15
            );
            String article_title = title_element.getAttribute("text");

            Assert.assertEquals(
                    "We see unexpected title",
                    "Java (programming language)",
                    article_title
            );
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
    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
    }

//    public static Pattern compile(String literal)
//    {
//        Pattern pattern = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
//    return pattern;
//    }



