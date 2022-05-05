package com.teklab.b7spotify.base.ui;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.teklab.b7spotify.utilities.datamanagers.ExcelManager;
import com.teklab.b7spotify.utilities.datamanagers.JsonManager;
import com.teklab.b7spotify.utilities.reportManagers.TestListener;
import com.teklab.b7spotify.utilities.webactionutils.DriverUtil;



public class UITestBase extends TestListener {
	//protected static WebDriver driver;

	
	protected static String myjsonfilePath = System.getProperty("user.dir")
	            + "/src/test/resources/testData/teklabRegression.json"; 
	protected static JsonManager json = new JsonManager (myjsonfilePath);	
	
	protected static ExcelManager xlread = new ExcelManager(); 
	
	
	
	@BeforeMethod
	public void beforeAllTestCase() {
		onExtentSetup();
	}
	@AfterMethod
	public void afterAllTestCase() {
		//write the report to output view  
		extent.flush();
	}
	@BeforeMethod
	public void beforeEachTestCase() {
		String browser = System.getProperty("browser");
		driver = DriverUtil.starBrowser("chrome");
      //   driver = DriverUtil.startBrowserInPrivateMode(browser);
		//driver = DriverUtil.starDockerContainerBrowser(browser);
	}
	

	
	

	@AfterMethod
	public static void closeBrowser() {
        driver.close();
		driver.quit();
	}
	
	
	
//	@BeforeMethod
//	public static void startChrome() {
//		String driverpath = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe";
//		System.setProperty("webdriver.chrome.driver", driverpath);
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//	}
//		
//		public void pause(int seconds) {
//		try {
//			Thread.sleep(seconds * 1000);// pause your application for two second
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void highLight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
		try {
			Thread.sleep(2000);// pause your application for two second
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
