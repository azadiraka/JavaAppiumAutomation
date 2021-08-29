package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "id:{FOLDER_NAME}";
        ARTICLE_BY_TITLE_TPL = "id:{ARTICLE_NAME}";
        CLOSE_SYNC_LISTS_BUTTON = "id:org.wikipedia:id/negativeButton";
        SWIPE_ACTION_TO_DELETE_BUTTON = "id:swipe action delete";
    }

    public IOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
