package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static String
            name_of_folder = "Learning programming",
            first_search_line = "Harry Potter",
            second_search_line = "Star Wars";

    @Test
    public void testSaveFirstArticleInMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstringByTitle("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElementWithSubstring("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

        ArticlePageObject.addToMyListAndCreateNewList(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.closeSearch();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickSaved();

        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncArticlesPopupIOSApp();
        }

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testAddTwoArticlesToSaved() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();

        SearchPageObject.typeSearchLine(first_search_line);
        SearchPageObject.clickByArticleWithSubstringByTitle(first_search_line);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.addArticlesToSaved();
        ArticlePageObject.closeArticle();

        SearchPageObject.clickCancelSearchButton();

        SearchPageObject.typeSearchLine(second_search_line);
        SearchPageObject.clickByArticleWithSubstringByTitle(second_search_line + " (film)");
        ArticlePageObject.addArticlesToSaved();
        ArticlePageObject.closeArticle();
        SearchPageObject.closeSearch();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickSaved();

        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncArticlesPopupIOSApp();
        }

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        MyListPageObject.waitArticleToAppear(first_search_line);
        MyListPageObject.waitArticleToAppear(second_search_line + " (film)");
        MyListPageObject.swipeArticleToDelete(second_search_line + " (film)");
        MyListPageObject.openArticleFromList(first_search_line);

        String article_name = ArticlePageObject.getArticleTitle(first_search_line);
        ArticlePageObject.assertArticleHasATextInTitle(article_name);

        ArticlePageObject.waitForUnsavedButton();
    }
}
