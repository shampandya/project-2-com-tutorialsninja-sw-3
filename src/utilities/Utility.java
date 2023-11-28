package utilities;

import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {

    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    //***********************************************************Actions methods********************************************************************************************//

    //This method will mouse hoover on element
    public void mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(by);
        actions.moveToElement(element).build().perform();
    }

    // This method hoover on element and click
    public void mouseHoverToElementAndClick(By by) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(by);
        actions.moveToElement(element).click().build().perform();
    }
    //****************************************************************Select methods*******************************************************************************//

    /**
     * This method will select options by visible text from dropdown menu
     */

    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        // Creating an object of Select class which is parameterised so pass dropDown
        Select select = new Select(dropDown);
        //Select by Visible Text
        select.selectByVisibleText(text);
    }

    /**
     * This method will select options by value from dropdown menu
     */

    public void selectByValueFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByValue(text);
    }

    /**
     * This method will select options by Index from dropdown menu
     */

    public void selectByIndexFromDropDown(By by, int index) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByIndex(index);
    }

    /**
     * This method will select options by contain text from dropdown menu
     */

    public void selectByContainsTextFromDropDown(By by) {
        WebElement dropDown = driver.findElement(by);
        // Creating an object of Select class which is parameterised so pass dropDown
        Select select = new Select(dropDown);
        //Select by Contain Text
        List<WebElement> list = select.getOptions();
    }
    //****************************************************************Alert Method*************************************************************************************//
}