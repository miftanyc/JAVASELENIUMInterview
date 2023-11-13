package first50;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class O02_ExplicitWaitForAjaxCall {

    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.manage().window().minimize();

        driver.get("https://omayo.blogspot.com/");
        driver.findElement(By.cssSelector("button[class='dropbtn']")).click();
        WebElement flipKart = driver.findElement(By.linkText("Flipkart"));
        WebElement flipWait = explicitWaitMechanism(driver, flipKart);
        flipWait.click();


    }

    public static WebElement explicitWaitMechanism(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement clickableEle = wait.until(ExpectedConditions.elementToBeClickable(element));
        return clickableEle;
    }
}
