import io.appium.java_client.AppiumDriver;
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {

    MainPageObject MainPageObject = new MainPageObject(driver);



    @Test
    public void testAddTwoArticlesToSaved() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        String first_search_line = "Harry Potter";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                first_search_line,
                "Cannot find search input",
                2);
        String first_article_locator = "//*[@text='Fantasy literature series by J.K. Rowling']";
        MainPageObject.waitForElementAndClick(
                By.xpath(first_article_locator),
                "Cannot find the article by the request" + first_search_line,
                5);
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find bookmark button",
                5);
        MainPageObject.waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find <- button to move to search results list",
                1);
        MainPageObject.waitForElementAndClick(
                By.id("Clear query"),
                "Cannot find 'X' button to clear search input",
                2);
        String second_search_line = "Star Wars";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                second_search_line,
                "Cannot find search input",
                2);
        String second_article_locator = "//*[@text='1977 American epic space-opera film directed by George Lucas']";
        MainPageObject.waitForElementAndClick(
                By.xpath(second_article_locator),
                "Cannot find the article by the request" + second_search_line,
                5);
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find bookmark button",
                5);
        MainPageObject.waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find <- button to move to search results list",
                1);
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot find <- button to move to main screen",
                5);
        MainPageObject.waitForElementAndClick(
                By.id("Saved"),
                "Cannot find Saved button",
                1);
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='Saved']"),
                "Cannot find 'Saved' list",
                5);
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']" + first_article_locator.substring(3)),
                "Cannot find first article in the 'Saved' list found by the request" + first_search_line,
                5);
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']" + second_article_locator.substring(3)),
                "Cannot find second article in the 'Saved' list found by the request" + second_search_line,
                5);
        MainPageObject.swipeElementToLeft(
                By.xpath(second_article_locator),
                "Cannot find second article in the 'Saved' list found by the request" + second_search_line);
        MainPageObject.waitForElementNotPresented(
                By.xpath(second_article_locator),
                "Cannot delete second article in the 'Saved' list found by the request" + second_search_line,
                5);
        MainPageObject.waitForElementAndClick(
                By.xpath(first_article_locator),
                "Cannot find first article in the 'Saved' list found by the request" + first_search_line,
                5);
        MainPageObject.assertElementHasText(
                By.id("pcs-edit-section-title-description"),
                "Fantasy literature series by J.K. Rowling",
                "Cannot find expected text in first article found by the request" + first_search_line,
                5);
    }

    @Test
    public void testArticleTitlePresent(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        String search_line = "iOS";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                2);
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Mobile operating system by Apple']"),
                "Cannot find the article by the request" + search_line,
                5);
        MainPageObject.assertElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find the article's title");
    }


    @Test
    public void testElementPresentAfterBackground(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultByDescription("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.getArticleTitle("Java (programming language)");
        this.backgroundApp(2);
        ArticlePageObject.getArticleTitle("Java (programming language)");
    }

    @Test
    public void testExpectedText(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/view_announcement_action_negative"),
                "GOT IT",
                "Cannot find expected text",
                2
        );
    }

    @Test
    public void testCancelArticleSearch() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Android",
                "Cannot find search input",
                2);

    }

    @Test
    public void testCheckWordsInSearch() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Android",
                "Cannot find search input",
                2);
        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Android",
                "Cannot find expected text",
                10);
    }


}
