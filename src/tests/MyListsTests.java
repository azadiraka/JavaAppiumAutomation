package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleInMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstringByDescription("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElementWithSubstring("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");
        String name_of_folder = "Learning programming";
        ArticlePageObject.addToMyListAndCreateNewList(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.closeSearch();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickSaved();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testAddTwoArticlesToSaved() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        String first_search_line = "Harry Potter";
        SearchPageObject.typeSearchLine(first_search_line);
        SearchPageObject.clickByArticleWithSubstringByTitle(first_search_line);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.addArticlesToSaved();
        ArticlePageObject.closeArticle();

        SearchPageObject.clickCancelSearchButton();

        String second_search_line = "Star Wars";
        SearchPageObject.typeSearchLine(second_search_line);
        SearchPageObject.clickByArticleWithSubstringByTitle(second_search_line + " (film)");
        ArticlePageObject.addArticlesToSaved();
        ArticlePageObject.closeArticle();
        SearchPageObject.closeSearch();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickSaved();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        MyListPageObject.openFolderByName("Saved");
        MyListPageObject.waitArticleToAppear(first_search_line);
        MyListPageObject.waitArticleToAppear(second_search_line + " (film)");
        MyListPageObject.swipeArticleToDelete(second_search_line + " (film)");
        MyListPageObject.openArticleFromList(first_search_line);
        String article_name = ArticlePageObject.getArticleTitle(first_search_line);
        ArticlePageObject.assertArticleHasATextInTitle(article_name);
    }
}
