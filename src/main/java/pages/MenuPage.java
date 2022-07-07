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
    List<WebElement> pageTitlesList;

    @FindBy(className = "UIMenuItemCard-c782d9890e061230")
    List<WebElement> menuItemsElementsList;

    @FindBy(className = "ccl-7be8185d0a980278")
    List<WebElement> btnList;

    @FindBy(className = "ccl-a206e125970432e3")
    List<WebElement> basketResultsList;

    @FindBy(xpath = "//button[@aria-label='Increase quantity']")
    List<WebElement> increaseQntButtonsList;


    /*
        Methods: Will be used for all actions needed in the menu page
     */
    public String getRestaurantName() {
        return pageTitlesList.get(0).getText();
    }


    public void selectFirstItemInTheMenu(WebDriver driver) {
        waitSec(3);
        clickButton(menuItemsElementsList.get(0));
    }

    public void addToBasket(WebDriver driver) {
        waitSec(3);
        // get the add button (It's located as the last button in the page list buttons)
        btnList.get(btnList.size() - 1).click();
        // wait until Item added and the dialog removed
        waitUntilElementNotVisible(driver, btnList.get(btnList.size() - 1));
    }

    public float cartGetTotalCost(WebDriver driver) {
       waitSec(3);
        // get the total cost
        String totalCost = basketResultsList.get(basketResultsList.size() - 1).getText();

        return removeCurrencyFromPrice(totalCost);
    }

    public void increaseItemQuantityToReachTotalCost(String cost) {
        float minimumCost = Float.parseFloat(cost);
        // get the current total cost
        float totalCost = cartGetTotalCost(driver);

        while (totalCost < minimumCost) {
            // increase items until reach the minimum free deliver cost
            cartIncreaseQuantityClick(driver);
            totalCost = cartGetTotalCost(driver);
        }
    }

    public void cartIncreaseQuantityClick(WebDriver driver) {
        waitSec(3);
        clickButton(increaseQntButtonsList.get(0));
    }

    public String cartGetDeliveryCost() {
        // get the delivery Cost
        String deliveryCost = basketResultsList.get(basketResultsList.size() - 4).getText();

        String[] arrOfStr = deliveryCost.split("£");
        deliveryCost = "";

        for (String a : arrOfStr)
            deliveryCost += a;

        return deliveryCost;
    }

    public void waitUntilPageLoaded(WebDriver driver) {
        waitSec(3);
        waitUntilElementBeVisible(driver, pageTitlesList.get(0));
    }
}
