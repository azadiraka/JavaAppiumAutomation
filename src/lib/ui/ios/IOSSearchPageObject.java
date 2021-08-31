package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SKIP_ONBOARDING = "xpath://XCUIElementTypeButton[@name='Skip']";
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "id:Clear text";
        SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_BY_DESC_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL =
                "xpath://XCUIElementTypeCell" +
                        "[.//XCUIElementTypeStaticText[@name='{TITLE}']]" +
                        "[.//XCUIElementTypeStaticText[@name='{DESCRIPTION}']]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT = "id:No results found";
        CLOSE_SEARCH_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
        ARTICLE_TITLES_IN_SEARCH = "id:org.wikipedia:id/page_list_item_title";
        EMPTY_SEARCH_LABEL = "id:org.wikipedia:id/search_empty_message";
        EMPTY_SEARCH_TEXT = "id:Search Wikipedia in more languages";
    }

    public IOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
