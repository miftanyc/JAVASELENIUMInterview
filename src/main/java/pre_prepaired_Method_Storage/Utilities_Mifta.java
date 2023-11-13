package pre_prepaired_Method_Storage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class Utilities_Mifta {


 /*--------------------------------------------------------------------------------------------*/
    //Explicit Wait Mechanism
    public static WebElement explicitWaitElementToBeClickable(WebDriver driver, WebElement webelement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webelement));
        return element;
    }

    public static WebElement explicitWaitVisibilityOfElement(WebDriver driver, WebElement webelement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.visibilityOf(webelement));
        return element;
    }



/*--------------------------------------------------------------------------------------------*/
    //Upload File Method
    public static void uploadFile(WebDriver driver, WebElement webelement, String filepath){
        webelement = explicitWaitElementToBeClickable(driver, webelement);
        webelement.sendKeys(filepath);
    }



/*-------------------------------------------------------------------------------------------*/
    //Download File Method
    public static ChromeOptions chromeOptionsReturn(String downloadFilepath){
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);   //Chrome Preference to Disable Popup
        chromePrefs.put("download.default_directory", downloadFilepath); //Chrome Preference to Change Download Folder into Project

        //Apply The Preferences to ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs",chromePrefs);        //Set The Preference to confirm
        return options;
    }


    public static void wait_For_Download_To_Be_Completed_And_Verify_Download_Completion(String downloadFilepath, int dynamicTimeoutInSeconds, int pollingIntervalMillis) {
        Path downloadDir = FileSystems.getDefault().getPath(downloadFilepath);
        long endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(dynamicTimeoutInSeconds);

        String downloadingExtension = ".crdownload"; // Temporary download extension

        try {
            while (System.currentTimeMillis() < endTime) {
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(downloadDir)) {
                    for (Path entry : stream) {
                        String fileName = entry.getFileName().toString();

                        // Check if the file is no longer being actively written to
                        if (!fileName.endsWith(downloadingExtension) &&
                                (System.currentTimeMillis() - Files.getLastModifiedTime(entry).toMillis() > pollingIntervalMillis)) {
                            System.out.println("File download completed: " + fileName);

                            // Delete The Downloaded File
                            delete_Downloaded_File(downloadFilepath, fileName);

                            return; // Download is complete
                        }
                    }
                }

                Thread.sleep(pollingIntervalMillis);
            }

            System.out.println("Timeout waiting for download to complete.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void delete_Downloaded_File(String downloadFilepath, String fileName){
        // Specify the file path you want to delete
        String filePath =downloadFilepath+"\\"+fileName; // Replace with the actual file path

        // Create a File object for the file you want to delete
        File file = new File(filePath);

        // Check if the file exists before attempting to delete
        if (file.exists()) {
            // Attempt to delete the file
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }


/*---------------------------------------------------------------------------------------*/


}
