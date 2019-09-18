package WithoutFindElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainProgramtoCallMethod extends Utils
{

    LoadProps props = new LoadProps();

    @BeforeMethod
    public void commonstep()
    {System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDriver\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximize the browser screen
        driver.manage().window().fullscreen();
        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver.get("https://demo.nopcommerce.com/");
        driver.get(props.getProperty("url"));
    }
    @AfterMethod
    public void closebrowser(){driver.quit();}

    public String generateEmail(String startValue)
    {
        String email = startValue.concat(new Date().toString());
        return email;
    }
    public static String randomDate()
    {
        DateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());
    }
    @Test
    //program for user should be able to login successfully
    public  void userShouldbeAbletoLoginSuccessfully()
    {
        //click on register button
        clickOnElement(By.xpath("//a[@class='ico-register']"));
        //enter first name
        enterText(By.id("FirstName"),props.getProperty("Firstname"));
        //enter last name
        enterText(By.xpath("//input[@name='LastName']"),props.getProperty("LastName"));
        //selecting date of birth
        selectByValue(By.name("DateOfBirthDay"),props.getProperty("dayofbirth"));
        selectByValue(By.name("DateOfBirthMonth"),props.getProperty("monthofbirth"));
        selectByValue(By.name("DateOfBirthYear"),props.getProperty("yearofbirth"));
        //calling method for generating email
        //removing special character from the string
        String s =generateEmail("").replaceAll("[^a-zA-Z0-9]","");
        //enter email
        enterText(By.name("Email"),s+props.getProperty("Email"));
        //enter passwords
        enterText(By.xpath("//input[@data-val-regex-pattern='^.{6,}$']"),props.getProperty("Password"));
        //enter confirm password field
        enterText(By.xpath("//input[@data-val-equalto-other='*.Password']"),props.getProperty("ConfirmPassword"));
        //click on register
        clickOnElement(By.xpath("//input[@class='button-1 register-next-step-button']"));
        String actual = getTextFromElement(By.xpath("//div[@class='result']"));
        String expected = "Your registration completed";
        Assert.assertEquals(actual,expected);
    }

    @Test
    //program for user should be able to refer product to a friend
    public void usershouldbeabletoreferproducttoafriend()
    {  //click on register button
        clickOnElement(By.xpath("//a[@class='ico-register']"));
        //enter first name
        enterText(By.id("FirstName"),props.getProperty("Firstname"));
        //enter last name
        enterText(By.xpath("//input[@name='LastName']"),props.getProperty("LastName"));
        //selecting date of birth
        selectByValue(By.name("DateOfBirthDay"),props.getProperty("dayofbirth"));
        selectByValue(By.name("DateOfBirthMonth"),props.getProperty("monthofbirth"));
        selectByValue(By.name("DateOfBirthYear"),props.getProperty("yearofbirth"));
        //calling method for generating email
        //removing special character from the string
        String s =generateEmail("").replaceAll("[^a-zA-Z0-9]","");
        //enter email
        enterText(By.name("Email"),s+props.getProperty("Email"));
        //enter passwords
        enterText(By.xpath("//input[@data-val-regex-pattern='^.{6,}$']"),props.getProperty("Password"));
        //enter confirm password field
        enterText(By.xpath("//input[@data-val-equalto-other='*.Password']"),props.getProperty("ConfirmPassword"));
        //click on register
        clickOnElement(By.xpath("//input[@class='button-1 register-next-step-button']"));
        String actual = getTextFromElement(By.xpath("//div[@class='result']"));
        String expected = "Your registration completed";
        Assert.assertEquals(actual,expected);
        //click on logo
        clickOnElement(By.xpath("//img[@alt='nopCommerce demo store']"));
        //click on apple macbook pro 13 inch
        clickOnElement(By.xpath("//img[@alt='Picture of Apple MacBook Pro 13-inch']"));
        //click on email friend
        clickOnElement(By.xpath("//input[@value='Email a friend']"));
        //fill up friend's email address details
        enterText(By.xpath("//input[@class='friend-email']"),props.getProperty("friendemail"));
        //enter details in message
        enterText(By.xpath("//textarea[@class='your-email']"),props.getProperty("textarea"));
        //click on send button
        clickOnElement(By.xpath("//input[@class='button-1 send-email-a-friend-button']"));
        String a = getTextFromElement(By.xpath("//div[@class='result']"));
        String e = "Your message has been sent.";
        Assert.assertEquals(a,e);
    }

    @Test
    //program for user should be able to verify that he is navigated to camera and photo page
    public void usershouldbeabletonavigatecameraandphotopag()
    {
        //click on logo
        clickOnElement(By.xpath("//img[@alt='nopCommerce demo store']"));
        //click on Electronics
        clickOnElement(By.linkText("Electronics"));
        //click on camera and photos
        clickOnElement(By.xpath("//a[@title='Show products in category Camera & photo']"));
        String actual = getTextFromElement(By.xpath("//h1[contains(text(),'Camera & photo')]"));
        String expected = "Camera & photo";
        Assert.assertEquals(actual,expected);
    }
    @Test
    //program that user should be able to verify jewelery by filter between $700.00 and $3,000.00 and he is on that filtered page.
    public void usershouldbeabletoselectjewelrybyfilter(){
        //click on logo
        clickOnElement(By.xpath("//img[@alt='nopCommerce demo store']"));
        //click on jewelry
        clickOnElement(By.linkText("Jewelry"));
        clickOnElement(By.xpath("//a[contains(@href,'700-3000')]"));
        //comparing that "$700,00 - $3,000.00 with actual filter value on page
        String s2 = getTextFromElement(By.xpath("//span[contains(@class,'item')]"));
        String expected = "$700.00 - $3,000.00";
        Assert.assertEquals(s2,expected);
        //extracting 700.0 numberic value from $700.00 string value
        String[] s3 = s2.split("-");
        String p = s3[0].substring(1);
        double q = Double.parseDouble(p);
        //extracting 3000.0 numeric value from $3,000.00
        s3[1] = s3[1].replaceAll(" ", "");
        s3[1] = s3[1].replaceAll(",", "");
        String r = s3[1].substring(1);
        double s = Double.parseDouble(r);
        //extracting 2100.0 numeric value from $2,100.00
        String s1 = getTextFromElement(By.xpath("//span[text()='$2,100.00']"));
        s1 = s1.substring(1);
        s1 = s1.replaceAll(",", "");
        double t = Double.parseDouble(s1);
        //checking whether 2100 is falling between 700 and 3000 or not
        Assert.assertTrue(q<s && s>t);
    }

    @Test
    public void usershouldbeabletoaddtwobooksincheckoutbasket()
    {
        //click on logo
        clickOnElement(By.xpath("//img[@alt='nopCommerce demo store']"));
        //click on books
        clickOnElement(By.linkText("Books"));
        //click on book1
        clickOnElement(By.xpath("//img[@alt=\"Picture of Fahrenheit 451 by Ray Bradbury\"]"));
        //click on ADD to cart
        clickOnElement(By.id("add-to-cart-button-37"));
        //delay for 3 seconds for adding items into basket.
        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException e) {e.printStackTrace();}
        //click on books
        clickOnElement(By.linkText("Books"));
        //click on book2
        clickOnElement(By.xpath("//img[@alt=\"Picture of Pride and Prejudice\"]"));
        //click on ADD to cart
        clickOnElement(By.id("add-to-cart-button-39"));
        //click on shopping basket
        clickOnElement(By.xpath("//span[@class='cart-label']"));

        //get text for 1st book
        String r= "FR_451_RB";
        String p = getTextFromElement(By.xpath("//span[text()=\"FR_451_RB\"]"));
        //checking 1st book added into basket
        Assert.assertEquals(p,r);

        //get text for 3rd book
        String s="PRIDE_PRJ";
        String q = getTextFromElement(By.xpath("//span[text()=\"PRIDE_PRJ\"]"));
        //checking 3rd book is added into basket.
        Assert.assertEquals(q,s);
    }
}
