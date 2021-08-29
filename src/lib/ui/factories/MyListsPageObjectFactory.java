package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidMyListsPageObject;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.IOSMyListsPageObject;
import lib.ui.ios.IOSNavigationUI;

public class MyListsPageObjectFactory{
    public static MyListsPageObject get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else {
            return new IOSMyListsPageObject(driver);
        }
    }
}
