package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultByDescription("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearchButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNonEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfSearchByNameAndDescription(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("MacBook");
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "There are not 3 search results at least",
                amount_of_search_results >= 3);

        SearchPageObject.waitForElementByTitleAndDescription("MacBook Pro", "Line of notebook computers");
    }

    @Test
    public void testAmountOfEmptySearch(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        String search_line = "sdjfnsdndfvjb";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultSearch();
    }

    @Test
    public void testElementPresentAfterBackground(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultByDescription("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.getArticleTitle("Java (programming language)");
        this.backgroundApp(2);
        ArticlePageObject.getArticleTitle("Java (programming language)");
    }

    @Test
    public void testCancelArticleSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        String search_line = "Android";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.assertArticlesInSearchHasTextInTitle(search_line);
        SearchPageObject.clickCancelSearchButton();
        SearchPageObject.assertEmptySearchText();
    }
}
