package importantDemonstration.downloadFileMonitor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pre_prepaired_Method_Storage.Utilities_Mifta;


public class O04_downloadAFile {

    public static void main(String[] args) {

        //Where the file Should Download?
        String downloadFilepath = System.getProperty("user.dir")+"\\download";

        //Call Chrome Options Method from Utilities_Recall Class
        ChromeOptions options = Utilities_Mifta.chromeOptionsReturn(downloadFilepath);

        //Provide the ChromeOption as a parameter
        WebDriver driver = new ChromeDriver(options);

        // Navigate to the webpage where the file is located
        driver.get("https://omayo.blogspot.com/p/page7.html");

        // Find and click the element that initiates the download (replace with your own selector)
        WebElement downloadButton = Utilities_Mifta.explicitWaitElementToBeClickable(driver, driver.findElement(By.linkText("ZIP file")));
        downloadButton.click();

        // Verify Download Complete or Not by waitForDownloadToComplete() and Also Delete the Downloaded File if Download happened Method from Utilities_Recall Class
        Utilities_Mifta.wait_For_Download_To_Be_Completed_And_Verify_Download_Completion(downloadFilepath, 300, 500);

        // Close the WebDriver when done
        driver.quit();

    }

}
