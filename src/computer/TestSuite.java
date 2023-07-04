package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {


    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

       @Test

    public void verifyProductArrangeInAlphaBaticalOrder() {

           //1.1 Click on Computer Menu
           clickOnElement(By.xpath("//a[text()='Computers ']"));

           //1.2 Click on Desktop
           clickOnElement(By.linkText("Desktops"));

           //1.3 Select Sort By position "Name: Z to A"
           WebElement dropDown = dropDownMenu(By.name("products-orderby"));
           Select select = new Select(dropDown);
           select.selectByVisibleText("Name: Z to A");

           //1.4 Verify the Product will arrange in Descending order.
           List<WebElement> beforeFilterNameZtoAList = driver.findElements(By.xpath("item-grid"));
           List<Double> beforeFileNameZtoAList= new ArrayList<>();
           for(WebElement nameZtoA : beforeFilterNameZtoAList)
           {
               beforeFileNameZtoAList.add(Double.valueOf(nameZtoA.getText().replace("$" , "")));
           }

           select.selectByVisibleText("Name: Z to A");

           List<WebElement> afterFilterNameZtoAList = driver.findElements(By.xpath("item-grid"));
           List<Double>afterFileNameZtoAList = new ArrayList<>();
           for(WebElement nameZtoA : afterFilterNameZtoAList)
           {
               afterFileNameZtoAList.add(Double.valueOf(nameZtoA.getText().replace("$" , "")));
           }

           Collections.sort(beforeFileNameZtoAList);
           Assert.assertEquals(beforeFilterNameZtoAList,afterFilterNameZtoAList);

       }


    @Test

    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        //2.1	Click on Computer Menu.
        selectMenu("Computers");


        //2.2	Click on Desktop
        clickOnElement(By.xpath("//a[text()=' Desktops ']"));

        //2.3	Select Sort By position "Name: A to Z"

        selectByVisibleTextFromDropDown(By.name("products-orderby"), "Name: A to Z");
        Thread.sleep(1000);

        // 2.4 Click on "Add To Cart"
        Thread.sleep(2000);
        clickOnElement(By.xpath("//div[@class='product-item']/div[2]/div[3]/div[2]/button[1]"));

        // 2.5 Verify the Text "Build your own computer"
        String expectedMessage = "Build your own computer";
        String actualMessage = driver.findElement(By.xpath("//h1[contains(text(),'Build your own computer')]")).getText();
        // String actualMessage = getTextFromElement(By.linkText("Build your own computer"));
        Assert.assertEquals("Error Message Display",actualMessage,expectedMessage);

        //2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByVisibleTextFromDropDown(By.id("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");

        //2.7.Select "8GB [+$60.00]" using Select class
        selectByVisibleTextFromDropDown(By.id("product_attribute_2"),"8GB [+$60.00]");

        //2.8 Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));

        //2.9 Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));

        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"


        clickOnElement(By.id("product_attribute_5_10"));
        clickOnElement(By.id("product_attribute_5_12"));

        //2.11 Verify the price "$1360.00"
        String expectedPrice = "$1,460.00";
        String actualPrice =  driver.findElement(By.id("price-value-1")).getText();

        //checking actual and expected result
        Assert.assertEquals("",expectedPrice,actualPrice);

        //2.12 Click on "ADD TO CARD" Button.
        clickOnElement(By.id("add-to-cart-button-1"));

        //2.13 Verify the Message "The product has been added to your shopping cart" on Top
        //green Bar

        String expectedMessage2  ="The product has been added to your shopping cart";
        String actualMessage2 = getTextFromElement(By.xpath("//p[@class='content']"));

        //checking actual and expected result
        //Thread.sleep(2000);
        Assert.assertEquals(expectedMessage2,actualMessage2);
        //close the bar clicking on the cross button
        clickOnElement(By.xpath("//span[@class='close']"));

        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button
        WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
        WebElement goToCart = driver.findElement(By.xpath("//button[normalize-space()='Go to cart']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCart).build().perform();
        goToCart.click();

        //2.15 Verify the message "Shopping cart"
        String expectedText = "Shopping cart";
        String actualText = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        Assert.assertEquals("", expectedText, actualText);

        //  2.16	Change the Qty to "2" and Click on "Update shopping cart"

        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();
        sendTextToElement(By.xpath("//input[@class='qty-input']"),"2");
        clickOnElement(By.xpath("//button[@id='updatecart']"));

        //  2.17	Verify the Total"$2,950.00"
        String expectedValue = "$2,850.00";
        Thread.sleep(5000);
        String actualValue = getTextFromElement(By.xpath("//span[@class='value-summary']"));
        // Assert.assertEquals("", expectedValue, actualValue);

        //  2.18	click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //   2.19	Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //   2.20	Verify the Text “Welcome, Please Sign In!”
        String expectedText2 = "Welcome, Please Sign In!";
        String actualText2 = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        Assert.assertEquals("", expectedText2, actualText2);

        // 2.21Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));

        // 2.22 Fill the all mandatory field
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Prime");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Testing");
        sendTextToElement(By.xpath("//input[@type='email']"), "Primetesting@gmail.com");
        sendTextToElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "India");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "22, Portland Street");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "NW2 3UU");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07123456988");

        // 2.23	Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        // 2.24	Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));

        //2.25	Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //2.26	Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));


        //2.27	Select “Master card” From Select credit card dropdown
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");

        // 2.28	Fill all the details
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Mr John Smith");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5521573041475125");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "05");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2025");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "123");

        // 2.29	Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));


        // 2.30 Verify “Payment Method” is “Credit Card”
        String expectedText4 = "Credit Card";
        String actualText4 = getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]"));
        Assert.assertEquals("", expectedText4, actualText4);

        // 2.31	Verify “Shipping Method” is “Next Day Air”
        String expectedText5 = "Next Day Air";
        String actualText5 = getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']"));
        Assert.assertEquals("", expectedText5, actualText5);


        // 2.32	Verify Total is “$2,950.00”
        String expectedValue1 = "$2,940.00";
        Thread.sleep(5000);
        String actualValue1 = getTextFromElement(By.xpath("//span[@class='value-summary']"));
        Assert.assertEquals("", expectedValue, actualValue);

        //2.33	Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        // 2.34	Verify the Text “Thank You”
        String expectedText6 = "Thank you";
        String actualText6 = getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]"));
        Assert.assertEquals("", expectedText6, actualText6);

        // 2.35	Verify the message “Your order has been successfully processed!”
        String expectedText7 = "Your order has been successfully processed!";
        String actualText7 = getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
        Assert.assertEquals("", expectedText7, actualText7);


        // 2.36	Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        // 2.37 Verify the text “Welcome to our store”
        String expectedText8 = "Welcome to our store";
        String actualText8 = getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        Assert.assertEquals("", expectedText8, actualText8);

    }
    @After
    public void tearDown(){
    closeBrowser();
    }


}














