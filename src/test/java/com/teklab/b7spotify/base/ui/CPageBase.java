package com.teklab.b7spotify.base.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.teklab.b7spotify.utilities.reportManagers.TestListener;
import com.teklab.b7spotify.utilities.webactionutils.ElementActions;
import com.teklab.b7spotify.utilities.webactionutils.WaitUtil;


// helping method utilities method 
public class CPageBase extends TestListener {
	
	//	
    protected	ElementActions weu = new ElementActions(driver);
	protected   WaitUtil bbb = new WaitUtil(driver);
	
	public void pause(int seconds) {
		try {
			Thread.sleep(seconds * 1000);// pause your application for two second
		} catch (InterruptedException e) { 
			e.printStackTrace();
	}
	}
	public void highLight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
		
	}
	public void highLight(By bylocator) {
		WebElement  element  = driver.findElement(bylocator);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
		

	}
	public void typeIn(By bylocator,String text) {
		WebElement ele = driver.findElement(bylocator);
		ele.clear();
		ele.sendKeys(text);
		
	}
}
	