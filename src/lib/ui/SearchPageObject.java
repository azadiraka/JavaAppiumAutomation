package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
            SKIP_ONBOARDING = "org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_DESC_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.widget.TextView']",
            SEARCH_EMPTY_RESULT = "//*[@text='No results']",
            CLOSE_SEARCH_BUTTON = "//*[@class='android.widget.ImageButton']",
            ARTICLE_TITLES_IN_SEARCH = "org.wikipedia:id/page_list_item_title",
            EMPTY_SEARCH_LABEL = "org.wikipedia:id/search_empty_message",
            EMPTY_SEARCH_TEXT = "Search Wikipedia in more languages";

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
    /* TEMPLATE METHODS */

    public void clickSkipOnboardingButton()
    {
        this.waitForElementAndClick(By.id(SKIP_ONBOARDING), "Cannot find and click on SKIP onboarding button", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find Cancel search button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresented(By.id(SEARCH_CANCEL_BUTTON), "Cancel search button is still present", 5);
    }

    public void clickCancelSearchButton()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click Cancel search button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(By.id(SEARCH_INIT_ELEMENT),"Cannot find search input after clicking on init search element", 5);
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT),"Cannot find and click on search init element", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line, "Cannot find and type into search line", 5);
    }

    public void waitForSearchResultByDescription(String substring)
    {
        String search_result_xpath = getResultSearchElementByDescription(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search results with substring " + substring);
    }

    public void clickByArticleWithSubstringByDescription(String substring)
    {
        String search_result_xpath = getResultSearchElementByDescription(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search results with substring " + substring, 10);
    }
    public void waitForSearchResultByTitle(String substring)
    {
        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search results with substring " + substring);
    }

    public void clickByArticleWithSubstringByTitle(String substring)
    {
        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search results with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT),
                "Cannot find empty result label",
                5);
    }

    public void assertThereIsNoResultSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_EMPTY_RESULT), "We didn't find any result");
    }

    public void closeSearch()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_SEARCH_BUTTON),
                "Cannot find <- button",
                5);
    }

    public void assertArticlesInSearchHasTextInTitle(String article_name)
    {
        this.assertElementHasText(
                By.id(ARTICLE_TITLES_IN_SEARCH),
                article_name,
                "Cannot find text in titles in list with " + article_name,
                10);
    }

    public void assertEmptySearchText()
    {
        this.assertElementHasText(
                By.id(EMPTY_SEARCH_LABEL),
                EMPTY_SEARCH_TEXT,
                "Articles are still present",
                10);
    }
}
