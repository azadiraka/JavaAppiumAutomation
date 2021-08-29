package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
            SAVED_BUTTON;

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
