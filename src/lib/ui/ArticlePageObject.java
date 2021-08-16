package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    public final static String
            TITLE_TPL = "//*[@class='android.view.View'][@text='{SUBSTRING}']",
            FOOTER_ELEMENT = "//*[@text='View article in browser']",
            BOOKMARK_BUTTON = "org.wikipedia:id/article_menu_bookmark",
            ADD_TO_ANOTHER_LIST_BUTTON = "//*[@text='Add to another reading list']",
            CREATE_NEW_LIST_BUTTON = "//*[@text='Create new']",
            NAME_OF_LIST_INPUT = "org.wikipedia:id/text_input",
            OK_BUTTON = "android:id/button1",
            CLOSE_ARTICLE_BUTTON = "Navigate up";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getArticleTitleWithSubstring(String substring)
    {
        return TITLE_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public WebElement waitForTitleElementWithSubstring(String substring)
    {
        String article_title = getArticleTitleWithSubstring(substring);
        return this.waitForElementPresent(By.xpath(article_title), "Cannot find the title of article", 5);
    }

    public String getArticleTitle(String substring)
    {
        WebElement title_element = waitForTitleElementWithSubstring(substring);
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find footer element",
                20
        );
    }

    public void addToMyListAndCreateNewList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.id(BOOKMARK_BUTTON),
                "Cannot find bookmark button",
                5);
        this.waitForElementAndClick(
                By.id(BOOKMARK_BUTTON),
                "Cannot find bookmark button",
                5);
        this.waitForElementAndClick(
                By.xpath(ADD_TO_ANOTHER_LIST_BUTTON),
                "Cannot find 'Add to another reading list' button",
                5);
        this.waitForElementAndClick(
                By.xpath(CREATE_NEW_LIST_BUTTON),
                "Cannot find 'Create new' button",
                5);
        this.waitForElementAndSendKeys(
                By.id(NAME_OF_LIST_INPUT),
                name_of_folder,
                "Cannot find 'Name of this list' input",
                2);
        this.waitForElementAndClick(
                By.id(OK_BUTTON),
                "Cannot find 'OK' button",
                2);
    }

    public void addArticlesToSaved()
    {
        this.waitForElementAndClick(
                By.id(BOOKMARK_BUTTON),
                "Cannot find bookmark button",
                5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.id(CLOSE_ARTICLE_BUTTON),
                "Cannot find <- button",
                1);
    }

    public void assertArticleHasATextInTitle(String article_name)
    {
        String article_xpath = getArticleTitleWithSubstring(article_name);
        this.assertElementHasText(
                By.xpath(article_xpath),
                article_name,
                "Cannot find expected text in first article found by the request " + article_name,
                5);
    }
}
