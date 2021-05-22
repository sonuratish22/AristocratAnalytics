package com.aristo.analytics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReadFile {
    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final String USER_LOCAL_SYSTEM_PATH = "C:\\Users\\SK101865\\Perforce\\Sonu.Kumar_W70238_main_1692";
    private static final String GAME_NAME = "5Dragons_Rising";
    private static final String DOCUMENTS_ANALYTICS = "Documents\\Analytics";
    private static final String FILE_PATH_PREFIX = "file:///";
    private static final By CORE_MEAN_SELECTOR = By.xpath("/html/body/table[2]/tbody/tr[2]/td[2]");
    public static final By totalRTP = By.xpath("/html/body/table[2]/tbody/tr[2]/td[3]");
    public static final By FREEGAMERTP = By.xpath("/html/body/table[4]/tbody/tr[1]/td[3]");
    public static final By PMJACKPOTRTP = By.xpath("/html/body/table[9]/tbody/tr[1]/td[3]");
    public static final By GRANDJACKPOTRTP = By.xpath("/html/body/table[13]/tbody/tr[1]/td[3]");
    public static final By MAJORJACKPOTRTP = By.xpath("/html/body/table[17]/tbody/tr[1]/td[3]");
    public static final By MINORJACKPOTRTP = By.xpath("/html/body/table[21]/tbody/tr[1]/td[3]");
    public static final By MINIJACKPOTRTP = By.xpath("/html/body/table[25]/tbody/tr[1]/td[3]");
    public static final By BASEGAMERTP = By.xpath("/html/body/table[28]/tbody/tr[1]/td[3]");

    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("Browser launched Successfully");
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }

   /* @Test
    public void loadOneHTMLPageTest(){
        driver.get("file:///C:/Users/SK101865/Perforce/Sonu.Kumar_W70238_main_1692/5Dragons_Rising/Documents/Analytics/Var01/3x5/Var01_3x5_10FG_Bet3.html");
        String mean = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td[2]")).getText();
        System.out.println(mean);
        String meanValue = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td[3]")).getText();
        System.out.println(meanValue);
    }*/

    @Test
    public void readAllFilesTest(){
        System.out.println("i____________");
        Map<String, String> fileNameVsPath = getAllFileNameVsPath();
        System.out.println(fileNameVsPath);
        StringBuilder sb = new StringBuilder("FileName, FilePath, baseGameRTP, freeGameRTP Mean, grandJackpotRTP, majorJackpotRTP, minorJackpotRTP, miniJackpotRTP,pmJackpotRTP ,TotalRTP Mean ").append(NEW_LINE);
        //StringBuilder sb = new StringBuilder("FileName, FilePath, TotalRTP Mean, freeGameRTP Mean, pmJackpotRTP, grandJackpotRTP, majorJackpotRTP, minorJackpotRTP, miniJackpotRTP, baseGameRTP ").append(NEW_LINE);
        Set<Map.Entry<String, String>> entries = fileNameVsPath.entrySet();
        for(Map.Entry<String, String> entry : entries){
            String fileName = entry.getKey();
            String filePath = entry.getValue();

            System.out.println("loading fileName="+fileName);

            driver.get(FILE_PATH_PREFIX +filePath);
            String TotalRTPValue = driver.findElement(totalRTP).getText();
           // System.out.println(TotalRTPValue);
            String freeGameRTP = driver.findElement(FREEGAMERTP).getText();
            String pmJackpotRTP = driver.findElement(PMJACKPOTRTP).getText();
            String grandJackpotRTP = driver.findElement(GRANDJACKPOTRTP).getText();
            String majorJackpotRTP = driver.findElement(MAJORJACKPOTRTP).getText();
            String minorJackpotRTP = driver.findElement(MINORJACKPOTRTP).getText();
            String miniJackpotRTP = driver.findElement(MINIJACKPOTRTP).getText();
            String baseGameRTP = driver.findElement(BASEGAMERTP).getText();

           // sb.append(String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s,", fileName, filePath, TotalRTPValue, freeGameRTP, pmJackpotRTP, grandJackpotRTP, majorJackpotRTP, minorJackpotRTP, miniJackpotRTP, baseGameRTP,  ""));
            sb.append(String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s,", fileName, filePath, baseGameRTP, freeGameRTP,  grandJackpotRTP, majorJackpotRTP, minorJackpotRTP, miniJackpotRTP, pmJackpotRTP, TotalRTPValue, ""));

            sb.append(NEW_LINE);
        }

        String csvFileContent = sb.toString();
        System.out.println("-----printing csvFileContent------");
        System.out.println(csvFileContent);

        try {
            Files.writeFile(csvFileContent, new File(USER_LOCAL_SYSTEM_PATH + File.separator + GAME_NAME + File.separator + "analytics_data.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Map<String, String> getAllFileNameVsPath() {
        String filePath = USER_LOCAL_SYSTEM_PATH + File.separator + GAME_NAME + File.separator + DOCUMENTS_ANALYTICS;
        File file = new File(filePath);
        Map<String, String> fileNameVsPath = new HashMap<>();
        traverseAllFiles(file, fileNameVsPath);
        return fileNameVsPath;
    }

    private void traverseAllFiles(File file, Map<String, String> fileNameVsPath){
        File[] files = file.listFiles();
        for (File f : files){
            if(f.isDirectory()){
                //go inside the directory recursively
                traverseAllFiles(f, fileNameVsPath);
            }else{
                //read the file
               // System.out.println("fileName=" + f.getName());
                //System.out.println(f.getAbsolutePath());
                fileNameVsPath.put(f.getName(), f.getAbsolutePath());
            }
        }
    }

}
