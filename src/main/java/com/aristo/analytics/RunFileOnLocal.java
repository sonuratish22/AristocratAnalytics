package com.aristo.analytics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RunFileOnLocal {

    public static final By totalRTP = By.xpath("/html/body/table[2]/tbody/tr[2]/td[3]");
    public static WebDriver driver;
    public static final String filePath = "C:/Users/SK101865/Desktop/Games/5DragonRisingJP/Analytics";
    public static final String FILE_PATH_PREFIX = "file:///";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("Browser Launched Successfully");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        System.out.println("Browser closed Successfully");
    }


    @Test
    public void readAllFiles() throws InterruptedException {
        Map<String, String> fileNameVsPath = getAllFileNameVsPath();
        System.out.println(fileNameVsPath);
        Set<Map.Entry<String, String>> entries = fileNameVsPath.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String fileName = entry.getKey();
            String filePath = entry.getValue();
            driver.get(FILE_PATH_PREFIX + filePath);
            String TotalRTPValue = driver.findElement(totalRTP).getText();
            String freeGameRTP = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr[1]/td[3]")).getText();
            String pmJackpotRTP = driver.findElement(By.xpath("/html/body/table[9]/tbody/tr[1]/td[3]")).getText();
            String grandJackpotRTP = driver.findElement(By.xpath("/html/body/table[13]/tbody/tr[1]/td[3]")).getText();
            String majorJackpotRTP = driver.findElement(By.xpath("/html/body/table[17]/tbody/tr[1]/td[3]")).getText();
            String minorJackpotRTP = driver.findElement(By.xpath("/html/body/table[21]/tbody/tr[1]/td[3]")).getText();
            String miniJackpotRTP = driver.findElement(By.xpath("/html/body/table[25]/tbody/tr[1]/td[3]")).getText();
            String baseGameRTP = driver.findElement(By.xpath("/html/body/table[28]/tbody/tr[1]/td[3]")).getText();
            System.out.println("filePath" + filePath + "," + "fileName"+fileName + "," + "TotalRTPValue" + TotalRTPValue + "," + "freeGameRTP:" + freeGameRTP + "," + "pmJackpotRTP:" + pmJackpotRTP + "," + "grandJackpotRTP:" + grandJackpotRTP + "," + "majorJackpotRTP" + majorJackpotRTP + "," + "minorJackpotRTP" + minorJackpotRTP + "," + "miniJackpotRTP" + miniJackpotRTP + "," + "baseGameRTP" + baseGameRTP);
        }
    }

        private Map<String, String> getAllFileNameVsPath () {
            File file = new File(filePath);
            Map<String, String> fileNameVsPath = new HashMap<>();
            transverseAllFiles(file, fileNameVsPath);
            return fileNameVsPath;
        }

        //Use here as recersive else it will take lots of loop to read the data.
        public void transverseAllFiles (File file, Map < String, String > fileNameVsPath){
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    transverseAllFiles(f, fileNameVsPath);
                } else {
                    fileNameVsPath.put(f.getName(), f.getAbsolutePath());
                }
            }
        }
}








