	package com.teklab.b7spotify.tests.ui.regression.register;

import org.testng.annotations.Test;

import com.teklab.b7spotify.base.ui.UITestBase;
import com.teklab.b7spotify.pages.ui.c_pages.registerModule.SporityPage;

public class SignUpTest extends UITestBase{
	 
@Test
  //go to spority().clickSignUp().enterllinfo().verify	
        public void sporityTestCase() {
  
	logger = extent.createTest("spority test case 1");
	new SporityPage().goToSignUpPage().enterSignInfo().verifyUrl();
        }



        public void sporityTestCase2() {
        	
        }
	
}
