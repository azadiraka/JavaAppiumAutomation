package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    public static final String
            LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
            NEXT_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Next']",
            NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            ADD_OR_EDIT_LINK = "xpath://XCUIElementTypeStaticText[@name='Add or edit preferred languages']",
            LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']",
            GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Get started']";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(
                LEARN_MORE_LINK,
                "Cannot find 'Learn more' link",
                5);
    }

    public void waitForNewWaysToExploreText()
    {
        this.waitForElementPresent(
                NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'New ways to explore' text",
                5);
    }

    public void waitForAddOrEditPreferredLanguagesLink()
    {
        this.waitForElementPresent(
                ADD_OR_EDIT_LINK,
                "Cannot find 'Add or edit preferred languages' link",
                5);
    }

    public void waitForLearnMoreAboutDataCollectedLink()
    {
        this.waitForElementPresent(
                LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find 'Learn more about data collected' link",
                5);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(
                NEXT_BUTTON,
                "Cannot find and click on 'Next' button",
                5);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find and click on 'Get started' button",
                5);
    }
}
