import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

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
                "Cannot find an fragment_onboarding_skip_button",
                1);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find an element_search_line_init",
                1);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find an element_tap_search_line and find Appium topics",
                1);
    }

    @Test
    public void testCancelSearch() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find an fragment_onboarding_skip_button",
                1);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia'",
                1);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find an element_tap_search_line and find Java topics",
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
                "Cannot find an fragment_onboarding_skip_button",
                1);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find an element_search_line_init",
                2);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find an element_tap_search_line and find Java topics",
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
                "Cannot find an fragment_onboarding_skip_button",
                1);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find an element_search_line_init",
                2);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Android",
                "Cannot find an element_tap_search_line and find Java topics",
                2);
        assertElementHasText(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Android",
                "Cannot find expected text",
                10);
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find input text 'Android'",
                2);
        assertElementHasText(
                By.id("org.wikipedia:id/search_empty_message"),
                "Search Wikipedia in more languages",
                "Articles are still present",
                2);
    }

    @Test
    public void testCheckWordsInSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find an fragment_onboarding_skip_button",
                1);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find an element_search_line_init",
                2);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Android",
                "Cannot find an element_tap_search_line and find Java topics",
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

}
