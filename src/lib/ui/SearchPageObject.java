package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject{

    private static final String
            SKIP_ONBOARDING = "id:org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container",
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_DESC_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL =
                    "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']" +
                            "[.//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']]" +
                            "[.//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']]",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.widget.TextView']",
            SEARCH_EMPTY_RESULT = "xpath://*[@text='No results']",
            CLOSE_SEARCH_BUTTON = "xpath://*[@class='android.widget.ImageButton']",
            ARTICLE_TITLES_IN_SEARCH = "id:org.wikipedia:id/page_list_item_title",
            EMPTY_SEARCH_LABEL = "id:org.wikipedia:id/search_empty_message",
            EMPTY_SEARCH_TEXT = "id:Search Wikipedia in more languages";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElementByDescription(String substring)
    {
        return SEARCH_RESULT_BY_DESC_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitle(String substring)
    {
        return SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchWithTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /* TEMPLATE METHODS */

    public void clickSkipOnboardingButton()
    {
        this.waitForElementAndClick(SKIP_ONBOARDING, "Cannot find and click on SKIP onboarding button", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find Cancel search button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresented(SEARCH_CANCEL_BUTTON, "Cancel search button is still present", 5);
    }

    public void clickCancelSearchButton()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click Cancel search button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking on init search element", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click on search init element", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search line", 5);
    }

    public void waitForSearchResultByDescription(String substring)
    {
        String search_result_xpath = getResultSearchElementByDescription(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search results with substring " + substring);
    }

    public void clickByArticleWithSubstringByDescription(String substring)
    {
        String search_result_xpath = getResultSearchElementByDescription(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search results with substring " + substring, 10);
    }

    public void waitForSearchResultByTitle(String substring)
    {
        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search results with substring " + substring);
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_with_title_and_description = getResultSearchWithTitleAndDescription(title, description);
        this.waitForElementPresent(
                search_result_with_title_and_description,
                "Cannot find search results with title '" + title + "' and description '" + description + "'",
                10);
    }

    public void clickByArticleWithSubstringByTitle(String substring)
    {
        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search results with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT,
                "Cannot find empty result label",
                5);
    }

    public void assertThereIsNoResultSearch()
    {
        this.assertElementNotPresent(SEARCH_EMPTY_RESULT, "We didn't find any result");
    }

    public void closeSearch()
    {
        this.waitForElementAndClick(
                CLOSE_SEARCH_BUTTON,
                "Cannot find <- button",
                5);
    }

    public void assertArticlesInSearchHasTextInTitle(String article_name)
    {
        this.assertElementHasText(
                ARTICLE_TITLES_IN_SEARCH,
                article_name,
                "Cannot find text in titles in list with " + article_name,
                10);
    }

    public void assertEmptySearchText()
    {
        this.assertElementHasText(
                EMPTY_SEARCH_LABEL,
                EMPTY_SEARCH_TEXT,
                "Articles are still present",
                10);
    }
}
