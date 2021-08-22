package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject
{
    public final static String
            SAVED_BUTTON = "xpath://*[@content-desc='Saved']";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickSaved()
    {
        this.waitForElementAndClick(
                SAVED_BUTTON,
                "Cannot find Saved button",
                1);
    }
}
