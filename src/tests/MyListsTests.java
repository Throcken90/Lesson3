package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);

    }
    @Test
    public void saveArticlesToTheFolder() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("QA");
        SearchPageObject.clickByArticleWithSubstring("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text='Wikimedia disambiguation page']");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String name_of_folder = "Software Testing";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();



        SearchPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        SearchPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        SearchPageObject.waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find got it tip overlay",
                5
        );
        SearchPageObject.waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article folder",
                5
        );
        String name_of_folder = "Software Testing";
        SearchPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );
        SearchPageObject.waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot press Ok button ",
                5
        );
        SearchPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                5
        );



        SearchPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        SearchPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Quality",
                "Cannot find Search input",
                5);
        SearchPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text='Way of preventing mistakes or defects in manufactured products and avoiding problems when delivering solutions or services to customers; (ISO 9000) part of quality management focused on providing confidence that quality requirements will be fulfilled']"),
                "Cannot find Search Wikipedia input",
                5);
        SearchPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        SearchPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        SearchPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        SearchPageObject.waitForElementAndClick(By.id("org.wikipedia:id/item_title"),
                "Cannot find got it tip overlay",
                5
        );

        SearchPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                5
        );
        SearchPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My lists,cannot click it",
                5
        );
        SearchPageObject.waitForElementAndClick(By.id("org.wikipedia:id/item_title"),
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
                "QA",
                article_title
        );
    }
}
