package com.teklab.b7spotify.utilities.webactionutils;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.teklab.b7spotify.base.ui.CPageBase;
import com.teklab.b7spotify.utilities.reportManagers.Log4jManager;



public class DriverUtil  extends CPageBase{
     
	
	
	public static WebDriver starBrowser(String browserName) {
		try {
			if(browserName.equalsIgnoreCase("ie"))
				return startIEBrowser();
			else if (browserName.equalsIgnoreCase("chrome"))
			    return startChromeBrowser();
			else if (browserName.equalsIgnoreCase("firefox"))
				return startFirefoxBrowser();
			else if (browserName.equalsIgnoreCase("edge"))
				return startEdgeBrowser();
			else
				throw new Exception("ERROR!!!You choose'" + browserName
						+"',Currently supporting browser are 'IE,Chrome,Firefox and Edge browser'");
		}catch (Exception ex) {
			Log4jManager.error("something went wrong at startLocalBrowser()");
			ex.printStackTrace();
		}
		return null;
	}
	
	public static WebDriver startBrowserInPrivateMode(String browserName) {
		try {
			if(browserName.equalsIgnoreCase("ie"))
				return startIEBrowser();
			else if (browserName.equalsIgnoreCase("chrome"))
				return  startChromeBrowserInprivateMode();
			else if (browserName.equalsIgnoreCase("firefox"))
				return startFirefoxBrowserInPrivateMode();
			else
				throw new Exception("ERROR!!!You choose'" + browserName
						+"',Currently supporting browser are 'IE,Chrome and Firefox '");
		}catch(Exception ex) {
			Log4jManager.error("something went wrong ata starbrowserinPrivateMode()");
			ex.printStackTrace();
		}
		return null;
	}
	
	public static WebDriver starDockerContainerBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("ie"))
				return startIEBrowser();
			else if(browserName.equalsIgnoreCase("edge"))
				return startEdgeBrowser();
			else if (browserName.equalsIgnoreCase("chrome"))
				return startDockerContainerChromeBrowser();
			else if (browserName.equalsIgnoreCase("firefox"))
				return startDockerContainerFirefoxBrowser();
			else 
				throw new Exception("ERROR!!!You choose'" + browserName
						+"',Currently supporting browser are 'IE,Chrome and Firefox '");
		}catch(Exception ex) {
			Log4jManager.error("something went wrong at starDockerContainerBrowser()");
			ex.printStackTrace();
		}
		return null;
	}
	
	
	private static WebDriver startChromeBrowser() {
		System.out.println("initiating Chrome Driver");
		try {
			String driverpath = System.getProperty("user.dir") + getLocalDriverPath("chromedriver");
			System.setProperty("webdriver.chrome.driver", driverpath);
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated Chrome Driver");
		return driver;
	}
	
	
	private static WebDriver startChromeBrowserInprivateMode() {
		System.out.println("Initiating Chrome Driver");
		try {
			String driverpath = System.getProperty("user.dir") + getLocalDriverPath("chromedriver");
			System.setProperty("webdriver.chrome.driver", driverpath);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("incognito");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully initiated chrome driver in private mode");
		return driver;
	}
	
	private static WebDriver startFirefoxBrowser() {
		System.out.println("initiating fire fox driver");
		try {
			String driverpath = System.getProperty("user.dir") + getLocalDriverPath("geckodriver");
			System.setProperty("webdriver.gecko.driver", driverpath);
		
			
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated firefox Driver");
		return driver;
			
	}
	
	private static WebDriver startFirefoxBrowserInPrivateMode() {
		System.out.println("Initiating Firefox Driver");
		try {
			String driverpath = System.getProperty("user.dir") + getLocalDriverPath("geckodriver");
			System.setProperty("webdriver.gecko.driver", driverpath);
		    DesiredCapabilities caps = new DesiredCapabilities();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
			caps.setCapability("moz:firefoxOptions", options);
			driver = new FirefoxDriver(caps);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully initiated firefox driver in private mode");
		return driver;
	}
	
	private static WebDriver startDockerContainerChromeBrowser() {
		System.out.println("Initiating Chrome Driver in Docker Container");
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName(BrowserType.CHROME);
			cap.setPlatform(Platform.LINUX);
			cap.setVersion("");
			cap.setCapability("timeoutInSeconds", 600);
			cap.setCapability("keep_alive", true);
			
			driver = new RemoteWebDriver (new URL("http://192.168.99.100:4444/wd/hub"),cap);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated Chrome Driver in Docker Container");
		return driver;
	}
	
	private static WebDriver startDockerContainerFirefoxBrowser() {
		System.out.println("Initiating firefox driver in docker container");
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			
			cap.setPlatform(Platform.LINUX);
			cap.setVersion("");
			cap.setCapability("timeoutInSeconds", 300);
			cap.setCapability("keep_alive", true);
			
			driver = new RemoteWebDriver (new URL("http://192.168.99.100:4444/wd/hub"),cap);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated firefox Driver in Docker Container");
		return driver;
	}
	
	private static WebDriver startEdgeBrowser() {
		System.out.println("Initiating Edge Driver");
		try {
			String driverpath = System.getProperty("user.dir") + getLocalDriverPath("msedgedriver");
			System.setProperty("webdriver.edge.driver", driverpath);
			driver = new EdgeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated edge Driver");
		return driver;
		}
	
	private static WebDriver startIEBrowser() {
		System.out.println("Initiating IE Driver");
		try {
			String driverpath = System.getProperty("user.dir") + getLocalDriverPath("IEDriverServer");
			System.setProperty("webdriver.ie.driver", driverpath);
			driver = new InternetExplorerDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		Log4jManager.info("Successfully Initiated IE Driver");
		return driver;
		}
	
	private static String getLocalDriverPath(String driverinfo) {
		return "/src/test/resources/drivers/" + driverinfo+".exe";
	}
	}
	
