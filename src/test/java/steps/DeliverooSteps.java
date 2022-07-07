package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.MenuPage;
import pages.RestaurantsPage;
import tests.TestBase;

public class DeliverooSteps extends TestBase {
    // Page objects
    HomePage homePage;
    RestaurantsPage restaurantsPage;
    MenuPage menuPage;

    @Given("The User in the Deliveroo home page {string}")
    public void theUserInTheDeliverooHomePage(String homeUrl) {
        // New Home page object
        homePage = new HomePage(driver);
        // open home page
        homePage.openPage();
        // Assert that user in the home page URL
        Assert.assertEquals(driver.getCurrentUrl(), homeUrl);
    }

    @When("The User entered his {string}")
    public void theUserEnteredHisAddress(String address) {
        // User enter his address
        homePage.addUserAddress(address);
    }

    @And("The User select the first suggestion address")
    public void theUserSelectTheFirstSuggestionAddress() {
        // Select the first suggestion address in the search results
        homePage.selectFirstSuggestionAddress(driver);
    }

    @And("The user redirected into restaurants page")
    public void theUserRedirectedIntoTheRestaurantsPage() {
        // New restaurant page object
        restaurantsPage = new RestaurantsPage(driver);

        // Assert that user redirected into restaurant page by checking URL
        Assert.assertTrue(driver.getCurrentUrl().contains("/restaurants/"));

        // Accept new users offer is displayed for new users
        restaurantsPage.acceptNewUsersOffer(driver);
    }

    @And("The User enter the {string} in the search input")
    public void theUserEnterTheRestaurantNameInTheSearchInput(String restaurant) {
        // search for the restaurant
        restaurantsPage.searchForRestaurant(restaurant);
    }

    @And("The User click on the first restaurant from the results")
    public void theUserClickOnTheFirstRestaurantFromTheResults() {
        // The user select first result
        restaurantsPage.selectFirstRestaurantResult();
    }

    @Then("User redirected into the {string} page")
    public void userRedirectedIntoTheRestaurantPage(String restaurant) {
        // Create a new menu page object
        menuPage = new MenuPage(driver);
        // get displayed restaurant name and assert that it is the search restaurant
        String displayedRestaurant = menuPage.getRestaurantName();
        Assert.assertTrue(displayedRestaurant.contains(restaurant));
    }

    @And("The User select the Free delivery from side menu filter")
    public void theUserSelectTheFreeDeliveryFromSideMenuFilter() {
        // user filter by Free Delivery filter
        restaurantsPage.selectFilter("Free Delivery");
    }

    @And("The User select the first restaurant in the free delivery results")
    public void theUserSelectTheFirstRestaurantInTheFreeDeliveryFilterResults() {
        // The user select first restaurant in the filtered results
        restaurantsPage.selectFirstRestaurantFilterResult(driver);
    }

    @And("The user redirected to restaurant menu page")
    public void theUserRedirectedToRestaurantMenuPage() {
        // Create a new menu page object in case it's not called from first scenario
        menuPage = new MenuPage(driver);
        // Assert that user redirected in the mnu page by checking URL
        Assert.assertTrue(driver.getCurrentUrl().contains("menu"));
    }

    @And("The User select the first item in the restaurant")
    public void theUserSelectTheFirstItemInTheRestaurant() {
        // User select first item in the restaurant
        menuPage.selectFirstItemInTheMenu(driver);
    }

    @And("The User select his options and add the item into the Basket")
    public void theUserSelectHisOptionsAndAddTheItemIntoTheBasket() {
        // User add the item to the basket
        menuPage.addToBasket(driver);
    }

    @And("The increase the order item counter until reach the {string}")
    public void theIncreaseTheOrderItemCounterUntilReachTheMinimumCost(String minimumFreeDeliveryCost) {
        // this to increase the items counter in case the item not reach the Free deliver minimum cost
        menuPage.increaseItemQuantityToReachTotalCost(minimumFreeDeliveryCost);
    }

    @Then("The user get the free delivery fee")
    public void theUserGetTheFreeDeliveryFee() {
        // get the Delivery cost displayed in the Basket and check it's free
        String deliveryCost = menuPage.cartGetDeliveryCost();
        Assert.assertEquals(deliveryCost, "Free");
    }
}
