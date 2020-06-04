package com.hrms.testcases;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;

public class LoginTest extends CommonMethods {
	
//	@BeforeMethod
//	public void openBrowser() {
//		setUp();
//		initialize(); 
//	}
//
//	@AfterMethod
//	public void closeBrowser() {
//		tearDown();
//	}
	
	@Test(groups="smoke")
	public void validAdminLogin() throws InterruptedException {
		wait(10);
		//LoginPageElements login=new LoginPageElements();
		sendText(login.username, ConfigsReader.getProperty("username"));
		sendText(login.password, ConfigsReader.getProperty("password"));
		click(login.LoginBtn);
		wait(2);
		
		//DashBoardPageElements db= new DashBoardPageElements();
		String expectedUsers="Welcome Admin";
		String actualUser=dashboard.welcome.getText();
		Assert.assertEquals(actualUser, expectedUsers, "Admin is NOT logged in");
		Assert.assertTrue(actualUser.contains(ConfigsReader.getProperty("username")));
		
	}
	
	
	@Test(groups="regression")
	public void invalidPasswordLogin() {
		//LoginPageElements login= new LoginPageElements();
		sendText(login.username, ConfigsReader.getProperty("username"));
		sendText(login.password, "uiuguig");
		click(login.LoginBtn);
		
		String expected="Invalid credentials";
		Assert.assertEquals(login.errorMsg.getText(), expected, "Error message text is not matched");
	}
	
	@Test(groups="regression")
	public void emptyUsernameLogin() {
		//LoginPageElements login= new LoginPageElements();
		sendText(login.password, ConfigsReader.getProperty("password"));
		click(login.LoginBtn);
		
		String expected="Username cannot be empty";
		
		Assert.assertEquals(login.errorMsg.getText(), expected, "Error message text is not matched");  
	}
	
	@Test
	public void timeStamp() {
		Date d=new Date();
		System.out.println(d.getTime());
		SimpleDateFormat sdf=new SimpleDateFormat("yyy_MM_dd_HH_mm_ss");
		System.out.println(sdf.format(d.getTime()));
		
	}
}
