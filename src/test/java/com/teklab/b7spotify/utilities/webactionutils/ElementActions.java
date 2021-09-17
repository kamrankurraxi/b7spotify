package com.teklab.b7spotify.utilities.webactionutils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.teklab.b7spotify.utilities.reportManagers.Log4jManager;



public class ElementActions  {
	// put private to limit access
	
	private WebDriver driver;	
	// how to  initialize webdriver object ;
	
	public ElementActions(WebDriver incomingDriver) {
		this.driver = incomingDriver;
		
		
		
	}
	public void pause(int seconds) {
		try {
			Thread.sleep(seconds * 1000);// pause your application for two second   
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void highLight(WebElement element) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element); 
		}catch(Exception e) {
			Log4jManager.error("Exception at highlight element : " + element);
		}
	}
	public void highLight(By bylocator) {
		try {
		WebElement  element  = driver.findElement(bylocator);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
		}catch(Exception e) {
			Log4jManager.error("Exception at highlight element useing by locator : " + bylocator);
		}

	}
	public void typeIn(By bylocator,String text) {
		WebDriverWait wait = new WebDriverWait(driver , 5);
		try {
		     WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
		     ele.clear();
		     ele.sendKeys(text);
		}catch(Exception e) {
			Log4jManager.error("failed to type text to" + bylocator.toString());
		}
	}
	public void typeIn(WebElement  ele,String text) {
		try {
			ele.clear();
			ele.sendKeys(text);
		}catch(Exception e) {
			Log4jManager.error("failed to type text to" + ele.toString());
		}
	}
	
	
	public void click(By bylocator) {
		WebDriverWait wait = new WebDriverWait(driver , 5);
		try {
		    wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator)).click();
		     
		}catch(Exception e) {
			Log4jManager.error("failed to type text to" + bylocator.toString());
		}
	}
	
	public WebElement findBy(By bylocator) {
		WebDriverWait wait = new WebDriverWait(driver , 5);
		WebElement ele = null;
		try {
		      ele = wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
		     return ele;
		}catch(Exception e) {
			Log4jManager.error("failed to type text to" + bylocator.toString());
		}
		return ele;
	}
}
