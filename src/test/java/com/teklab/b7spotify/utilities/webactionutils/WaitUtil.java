package com.teklab.b7spotify.utilities.webactionutils;

import org.openqa.selenium.WebDriver;

public class WaitUtil {

	private WebDriver driver ;
	
	public WaitUtil(WebDriver incomingDriver) {
		this.driver = incomingDriver;
		
	}
	
	
	
	
	public void pause(int seconds) {
		try {
			Thread.sleep(seconds * 1000);// pause your application for two second
		} catch (InterruptedException e) {
			e.printStackTrace();
	}
	}
	
}
