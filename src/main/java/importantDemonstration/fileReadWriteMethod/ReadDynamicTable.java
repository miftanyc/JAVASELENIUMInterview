package importantDemonstration.fileReadWriteMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pre_prepaired_Method_Storage.ExcelUtility;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ReadDynamicTable {

    public static <currency> void main(String[] args) throws IOException {

        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        String filepath = "./readableFile/web scarp.xlsx";
        ExcelUtility util = new ExcelUtility(filepath);

        driver.get("https://cosmocode.io/automation-practice-webtable/");
        //table#countries tbody tr:nth-child(3) td:nth-child(2)
        //table#countries tbody tr:nth-child(1) td:nth-child(1)

        // get Row Number
        List<WebElement> rowList = driver.findElements(By.cssSelector("table#countries tbody tr"));
        int rows = rowList.size();

        //Get The Table Header
        String country = driver.findElement(By.cssSelector("table#countries tbody tr:nth-child(1) td:nth-child(2)")).getText();
        String capital = driver.findElement(By.cssSelector("table#countries tbody tr:nth-child(1) td:nth-child(3)")).getText();
        String currency = driver.findElement(By.cssSelector("table#countries tbody tr:nth-child(1) td:nth-child(4)")).getText();
        String primaryLang = driver.findElement(By.cssSelector("table#countries tbody tr:nth-child(1) td:nth-child(5)")).getText();

        //Set Table Header
        util.setCellData("demographic",0, 0,country);
        util.setCellData("demographic",0, 1,capital);
        util.setCellData("demographic",0, 2,currency);
        util.setCellData("demographic",0, 3,primaryLang);

        for(int r=1; r<rows; r++){
            //Get Table Data
            r+=1; //Increment the r value by 1 to increase r value
            String countryData = driver.findElement(By.cssSelector("table#countries tbody tr:nth-child("+r+") td:nth-child(2)")).getText();
            String capitalData = driver.findElement(By.cssSelector("table#countries tbody tr:nth-child("+r+") td:nth-child(3)")).getText();
            String currencyData = driver.findElement(By.cssSelector("table#countries tbody tr:nth-child("+r+") td:nth-child(4)")).getText();
            String primaryLangData = driver.findElement(By.cssSelector("table#countries tbody tr:nth-child("+r+") td:nth-child(5)")).getText();

            //Set Table Data
            r-=1; //Decrement the r value by 1 to decrease r value.
            util.setCellData("demographic", r,0,countryData);
            util.setCellData("demographic", r,1,capitalData);
            util.setCellData("demographic", r,2,currencyData);
            util.setCellData("demographic", r,3,primaryLangData);
        }

        System.out.println("Web-Table Scrapping Completed");
        driver.close();









    }
}
