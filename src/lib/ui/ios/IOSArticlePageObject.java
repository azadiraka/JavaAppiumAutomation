package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        BOOKMARK_BUTTON = "id:Save for later";
        ADD_TO_ANOTHER_LIST_BUTTON = "id:Add “Java (programming language)” to a reading list?";
        CREATE_NEW_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Create a new list']";
        NAME_OF_LIST_INPUT = "xpath://XCUIElementTypeTextField[@value='reading list title']";
        OK_BUTTON = "xpath://XCUIElementTypeButton[@name='Create reading list']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CLOSE_SYNC_ARTICLES_POPUP = "id:Close";
    }

    public IOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
