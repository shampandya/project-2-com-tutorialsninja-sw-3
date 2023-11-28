package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class DesktopsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

        @Before
           public void setUp(){
            openBrowser(baseUrl);
        }

        @Test
        public void verifyProductArrangeInAlphaBaticalOrder(){
            //1.1 Mouse hover on Desktops Tab.and click
            mouseHoverToElementAndClick(By.xpath("//a[@class = 'dropdown-toggle' and contains(text(), 'Desktops')]"));
            //1.2 Click on “Show All Desktops”
            mouseHoverToElementAndClick(By.xpath(" //a[contains(text(), 'Show AllDesktops')]"));

            //1.3 Select Sort By position "Name: Z to A"
            selectByValueFromDropDown(By.xpath("//select[@id='input-sort']"),"https://tutorialsninja.com/demo/index.php?route=product/category&path=20&sort=pd.name&order=DESC");

            //1.4 Verify the Product will arrange in Descending order.
            String actualTextOnDropDown = driver.findElement(By.xpath("//select/option[contains(text(), 'Name (Z - A)')]")).getText();
            String expectedTextOnDropDown = "Name (Z - A)";
            Assert.assertEquals(expectedTextOnDropDown,actualTextOnDropDown);
        }

        @Test
        public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        mouseHoverToElementAndClick(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md' and contains(text(), 'Currency')]"));
        mouseHoverToElementAndClick(By.xpath("//button[@name='GBP' and contains(text(), '£Pound Sterling')]"));
            //1.1 Mouse hover on Desktops Tab.and click
            mouseHoverToElementAndClick(By.xpath("//a[@class = 'dropdown-toggle' and contains(text(), 'Desktops')]"));
            //1.2 Click on “Show All Desktops”
            mouseHoverToElementAndClick(By.xpath(" //a[contains(text(), 'Show AllDesktops')]"));

            //1.3 Select Sort By position "Name:  A to Z"
            selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']//option[contains(text(),'Name (A - Z)')]"),"Name (A - Z)");

            //2.4 Select product “HP LP3065”
            clickOnElement(By.xpath("//a[contains(text(),'HP LP3065')]"));

            //2.5 Verify the Text "HP LP3065"
            String expectedText = "HP LP3065";
            String actualText = getTextFromElement(By.xpath("//h1[contains(text(),'HP LP3065')]"));
            Assert.assertEquals("NotMatching", expectedText, actualText);

            //2.6 Select Delivery Date "2023-11-27"
            String date = "23";
            String month ="November";
            String year = "2023";

            clickOnElement(By.xpath("//button[@class = 'btn btn-default' and @type = 'button']//i[@class='fa fa-calendar']"));
            while (true){
                String monthYear = getTextFromElement(By.xpath("//th[@class ='picker-switch' and contains(text(),'April 2011')]"));
                System.out.println(monthYear);
                String [] a = monthYear.split(" ");
                String mon = a[0];
                String yer = a[1];
                if(mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)){
                    break;
                }else{
                    clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
                }
            }
            //select the date
            List<WebElement> allDates = driver.findElements(By.xpath("//div[@class='datepicker']//tbody//tr/td"));
            for(WebElement dt : allDates){
                if(dt.getText().equalsIgnoreCase(date)){
                    dt.click();
                    break;
                }
            }
            //2.7.Enter Qty "1” using Select class. -- There is no Dropdown menu to choose from.
            driver.findElement(By.xpath("//input[@id='input-quantity']")).clear();
            sendTextToElement(By.xpath("//input[@id='input-quantity']"), "1");

            //2.8 Click on “Add to Cart” button
            clickOnElement(By.xpath("//button[@id='button-cart']"));

            //2.9 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
            String expMessageS = "Success: You have added HP LP3065 to your shopping cart!";
            String actMessageS = getTextFromElement(By.xpath("//div[@class = 'alert alert-success alert-dismissible']")).substring(0, 56);
            Assert.assertEquals("Not correct text", expMessageS, actMessageS);

            Thread.sleep(5000);
            //2.10 Click on link “shopping cart” display into success message
            clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));

            //2.11 Verify the text "Shopping Cart"
            String expMessage1 = "Shopping Cart";
            String actMessage1 = getTextFromElement(By.xpath("//h1[contains(text(), 'Shopping Cart')]")).substring(0, 13);
            Assert.assertEquals("Not correct text", expMessage1, actMessage1);

            //2.12 Verify the Product name "HP LP3065"

            String expProductName = "HP LP3065";
            String actProductName = getTextFromElement(By.xpath("(//a[text()='HP LP3065'])[2]"));
            Assert.assertEquals("Not correct text", expProductName, actProductName);

            //2.13 Verify the Delivery Date "2022-11-30"
            String expTextDate = "2022-11-30";
            String actTextDate = getTextFromElement(By.xpath("(//small[contains(text(), '2022-11-30')])[2]")).substring(15, 25);
            Assert.assertEquals("Not correct Text", expTextDate, actTextDate);
            //assertAssert("Not Matching", expTextDate, actTextDate);

            //2.14 Verify the Model "Product21"
            String expTextModel = "Product 21";
            String actTextModel = getTextFromElement(By.xpath("//td[contains(text(),'Product 21')]"));
            Assert.assertEquals("Not correct Text", expTextModel, actTextModel);

            //assertAssert("Not Matching", expTextModel, actTextModel);

            //2.15 Verify the Total "£74.73"
            clickOnElement(By.xpath("//form[@id='form-currency']"));
            clickOnElement(By.xpath("//button[@name='GBP']"));
            String expTextTotal = "£74.73";
            String actTextTotal = getTextFromElement(By.xpath("(//td[@class='text-right' and text()='£74.73'])[5]"));
            Assert.assertEquals("Not Matching", expTextTotal, actTextTotal);

        }

        @After
            public void tearDown(){
                closeBrowser();
        }
}
