package com.teklab.b7spotify.pages.ui.c_pages.registerModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.teklab.b7spotify.base.ui.CPageBase;
import com.teklab.b7spotify.utilities.reportManagers.Log4jManager;



public class SporityPage extends CPageBase{
	protected static WebDriver driver;
		private String url="https://www.spotify.com/us/signup/";
		
		@FindBy(xpath="//input[@name='displayname']")
		@CacheLookup
		private WebElement fName;
		
		@FindBy(xpath="//input[@name='email']")
		private WebElement Email;
		
		@FindBy(xpath="//input[@name='password']")
	    private WebElement PASSW;
		
	   public  SporityPage() {
		   PageFactory.initElements(driver, this);
	   }
	   
		public SporityPage goToSignUpPage() {
			logger.createNode("user go to sign up page");
		    driver.get(url);
			System.out.println("user go to yahoo sign up");
			Log4jManager.info("user landed on sign up page");
			return this;
			
		}
		public SporityPage enterSignInfo() {
			logger.createNode("user tries to enter all information");
			System.out.println("user enter all sign up infromation");
			enterFirstName();
			enterPassword();
			enterEmail();
			Log4jManager.info("user entered all information");
			
			return this;
		}
	public SporityPage  verifyUrl() {
			
			logger.createNode("user verigying url");
			String expectedURL = "https://www.spotify.com/us/signup/?sp_t_counter=1";
			String actualURL = driver.getCurrentUrl();
			Assert.assertEquals(actualURL, expectedURL);
			Log4jManager.info("go to to the page");
			return this ;
		}

		private void enterEmail() {
			weu.typeIn(Email, "kamrankurraxi");
			
		}

		private void enterPassword() {
			weu.typeIn(PASSW, "111111");
			
		}

		private void enterFirstName() {
			weu.typeIn(fName, "kakakak");
			weu.highLight(fName);
			
		}

		
	}

