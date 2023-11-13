package importantDemonstration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class O33_GetBrokenLink {

    @Test
    public static void linkChecker(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.infinite.com/digital-engineering-services/");
        List<WebElement> links = driver.findElements(By.tagName("a"));
        List<String> goodLinks = new ArrayList<String>();
        List<String> badLinks = new ArrayList<String>();


        for(WebElement link : links){
            String url = link.getAttribute("href");
            System.out.println("----------------------------------");
            System.out.print(url);
            if(url==null || url.isEmpty()){
                System.out.println(" >> URL is Empty");
                continue;
            }

            URL urlObj = null;
            try {
                urlObj = new URL(url);
                String protocol = urlObj.getProtocol();
                System.out.println("URL Protocol: "+ protocol);
                if("http".equals(protocol)||"https".equals(protocol)){
                    HttpURLConnection huc = null;
                    try {
                        huc = (HttpURLConnection) (new URL(url).openConnection());
                        huc.connect();
                        if(huc.getResponseCode()>=400) {  //Coz 400 t0 500 code are for broken link
                            System.out.println("  >>  This URL is Bad");
                            badLinks.add(url);
                        }else{
                            System.out.println("  >>  This Link is Good");
                            //Store the Good link
                            goodLinks.add(url);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Size of Initial Link"+links.size());
        System.out.println("Size Of Final: "+goodLinks.size());


        System.out.println("////////////////////////    Bad Link Printed Below  /////////////////////////////");

        Iterator<String> printBadLink = badLinks.iterator();
        if(printBadLink.hasNext()){
            System.out.println(printBadLink.next());
        }

    }
}
