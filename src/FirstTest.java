import com.sun.source.tree.AssertTree;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/sashazadiraka/Desktop/JavaAppiumAutomation/apps/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void firstTest() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                1);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find search input",
                1);
    }

    @Test
    public void testCancelSearch() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                1);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                2);

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find input text 'Java'",
                2
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@class, 'android.widget.ImageButton')]"),
                "Cannot find <- element to cancel search",
                1);

        waitForElementNotPresented(
                By.xpath("//*[contains(@class, 'android.widget.ImageButton')]"),
                "<- element is still presented",
                1);
    }

    @Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find the Search Wikipedia input",
                2);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                2);
        waitForElementAndClick(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find Java article",
                5);
        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@class='android.view.View']//*[@text='Java (programming language)']"),
                "Cannot find title of Java article",
                15);
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSwipeArticle(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find search input",
                2);
        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView'][@text='Appium']"),
                "Cannot find Appium article in search",
                5);
        waitForElementPresent(
                By.xpath("//*[@class='android.view.View']//*[@text='Appium']"),
                "Cannot find title of Appium article",
                15);
        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find the end of the article",
                20);
    }

    @Test
    public void saveFirstArticleInMyList() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                2);
        waitForElementAndClick(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find Java article",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find bookmark button",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find bookmark button",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='Add to another reading list']"),
                "Cannot find 'Add to another reading list' button",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='Create new']"),
                "Cannot find 'Create new' button",
                5);
        String name_folder = "Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_folder,
                "Cannot find 'Name of this list' input",
                2);
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find 'OK' button",
                2);
        waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find <- button",
                1);
        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot find <- button",
                5);
        waitForElementAndClick(
                By.id("Saved"),
                "Cannot find Saved button",
                1);
        waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Cannot find Learning programming list",
                5);
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find Java article");
        waitForElementNotPresented(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete Java article",
                10);
    }

    @Test
    public void addTwoArticlesToSaved() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        String first_search_line = "Harry Potter";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                first_search_line,
                "Cannot find search input",
                2);
        String first_article_locator = "//*[@text='Fantasy literature series by J.K. Rowling']";
        waitForElementAndClick(
                By.xpath(first_article_locator),
                "Cannot find the article by the request" + first_search_line,
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find bookmark button",
                5);
        waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find <- button to move to search results list",
                1);
        waitForElementAndClick(
                By.id("Clear query"),
                "Cannot find 'X' button to clear search input",
                2);
        String second_search_line = "Star Wars";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                second_search_line,
                "Cannot find search input",
                2);
        String second_article_locator = "//*[@text='1977 American epic space-opera film directed by George Lucas']";
        waitForElementAndClick(
                By.xpath(second_article_locator),
                "Cannot find the article by the request" + second_search_line,
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find bookmark button",
                5);
        waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find <- button to move to search results list",
                1);
        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot find <- button to move to main screen",
                5);
        waitForElementAndClick(
                By.id("Saved"),
                "Cannot find Saved button",
                1);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='Saved']"),
                "Cannot find 'Saved' list",
                5);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']" + first_article_locator.substring(3)),
                "Cannot find first article in the 'Saved' list found by the request" + first_search_line,
                5);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']" + second_article_locator.substring(3)),
                "Cannot find second article in the 'Saved' list found by the request" + second_search_line,
                5);
        swipeElementToLeft(
                By.xpath(second_article_locator),
                "Cannot find second article in the 'Saved' list found by the request" + second_search_line);
        waitForElementNotPresented(
                By.xpath(second_article_locator),
                "Cannot delete second article in the 'Saved' list found by the request" + second_search_line,
                5);
        waitForElementAndClick(
                By.xpath(first_article_locator),
                "Cannot find first article in the 'Saved' list found by the request" + first_search_line,
                5);
        assertElementHasText(
                By.id("pcs-edit-section-title-description"),
                "Fantasy literature series by J.K. Rowling",
                "Cannot find expected text in first article found by the request" + first_search_line,
                5);
    }

    @Test
    public void testAmountOfNonEmptySearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        String search_line = "Linkin park Discography";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                2);
        String result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.widget.TextView']";
        waitForElementPresent(
                By.xpath(result_locator),
                "Cannot find anything by the request" + search_line,
                15);
        int amount_of_search_results = getAmountOfElements(
                By.xpath(result_locator));
        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        String search_line = "sdjfnsdndfvjb";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                2);
        String result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.widget.TextView']";
        String empty_search_label = "//*[@text='No results']";

        waitForElementPresent(
                By.xpath(empty_search_label),
                "Cannot find empty search label by the request" + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(result_locator),
                "We've found some results by the request" + search_line
        );
    }

    @Test
    public void testElementPresentAfterBackground(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        String search_line = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                2);
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find Java article",
                5);
        driver.runAppInBackground(2);
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find Java article after returning from background",
                5);
    }

    @Test
    public void testExpectedText(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        assertElementHasText(
                By.id("org.wikipedia:id/view_announcement_action_negative"),
                "GOT IT",
                "Cannot find expected text",
                2
        );
    }

    @Test
    public void testCancelArticleSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Android",
                "Cannot find search input",
                2);

    }

    @Test
    public void testCheckWordsInSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find a SKIP button",
                1);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the Search Wikipedia input",
                2);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Android",
                "Cannot find search input",
                2);
        assertElementHasText(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Android",
                "Cannot find expected text",
                10);
    }

    private WebElement waitForElementPresent (By by, String error_message, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent (By by, String error_message) {
        return waitForElementPresent(by, error_message, 1);
    }

    private WebElement waitForElementAndClick (By by, String error_message, long timeoutInSec) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSec);
        element.click();
        return element;
    }

    private WebElement waitForElementAndClear (By by, String error_message, long timeoutInSec) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSec);
        element.clear();
        return element;
    }

    private WebElement waitForElementAndSendKeys (By by, String value, String error_message, long timeoutInSec) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSec);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresented(By by, String error_message, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private String assertElementHasText(By by, String expected_text, String error_message, long timeoutInSec) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSec);
        String title_element = element.getAttribute("text");
        Assert.assertEquals(
                "Cannot find expected text",
                title_element.contains(expected_text),
                true
        );
        return title_element;
    }

    protected void swipeUp (int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;

        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find an element by swiping up\n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = (left_x + element.getSize().getWidth()) / 2;
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 1) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

}
