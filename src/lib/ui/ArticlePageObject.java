package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    public final static String
            TITLE_TPL = "//*[@class='android.view.View'][@text='{SUBSTRING}']",
            FOOTER_ELEMENT = "//*[@text='View article in browser']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getArticleTitleWithSubstring(String substring)
    {
        return TITLE_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public WebElement waitForTitleElementWithSubstring(String substring)
    {
        String article_title = getArticleTitleWithSubstring(substring);
        return this.waitForElementPresent(By.xpath(article_title), "Cannot find the title of article", 5);
    }

    public String getArticleTitle(String substring)
    {
        WebElement title_element = waitForTitleElementWithSubstring(substring);
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find footer element",
                20
        );
    }
}
