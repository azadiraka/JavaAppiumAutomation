package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SKIP_ONBOARDING = "id:org.wikipedia:id/fragment_onboarding_skip_button";
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_DESC_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL =
                "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']" +
                        "[.//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']]" +
                        "[.//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']]";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.widget.TextView']";
        SEARCH_EMPTY_RESULT = "xpath://*[@text='No results']";
        CLOSE_SEARCH_BUTTON = "xpath://*[@class='android.widget.ImageButton']";
        ARTICLE_TITLES_IN_SEARCH = "id:org.wikipedia:id/page_list_item_title";
        EMPTY_SEARCH_LABEL = "id:org.wikipedia:id/search_empty_message";
        EMPTY_SEARCH_TEXT = "id:Search Wikipedia in more languages";
    }

    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
