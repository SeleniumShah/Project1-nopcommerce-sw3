package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {

    public void clickOnElement(By by) {
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
        //driver.findElement(by).click(); use always use this 1 line code to avoid repetitive line.
    }

    /**
     * This method will get text from element
     *
     * @param by
     * @return
     */
    public String getTextFromElement(By by) {
        String text = driver.findElement(by).getText();
        return text;
        //return driver.findElement(by).getText(); - We made above 2 line code into one line code.
    }

    /**
     * This method will send text to element
     *
     * @param by
     * @param text
     */

    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    /**
     * create method with name "selectMenu"
     *
     * @param menu
     */

    public void selectMenu(String menu){
        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']/li/a[text() = '" + menu + " ']")).click();

    }

    public void selectMenuAndClick(String menu) {
        WebElement menu1= driver.findElement(By.linkText(menu));
        Actions actions = new Actions(driver);
        actions.moveToElement(menu1).click().build().perform();

    }

    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    public List<WebElement> getOptionsFromDropDown(String elementName) {
        return new Select(driver.findElement(By.name(elementName))).getOptions();
    }

//
public void verifyText(String text,By by){
    String expectedText = text;
    String actualText = getTextFromElement(by);
    Assert.assertEquals(expectedText,actualText);
}

    //This Method will hoover element
    public void mouseHoverOnElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    public void mouseHoverAndClick(By by) {
        Actions actions = new Actions(driver);
        WebElement desktopsTab = driver.findElement(by);
        actions.moveToElement(desktopsTab).click().perform();
    }

    //This method will open dropDown Menu.
    public WebElement dropDownMenu(By by)
    {
        return driver.findElement(by);
    }
}



