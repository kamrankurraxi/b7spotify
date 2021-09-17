 package com.teklab.b7spotify.utilities.reportManagers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.teklab.b7spotify.utilities.apiutils.Endpoint;

public class TestListener extends TestListenerAdapter {
	protected static WebDriver driver;// is used in uitestbase and Cpagebase
	
	protected static Endpoint apiHelper ;//will use this in the futuere
	
	//Exten report, we could set htmlreporter and extent to static to avoid garbage collection
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	//we created an extentTest referencs as "logger" . recommend calling it report
	public static ExtentTest logger;
	public static Calendar cal = Calendar.getInstance();
	//public static long time = cal.getTimeInMillis();
	public static String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddyyy_HHmmss_SSS"));
	
	public void onExtentSetup()
	{
		//initialize the HtmlReporter,initialize ExtentReports and attach the HtmlReporter
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/extentReports/myExtentReport"+ time +".html");
	//	htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/src/test/resource/extent-config.xml/");
	//  if you want to add configuration file ,use the code above and provide extent-config.xml
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		//to add system or environment info by using the setSysteminfo method
		extent.setSystemInfo("Host Name", "Windows ");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("User Name","Kamran");
		
		//configuration items to change the look and feel 
		//add content.mange tests etc
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setJS("js-string");
		htmlReporter.config().setDocumentTitle("Test Automation Report");//webpage title
		htmlReporter.config().setReportName("Kamran Extent Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().enableTimeline(true);
		htmlReporter.config().setTimeStampFormat("MMM DD,yyyy HH:mm:ss");
	}
	
	public void onTestStart(ITestResult result) {
		String TestCaseID =result.getName();
		Log4jManager.info("******************************");
		Log4jManager.info("Test Case " +TestCaseID + "Started");
		
	}
	public void onTestSuccess(ITestResult result) {
		String TestCaseID = result.getName();
		Log4jManager.info("Test Case :" + TestCaseID +"Passed");
		logger.getModel().setEndTime(getTime(result.getEndMillis()));
		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"Test Case Failed",ExtentColor.GREEN));
			
		}
		//MarkupHelper class is used to create labels like 'passed'/'failed' and also to give colors
		//to the different status in the test report using ExtentColor enum.
	}
	public void onTestFailure(ITestResult result) {
		String TestCaseID = result.getName();
		Log4jManager.info("Test Case:" + TestCaseID +" Failed");
		logger.getModel().setEndTime(getTime(result.getEndMillis()));
		if(result.getStatus() == ITestResult.FAILURE&& driver!=null) {
			//MarkupHelper is used to display the output in different colors 
			logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName()+"Test Case Failed",ExtentColor.RED));
			//TO ADD screenshot report
			String screenshotPath;
			try {
				   screenshotPath = getScreenShot(driver,result.getName());
				   logger.fail("Test Case Failed Snapshot is below" + logger.addScreenCaptureFromPath(screenshotPath));
			}catch(IOException e) {
				e.printStackTrace();
			}
								
			}
	}
	public void onTestSkipped(ITestResult result) {
		String TestCaseID = result.getName();
		Log4jManager.info("Test Case: " +TestCaseID + " Skipped");
		if(result.getStatus()==ITestResult.SKIP) {
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() +"Test Case Skipped ", ExtentColor.ORANGE));
		}
}
				private String getScreenShot(WebDriver driver , String screenshotName) throws IOException {
					String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					TakesScreenshot ts = (TakesScreenshot)driver;
					File source = ts.getScreenshotAs(OutputType.FILE);
					
					String destination = System.getProperty("user.dir")+"/Screenshots/"+ screenshotName + dateName + ".png";
					File finalDestination = new File(destination);
					FileUtils.copyFile(source,finalDestination);
					return destination;
				}
	   private Date getTime(long millis) {
		   cal.setTimeInMillis(millis);
		   return cal.getTime();
	   }
 	
}
