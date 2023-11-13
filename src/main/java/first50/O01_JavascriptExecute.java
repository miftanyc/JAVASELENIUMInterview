package first50;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class O01_JavascriptExecute {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().minimize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

        driver.get("https://omayo.blogspot.com/");

        String mainWindow = driver.getWindowHandle();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('alert1').click();");
        Thread.sleep(3000);
        driver.switchTo().alert().accept();

        driver.switchTo().window(mainWindow);
        WebElement textBox = driver.findElement(By.id("ta1"));
        js.executeScript("arguments[0].scrollIntoView()",textBox);
        js.executeScript("document.getElementById('ta1').value='<<<<< This text is written to test javascript execution >>>>>>';");

        Thread.sleep(3000);
        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");

        Thread.sleep(3000);

        js.executeScript("window.scrollTo(0, 600)"); // 0 in x Axis (Horizontal), 600 in Y axis (Vertical);

        Thread.sleep(3000);
        String title = (String) js.executeScript("return document.title");// by casting String to executeScript we can get string return, otherwise executeScript return object.
        System.out.println("Javascript captured page title: "+title);
    }

}
