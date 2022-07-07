package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RestaurantsPage extends PageBase {

    public RestaurantsPage(WebDriver driver) {
        super(driver);
        waitUntilElementBeVisible(driver, searchInput);
    }

    /*
        Web Elements
     */
    @FindBy(className = "ccl-e2683e5cd3d2680f")
    WebElement offerDialog;

    @FindBy(className = "ccl-bc70252bc472695a")
    List<WebElement> btnList;

    @FindBy(name = "search")
    WebElement searchInput;

    @FindBy(className = "HomeSuggestionRow-a076e6c92509b9fa")
    List<WebElement> restaurantSearchResultElements;

    @FindBy(className = "HomeFeedUICard-d38caa5cc97794b4")
    List<WebElement> restaurantFilterResultElements;

    @FindBy(className = "FilterTag-9986b96ac911a4d0")
    WebElement filterResultsTag;

    @FindBy(xpath = "//input[@value='Free Delivery']")
    WebElement freeDeliveryRadioBtnFilter;

    @FindBy(xpath = "//input[@value='Meal deals']")
    WebElement mealDealsRadioBtnFilter;

    // variables

    /*
     Methods: Will be used for all actions needed in the Restaurants page
  */
    public void acceptNewUsersOffer(WebDriver driver) {
        // This is a special scenario when offer modal displayed for new users (if it displayed accept it then continue)
        try {
            // wait until offer dialog displayed
            waitUntilElementBeVisible(driver, offerDialog);
            // Accept the offer for new users
            clickButton(btnList.get(btnList.size() - 1));
        } catch (Exception e) {
            // there is no offer displayed
        }
    }

    public void searchForRestaurant(String restaurant) {
        setTextElement(searchInput, restaurant);
    }

    public void selectFirstRestaurantResult() {
        waitSec(2);
        clickButton(restaurantSearchResultElements.get(1));
    }
    public void selectFilter(String filterName) {

        if (filterName.equals("Free Delivery")) {
            clickButton(freeDeliveryRadioBtnFilter);
        } else if (filterName.equals("Meal Deals")) {
           clickButton(mealDealsRadioBtnFilter);
        }
    }

    public void selectFirstRestaurantFilterResult(WebDriver driver) {
        // Wait until filter results tag is displayed
        waitUntilElementBeVisible(driver, filterResultsTag);
        // select the first restaurant in the free delivery filter results
        clickButton(restaurantFilterResultElements.get(0));
    }
}
