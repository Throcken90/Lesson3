package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "//*[@text='View page in browser']",
    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@resource-id ='org.wikipedia:id/title' and @text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";



    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
public WebElement waitForTitleElement()
{
    return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
}
public String getArticleTitle()
{
    WebElement title_element = waitForTitleElement();
    return title_element.getAttribute("text");
}
public void swipeToFooter()
{
    this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 20);

}
public void addArticleToMyList(String name_of_folder)
{
    this.waitForElementAndClick(
            By.xpath(OPTIONS_BUTTON),
            "Cannot find button to open article options",
            15
    );
    this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
            "Cannot find option to add article to reading list",
            5
    );
    this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY),
            "Cannot find got it tip overlay",
            5
    );
    this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT),
            "Cannot find input to set name of article folder",
            5
    );
    this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT),
            name_of_folder,
            "Cannot put text into article folder input",
            5
    );
    this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON),
            "Cannot press Ok button ",
            5
    );
}
public void closeArticle()
{
    this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),
            "Cannot find navigation button to My Lists",
            5);
}

}
