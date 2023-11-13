package first50;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class O31_GetHandleVsGetHandles {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://omayo.blogspot.com/");

        String mainWindow = driver.getWindowHandle();
        String mainWindowTitle = driver.getTitle();

        System.out.println(mainWindowTitle);
        System.out.println(mainWindow);

        driver.findElement(By.linkText("Open a popup window")).click();

        Set<String> allWindow = driver.getWindowHandles();
        Iterator<String> itr = allWindow.iterator();
        if(itr.hasNext()){
            String window = itr.next();
            if(!driver.switchTo().window(window).getTitle().contains("omayo (QAFox.com)")){
                    System.out.println(window);
                    driver.switchTo().window(window).close();
            }
        }

    }

}
