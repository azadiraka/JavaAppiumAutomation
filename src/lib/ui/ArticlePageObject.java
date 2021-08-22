package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    public final static String
            TITLE_TPL = "xpath://*[@class='android.view.View'][@text='{SUBSTRING}']",
            FOOTER_ELEMENT = "xpath://*[@text='View article in browser']",
            BOOKMARK_BUTTON = "id:org.wikipedia:id/article_menu_bookmark",
            ADD_TO_ANOTHER_LIST_BUTTON = "xpath://*[@text='Add to another reading list']",
            CREATE_NEW_LIST_BUTTON = "xpath://*[@text='Create new']",
            NAME_OF_LIST_INPUT = "id:org.wikipedia:id/text_input",
            OK_BUTTON = "id:android:id/button1",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";

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
        return this.waitForElementPresent(article_title, "Cannot find the title of article", 5);
    }

    public String getArticleTitle(String substring)
    {
        WebElement title_element = waitForTitleElementWithSubstring(substring);
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find footer element",
                20
        );
    }

    public void addToMyListAndCreateNewList(String name_of_folder)
    {
        this.waitForElementAndClick(
                BOOKMARK_BUTTON,
                "Cannot find bookmark button",
                5);
        this.waitForElementAndClick(
                BOOKMARK_BUTTON,
                "Cannot find bookmark button",
                5);
        this.waitForElementAndClick(
                ADD_TO_ANOTHER_LIST_BUTTON,
                "Cannot find 'Add to another reading list' button",
                5);
        this.waitForElementAndClick(
                CREATE_NEW_LIST_BUTTON,
                "Cannot find 'Create new' button",
                5);
        this.waitForElementAndSendKeys(
                NAME_OF_LIST_INPUT,
                name_of_folder,
                "Cannot find 'Name of this list' input",
                2);
        this.waitForElementAndClick(
                OK_BUTTON,
                "Cannot find 'OK' button",
                2);
    }

    public void addArticlesToSaved()
    {
        this.waitForElementAndClick(
                BOOKMARK_BUTTON,
                "Cannot find bookmark button",
                5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find <- button",
                1);
    }

    public void assertArticleHasATextInTitle(String article_name)
    {
        String article_xpath = getArticleTitleWithSubstring(article_name);
        this.assertElementHasText(
                article_xpath,
                article_name,
                "Cannot find expected text in first article found by the request " + article_name,
                5);
    }
}
