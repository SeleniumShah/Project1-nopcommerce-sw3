package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Utility;

public class Electronics extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully(){

        //1.1 Mouse Hover on “Electronics” Tab
        mouseHoverOnElement(By.xpath("//a[text()='Electronics ']"));

        //1.2 Mouse Hover on “Cell phones” and click
        mouseHoverAndClick(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));

        //1.3 Verify the text “Cell phones”
        verifyText("Cell phones",By.xpath("//h1[contains(text(),'Cell phones')]"));
    }
    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully()throws InterruptedException{

        //2.1 Mouse Hover on “Electronics” Tab
        mouseHoverOnElement(By.xpath("//a[text()='Electronics ']"));

        //2.2 Mouse Hover on “Cell phones” and click
        mouseHoverAndClick(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));

        //2.3 Verify the text “Cell phones”
        verifyText("Cell phones",By.xpath("//h1[contains(text(),'Cell phones')]"));

        //2.4 Click on List View Tab
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));

        //2.5 Click on product name “Nokia Lumia 1020” link
        //Thread.sleep(1000);
        mouseHoverOnElement(By.xpath("//h2[@class='product-title']//a[contains(text(),'Nokia Lumia 1020')]"));
        clickOnElement(By.xpath("//h2[@class='product-title']//a[contains(text(),'Nokia Lumia 1020')]"));

        //2.6 Verify the text “Nokia Lumia 1020”
        verifyText("Nokia Lumia 1020",By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"));

        //2.7 Verify the price “$349.00”
        verifyText("$349.00",By.xpath("//span[contains(text(),'$349.00')]"));

        //2.8 Change quantity to 2
        sendTextToElement(By.id("product_enteredQuantity_20"), Keys.BACK_SPACE + "2");

        //2.9 Click on “ADD TO CART” tab
        clickOnElement(By.id("add-to-cart-button-20"));

        //2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar After that close the bar clicking on the cross button.
        verifyText("The product has been added to your shopping cart",By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        clickOnElement(By.xpath("//span[@title='Close']"));
        Thread.sleep(1000);

        //2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        Thread.sleep(1000);

        //2.12 Verify the message "Shopping cart"
        verifyText("Shopping cart",By.xpath("//h1[contains(text(),'Shopping cart')]"));

        //2.13 Verify the quantity is 2
        verifyText("(2)",By.xpath("//span[contains(text(),'(2)')]"));

        //2.14 Verify the Total $698.00
        verifyText("$698.00",By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"));

        //2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //2.16 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //2.17 Verify the Text “Welcome, Please Sign In!”
        verifyText("Welcome, Please Sign In!",By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));

        //2.18 Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));

        //2.19 Verify the text “Register”
        verifyText("Register",By.xpath("//h1[contains(text(),'Register')]"));

        //2.20 Fill the mandatory fields
        sendTextToElement(By.id("FirstName"),"Prime");
        sendTextToElement(By.id("LastName"),"Patel");
        sendTextToElement(By.id("Email"),"Prime12378@gmail.com");
        sendTextToElement(By.id("Password"),"Prime1234");
        sendTextToElement(By.id("ConfirmPassword"),"Prime1234");

        //2.21 Click on “REGISTER” Button
        clickOnElement(By.id("register-button"));

        //2.22 Verify the message “Your registration completed”
        verifyText("Your registration completed",By.xpath("//div[contains(text(),'Your registration completed')]"));

        //2.23 Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[@class='button-1 register-continue-button']"));

        //2.24 Verify the text “Shopping card”
        verifyText("Shopping cart",By.xpath("//h1[contains(text(),'Shopping cart')]"));

        //2.25 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //2.26 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //2.27 Fill the Mandatory fields
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"),"United Kingdom");
        sendTextToElement(By.id("BillingNewAddress_City"),"London");
        sendTextToElement(By.id("BillingNewAddress_Address1"),"123 Downing Street");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"),"EC157YN");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"),"0135879213");

        //2.28 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.id("shippingoption_2"));

        //2.30 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //2.31 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        //2.32 Select “Visa” From Select credit card dropdown
        selectByVisibleTextFromDropDown(By.id("CreditCardType"), "Visa");

        //2.33 Fill all the details
        sendTextToElement(By.id("CardholderName"), "Mr Prime Patel");
        sendTextToElement(By.id("CardNumber"), "5356 6548 1418 5420");
        selectByVisibleTextFromDropDown(By.id("ExpireMonth"), "12");
        selectByVisibleTextFromDropDown(By.id("ExpireYear"), "2026");
        sendTextToElement(By.id("CardCode"), "456");

        //2.34 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //2.35 Verify “Payment Method” is “Credit Card”
        verifyText("Payment Method:",By.xpath("//span[contains(text(),'Payment Method:')]"));

        //2.36 Verify “Shipping Method” is “2nd Day Air”
        verifyText("Credit Card",By.xpath("//span[contains(text(),'Credit Card')]"));
        verifyText("Shipping Method:",By.xpath("//span[contains(text(),'Shipping Method:')]"));
        verifyText("2nd Day Air",By.xpath("//span[contains(.,'2nd Day Air')]"));

        //2.37 Verify Total is “$698.00”
        verifyText("$698.00",By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"));

        //2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //2.39 Verify the Text “Thank You”
        verifyText("Thank you",By.xpath("//h1[contains(text(),'Thank you')]"));

        //2.40 Verify the message “Your order has been successfully processed!”
        verifyText("Your order has been successfully processed!",By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));

        //2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //2.42 Verify the text “Welcome to our store”
        verifyText("Welcome to our store",By.xpath("//h2[contains(text(),'Welcome to our store')]"));

        //2.43 Click on “Logout” link
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));

        //2.44 Verify the URL is “https://demo.nopcommerce.com/”
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://demo.nopcommerce.com/");
    }

    @After
    public void tearDown(){
        closeBrowser();
    }

}


