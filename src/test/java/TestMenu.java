import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestMenu {

    WebDriver driver;
    WebDriverWait wait;

    @Test
    public void testMenuHover() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver" , "driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/jqueryui/menu");
        wait = new WebDriverWait(driver, 10);


        WebElement linkEnabled = driver.findElement(By.linkText("Enabled"));
        Actions actions = new Actions(driver);
        actions.moveToElement(linkEnabled).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Downloads")));
        WebElement linkDownload = driver.findElement(By.linkText("Downloads"));
        actions.moveToElement(linkDownload).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("CSV")));
        WebElement linkCsv = driver.findElement(By.linkText("CSV"));
        actions.moveToElement(linkCsv).click().build().perform();
        driver.quit();
    }
}
