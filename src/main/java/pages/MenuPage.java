package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuPage extends PageBase {

    public MenuPage(WebDriver driver) {
        super(driver);
        waitUntilPageLoaded(driver);
    }

    /*
       Web Elements
    */
    @FindBy(tagName = "h1")
    List<WebElement> pageTitles;

    @FindBy(className = "UIMenuItemCard-4fc58716499db6c7")
    List<WebElement> menuItemsElementsList;

    @FindBy(className = "ccl-ed9aadeaa18a9f19")
    List<WebElement> btnList;

    @FindBy(className = "ccl-115b2fb253c504a9")
    List<WebElement> basketResultsValues;

    @FindBy(xpath = "//button[@aria-label='Increase quantity']")
    WebElement increaseQntBtn;


    /*
        Methods: Will be used for all actions needed in the menu page
     */
    public String getRestaurantName() {
        return pageTitles.get(0).getText();
    }


    public void selectFirstItemInTheMenu(WebDriver driver) {
        waitUntilElementBeVisible(driver, menuItemsElementsList.get(0));
        clickButton(menuItemsElementsList.get(0));
    }

    public void addToBasket(WebDriver driver) {
        // get the add button (It's located as the last button in the page list buttons)
        btnList.get(btnList.size() - 1).click();
        // wait until Item added and the dialog removed
        waitUntilElementNotVisible(driver, btnList.get(btnList.size() - 1));
    }

    public float cartGetTotalCost() {
        // get the total cost
        String totalCost = basketResultsValues.get(basketResultsValues.size() - 1).getText();

        return removeCurrencyFromPrice(totalCost);
    }

    public void increaseItemQuantityToReachTotalCost(String cost) {
        float minimumCost = Float.parseFloat(cost);
        // get the current total cost
        float totalCost = cartGetTotalCost();

        while (totalCost < minimumCost) {
            // increase items until reach the minimum free deliver cost
            cartIncreaseQuantityClick();
            totalCost = cartGetTotalCost();
        }
    }

    public void cartIncreaseQuantityClick() {
        clickButton(increaseQntBtn);
    }

    public String cartGetDeliveryCost() {
        // get the delivery Cost
        String deliveryCost = basketResultsValues.get(basketResultsValues.size() - 4).getText();

        String[] arrOfStr = deliveryCost.split("£");
        deliveryCost = "";

        for (String a : arrOfStr)
            deliveryCost += a;

        return deliveryCost;
    }

    public void waitUntilPageLoaded(WebDriver driver) {
        waitUntilElementBeVisible(driver, pageTitles.get(0));
    }
}
