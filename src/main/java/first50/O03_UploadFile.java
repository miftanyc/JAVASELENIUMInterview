package first50;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pre_prepaired_Method_Storage.Utilities_Mifta;

import java.time.Duration;

public class O03_UploadFile {

    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://davidwalsh.name/demo/multiple-file-upload.php");

        WebElement uploadButton = Utilities_Mifta.explicitWaitElementToBeClickable(driver, driver.findElement(By.cssSelector("input#filesToUpload")));

        Utilities_Mifta.uploadFile(driver, uploadButton, System.getProperty("user.dir")+"/upload/Test_Information.txt");

    }
}
