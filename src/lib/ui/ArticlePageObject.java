package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE_TPL,
            FOOTER_ELEMENT,
            ADD_TO_SAVED_BUTTON,
            REMOVE_FROM_SAVED_BUTTON,
            ADD_TO_ANOTHER_LIST_BUTTON,
            CREATE_NEW_LIST_BUTTON,
            NAME_OF_LIST_INPUT,
            OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            CLOSE_SYNC_ARTICLES_POPUP;

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
        return this.waitForElementPresent(article_title, "Cannot find the title of article. The title is " + substring, 5);
    }

    public String getArticleTitle(String substring)
    {
        WebElement title_element = waitForTitleElementWithSubstring(substring);
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find footer element",
                    40);
        } else {
            this.swipeTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find footer element",
                    40);
        }
    }

    public void addToMyListAndCreateNewList(String name_of_folder)
    {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    ADD_TO_SAVED_BUTTON,
                    "Cannot find bookmark button",
                    5);
            this.waitForElementAndClick(
                    ADD_TO_SAVED_BUTTON,
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
        } else {
            this.waitForElementAndClick(
                    ADD_TO_SAVED_BUTTON,
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
    }

    public void addArticlesToSaved()
    {
        this.waitForElementAndClick(
                ADD_TO_SAVED_BUTTON,
                "Cannot find bookmark button",
                5);
    }

    public void waitForUnsavedButton()
    {
        this.waitForElementPresent(
                REMOVE_FROM_SAVED_BUTTON,
                "Perhaps this article was not added to Saved list",
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

    public void closeSyncArticlesPopupIOSApp()
    {
        this.waitForElementAndClick(
                CLOSE_SYNC_ARTICLES_POPUP,
                "Cannot find and close sync articles popup",
                5);
    }
}
