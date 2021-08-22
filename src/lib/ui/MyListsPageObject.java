package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject
{
    public static final String
            FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{ARTICLE_NAME}']",
            CLOSE_SYNC_LISTS_BUTTON = "id:org.wikipedia:id/negativeButton";

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
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5);
    }

    public void closeSyncLists(){
        this.waitForElementAndClick(
                CLOSE_SYNC_LISTS_BUTTON,
                "Cannot find Close sync lists button 'Not now'",
                5);
    }

    public void waitArticleToAppear(String article_title)
    {
        String article_xpath = getSavedArticleByXPath(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                10);
    }

    public void waitArticleToDisappear(String article_title)
    {
        String article_xpath = getSavedArticleByXPath(article_title);
        this.waitForElementNotPresented(
                article_xpath,
                "The article is still present " + article_title,
                10);
    }

    public void swipeArticleToDelete(String article_title)
    {
        this.waitArticleToAppear(article_title);
        String article_xpath = getSavedArticleByXPath(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot delete saved article " + article_title);
        this.waitArticleToDisappear(article_title);
    }

    public void openArticleFromList(String article_name)
    {
        String article_xpath = getSavedArticleByXPath(article_name);
        this.waitForElementAndClick(
                article_xpath,
                "Cannot find the article by title " + article_name,
                5);
    }
}
