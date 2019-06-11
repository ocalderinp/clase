import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestClase {

    String user = "John Doe";
    String password = "ThisIsNotAPassword";
    WebDriver driver;
    WebDriverWait wait;
    SoftAssert softAssert;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver" , "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://katalon-demo-cura.herokuapp.com/#appointment");
        wait = new WebDriverWait(driver, 10);
        softAssert = new SoftAssert();
    }

    @Test (priority = 0)
    public void testLogin(){
        WebElement mAppointment = driver.findElement(By.id("btn-make-appointment"));
        mAppointment.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));

        WebElement name = driver.findElement(By.id("txt-username"));
        name.sendKeys(user);

        WebElement pass = driver.findElement(By.id("txt-password"));
        pass.sendKeys(password);

        WebElement btnLogin = driver.findElement(By.id("btn-login"));
        btnLogin.click();

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Make Appointment')]")));
        Assert.assertTrue(driver.findElement(By.id("appointment")).isDisplayed());

    }

    @Test (priority = 1)
    public void testMakeAppointment(){

        WebElement facility = driver.findElement(By.id("combo_facility"));
        Select facility_select = new Select(facility);
        facility_select.selectByValue("Hongkong CURA Healthcare Center");

        WebElement check = driver.findElement(By.id("chk_hospotal_readmission"));
        check.click();

        WebElement combo = driver.findElement(By.id("radio_program_none"));
        combo.click();

        WebElement date = driver.findElement(By.id("txt_visit_date"));
        date.sendKeys("20/09/2019");

        WebElement comment = driver.findElement(By.id("txt_comment"));
        comment.sendKeys("Bla bla bla bla");

        WebElement btnMAppointment = driver.findElement(By.id("btn-book-appointment"));
        btnMAppointment.click();

        Assert.assertTrue(true);
        Assert.assertFalse(false);

        Assert.assertTrue(driver.findElement(By.tagName("h2")).isDisplayed());
        softAssert.assertEquals(driver.findElement(By.id("facility")).getText(),"Hongkong CURA Healthcare Center");
        softAssert.assertEquals(driver.findElement(By.id("hospital_readmission")).getText(),"Yes");
        softAssert.assertEquals(driver.findElement(By.id("program")).getText(),"None");
        softAssert.assertEquals(driver.findElement(By.id("visit_date")).getText(),"20/09/2019");
        softAssert.assertEquals(driver.findElement(By.id("comment")).getText(),"Bla bla bla bla");
        softAssert.assertAll();

    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
