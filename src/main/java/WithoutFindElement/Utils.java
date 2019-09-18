package WithoutFindElement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils extends BasePage
{
    public static void clickOnElement(By by){driver.findElement(by).click();}
    public void enterText(By by,String text){driver.findElement(by).sendKeys(text);}
    public  String getTextFromElement (By by)
    {
        String actual = driver.findElement(by).getText();
        return actual;
    }

    public static void waitforClickable(By by,long time)
    {
        WebDriverWait wait  = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitforAlertPresent(long time)
    {
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitforElementVisible(By by,long time)
    {
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void selectByText(By by,String text)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    public static void selectByValue(By by,String text)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(text);
    }


}
