package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.awt.*;

public class MyListsPageObject extends MainPageObject
{
    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{ARTICLE_NAME}']",
            CLOSE_SYNC_LISTS_BUTTON = "org.wikipedia:id/negativeButton";

    /* TEMPLATE METHODS */
    private static String getFolderNameByXPath(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleByXPath(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_NAME}", article_title);
    }
    /* TEMPLATE METHODS */

    public MyListsPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderNameByXPath(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                5);
    }

    public void closeSyncLists(){
        this.waitForElementAndClick(
                By.id(CLOSE_SYNC_LISTS_BUTTON),
                "Cannot find Close sync lists button 'Not now'",
                5);
    }

    public void waitArticleToAppear(String article_title)
    {
        String article_xpath = getSavedArticleByXPath(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find saved article by title " + article_title,
                10);
    }

    public void waitArticleToDisappear(String article_title)
    {
        String article_xpath = getSavedArticleByXPath(article_title);
        this.waitForElementNotPresented(
                By.xpath(article_xpath),
                "The article is still present " + article_title,
                10);
    }

    public void swipeArticleToDelete(String article_title)
    {
        this.waitArticleToAppear(article_title);
        String article_xpath = getSavedArticleByXPath(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot delete saved article " + article_title);
        this.waitArticleToDisappear(article_title);
    }
}
