package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /*
      Web Elements
   */
    @FindBy(linkText = "Deliveroo")
    WebElement homePageLink;

    @FindBy(id = "location-search")
    WebElement locationInput;

    @FindBy(className = "ccl-52cba27ceeb9f260")
    List<WebElement> suggestionAddresses;

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement acceptCookiesBtn;

    /*
      Methods: Will be used for all actions needed in the Home page
   */
    public void openPage() {
        clickButton(homePageLink);
    }

    public void acceptCookies() {
        // This is a special scenario when accept cookies disclaimer are displayed
        try {
            clickButton(acceptCookiesBtn);
        } catch (Exception e) {
            // accept cookies not displayed
        }
    }

    public void addUserAddress(String address) {
        // accept cookies if displayed
        acceptCookies();
        // add user address
        setTextElement(locationInput, address);
    }

    public void selectFirstSuggestionAddress(WebDriver driver) {
       waitSec(3);
        clickButton(suggestionAddresses.get(0));
    }

}
