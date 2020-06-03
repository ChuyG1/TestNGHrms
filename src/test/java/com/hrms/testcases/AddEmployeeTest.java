package com.hrms.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import com.hrms.utils.ExcelUtility;

public class AddEmployeeTest extends CommonMethods {
	
//	public void openBrowser() {
//		setUp();
//	}
//	
//	@AfterMethod
//	public void closeBrowser() {
//		tearDown();
//	}
	
	@Test(dataProvider = "userDataFromExcel", groups = {"homeword"})
	public void test(String firstName, String lastName, String username, String password) {
		//Syste.out.println("firstName + " " + lastName + " " + username + " " +
		// password
		
		// login into HRMS
		login.login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
		
		// navigate to Add Employee page
		dashboard.navigateToAddEmployee();
		wait(1);
		
		//add employee information
		sendText(addEmp.firstName, firstName);
		sendText(addEmp.lastName, lastName);
		
		// get EmployeeID
		String expectedEmpId=addEmp.employeeId.getAttribute("value");
		 System.out.println(expectedEmpId);
		
		 //click on create login details
		click(addEmp.checkboxLoginDetails);
		wait(1);
		
		sendText(addEmp.username, username);
		sendText(addEmp.password, password);
		sendText(addEmp.confirmpassword, password);
		click(addEmp.saveBtn);
		wait(5);
		
		waitForVisibility(pdetails.lblPersonalDetails);
		String actualEmpId = pdetails.employeeId.getAttribute("value");
		Assert.assertEquals(actualEmpId, expectedEmpId, "Employee ID did not match!");
		
		//take a screenshot
		takeScreenshot(firstName + "_"+ lastName);
	}
	
	@DataProvider(name = "userData")
	public Object[][] getData() {
		Object[][] data = {
				{"Rohani", "Sahki", "rohani123", "AmirKhang_@123"} };
			return data;
		
	}
	
	@DataProvider(name = "userDataFromExcel")
	public Object[][] getData2(){
	return ExcelUtility.excelIntoArray(System.getProperty("user.dir")+ "/testdata/Excel.xlsx", "Employee");
	}
	
}
