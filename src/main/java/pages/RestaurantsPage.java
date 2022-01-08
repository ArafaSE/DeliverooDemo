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
    @FindBy(className = "ccl-9bcf8db942e088a2")
    WebElement offerDialog;

    @FindBy(className = "ccl-cce251427bbe4ec4")
    List<WebElement> btnList;

    @FindBy(name = "search")
    WebElement searchInput;

    @FindBy(className = "HomeSuggestionRow-96fe664c521a4881")
    List<WebElement> restaurantSearchResultElements;

    @FindBy(className = "HomeFeedUICard-a0d1bb5bd14433a9")
    List<WebElement> restaurantFilterResultElements;

    @FindBy(className = "FilterTag-0b65fd7f2e66a6fb")
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
