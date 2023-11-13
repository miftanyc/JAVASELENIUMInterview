package first50;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class O32_HandlingHiddenElement {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.letskodeit.com/practice");

        //First Hide the element
        driver.findElement(By.cssSelector("input[id='hide-textbox'][value='Hide']")).click();
        synchronized (driver){
            driver.wait(2000);
        }
        driver.findElement(By.cssSelector("input[id='show-textbox'][value='Show']")).click();

        //Second Type the text in the box
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("document.getElementById('displayed-text').value='this is test'");

        //Third show the text box again
        driver.findElement(By.cssSelector("input[id='show-textbox'][value='Show']")).click();

        Thread.sleep(5000);
        driver.close();
    }
}
