package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
                TITLE_TPL = "xpath://*[@class='android.view.View'][@text='{SUBSTRING}']";
                FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
                ADD_TO_SAVED_BUTTON = "id:org.wikipedia:id/article_menu_bookmark";
                ADD_TO_ANOTHER_LIST_BUTTON = "xpath://*[@text='Add to another reading list']";
                CREATE_NEW_LIST_BUTTON = "xpath://*[@text='Create new']";
                NAME_OF_LIST_INPUT = "id:org.wikipedia:id/text_input";
                OK_BUTTON = "id:android:id/button1";
                CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
