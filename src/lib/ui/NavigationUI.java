package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject
{
    public final static String
            SAVED_BUTTON = "Saved";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickSaved()
    {
        this.waitForElementAndClick(
                By.id(SAVED_BUTTON),
                "Cannot find Saved button",
                1);
    }
}
